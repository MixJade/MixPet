<template>
  <!--  封装的公共导航栏-->
  <header class="page-header">
    <div class="back" @click="$router.back()">
      <img alt="返回" src="/icon/back.svg">
      <span>返回</span>
    </div>
    <span>
      <slot></slot>
    </span>
  </header>
</template>

<script lang="ts" setup>
</script>
<style lang="scss" scoped>
.page-header {
  margin-bottom: 12px;
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 4px;
  height: 32px;
  -webkit-user-select: none;
  user-select: none;
  cursor: pointer;

  .back {
    color: #5b88ea;
    display: flex;
    align-items: center;
    height: 32px;
    width: 72px;
    border-right: #606266 dotted 4px;
  }

  & > span {
    font-size: large;
    font-weight: bold;
    color: #606266;
  }
}
</style>
