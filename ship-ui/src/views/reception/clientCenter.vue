<template>
  <div>
    <div class="radio-group">
      <label>
        <input v-model="checkValue" name="radio" type="radio" value="/reception">
        <span style="color: red">返回主页</span>
      </label>
      <label>
        <input v-model="checkValue" name="radio" type="radio" value="/reception/clientCenter">
        <span>个人资料</span>
      </label>
      <label>
        <input v-model="checkValue" name="radio" type="radio" value="/reception/clientCenter/clientPet">
        <span>宠物信息</span>
      </label>
      <label>
        <input v-model="checkValue" name="radio" type="radio" value="/reception/clientCenter/clientAppoint">
        <span>挂号查看</span>
      </label>
    </div>
    <LightBox>
      <router-view></router-view>
    </LightBox>
  </div>
</template>

<script lang="ts" setup>
import {ref, watch} from 'vue'
import router from "@/router";
import LightBox from "@/components/card/LightBox.vue";

const checkValue = ref<string>(router.currentRoute.value.fullPath)
watch(checkValue, () => {
  router.push(checkValue.value)
})
</script>

<style lang="scss" scoped>
$txt-color: #337ecc;
$round-color: #DBA21C;
$bord-color: #887456;
.radio-group {
  margin-left: 40px;
  margin-top: 10px;
  @media (max-width: 800px) {
    display: flex;
  }

  label {
    max-width: 200px;
    display: flex;
    cursor: pointer;
    font-weight: 500;
    position: relative;
    overflow: hidden;
    margin-bottom: 0.375em;

    input {
      display: none;

      &:checked + span {
        background-color: $txt-color;
        color: white;

        &:before {
          box-shadow: inset 0 0 0 0.4375em $round-color;
        }
      }
    }

    span {
      display: flex;
      align-items: center;
      padding: 0.375em 0.75em 0.375em 0.375em;
      border-radius: 99em;
      transition: 0.25s ease;
      color: $txt-color;

      &:hover {
        background-color: #b3e19d;
      }

      &:before {
        display: flex;
        flex-shrink: 0;
        content: "";
        background-color: #fff;
        width: 1.5em;
        height: 1.5em;
        border-radius: 50%;
        margin-right: 0.375em;
        transition: 0.25s ease;
        box-shadow: inset 0 0 0 0.125em $bord-color;
      }
    }
  }
}
</style>
