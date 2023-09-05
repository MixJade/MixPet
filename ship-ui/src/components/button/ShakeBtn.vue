<template>
  <button type="button">
    <span><slot></slot></span>
  </button>
</template>

<script lang="ts" setup>
</script>

<style lang="scss" scoped>
button {
  position: relative;
  margin: 8px;
  padding: 12px 24px;
  cursor: pointer;
  background-color: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 4px dashed #67C23A;
  border-radius: 10px;
  z-index: 0;
  overflow: hidden;

  span {
    color: #67C23A;
    font-size: 14px;
    font-weight: 700;
    letter-spacing: 1px;
    z-index: 20;
  }

  &:after {
    background: #38ef7d;
    content: "";
    height: 155px;
    left: -75px;
    opacity: 0.4;
    position: absolute;
    top: -50px;
    transform: rotate(35deg);
    transition: all 550ms cubic-bezier(0.19, 1, 0.22, 1);
    width: 50px;
    z-index: -10;
  }

  &:hover {
    animation: rotate624 0.7s ease-in-out both;

    span {
      animation: storm1261 1s ease-in-out both;
      animation-delay: 0.1s;
    }

    &:after {
      left: 120%;
      transition: all 550ms cubic-bezier(0.19, 1, 0.22, 1);
    }
  }
}


@keyframes rotate624 {
  0% {
    transform: rotate(0deg) translate3d(0, 0, 0);
  }

  25% {
    transform: rotate(3deg) translate3d(0, 0, 0);
  }

  50% {
    transform: rotate(-3deg) translate3d(0, 0, 0);
  }

  75% {
    transform: rotate(1deg) translate3d(0, 0, 0);
  }

  100% {
    transform: rotate(0deg) translate3d(0, 0, 0);
  }
}

@keyframes storm1261 {
  0% {
    transform: translate3d(0, 0, 0) translateZ(0);
  }

  25% {
    transform: translate3d(4px, 0, 0) translateZ(0);
  }

  50% {
    transform: translate3d(-3px, 0, 0) translateZ(0);
  }

  75% {
    transform: translate3d(2px, 0, 0) translateZ(0);
  }

  100% {
    transform: translate3d(0, 0, 0) translateZ(0);
  }
}
</style>
