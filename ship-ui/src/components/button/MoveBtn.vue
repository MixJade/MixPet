<template>
  <button type="button"><slot></slot></button>
</template>

<script lang="ts" setup>
</script>

<style lang="scss" scoped>
button {
  /* 这是四个字的宽高 */
  width: 130px;
  height: 44px;
  margin-left: -65px;
  margin-top: -22px;
  /* 大小样式 */
  box-sizing: border-box;
  padding: 8px 30px;
  border-radius: 20px;
  border: dashed #006400;
  font-size: 16px;
  font-weight: 700;
  display: inline-block;
  outline: none;
  cursor: pointer;
  color: black;
  background-image: linear-gradient(90deg, #66ff66 50%, ghostwhite 50%);
  background-size: 200% 100%;
  background-position: 100% 0;
  transition: background-position 0.5s;

  &:hover {
    background-position: 0 0;
  }
}
</style>