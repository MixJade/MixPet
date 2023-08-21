<template>
  <el-avatar :src="'/api/common/download?name='+rolePhoto" alt="详情头像"/>
  <!-- 用户、管理员、医生的个人信息展示-->
  <table class="myTable">
    <tr v-for="t in tabCon">
      <td class="bold">{{ t.tit }}</td>
      <td>{{ t.con }}</td>
    </tr>
    <tr>
      <td class="bold">操作</td>
      <td>
        <el-button type="primary" @click="updateRole">修改资料</el-button>
        &nbsp;
        <el-button type="warning" @click="updatePwd">重置密码</el-button>
      </td>
    </tr>
    <tr>
      <td class="bold">其它操作</td>
      <td>
        <el-button type="danger" @click="logout">退出登录</el-button>
      </td>
    </tr>
  </table>
</template>

<script lang="ts" setup>
/*=====三个角色的个人信息展示页面=====*/
import {reqLogout} from "@/request/PowerApi";
import {useRouter} from "vue-router";

defineProps<{
  rolePhoto: string,
  tabCon: DetailTabType[]
}>()
const emit = defineEmits<{
  (e: "upRole"): void,
  (e: "upPwd"): void
}>()

export interface DetailTabType {
  tit: string,
  con: any
}

const updateRole = () => {
  emit("upRole");
}
const updatePwd = () => {
  emit("upPwd");
}
// 退出登录
const router = useRouter()
const logout = (): void => {
  reqLogout().then(resp => {
    if (resp.code === 204) router.push("/")
  })
}
</script>

<style lang="scss" scoped>
.el-avatar {
  width: 128px;
  height: 128px;
  position: relative;
  left: 50%;
  margin-left: -64px;
}

/*设置信息展示表格*/
.myTable {
  border-collapse: collapse;
  text-align: center;
  /*设置定位*/
  position: relative;
  width: 60%;
  left: 20%;
  margin-bottom: 20px;

  td {
    border: none;
    padding: 2px;
  }

  .bold {
    text-align: right;
    font-weight: bold;
  }

  .bold + td {
    text-align: left;
    padding-left: 50px;
  }
}
</style>