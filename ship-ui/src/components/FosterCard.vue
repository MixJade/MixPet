<template>
  <el-row :gutter="12">
    <el-col
        v-for="pet in cardList"
        :key="pet.fosterId"
        :md="8" :sm="12" :xs="24"
    >
      <el-card>
        <div class="my-pet-card">
          <img :src="'/api/common/download?name='+pet.petPhoto" alt="宠物"/>
          <div class="my-pet-info">
            <ul style="list-style: none;">
              <li><strong>{{ pet.petName }}</strong></li>
              <li>{{ getAge(pet.petAge) }}岁，&nbsp;{{ pet.petSex ? "公" : "母" }}，{{ pet.petVariety }}</li>
              <li>{{ pet.petInfo }}</li>
              <li>订单号：{{ pet.fosterCode }}</li>
              <li>备注：{{ pet.petInfo }}</li>
              <li>寄养人：{{ pet.clientName }}</li>
              <li>到期时间：{{ pet.fosterTerm }}</li>
            </ul>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script lang="ts" setup>
/*=====前台的寄养卡片批量展示=====*/
import {getAge} from "@/utils/TimeUtil";
import {FosterCardDto} from "@/model/DO/FosterCardDto";
// 接收参数：宠物卡片
defineProps<{
  readonly cardList: FosterCardDto[]
}>()
</script>

<style lang="scss" scoped src="public/pet-info.scss">
</style>