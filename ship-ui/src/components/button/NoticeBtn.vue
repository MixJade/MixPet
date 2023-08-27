<template>
  <button class="btn">
    <slot></slot>
  </button>
</template>

<script lang="ts" setup>
</script>

<style scoped>
.btn {
  width: 100%;
  height: 56px;
  background-color: #98FB98;
  border: solid #32CD32;
  font-size: 1.1em;
  cursor: pointer;
  border-radius: 12px;
  transition: all .5s;
}

.btn:hover {
  transform: translateY(-10px);
  box-shadow: 0 7px 0 -2px #f85959,
  0 15px 0 -4px #39a2db,
  0 16px 10px -3px #39a2db;
}
</style>
