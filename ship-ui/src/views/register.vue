<template>
  <div class="register">
    <svg preserveAspectRatio="none" viewBox="0 0 400 300">
      <!-- 定义填充色-->
      <defs>
        <linearGradient id="Gradient" x1="0" x2="100%" y1="100%" y2="0%">
          <stop offset="0%" stop-color="#FF82BB"/>
          <stop offset="100%" stop-color="#F9F871"/>
        </linearGradient>
        <linearGradient id="Gradient2" x1="0" x2="100%" y1="100%" y2="0%">
          <stop offset="0%" stop-color="#007DCC"/>
          <stop offset="100%" stop-color="#90E9FF"/>
        </linearGradient>
      </defs>
      <!--大三角形的三个点-->
      <polygon fill="url(#Gradient)" points="0,0 400,0 0,300"/>
      <!--小三角形的三个点-->
      <polygon fill="url(#Gradient2)" points="100,100 300,100 100,250"/>
      <!--小小三角形的三个点-->
      <polygon fill="url(#Gradient)" points="150,150 250,150 150,225"/>
    </svg>
    <PageHead>注册账号</PageHead>
    <div class="register-card">
      <h2 style="color: #3c91ff">注册账号</h2>
      <label>
        账号：
        <input v-model="regVo.username" autocomplete="off" class="input" name="username" placeholder="username"
               type="text" @change="isExist">
      </label>
      <label>
        密码：
        <input v-model="regVo.password" class="input" name="password" placeholder="password" type="password">
      </label>
      <label>
        姓名：
        <input v-model="regVo.name" autocomplete="off" class="input" name="clientName" placeholder="name"
               type="text">
      </label>
      <label>
        性别：
        <label style="padding: 0 24px"><input v-model="regVo.sex" name="sex" type="radio" value="true"/>男</label>
        <label style="padding: 0 24px"><input v-model="regVo.sex" name="sex" type="radio" value="false"/>女</label>
      </label>
      <label>
        邮箱：
        <input v-model="regVo.mail" autocomplete="off" class="input" name="email" placeholder="email@qq.com"
               type="email">
      </label>
      <label>
        验证码：
        <input v-model="regVo.code" autocomplete="off" class="code-input" name="check-code" placeholder="code"
               type="text">
        <button type="button" v-bind:disabled="mailBtn.dis" @click="sendMail">{{ mailBtn.btnTxt }}</button>
      </label>
      <button type="button" @click="toReg">进行注册</button>
    </div>
  </div>
</template>

<script lang="ts" setup>
import PageHead from "@/components/row/PageHead.vue";
import {reactive} from "vue";
import {ElMessage} from "element-plus";
import {reqLogin, reqRegisExist, reqRegister, reqSendRegMail} from "@/request/PowerApi";
import {RegisterVo} from "@/model/VO/RegisterVo";
import {LoginVo, RoleEnum} from "@/model/VO/LoginVo";

const regVo = reactive<RegisterVo>({
  username: "",
  password: "",
  name: "",
  sex: true,
  mail: "",
  code: ""
})
const mailBtn = reactive({btnTxt: "发送验证码", dis: false})
const sendMail = (): void => {
  if (noMail()) return;
  mailBtn.dis = true;
  let countDown = 30;
  let intVal = setInterval(function () {
    if (countDown === 0) {
      clearInterval(intVal)
      mailBtn.dis = false;
      mailBtn.btnTxt = "发送验证码";
      return
    }
    mailBtn.btnTxt = "等待中(" + countDown + ")";
    countDown--;
  }, 1000)
  reqSendRegMail(regVo.mail)
}

// 邮箱格式验证
const noMail = (): boolean => {
  const reg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  if (!reg.test(regVo.mail)) {
    ElMessage.warning("邮箱格式不对")
    return true;
  } else return false;
}

// 用户名是否存在
const isExist = () => {
  reqRegisExist(regVo.username)
}

// 进行注册
const toReg = (): void => {
  if (regVo.username == '') {
    ElMessage.warning("用户名不能为空")
    return;
  }
  if (regVo.password.length < 5) {
    ElMessage.warning("密码长度不能低于5位")
    return;
  }
  if (regVo.name == '') {
    ElMessage.warning("请填写姓名")
    return;
  }
  reqRegister(regVo).then(res => {
    if (res.code === 1) {
      const loginVo: LoginVo = {
        username: regVo.username,
        password: regVo.password,
        remember: false,
        role: RoleEnum.CLIENT,
      }
      reqLogin(loginVo).then(res => {
        if (res.code === 1) location.href = "/#/reception"
      })
    }
  })
}
</script>
<style lang="scss" scoped>
/*整个登录组件*/
.register {
  width: 94vw;
  height: 94vh;

  svg {
    z-index: -1;
    position: absolute;
    width: 94%;
    height: 94%;
  }

  .register-card {
    position: absolute;
    left: 50%;
    top: 50%;
    width: 320px;
    max-width: 90vw;
    margin-top: -220px;
    margin-left: -196px;
    text-align: center;
    -webkit-user-select: none;
    user-select: none;
    padding: 12px 36px;
    border-radius: 24px;
    background-color: rgba(0, 0, 0, 0.1); /* 子元素的背景颜色，含半透明 */
    backdrop-filter: blur(10px);

    & > label {
      display: flex;
      align-items: center;
      background: #fafafa;
      box-shadow: 5px 5px 10px 0 rgba(0, 0, 0, 0.3);
      width: 280px;
      border-radius: 16px;
      box-sizing: border-box;
      padding: 4px 12px;
      margin-bottom: 8px;
      margin-left: 20px;
      /* 输入框 */
      & > input {
        box-shadow: inset 13px 13px 10px #dcdcdc,
        inset -13px -13px 10px #f4f4f4;
        background: #e8e8e8;
        box-sizing: border-box;
        padding: 12px;
        border: none;
        border-left: 2px solid #4998FF;
        color: #5e5e5e;
        transition: all 0.2s ease-in-out;

        &:focus {
          border-left: 2px solid #4998FF;
          outline: none;
          box-shadow: inset 13px 13px 10px #BFF0FA, inset -13px -13px 10px #f4f4f4;
        }
      }

      .input {
        border-radius: 12px;
        width: 200px;
      }

      .code-input {
        border-radius: 12px 0 0 12px;
        width: 80px;

        & + button {
          margin-left: 4px;
          font-weight: 700;
          width: 100px;
          border-radius: 0 12px 12px 0;
        }
      }
    }

    /* 按钮样式 */
    button {
      padding: 12px;
      background: linear-gradient(135deg, #3c91ff, #7fd4e7);
      color: white;
      border: none;

      &:hover {
        background: linear-gradient(135deg, #1f6bcc, #7fd4e7);
      }

      &:disabled {
        background: #cccccc;
      }
    }

    /* 发送按钮 */
    & > button {
      width: 50%;
      border-radius: 1rem;
      font-weight: 900;
    }
  }
}
</style>
