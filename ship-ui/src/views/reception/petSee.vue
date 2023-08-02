<template>
  <div>
    <PageHead>查看全部宠物</PageHead>
    <el-space direction="vertical">
      <PetCard :card-list="petCardTxt.records"/>
      <el-pagination :current-page="page.numPage" :page-size="page.pageSize" :total="petCardTxt.total"
                     background
                     hide-on-single-page
                     layout="total, prev, pager, next"
                     @current-change="changePage"
      />
    </el-space>
  </div>
</template>

<script lang="ts" setup>
// 宠物的卡片信息
import {Page} from "@/model/DO/Page";
import {Pet} from "@/model/entiy/Pet";
import PetCard from "@/components/PetCard.vue";
import {onMounted, reactive, watch} from "vue";
import PageHead from "@/components/PageHead.vue";
import {PageQuery} from "@/model/VO/BackQuery";
import {reqPetSee} from "@/request/PetApi";

const page: PageQuery = reactive({
  numPage: 1,
  pageSize: 8
})
const petCardTxt: Page<Pet> = reactive({records: [], total: 0})
onMounted(() => {
  reqPetSee(page).then(res => {
    petCardTxt.records = res.records
    petCardTxt.total = res.total
  })
})
// 监视器的使用
watch(page, () => {
  reqPetSee(page).then(res => {
    petCardTxt.records = res.records
  })
})
// 分页条
const changePage = (val: number): void => {
  page.numPage = val
}
</script>

<style scoped>
</style>