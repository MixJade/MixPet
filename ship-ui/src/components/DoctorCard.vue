<template>
  <el-row :gutter="12">
    <el-col
        v-for="doctor in cardList"
        :key="doctor.doctorId"
        :md="6" :sm="12" :xs="24"
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
            <router-link :to="'/reception/doctorOne/'+doctor.doctorId">
              <el-button type="primary">详情</el-button>
            </router-link>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>

</template>

<script lang="ts" setup>
/*=====前台的医生卡片批量展示=====*/
import {getAge} from "@/utils/TimeUtil";
import {DoctorDto} from "@/model/DO/DoctorDto";
// 接收参数：医生卡片
defineProps<{
  readonly cardList: DoctorDto[]
}>()
</script>

<style scoped src="public/css/pet-info.css">
</style>