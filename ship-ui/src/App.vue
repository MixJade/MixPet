<template>
  <router-view v-slot="{ Component }">
    <transition name="flip">
      <component :is="Component"/>
    </transition>
  </router-view>
</template>
<script setup lang="ts">
console.log(import.meta.env.VITE_BACK_URL)
</script>
<style scoped lang="scss">
.flip-enter-active {
  animation: flip-in 0.5s;
}

.flip-leave-active {
  animation: flip-out 0.5s;
}

@keyframes flip-in {
  from {
    transform: rotateY(90deg);
  }
  to {
    transform: rotateY(0deg);
  }
}

@keyframes flip-out {
  from {
    transform: rotateY(0deg);
  }
  to {
    transform: rotateY(90deg);
  }
}
</style>
