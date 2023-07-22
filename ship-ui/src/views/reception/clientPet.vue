<template>
  <el-card v-for="pet in petList" :key="pet.petId">
    <el-row :gutter="20" class="my-pet-card">
      <el-col
          :xs="24" :sm="8" :md="6"
      ><img src="/picture/pet-ex.jpg" alt="宠物"/>
      </el-col>
      <el-col
          :xs="24" :sm="14" :md="18"
      >
        <el-descriptions :title="pet.petName" :column="3" border>
          <el-descriptions-item
              label="品种"
              label-align="right"
              align="center">
            <el-tag>{{ pet.petVariety }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="健康状况" label-align="right" align="center">
            <el-tag>{{ pet.petStatus }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="年龄" label-align="right" align="center">
            {{ getAge(pet.petAge) }}岁
          </el-descriptions-item>
          <el-descriptions-item label="性别" label-align="right" align="center">
            <TagSex :sex="pet.petSex"/>
          </el-descriptions-item>
          <el-descriptions-item label="操作" label-align="right" align="center">
            <el-button type="success" @click="this.$router.push('/reception/petOne/'+pet.petId)">详情
            </el-button>
          </el-descriptions-item>
        </el-descriptions>
      </el-col>
    </el-row>
  </el-card>
</template>

<script setup lang="ts">
import {Pet} from "@/modal/entiy/Pet";
import {getAge} from "@/utils/TimeUtil";
import TagSex from "@/components/TagSex.vue";
import {onMounted, ref} from "vue";
import {reqClientPet} from "@/request/PetApi";

const petList = ref<Pet[]>([]);
onMounted(() => {
  reqClientPet().then(res => {
    petList.value = res
  })
})
</script>

<style scoped>
</style>