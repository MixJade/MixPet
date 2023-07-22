<template>
  <el-card shadow="always" v-for="a in appointList" :key="a.appointmentId">
    <template #header>
      <div>
        <span>单号：{{ a.appointmentId }}</span>
      </div>
    </template>
    <el-descriptions :title="'就诊时间：'+ moveT(a.appointmentDate)" :column="3" border>
      <el-descriptions-item
          label="宠物"
          label-align="right"
          align="center">
        {{ a.petName }}
      </el-descriptions-item>
      <el-descriptions-item label="科室" label-align="right" align="center">
        <el-tag>{{ a.departmentName }}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item label="医生" label-align="right" align="center">
        {{ a.doctorName }}
      </el-descriptions-item>
      <el-descriptions-item label="描述" label-align="right" align="center">
        {{ a.appointmentInfo }}
      </el-descriptions-item>
      <el-descriptions-item label="挂号时间" label-align="right" align="center">
        {{ moveT(a.createTime) }}
      </el-descriptions-item>
    </el-descriptions>
  </el-card>
</template>

<script setup lang="ts">
import {AppointDto} from "@/modal/DO/AppointDto";
import {moveT} from "@/utils/TimeUtil";
import {onMounted, ref} from "vue";
import {reqClientAppoint} from "@/request/Appoint";

const appointList = ref<AppointDto[]>([])
onMounted(() => {
  reqClientAppoint().then(res => {
    appointList.value = res
  })
})
</script>

<style scoped>
</style>