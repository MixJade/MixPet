<template>
  <ChatPanel :groupList="groupList" :msg-list="msgList" :role-name="doctor.doctorName" opType="医生"
             @cutOther="cutDoctor"
             @sendMsg="sendMsg"
  />
</template>

<script lang="ts" setup>
import {ChatGroup} from "@/model/DO/ChatGroup";
import {MsgDo} from "@/model/DO/MsgDo";
import ChatPanel from "@/components/chat/ChatPanel.vue";
import {onMounted, ref} from "vue";
import {reqDoctorIdMsg, reqClientMsgGroup, reqClientMsgList} from "@/request/MsgApi";

onMounted(() => {
  reqDoctorIdMsg().then(res => {
    cutDoctor(res)
  })
})
const props = defineProps<{
  doctorId?: number | string
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
  doctor.doctorId = props.doctorId as number
}
// 得到分组与聊天记录
const groupList = ref<ChatGroup[]>([])
const msgList = ref<MsgDo[]>([])
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
</script>

<style scoped>
</style>
