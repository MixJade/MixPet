<template>
  <el-form
      :label-position="'right'"
      :model="formLogin"
      class="login-card"
      label-width="80px"
  >
    <h3>{{ getRoleName }}登录</h3>
    <el-form-item label="用户名">
      <el-input v-model="formLogin.username" :prefix-icon="User" type="text"/>
    </el-form-item>
    <el-form-item label="密码">
      <el-input v-model="formLogin.password" :prefix-icon="Lock" show-password type="password"/>
    </el-form-item>
    <el-form-item label="记住我">
      <el-switch v-model="formLogin.remember"/>
    </el-form-item>
    <el-button :type="btnType" round style="width: 80%" @click="toLogin">{{ getRoleName }}登录</el-button>
  </el-form>
</template>

<script lang="ts" setup>
import {computed, reactive, Ref, ref} from "vue";
import {Lock, User} from "@element-plus/icons-vue"
import {buttonTypes} from "element-plus"
import {reqLogin} from "@/request/PowerApi";
import {LoginVo, RoleEnum} from "@/modal/VO/LoginVo";
// 定义登录的角色
const prop = defineProps<{
  role: RoleEnum
}>()
// 登陆成功的事件
const emit = defineEmits<{
  (e: "loginSuc"): void
}>()
// 根据登录角色不同使用不同按钮
const btnType: Ref<"primary" | "success" | "info"> = ref(buttonTypes[1])
// 根据传来的角色设置展示
const getRoleName = computed(() => {
  switch (prop.role) {
    case RoleEnum.ADMIN:
      btnType.value = buttonTypes[2]
      return "管理员";
    case RoleEnum.DOCTOR:
      btnType.value = buttonTypes[4]
      return "医生";
    default:
      return "用户";
  }
})
// 登录的数据
const formLogin: LoginVo = reactive({
  username: "",
  password: "",
  remember: true,
  role: RoleEnum.CLIENT
})

// 点击登录之后
const toLogin = () => {
  formLogin.role = prop.role
  reqLogin(formLogin).then(resp => {
    console.log(resp.msg)
    if (resp.code == 1) {
      emit("loginSuc")
    }
  })
}
</script>

<style lang="scss" scoped>
/*登录的小卡片*/
.login-card {
  background-color: #cccccc;
  max-width: 460px;
  text-align: center;

  .el-input {
    width: 80%;
  }
}
</style>