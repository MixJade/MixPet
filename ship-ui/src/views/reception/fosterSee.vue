<template>
  <div class="my-space">
    <PageHead>查看全部寄养订单</PageHead>
    <FosterCard :card-list="fosterCardTxt.records"/>
  </div>
</template>

<script lang="ts" setup>
// 宠物的卡片信息
import {Page} from "@/model/DO/Page";
import {FosterCardDto} from "@/model/DO/FosterCardDto";
import {onMounted, reactive} from "vue";
import FosterCard from "@/components/card/FosterCard.vue";
import PageHead from "@/components/PageHead.vue";
import {reqFosterPet} from "@/request/FosterApi";

// 寄养宠物的卡片信息
const fosterCardTxt: Page<FosterCardDto> = reactive({records: [], total: 6})

onMounted(() => {
  reqFosterPet(8).then(res => {
    fosterCardTxt.records = res.records
  })
})
</script>

<style scoped src="public/css/my-space.css"/>
