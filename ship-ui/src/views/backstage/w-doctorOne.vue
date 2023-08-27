<template>
  <LightBox>
    <router-link to="/doctorPage">
      <el-button style="position: absolute" type="success">悬壶济世</el-button>
    </router-link>
    <DetailTable
        :role-photo="doctor.doctorPhoto"
        :tab-con="em"
        @upPwd="updatePwd"
        @upRole="updateRole"
    />
  </LightBox>
</template>

<script lang="ts" setup>
import DetailTable, {DetailTabType} from "@/components/card/DetailTable.vue";
import {getJob} from "@/utils/JobUtil";
import {getAge} from "@/utils/TimeUtil";
import {computed, reactive} from "vue";
import {DoctorDto, exampleDoctorOne} from "@/model/DO/DoctorDto";
import {reqGetLoginD} from "@/request/DoctorApi";
import LightBox from "@/components/card/LightBox.vue";
// 设置展示信息
const doctor: DoctorDto = reactive(exampleDoctorOne())
// 发起请求
reqGetLoginD().then(res => {
  doctor.doctorId = res.doctorId
  doctor.username = res.username
  doctor.doctorName = res.doctorName
  doctor.doctorGender = res.doctorGender
  doctor.authLv = res.authLv
  doctor.departmentName = res.departmentName
  doctor.doctorTel = res.doctorTel
  doctor.doctorPhoto = res.doctorPhoto
})
const em = computed((): DetailTabType[] => [
  {tit: "账号", con: doctor.username},
  {tit: "姓名", con: doctor.doctorName},
  {tit: "性别", con: doctor.doctorGender ? "男" : "女"},
  {tit: "职位", con: getJob(doctor.authLv)},
  {tit: "科室", con: doctor.departmentName},
  {tit: "联系方式", con: doctor.doctorTel},
  {tit: "年龄", con: getAge("1990-10-10")},
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