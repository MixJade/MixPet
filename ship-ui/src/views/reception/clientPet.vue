<template>
  <div v-for="pet in petList" :key="pet.petId" class="pa-card">
    <div class="my-row-2">
      <div class="left">
        <img :src="'/api/common/download?name='+pet.petPhoto" alt="宠物" class="my-avatar"/>
      </div>
      <div class="right">
        <el-descriptions :column="3" :title="pet.petName" border>
          <el-descriptions-item
              align="center"
              label="品种"
              label-align="right">
            <el-tag>{{ pet.petVariety }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item align="center" label="健康状况" label-align="right">
            <el-tag>{{ pet.petStatus }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item align="center" label="年龄" label-align="right">
            {{ getAge(pet.petAge) }}岁
          </el-descriptions-item>
          <el-descriptions-item align="center" label="性别" label-align="right">
            <TagSex :sex="pet.petSex"/>
          </el-descriptions-item>
          <el-descriptions-item align="center" label="操作" label-align="right">
            <el-button type="success" @click="$router.push('/reception/petOne/'+pet.petId)">详情
            </el-button>
          </el-descriptions-item>
        </el-descriptions>
      </div>
    </div>
    <el-empty v-if="petList.length===0" description="暂无宠物"/>
  </div>
</template>

<script lang="ts" setup>
import {Pet} from "@/model/entiy/Pet";
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

<style lang="scss" scoped>
@import url("/css/pa-card.css");

.my-row-2 {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-areas: "left right right right";
  /* 在屏幕宽度小于992px时改变布局 */
  @media screen and (max-width: 992px) {
    grid-template-columns: 1fr;
    grid-template-areas:"left" "right";
  }

  .left {
    grid-area: left;

    img.my-avatar {
      border-radius: 50%;
      width: 128px;
      height: 128px;
    }
  }

  .right {
    grid-area: right;
  }
}
</style>