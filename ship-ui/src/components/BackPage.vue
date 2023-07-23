<template>
  <p></p>
  <el-pagination
      background
      layout="total, sizes,prev, pager, next"
      hide-on-single-page
      :page-sizes="[6, 9, 12, 15]"
      :page-size="pu.pageSize"
      :current-page="pu.numPage"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
  />
</template>

<script setup lang="ts">
import {reactive} from "vue";
import {PageQuery} from "@/modal/VO/BackQuery";

defineProps<{
  total: number
}>()
const emit = defineEmits<{
  (e: "changePu", value: PageQuery): void,
}>()
const pu = reactive<PageQuery>({
  numPage: 1,
  pageSize: 6
})
const handleSizeChange = (val: number) => {
  pu.pageSize = val
  emit("changePu", pu)
}
const handleCurrentChange = (val: number) => {
  pu.numPage = val
  emit("changePu", pu)
}
</script>

<style scoped>

</style>