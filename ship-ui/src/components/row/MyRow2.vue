<template>
  <div class="my-row-2">
    <div class="left">
      <slot></slot>
    </div>
    <div class="right">
      <slot name="right"></slot>
    </div>
  </div>
</template>

<script lang="ts" setup>
</script>

<style lang="scss" scoped>
.my-row-2 {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-areas: "left right right";
  /* 在屏幕宽度小于992px时改变布局 */
  @media screen and (max-width: 992px) {
    grid-template-columns: 1fr;
    grid-template-areas:
      "left"
      "right";
  }

  .left {
    grid-area: left;
    text-align: center;
  }

  .right {
    grid-area: right;
    text-align: center;
    max-height: 360px;
    overflow-y: auto;
  }
}
</style>
