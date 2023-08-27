<template>
  <img class="my-avatar" alt="头像" :src="'/api/common/download?name='+name"/>
</template>

<script lang="ts" setup>
defineProps<{
  name:string;
}>()
</script>

<style scoped>
img.my-avatar {
  border-radius: 50%;
  width: 48px;
  height: 48px;
}
</style>
