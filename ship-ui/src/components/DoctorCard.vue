<template>
  <el-row :gutter="12">
    <el-col
        v-for="doctor in cardList"
        :key="doctor.doctorId"
        :xs="24" :sm="12" :md="6"
        style="margin-bottom: 10px"
    >
      <el-card>
        <div class="my-pet-card">
          <img :src="'/api/common/download?name='+doctor.doctorPhoto" alt="医生"/>
          <div class="my-pet-info">
            <strong>{{ doctor.doctorName }}</strong>
            <br>
            {{ getAge(doctor.doctorAge) }}岁，&nbsp;{{ doctor.doctorGender ? "男" : "女" }}
            <br>
            {{ doctor.doctorJob }}
            <p>{{ doctor.doctorInfo }}</p>
            <el-button type="primary" @click="this.$router.push('/reception/doctorOne/'+doctor.doctorId)">详情
            </el-button>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>

</template>

<script setup lang="ts">
import {getAge} from "@/utils/TimeUtil";
import {DoctorDto} from "@/modal/DO/DoctorDto";
// 接收参数：医生卡片
defineProps<{
  readonly cardList: DoctorDto[]
}>()
</script>

<style scoped lang="scss" src="public/pet-info.scss">
</style>