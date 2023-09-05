<template>
  <div class="find-pwd">
    <PageHead>找回密码</PageHead>
    <img alt="云朵" class="cloud" src="/picture/cloud.png">
    <div class="send-mail">
      <img alt="信封表面" src="/picture/envelope_1.svg">
      <div>
        <div>
          <h3>找回密码</h3>
          <label>
            账号：
            <input v-model="findPwd.username" autocomplete="off" name="username" placeholder="账号" type="text">
          </label>
          <label>
            邮箱：
            <input v-model="findPwd.mail" autocomplete="off" name="mail" placeholder="邮箱" type="email">
          </label>
          <label class="check-code">
            验证码
            <input v-model="findPwd.checkCode" autocomplete="off" name="checkCode" placeholder="验证码" type="text">
            <button type="button" v-bind:disabled="mailBtn.dis" @click="sendMail">{{ mailBtn.btnTxt }}</button>
          </label>
          <label>
            密码：
            <input v-model="findPwd.newPwd" autocomplete="off" name="password" placeholder="密码" type="password">
          </label>
          <button type="button" @click="toFind">重置密码</button>
        </div>
      </div>
      <img alt="信封背景" src="/picture/envelope_2.svg">
    </div>
    <img alt="云朵" class="cloud" src="/picture/cloud.png">
    <p style="position: absolute;right: 30%;bottom:10%;z-index: -1">输入时鼠标别放在输入法上</p>
  </div>
</template>

<script lang="ts" setup>
import PageHead from "@/components/row/PageHead.vue";
import {reactive} from "vue";
import {ElMessage} from "element-plus";

interface FindPwd {
  username: string;
  mail: string;
  checkCode: string;
  newPwd: string;
}

const findPwd = reactive<FindPwd>({
  username: "",
  mail: "",
  checkCode: "",
  newPwd: ""
})

// 发送邮件
const mailBtn = reactive({btnTxt: "发送验证码", dis: false})
const sendMail = () => {
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
}

// 进行密码找回
const toFind = () => {
  if (noMail()) return;
  ElMessage.success(
      `登录成功!账号:${findPwd.username},
      邮箱：${findPwd.mail},
      验证码：${findPwd.checkCode},
      密码:${findPwd.newPwd}`,
  )
}

// 邮箱格式验证
const noMail = (): boolean => {
  const reg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  if (!reg.test(findPwd.mail)) {
    ElMessage.warning("邮箱格式不对")
    return true;
  } else return false;
}
</script>

<style lang="scss" scoped>
.find-pwd {
  width: 98vw;
  height: 98vh;
  background-image: linear-gradient(#F0E68C, transparent);

  & > img.cloud {
    position: absolute;

    &:first-of-type {
      mix-blend-mode: screen; /* 设置背景融合 */
      left: 10%;
      top: 10%;
    }

    &:last-of-type {
      z-index: -1;
      left: 55%;
      top: 50%;
    }
  }
}

.send-mail {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);

  img {
    width: 452px;
    max-width: 92vw;
    height: 456px;
    pointer-events: none; /* 禁用事件，让下面div事件能够触发 */
    &:first-of-type {
      position: absolute;
      z-index: 20;
    }

    & :last-of-type {
      z-index: 10;
    }
  }

  & > div {
    position: absolute;
    z-index: 15;
    inset: 8px;
    border-radius: 24px;
    background-color: transparent;
    overflow-y: hidden;

    & > div {
      position: absolute;
      box-sizing: border-box;
      padding: 8px;
      border-radius: 24px;
      inset: 0;
      background-color: #E4E7ED;
      transition: transform 1s;
      transform: translateY(50%);
      text-align: center;
      display: block;

      &:hover {
        transform: translateY(0);
      }
    }

    label {
      display: block;
      margin-bottom: 4px;
      height: 40px;

      input {
        width: 186px;
        height: 40px;
        box-sizing: border-box;
        padding: 10px;
        border: 2px solid white;
        border-radius: 5px;
        box-shadow: 3px 3px 2px #FFA500;

        &:focus {
          color: #FF8C00;
          outline-color: #FF8C00;
          box-shadow: -3px -3px 15px #FF8C00;
          transition: .5s;
          transition-property: box-shadow;
        }
      }

      &.check-code {
        input {
          width: 86px;
          border-radius: 5px 0 0 5px;
        }

        button {
          border-radius: 0 5px 5px 0;
          box-shadow: 3px 3px 2px #FFA500;
        }
      }
    }

    button {
      height: 41px;
      width: 100px;
      border-radius: 5px;
      padding: 10px;
      border: 2px solid white;
      background-color: #F56C6C;
      color: white;

      &:hover, &:disabled {
        background-color: #fab6b6;
      }
    }
  }
}

@media screen and (max-width: 520px) {
  img.cloud {
    display: none;
  }
}
</style>
