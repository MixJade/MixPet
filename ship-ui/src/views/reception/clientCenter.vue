<template>
  <div>
    <el-radio-group v-model="radio1" @change="getChange">
      <ul>
        <li>
          <el-radio label="/reception" size="large" border style="color: #F56C6C">返回主页</el-radio>
        </li>
        <li>
          <el-radio label="/reception/clientCenter" size="large" border>个人中心</el-radio>
        </li>
        <li>
          <el-radio label="/reception/clientCenter/clientPet" size="large" border>宠物列表</el-radio>
        </li>
        <li>
          <el-radio label="/reception/clientCenter/clientAppoint" size="large" border>挂号查看</el-radio>
        </li>
      </ul>
    </el-radio-group>
    <div class="one-basePage">
      <router-view></router-view>
    </div>
  </div>
</template>

<script setup lang="ts">
import {ref} from 'vue'
import {useRouter} from "vue-router";

const router=useRouter()
const radio1 = ref(router.currentRoute.value.fullPath)
const getChange = (): void => {
  router.push(radio1.value)
}
</script>

<style scoped>
</style>