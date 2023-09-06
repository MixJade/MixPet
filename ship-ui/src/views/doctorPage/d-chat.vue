<template>
  <ChatPanel :groupList="groupList" :msg-list="msgList" :role-name="client.clientName" opType="用户"
             @cutOther="cutClient " @sendMsg="sendMsg"/>
</template>

<script lang="ts" setup>
import {ChatGroup} from "@/model/DO/ChatGroup";
import {MsgDo} from "@/model/DO/MsgDo";
import ChatPanel from "@/components/chat/ChatPanel.vue";
import {onMounted, ref} from "vue";
import {reqClientIDMsg, reqDoctorMsgGroup, reqDoctorMsgList} from "@/request/MsgApi";

onMounted(() => {
  reqClientIDMsg().then(res => {
    cutClient(res)
  })
})
const props = defineProps<{
  clientId?: number | string
}>()
// 当前用户的ID
const client = {
  clientId: 1,
  clientName: "无"
}
if (props.clientId == null || props.clientId == '') {
  // 没有收到ID，执行ID的初始化逻辑
  client.clientId = 1;
} else {
  client.clientId = props.clientId as number
}
// 得到分组与聊天记录
const groupList = ref<ChatGroup[]>([]);
const msgList = ref<MsgDo[]>([]);
// 发送消息
const sendMsg = (val: string): void => {
  console.log(val)
}
// 切换用户
const cutClient = (clientId: number): void => {
  console.log(clientId)
  client.clientId = clientId
  reqDoctorMsgList(clientId).then(res1 => {
    msgList.value = res1
  })
  reqDoctorMsgGroup(clientId).then(res2 => {
    client.clientName = res2[0].roleName
    groupList.value = res2
  })
}
</script>

<style scoped>
</style>
