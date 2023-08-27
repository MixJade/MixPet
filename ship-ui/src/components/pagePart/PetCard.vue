<template>
  <MyRow>
    <MyCard v-for="pet in cardList"
            :key="pet.petId"
            :data='{
        photo: pet.petPhoto,
        tit: pet.petName,
        age: getAge(pet.petAge)+"岁",
        sex: pet.petSex ? "公" : "母",
        job: pet.petVariety,
        info1: pet.petInfo,
        info2: "健康："+pet.petStatus,
        link:"/reception/petOne/"+pet.petId
      }' :type="'success'"/>
  </MyRow>
</template>

<script lang="ts" setup>
/*=====前台的宠物卡片批量展示=====*/
import {getAge} from "@/utils/TimeUtil";
import {Pet} from "@/model/entiy/Pet";
import MyCard from "@/components/card/MyCard.vue";
import MyRow from "@/components/show/MyRow.vue";

// 接收参数：宠物卡片
defineProps<{
  readonly cardList: Pet[]
}>()
</script>

<style scoped>
</style>