<template>
  <ChatPanel :groupList="groupList" :msg-list="msgList" :role-name="client.clientName" opType="用户"
             @cutOther="cutClient " @sendMsg="sendMsg"/>
</template>

<script lang="ts" setup>
import {exampleDChatGroup} from "@/model/VO/ChatGroup";
import {exampleDMsg} from "@/model/VO/MsgVo";
import ChatPanel from "@/components/chat/ChatPanel.vue";

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
const groupList = exampleDChatGroup(client.clientId);
const msgList = exampleDMsg(client.clientId);
// 发送消息
const sendMsg = (val: string): void => {
  console.log(val)
}
// 切换用户
const cutClient = (clientId: number): void => {
  console.log(clientId)
}
</script>

<style scoped>
</style>
