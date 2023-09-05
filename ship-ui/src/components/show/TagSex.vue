<template>
  <el-tag v-if="sex" effect="dark" round>
    <el-icon>
      <Male/>
    </el-icon>
  </el-tag>
  <el-tag v-else effect="dark" round type="danger">
    <el-icon>
      <Female/>
    </el-icon>
  </el-tag>
</template>

<script lang="ts" setup>
/*=====性别的封装展示标签=====*/
import {Female, Male} from "@element-plus/icons-vue"

defineProps<{
  sex: boolean
}>()
</script>

<style scoped>

</style>