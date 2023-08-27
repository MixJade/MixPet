<template>
  <div class="one-basePage">
    <div class="really">
      <slot></slot>
    </div>
  </div>
</template>

<script lang="ts" setup>
</script>

<style scoped>
/* 个人中心的展示页面 */
.one-basePage {
  position: absolute;
  width: 680px;
  max-width: 90vw;
  height: 500px;
  left: 50vw;
  top: 10vh;
  transform: translateX(-50%);
  background-color: whitesmoke;
  box-shadow: 5px 5px 10px 0 rgba(0, 0, 0, 0.5);
  border-radius: 20px;
  z-index: 0;
  overflow: hidden; /* 隐藏被遮挡的元素 */
}

/* 流光盒子 */
.one-basePage:before {
  content: "";
  position: absolute;
  top: 70px; /* 父元素高-自己的高，再除2 */
  left: -84px; /* 自己的宽-父元素宽，再除2 */
  width: 848px; /* 父元素高的平方+父元素宽的平方，再开根号 */
  height: 360px;
  z-index: -2;
  background-image: linear-gradient(45deg, #03a9f4, #f441a5, #ffeb3b, #03a9f4);
  animation: rotate-a9 4s linear infinite; /* 设置旋转动画 */
}

/* 响应式，父元素宽度除0.9 */
@media (max-width: 756px) {
  .one-basePage:before {
    position: fixed;
    left: 50vw;
    margin-left: -424px;
  }
}

/* 旋转动画 */
@keyframes rotate-a9 {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}

/* 流光盒子2 */
.one-basePage .really {
  padding: 10px;
  box-sizing: border-box; /* 让padding向內扩张 */
  position: absolute;
  inset: 8px; /* inset:1px等价与left:1px;top:1px;right:1px;bottom:1px; */
  border-radius: 18px;
  background-color: inherit;
  overflow-y: auto;
}
</style>
