<template>
  <el-tag v-if="sex" effect="dark" round>
    <el-icon>
      <Male/>
    </el-icon>
  </el-tag>
  <el-tag v-else type="danger" effect="dark" round>
    <el-icon>
      <Female/>
    </el-icon>
  </el-tag>
</template>

<script setup lang="ts">
import {Male, Female} from "@element-plus/icons-vue"

defineProps<{
  sex: boolean
}>()
</script>

<style scoped>

</style>