<template>
  <el-card v-for="a in appointList" :key="a.appointmentId" shadow="always">
    <template #header>
      <div>
        <span>单号：{{ a.appointmentId }}</span>
      </div>
    </template>
    <el-descriptions :column="3" :title="'就诊时间：'+ moveT(a.appointmentDate)" border>
      <el-descriptions-item
          align="center"
          label="宠物"
          label-align="right">
        {{ a.petName }}
      </el-descriptions-item>
      <el-descriptions-item align="center" label="科室" label-align="right">
        <el-tag>{{ a.departmentName }}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item align="center" label="医生" label-align="right">
        {{ a.doctorName }}
      </el-descriptions-item>
      <el-descriptions-item align="center" label="描述" label-align="right">
        {{ a.appointmentInfo }}
      </el-descriptions-item>
      <el-descriptions-item align="center" label="挂号时间" label-align="right">
        {{ moveT(a.createTime) }}
      </el-descriptions-item>
    </el-descriptions>
  </el-card>
  <el-empty v-if="appointList.length===0" description="暂无挂号"/>
</template>

<script lang="ts" setup>
import {AppointDto} from "@/modal/DO/AppointDto";
import {moveT} from "@/utils/TimeUtil";
import {onMounted, ref} from "vue";
import {reqClientAppoint} from "@/request/AppointApi";

const appointList = ref<AppointDto[]>([])
onMounted(() => {
  reqClientAppoint().then(res => {
    appointList.value = res
  })
})
</script>

<style scoped>
</style>