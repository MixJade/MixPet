<template>
  <p></p>
  <el-pagination
      :current-page="pu.numPage"
      :page-size="pu.pageSize"
      :page-sizes="[6, 9, 12, 15]"
      :total="total"
      background
      hide-on-single-page
      layout="total, sizes,prev, pager, next"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
  />
</template>

<script lang="ts" setup>
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