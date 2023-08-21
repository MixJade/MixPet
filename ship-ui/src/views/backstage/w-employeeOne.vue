<template>
  <div class="one-basePage">
    <div class="really">
      <DetailTable
          :role-photo="employee['employeePhoto']"
          :tab-con="em"
          @upPwd="updatePwd"
          @upRole="updateRole"
      />
    </div>
  </div>
</template>

<script lang="ts" setup>
import DetailTable, {DetailTabType} from "@/components/card/DetailTable.vue";
import {Employee, exampleEmployee} from "@/model/entiy/Employee";
import {getJob} from "@/utils/JobUtil";
import {getDaysFromToday} from "@/utils/TimeUtil";
import {computed, reactive} from "vue";
import {reqGetLoginE} from "@/request/EmployeeApi";
// 设置展示信息
const employee: Employee = reactive(exampleEmployee())
// 发起请求
reqGetLoginE().then(res => {
  employee.employeeId = res.employeeId
  employee.employeeUsername = res.employeeUsername
  employee.employeePassword = res.employeePassword
  employee.employeeName = res.employeeName
  employee.employeeLevel = res.employeeLevel
  employee.employeeTel = res.employeeTel
  employee.employeePhoto = res.employeePhoto
  employee.createTime = res.createTime
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

<style scoped src="public/css/one-basePage.css"/>