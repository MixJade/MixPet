<template>
  <button type="button">
    <slot></slot>
  </button>
</template>

<script lang="ts" setup>
/*=====流光溢彩的按钮=====*/
</script>

<style lang="scss" scoped>
$my-height: 32px;
$my-width: 200px;
button {
  margin-bottom: 5px;
  font-size: medium;
  position: relative;
  display: inline-block;
  width: $my-width;
  height: $my-height;
  color: white;
  line-height: $my-height;
  background: skyblue linear-gradient(90deg,
      #03a9f4, #f441a5, #ffeb3b, #03a9f4
  );
  background-size: 400%;
  border: white;
  border-radius: 15px;
  /* 渐变色动画 */
  @keyframes animate {
    0% {
      background-position: 0 0;
    }
    100% {
      background-position: 400% 0;
    }
  }
  /* 悬停播放动画 */
  &:hover {
    animation: animate 8s linear infinite;
    /* 光影盒子显示 */
    &:before {
      animation: animate 8s linear infinite;
      filter: blur(20px);
      opacity: 1;
    }
  }

  /* 光影盒子，大于原盒子 */
  &:before {
    content: "";
    position: absolute;
    left: -5px;
    top: -5px;
    right: -5px;
    bottom: -5px;
    z-index: -1;
    background: linear-gradient(90deg,
        #03a9f4, #f441a5, #ffeb3b, #03a9f4
    );
    background-size: 400%;
    border-radius: 15px;
    opacity: 0;
    transition: all 1s;
  }
}
</style>