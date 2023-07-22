<template>
  <div>
    <PageHead>查看全部医生</PageHead>
    <el-space direction="vertical">
      <DoctorCard :card-list="doctorCardTxt.records"/>
      <el-pagination
          hide-on-single-page
          layout="total, prev, pager, next"
          :current-page="page.numPage"
          :page-size="page.pageSize"
          :total="doctorCardTxt.total"
          @current-change="changePage"/>
    </el-space>
  </div>
</template>

<script setup lang="ts">
import {Page} from "@/modal/DO/Page";
import DoctorCard from "@/components/DoctorCard.vue";
import {onMounted, reactive, watch} from "vue";
import PageHead from "@/components/PageHead.vue";
import {DoctorDto} from "@/modal/DO/DoctorDto";
import {PageQuery} from "@/modal/VO/BackQuery";
import {reqDoctorSee} from "@/request/DoctorApi";

const page: PageQuery = reactive({
  numPage: 1,
  pageSize: 8
})
const doctorCardTxt: Page<DoctorDto> = reactive({records: [], total: 0})
onMounted(() => {
  reqDoctorSee(page).then(res => {
    doctorCardTxt.records = res.records
    doctorCardTxt.total = res.total
  })
})
// 监视器的使用
watch(page, () => {
  reqDoctorSee(page).then(res => {
    doctorCardTxt.records = res.records
    doctorCardTxt.total = res.total
  })
})
// 分页条
const changePage = (val: number): void => {
  page.numPage = val
}
</script>

<style scoped>
</style>