<template>
  <ChatPanel :groupList="groupList" :msg-list="msgList" :role-name="client.clientName" opType="用户"
             @cutOther="cutClient "
             @sendImgMsg="sendImgMsg"
             @sendTxtMsg="sendTextMsg"
  />
</template>

<script lang="ts" setup>
import {ChatGroup} from "@/model/DO/ChatGroup";
import {MsgDo} from "@/model/DO/MsgDo";
import ChatPanel from "@/components/chat/ChatPanel.vue";
import {onBeforeUnmount, onMounted, ref} from "vue";
import {reqDoctorGetNearClient, reqDoctorMsgGroup, reqDoctorMsgList, reqSendDoctorMsg} from "@/request/MsgApi";
import {ElMessage} from "element-plus";
import {Msg} from "@/model/entiy/Msg";
import {getNowISO} from "@/utils/TimeUtil";

/**
 ┌───────────────────────────────────┐
 │=============初始化的准备============│
 └───────────────────────────────────┘
 */
onMounted(() => {
  reqDoctorGetNearClient().then(res => {
    client.clientId = res.roleId
    client.clientName = res.roleName
    cutClient(res.roleId)
  })
})
// 当前用户的ID
const client = {
  clientId: 1,
  clientName: "无",
  clientPhoto: "defaultDoctor.jpg",
}
// 得到分组与聊天记录
const groupList = ref<ChatGroup[]>([]);
const msgList = ref<MsgDo[]>([]);
// WebSocket开始连接
const ws = new WebSocket("ws://localhost:8085/chat");  // 建立WebSocket对象
// 医生离线时的提示
ws.onclose = () => {
  ElMessage.warning("你已离开聊天页面")
}
// 这里是接受到消息之后刷新
ws.onmessage = (ev) => {
  const dataMsg = <Msg>JSON.parse(ev.data)
  if (dataMsg.clientId === client.clientId) {
    const msgDo: MsgDo = {
      createTime: getNowISO(),
      isImg: dataMsg.isImg,
      isMine: false,
      msgContent: dataMsg.msgContent,
      roleName: client.clientName,
      rolePhoto: client.clientPhoto
    }
    msgList.value.push(msgDo)
  }
}
/**
 ┌───────────────────────────────────┐
 │=============聊天时的过程============│
 └───────────────────────────────────┘
 */
// 切换用户
const cutClient = (clientId: number): void => {
  client.clientId = clientId
  reqDoctorMsgList(clientId).then(res1 => {
    msgList.value = res1
  })
  reqDoctorMsgGroup(clientId).then(res2 => {
    client.clientName = res2[0].roleName
    client.clientPhoto = res2[0].rolePhoto
    groupList.value = res2
  })
}

// 发送文字消息
const sendTextMsg = (val: string): void => {
  if (val === "") return;
  const msg: Msg = {
    clientId: client.clientId,
    isImg: false,
    msgContent: val
  }
  reqSendDoctorMsg(msg).then(res => {
    if (res.code === 1) {
      reqDoctorMsgList(client.clientId).then(res1 => {
        msgList.value = res1
      })
    }
  })
  ws.send(JSON.stringify(msg))
}
// 发送图片消息
const sendImgMsg = (imgName: string): void => {
  const msg: Msg = {
    clientId: client.clientId,
    isImg: true,
    msgContent: imgName
  }
  reqSendDoctorMsg(msg).then(res => {
    if (res.code === 1) {
      reqDoctorMsgList(client.clientId).then(res1 => {
        msgList.value = res1
      })
    }
  })
  ws.send(JSON.stringify(msg))
}
/**
 ┌───────────────────────────────────┐
 │=============退出时的操作============│
 └───────────────────────────────────┘
 */
onBeforeUnmount(() => {
  ws.close(1000, '正常关闭连接');
})
</script>

<style scoped>
</style>
