<template>
  <!--  封装的公共导航栏-->
  <el-page-header @back="this.$router.back()">
    <template #title>
      <span style="color: #5b88ea">返回</span>
    </template>
    <template #content>
      <span style="cursor: default;font-size: smaller"><slot></slot></span>
    </template>
  </el-page-header>
</template>

<script lang="ts" setup>
/*=====前台的返回栏，有当前页面的标题====*/
</script>
<style lang="scss" scoped>
.el-page-header {
  margin-bottom: 12px;
}
</style>