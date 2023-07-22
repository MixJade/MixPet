<template>
  <div>
    <PageHead>查看全部寄养订单</PageHead>
    <FosterCard :card-list="fosterCardTxt.records"/>
  </div>
</template>

<script setup lang="ts">
// 宠物的卡片信息
import {Page} from "@/modal/DO/Page";
import {FosterCardDto} from "@/modal/DO/FosterCardDto";
import {onMounted, reactive} from "vue";
import FosterCard from "@/components/FosterCard.vue";
import PageHead from "@/components/PageHead.vue";
import {reqFosterPet} from "@/request/FosterApi";

// 寄养宠物的卡片信息
const fosterCardTxt: Page<FosterCardDto> = reactive({records: [], total: 6})

onMounted(() => {
  reqFosterPet(6).then(res => {
    fosterCardTxt.records = res.records
  })
})
</script>

<style scoped>
</style>