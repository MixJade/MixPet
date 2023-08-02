<template>
  <div>
    <el-radio-group v-model="radio1" @change="getChange">
      <ul>
        <li>
          <el-radio border label="/reception" size="large" style="color: #F56C6C">返回主页</el-radio>
        </li>
        <li>
          <el-radio border label="/reception/clientCenter" size="large">个人中心</el-radio>
        </li>
        <li>
          <el-radio border label="/reception/clientCenter/clientPet" size="large">宠物列表</el-radio>
        </li>
        <li>
          <el-radio border label="/reception/clientCenter/clientAppoint" size="large">挂号查看</el-radio>
        </li>
      </ul>
    </el-radio-group>
    <div class="one-basePage">
      <router-view></router-view>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {ref} from 'vue'
import {useRouter} from "vue-router";

const router = useRouter()
const radio1 = ref(router.currentRoute.value.fullPath)
const getChange = (): void => {
  router.push(radio1.value)
}
</script>

<style scoped lang="scss">
.el-radio-group {
  background-color: #F0F2F5;
}
</style>