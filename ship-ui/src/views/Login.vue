<template>
  <div class="login-page">
    <el-tabs type="border-card">
      <el-tab-pane label="用户">
        <LoginForm :role="RoleEnum.CLIENT" @loginSuc="getLoginRole"/>
        <el-col>
          <el-button round type="success" @click="this.$router.push('/reception')">游客登录</el-button>
          <el-button round type="warning">账号注册</el-button>
          <el-button round type="danger">找回密码</el-button>
        </el-col>
      </el-tab-pane>
      <el-tab-pane label="管理员">
        <LoginForm :role="RoleEnum.ADMIN" @loginSuc="getLoginRole"/>
      </el-tab-pane>
      <el-tab-pane label="医生">
        <LoginForm :role="RoleEnum.DOCTOR" @loginSuc="getLoginRole"/>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script lang="ts" setup>
import LoginForm from "@/components/LoginForm.vue";
import {onMounted} from "vue";
import {reqGetLogin} from "@/request/PowerApi";
import {useRouter} from "vue-router";
import {RoleEnum} from "@/modal/VO/LoginVo";

// 登陆的初始化
onMounted(() => {
  getLoginRole()
})
// 判断当前登陆角色
const router = useRouter()
const getLoginRole = () => {
  reqGetLogin().then(resp => {
    if (resp === RoleEnum.CLIENT) router.push("/reception")
    else if (resp === RoleEnum.ADMIN) router.push("/backstage")
    else if (resp === RoleEnum.DOCTOR) router.push("/doctorPage")
    else console.log("当前未登陆")
  })
}
</script>

<style lang="scss" scoped>
/*整个登录组件*/
.login-page {
  background: {
    color: #ccc;
    size: cover;
    repeat: no-repeat;
  };
  width: 98vw;
  height: 90vh;
  position: fixed;
}

/*登录的标签栏*/
.el-tabs {
  position: absolute;
  margin-top: 5%;
  left: 50%;
  transform: translateX(-50%);

  .el-tab-pane {
    background-color: #cccccc;
  }
}

.el-col {
  padding-top: 10px;
  text-align: center;
}
</style>