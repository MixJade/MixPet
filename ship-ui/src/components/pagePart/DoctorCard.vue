<template>
  <MyRow>
    <MyCard
        v-for="doctor in cardList"
        :key="doctor.doctorId"
        :data='{
        photo: doctor.doctorPhoto,
        tit: doctor.doctorName,
        age: getAge(doctor.doctorAge)+"岁",
        sex: doctor.doctorGender ? "男" : "女",
        job: getJob(doctor.authLv),
        info1: doctor.doctorInfo,
        info2: doctor.username,
        link:"/reception/doctorOne/"+doctor.doctorId
      }' :type="'primary'"/>
  </MyRow>
</template>

<script lang="ts" setup>
/*=====前台的医生卡片批量展示=====*/
import MyCard from "@/components/card/MyCard.vue";
import MyRow from "@/components/show/MyRow.vue";
import {getAge} from "@/utils/TimeUtil";
import {getJob} from "@/utils/JobUtil";
import {DoctorDto} from "@/model/DO/DoctorDto";
// 接收参数：医生卡片
defineProps<{
  readonly cardList: DoctorDto[]
}>()
</script>

<style scoped>
</style>