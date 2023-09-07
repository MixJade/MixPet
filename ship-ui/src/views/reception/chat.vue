<template>
  <ChatPanel :groupList="groupList" :msg-list="msgList" :role-name="doctor.doctorName" opType="医生"
             @cutOther="cutDoctor"
             @sendTxtMsg="sendTextMsg"
             @sendImgMsg="sendImgMsg"
  />
</template>

<script lang="ts" setup>
import {ChatGroup} from "@/model/DO/ChatGroup";
import {MsgDo} from "@/model/DO/MsgDo";
import ChatPanel from "@/components/chat/ChatPanel.vue";
import {onBeforeUnmount, ref} from "vue";
import {reqClientGetNearDoctor, reqClientMsgGroup, reqClientMsgList, reqSendClientMsg} from "@/request/MsgApi";
import {ElMessage} from "element-plus";
import {Msg} from "@/model/VO/Msg";

/**
 ┌───────────────────────────────────┐
 │=============准备好的函数============│
 └───────────────────────────────────┘
 */
// 分组与聊天记录
const groupList = ref<ChatGroup[]>([])
const msgList = ref<MsgDo[]>([])
// 当前医生
const doctor = {
  doctorId: 1,
  doctorName: "无"
}
// 切换医生
const cutDoctor = (doctorID: number): void => {
  doctor.doctorId = doctorID
  reqClientMsgList(doctorID).then(res1 => {
    msgList.value = res1
  })
  reqClientMsgGroup(doctorID).then(res2 => {
    doctor.doctorName = res2[0].roleName
    groupList.value = res2
  })
}

/**
 ┌───────────────────────────────────┐
 │=============初始化的逻辑============│
 └───────────────────────────────────┘
 */
const props = defineProps<{
  doctorId?: number | string
}>()
if (props.doctorId == null || props.doctorId == '') {
  // 没有收到ID，执行ID的初始化逻辑
  reqClientGetNearDoctor().then(res => {
    doctor.doctorId = res.roleId;
    doctor.doctorName = res.roleName;
    cutDoctor(res.roleId)
  })
} else {
  // 收到了ID，切换过去
  doctor.doctorId = props.doctorId as number
  cutDoctor(doctor.doctorId)
}

// WebSocket开始连接
const ws = new WebSocket("ws://localhost:8085/chat");  // 建立WebSocket对象
// WebSocket生命周期【关闭时的操作】
ws.onclose = () => {
  ElMessage.warning("你已离开聊天页面")
}
// WebSocket生命周期【接收消息时的操作】
ws.onmessage = (ev) => {
  const dataStr = ev.data;
  console.log(dataStr)
  // 这里是接受到消息之后刷新
  reqClientMsgList(doctor.doctorId).then(res1 => {
    msgList.value = res1
  })
}

/**
 ┌───────────────────────────────────┐
 │=============聊天时的过程============│
 └───────────────────────────────────┘
 */
// 发送文字消息
const sendTextMsg = (val: string): void => {
  if (val==="") return;
  const msg: Msg = {
    doctorId: doctor.doctorId,
    isImg: false,
    msgContent: val
  }
  reqSendClientMsg(msg).then(res => {
    if (res.code === 1) {
      reqClientMsgList(doctor.doctorId).then(res1 => {
        msgList.value = res1
      })
    }
  })
  ws.send(JSON.stringify(msg))
}
// 发送图片消息
const sendImgMsg = (imgName: string): void => {
  const msg: Msg = {
    doctorId: doctor.doctorId,
    isImg: true,
    msgContent: imgName
  }
  reqSendClientMsg(msg).then(res => {
    if (res.code === 1) {
      reqClientMsgList(doctor.doctorId).then(res1 => {
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
  // 1000是关闭代码，表示正常关闭连接，'正常关闭连接'是一个可选的文字描述
  ws.close(1000, '正常关闭连接');
})
</script>

<style scoped>
</style>
