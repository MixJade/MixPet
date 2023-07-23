<template>
  <DetailTable
      :role-photo="client.clientPhoto"
      :tab-con="co"
      @upPwd="updatePwd"
      @upRole="updateRole"
  />
</template>

<script setup lang="ts">
import {Client} from "@/modal/entiy/Client";
import DetailTable, {DetailTabType} from "@/components/DetailTable.vue";
import {computed, onMounted, reactive} from "vue";
import {reqLoginUser} from "@/request/PowerApi";
// 设置展示信息
const client = reactive<Client>({
  "clientId": 0,
  "clientUsername": "",
  "clientName": "",
  "clientGender": false,
  "clientTel": "",
  "clientAge": "",
  "clientInfo": "",
  "clientPhoto": "",
})
const co = computed((): DetailTabType[] => [
  {tit: "账号", con: client.clientUsername},
  {tit: "姓名", con: client.clientName},
  {tit: "性别", con: client.clientGender ? "男" : "女"},
  {tit: "联系方式", con: client.clientTel},
  {tit: "出生日期", con: client.clientAge},
  {tit: "简介", con: client.clientInfo},
])
// 更新资料与修改密码
const updateRole = () => {
  console.log("修改资料")
}
const updatePwd = () => {
  console.log("修改密码")
}
// 获取用户信息
onMounted(() => {
  reqLoginUser().then(res => {
    Object.keys(res).forEach(key => {
      client[key] = res[key]
    })
  })
})
</script>

<style scoped lang="scss">
</style>