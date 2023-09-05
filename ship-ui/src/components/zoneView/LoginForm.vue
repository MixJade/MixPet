<template>
  <div class="login-card">
    <h3>{{ getRoleName }}登录</h3>
    <div class="my-input">
      <label>
        <input v-model="formLogin.username" autocomplete="off" name="username"
               placeholder="username"
               style="background-image: url('/icon/username.svg')" type="text">
        <span>帐号</span>
      </label>
    </div>
    <div class="my-input">
      <label>
        <input v-model="formLogin.password" name="password" placeholder="password"
               style="background-image: url('/icon/password.svg')" type="password"
               @keyup.enter="toLogin"
        >
        <span>密码</span>
      </label>
    </div>
    <div class="remember">
      <label class="check-label">
        <input v-model="formLogin.remember" class="check-input" name="remember" type="checkbox">
        <span class="check-block">
          <span class="inner">记住我</span>
        </span>
      </label>
    </div>
    <MyBtn @click="toLogin">登录</MyBtn>
  </div>
</template>

<script lang="ts" setup>
/*=====登录的页面，不同的角色同一个登录页面，不同的请求路径=====*/
import {computed, reactive} from "vue";
import MyBtn from "@/components/button/MyBtn.vue";
import {reqLogin} from "@/request/PowerApi";
import {LoginVo, RoleEnum} from "@/model/VO/LoginVo";
// 定义登录的角色
const prop = defineProps<{
  role: RoleEnum
}>()
// 登陆成功的事件
const emit = defineEmits<{
  (e: "loginSuc"): void
}>()
// 根据传来的角色设置展示
const getRoleName = computed(() => {
  switch (prop.role) {
    case RoleEnum.DOCTOR:
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
  width: 320px;
  text-align: center;
}

/* 自己写的浮动输入框 */
.my-input {
  position: relative;
  text-align: left;
  margin-top: 5px;
  left: 40px;
  /* 输入框 */
  input {
    transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
    width: 200px;
    height: 36px;
    padding: 10px 0 0 36px;
    border: #cfcfcf 2px solid;
    border-radius: 5px;
    background: {
      size: 24px 24px;
      repeat: no-repeat;
      position-y: center;
    };
    /*将占位符设为透明*/
    &::placeholder {
      color: transparent;
    }

    /* 获取焦点显示阴影 */
    &:focus {
      color: #212529;
      background-color: #fff;
      border-color: #86b7fe;
      outline: 0;
      box-shadow: 0 0 0 0.25rem rgba(13, 110, 253, 0.25);
    }

    /* 标签上浮 */
    &:not(:placeholder-shown) + span, &:focus + span {
      color: #c9ab0a;
      opacity: 0.65;
      transform: scale(0.8) translateY(-3px) translateX(3px);
    }

    /*标签样式*/
    & + span {
      position: absolute;
      font-size: medium;
      color: #6c757d;
      top: 0;
      left: 28px;
      height: 46px;
      line-height: 46px;
      overflow: hidden;
      white-space: nowrap;
      pointer-events: none;
      border: 1px solid transparent;
      transform-origin: 0 0;
      transition: opacity 0.1s ease-in-out, transform 0.1s ease-in-out;
    }
  }
}

/* 记住我的单选框 */
.remember {
  position: relative;
  margin-top: 5px;
  margin-bottom: 5px;
  left: 32px;
  width: 80px;

  .check-label {
    .check-block {
      display: flex;
      align-items: center;
      justify-content: center;
      width: 64px;
      height: 24px;
      border-radius: 12px;
      cursor: pointer;
      -webkit-user-select: none;
      padding: 2px 8px;
      background-color: rgba(0, 0, 0, 0.16);
      color: rgba(255, 255, 255, 0.7);
      transition-timing-function: cubic-bezier(0.25, 0.8, 0.25, 1);
      transition-duration: 500ms;
      transition-property: color, background-color, box-shadow;
      box-shadow: rgba(0, 0, 0, 0.15) 0 2px 1px 0 inset, rgba(255, 255, 255, 0.17) 0 1px 1px 0;

      .inner {
        display: inline-block;
        padding: 8px;
        font-size: 14px;
        font-weight: 300;
        pointer-events: none;
      }
    }

    .check-input {
      display: none;

      &:checked + .check-block {
        background-color: #409EFF;
        color: white;
        box-shadow: rgba(0, 0, 0, 0.23) 0 -4px 1px 0 inset, rgba(255, 255, 255, 0.17) 0 -1px 1px 0, rgba(0, 0, 0, 0.17) 0 2px 4px 1px;

        &:hover {
          background-color: #337ecc;
          box-shadow: rgba(0, 0, 0, 0.26) 0 -4px 1px 0 inset, rgba(255, 255, 255, 0.17) 0 -1px 1px 0, rgba(0, 0, 0, 0.15) 0 3px 6px 2px;
        }
      }
    }
  }
}
</style>