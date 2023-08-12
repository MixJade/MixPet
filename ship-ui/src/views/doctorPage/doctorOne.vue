<template>
  <div class="one-basePage">
    <el-button style="position: absolute" type="primary" @click="$router.back()">返回</el-button>
    <DetailTable
        :tab-con="dc"
        role-photo="/picture/doctor-ex.jpg"
        @upPwd="updatePwd"
        @upRole="updateRole"
    />
  </div>
</template>

<script lang="ts" setup>
import DetailTable, {DetailTabType} from "@/components/DetailTable.vue";
import {getAge} from "@/utils/TimeUtil";
import {computed, reactive} from "vue";
import {DoctorDto, exampleDoctorOne} from "@/model/DO/DoctorDto";
// 设置展示信息
const doctor: DoctorDto = reactive(exampleDoctorOne())
const dc = computed((): DetailTabType[] => [
  {tit: "工号", con: doctor.doctorCode},
  {tit: "姓名", con: doctor.doctorName},
  {tit: "性别", con: doctor.doctorGender ? "男" : "女"},
  {tit: "职位", con: doctor.doctorJob},
  {tit: "科室", con: doctor.departmentName},
  {tit: "联系方式", con: doctor.doctorTel},
  {tit: "简介", con: doctor.doctorInfo},
  {tit: "年龄", con: getAge(doctor.doctorAge)},
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