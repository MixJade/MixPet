<template>
  <div class="my-row">
    <slot></slot>
  </div>
</template>

<script lang="ts" setup>
</script>

<style scoped>
.my-row {
  --card-wid: 360px;
  /* 卡片专属的行布局 */
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(var(--card-wid), 1fr));
  justify-items: center;
  grid-gap: 10px;
}
</style>
