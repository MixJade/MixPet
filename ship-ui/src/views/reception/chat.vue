<template>
  <ChatPanel opType="医生" :role-name="doctor.doctorName" :groupList="groupList" :msg-list="msgList"
             @sendMsg="sendMsg"
             @cutOther="cutDoctor"
  />
</template>

<script setup lang="ts">
import {ChatGroup} from "@/modal/VO/ChatGroup";
import {MsgVo} from "@/modal/VO/MsgVo";
import ChatPanel from "@/components/ChatPanel.vue";
import {onBeforeMount, ref} from "vue";
import {reqClientMsg, reqClientMsgGroup, reqClientMsgList} from "@/request/MsgApi";

const props = defineProps<{
  doctorId?: Number
}>()
// 当前医生
const doctor = {
  doctorId: 1,
  doctorName: "无"
}
if (props.doctorId == null || props.doctorId == '') {
  // 没有收到ID，执行ID的初始化逻辑
  doctor.doctorId = 1;
} else {
  doctor.doctorId = props.doctorId
}
// 得到分组与聊天记录
const groupList = ref<ChatGroup[]>([])
const msgList = ref<MsgVo[]>([])
// 发送消息
const sendMsg = (val: string): void => {
  console.log(val)
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
onBeforeMount(() => {
  reqClientMsg().then(res => {
    cutDoctor(res)
  })
})
</script>

<style scoped>
</style>
