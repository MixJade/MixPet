<template>
  <div class="one-basePage">
    <DetailTable
        :role-photo="employee['employeePhoto']"
        :tab-con="em"
        @upPwd="updatePwd"
        @upRole="updateRole"
    />
  </div>
</template>

<script lang="ts" setup>
import DetailTable, {DetailTabType} from "@/components/DetailTable.vue";
import {Employee} from "@/modal/entiy/Employee";
import {getJob} from "@/utils/JobUtil";
import {getDaysFromToday} from "@/utils/TimeUtil";
import {computed, reactive} from "vue";
import {reqGetLoginE} from "@/request/EmployeeApi";
// 设置展示信息
const employee: Employee = reactive({
  "employeeId": 0,
  "employeeUsername": "",
  "employeeName": "",
  "employeeLevel": 0,
  "employeeTel": "",
  "employeePhoto": "zs.jpg",
  "createTime": ""
})
// 发起请求
reqGetLoginE().then(res => {
  Object.keys(res).forEach(key => {
    employee[key] = res[key]
  })
})
const em = computed((): DetailTabType[] => [
  {tit: "账号", con: employee.employeeUsername},
  {tit: "姓名", con: employee.employeeName},
  {tit: "职位", con: getJob(employee.employeeLevel)},
  {tit: "联系方式", con: employee.employeeTel},
  {tit: "在职时长", con: getDaysFromToday(employee.createTime)},
])
// 更新资料与修改密码
const updateRole = () => {
  console.log("修改资料")
}
const updatePwd = () => {
  console.log("修改密码")
}
</script>

<style scoped>
</style>