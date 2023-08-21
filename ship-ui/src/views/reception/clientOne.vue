<template>
  <DetailTable
      :role-photo="client.clientPhoto"
      :tab-con="co"
      @upPwd="updatePwd"
      @upRole="updateRole"
  />
</template>

<script lang="ts" setup>
import {Client, exampleClient} from "@/model/entiy/Client";
import DetailTable, {DetailTabType} from "@/components/card/DetailTable.vue";
import {computed, onMounted, reactive} from "vue";
import {reqLoginUser} from "@/request/PowerApi";
// 设置展示信息
const client = reactive<Client>(exampleClient())
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
    client.clientId = res.clientId
    client.clientUsername = res.clientUsername
    client.clientPassword = res.clientPassword
    client.clientName = res.clientName
    client.clientGender = res.clientGender
    client.clientTel = res.clientTel
    client.clientAge = res.clientAge
    client.clientInfo = res.clientInfo
    client.clientPhoto = res.clientPhoto
  })
})
</script>

<style lang="scss" scoped>
</style>