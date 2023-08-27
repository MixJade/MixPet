<template>
  <div class="card">
    <div class="card-details">
      <div class="text-title">{{ tit }}</div>
      <p class="text-body">{{ body }}</p>
    </div>
    <button type="button" class="card-button" @click="$router.push(routerPath)">点击跳转</button>
  </div>
</template>

<script lang="ts" setup>
import {ref} from "vue";

const props = defineProps<{
  tit: string;
  body: string;
  type: "primary" | "success" | "warning";
  routerPath: string;
}>()

const myColorBg = ref<string>("#d9ecff")
const myColorBor = ref<string>("#a0cfff")
const myColorHov = ref<string>("#409EFF")
const myColorTit = ref<string>("#337ecc")


if (props.type === "success") {
  myColorBg.value = "#e1f3d8"
  myColorBor.value = "#b3e19d"
  myColorHov.value = "#67C23A"
  myColorTit.value = "#529b2e"
}

if (props.type === "warning") {
  myColorBg.value = "#faecd8"
  myColorBor.value = "#f3d19e"
  myColorHov.value = "#E6A23C"
  myColorTit.value = "#b88230"
}
</script>

<style lang="scss" scoped>
.card {
  $bg-color: v-bind(myColorBg);
  $bor-color: v-bind(myColorBor);
  $hov-color: v-bind(myColorHov);
  $tit-color: v-bind(myColorTit);
  width: 100%;
  height: 128px;
  border-radius: 20px;
  background: $bg-color;
  position: relative;
  box-sizing: border-box;
  padding: 1.8rem;
  border: 3px solid $bor-color;
  transition: 0.5s ease-out;
  overflow: visible;
  -webkit-user-select: none;
  user-select: none;

  &:hover {
    border-color: $hov-color;
    box-shadow: 0 4px 18px 0 rgba(0, 0, 0, 0.25);

    .card-button {
      transform: translate(-50%, 50%);
      opacity: 1;
    }
  }

  .card-details {
    height: 100%;
    display: grid;
    text-align: center;
    place-content: center;

    .text-title {
      color: $tit-color;
      font-size: 1.5em;
      font-weight: bold;
    }

    .text-body {
      color: #868686;
    }
  }

  .card-button {
    transform: translate(-50%, 125%);
    width: 60%;
    border-radius: 1rem;
    border: none;
    background-color: $hov-color;
    color: #fff;
    font-size: 1rem;
    padding: .5rem 1rem;
    position: absolute;
    left: 50%;
    bottom: 0;
    opacity: 0;
    transition: 0.3s ease-out;
  }
}
</style>
