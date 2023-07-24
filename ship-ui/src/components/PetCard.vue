<template>
  <el-row :gutter="12">
    <el-col
        v-for="pet in cardList"
        :key="pet.petId"
        :md="6" :sm="12" :xs="24"
        style="margin-bottom: 10px"
    >
      <el-card>
        <div class="my-pet-card">
          <img :src="'/api/common/download?name='+pet.petPhoto" alt="宠物"/>
          <div class="my-pet-info">
            <strong>{{ pet.petName }}</strong>
            <p>{{ getAge(pet.petAge) }}岁，&nbsp;{{ pet.petSex ? "公" : "母" }}</p>
            <p>{{ pet.petInfo }}</p>
            <el-button type="success" @click="this.$router.push('/reception/petOne/'+pet.petId)">详情
            </el-button>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script lang="ts" setup>
import {getAge} from "@/utils/TimeUtil";
import {Pet} from "@/modal/entiy/Pet";
// 接收参数：宠物卡片
defineProps<{
  readonly cardList: Pet[]
}>()
</script>

<style lang="scss" scoped src="public/pet-info.scss">
</style>