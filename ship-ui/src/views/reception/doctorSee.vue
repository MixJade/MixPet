<template>
  <div class="my-space">
    <PageHead>查看全部医生</PageHead>
    <DoctorCard :card-list="doctorCardTxt.records"/>
    <el-pagination
        :current-page="page.numPage"
        :page-size="page.pageSize"
        :total="doctorCardTxt.total"
        hide-on-single-page
        layout="total, prev, pager, next"
        @current-change="changePage"/>
  </div>
</template>

<script lang="ts" setup>
import {Page} from "@/model/DO/Page";
import DoctorCard from "@/components/zoneView/DoctorCard.vue";
import {onMounted, reactive, watch} from "vue";
import PageHead from "@/components/row/PageHead.vue";
import {DoctorDto} from "@/model/DO/DoctorDto";
import {PageQuery} from "@/model/VO/BackQuery";
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

<style scoped src="public/css/my-space.css"/>
