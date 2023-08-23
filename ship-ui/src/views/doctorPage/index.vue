<template>
  <div class="my-index">
    <!-- 导航 -->
    <header>
      <ul>
        <li>
          <router-link to="/backstage">个人中心</router-link>
        </li>
        <li>
          <router-link to="/doctorPage/d-appoint">处理挂号</router-link>
        </li>
        <li>
          <router-link to="/doctorPage/d-chat">在线咨询</router-link>
        </li>
      </ul>
    </header>
    <main>
      <!-- 时钟 -->
      <div class="clock">
        <p>{{ timeNum.hh1 }}</p>
        <p>{{ timeNum.hh2 }}</p>
        <p>:</p>
        <p>{{ timeNum.mm1 }}</p>
        <p>{{ timeNum.mm2 }}</p>
        <p>:</p>
        <p>{{ timeNum.ss1 }}</p>
        <p>{{ timeNum.ss2 }}</p>
      </div>
      <!-- 卡片 -->
      <MyRow>
        <div class="box">
          <span></span>
          <div class="content">
            <h2>个人中心</h2>
            <p>或涵盖个人信息</p>
            <p>或守护数据隐私</p>
            <router-link to="/doctorPage/doctorOne">
              <el-button plain size="large" type="success">个人中心</el-button>
            </router-link>
          </div>
        </div>
        <div class="box">
          <span></span>
          <div class="content">
            <h2>处理挂号</h2>
            <p>或安排专业治疗</p>
            <p>或护佑生命安宁</p>
            <router-link to="/doctorPage/d-appoint">
              <el-button plain size="large" type="danger">处理挂号</el-button>
            </router-link>
          </div>
        </div>
        <div class="box">
          <span></span>
          <div class="content">
            <h2>在线咨询</h2>
            <p>或提供专业解答</p>
            <p>或构建知识桥梁</p>
            <router-link to="/doctorPage/d-chat">
              <el-button plain size="large" type="primary">在线咨询</el-button>
            </router-link>
          </div>
        </div>
      </MyRow>
    </main>
  </div>
</template>

<script lang="ts" setup>

import {onBeforeUnmount, onMounted, reactive} from "vue";
import MyRow from "@/components/show/MyRow.vue";
// 一秒执行一次
let timeTimer: NodeJS.Timer;

onMounted(() => {
  timeTimer = setInterval(myTime, 1000);
})

onBeforeUnmount(() => {
  clearInterval(timeTimer)
})

const timeNum = reactive({
  hh1: 0,
  hh2: 0,
  mm1: 0,
  mm2: 0,
  ss1: 0,
  ss2: 0
})

const myTime = () => {
  const time = new Date();
  const hh = time.getHours();  //时
  const mm = time.getMinutes();  //分
  const ss = time.getSeconds();  //秒
  // Math.floor() 向下取整
  timeNum.hh1 = Math.floor(hh / 10)
  timeNum.hh2 = hh % 10
  timeNum.mm1 = Math.floor(mm / 10)
  timeNum.mm2 = mm % 10
  timeNum.ss1 = Math.floor(ss / 10)
  timeNum.ss2 = ss % 10
}
</script>

<style lang="scss" scoped>
.my-index {
  -webkit-user-select: none;
  user-select: none;
  min-height: 98vh;
  background-image: linear-gradient(#228B22, #007565);
}

/* 导航 */
header {
  width: 100%;
  height: 50px;
  position: relative;
  top: 0;
  box-shadow: 0 5px 20px rgba(0, 0, 0, 0.5);
  border-radius: 10px;
  background-color: #303133;
  display: flex;
  align-items: center;
  justify-content: center;

  ul {
    width: 36%;
    min-width: 240px;
    height: 100%;
    display: flex;
    justify-content: center;
    line-height: 50px;

    li {
      width: 100%;
      display: inline-block;
      text-align: center;
      list-style: none;
      color: #FAFAFA;

      a {
        font-size: 16px;
        font-weight: bold;
        display: block;
      }

      &:nth-child(1):hover {
        color: #67C23A;
        border-bottom: #67C23A solid 5px;
      }

      &:nth-child(2):hover {
        color: red;
        border-bottom: red solid 5px;
      }

      &:nth-child(3):hover {
        color: mediumpurple;
        border-bottom: mediumpurple solid 5px;
      }
    }
  }
}

/* 时钟 */
.clock {
  display: flex;
  width: 100%;
  justify-content: center;

  p {
    width: 56px;
    font-size: 64px;
    color: #fff;
    text-align: center;
    font-weight: 700;
    /* 文字阴影 实现3D效果 */
    text-shadow: 0 1px 0 #deafaf,
    0 2px 0 #bda8a8,
    0 3px 0 #d8a1a1,
    0 4px 0 #d59999,
    0 5px 0 #d29292;
  }
}

/* 卡片入口 */
.box {
  position: relative;
  margin-top: 8px;
  width: 360px;
  height: 280px;
  display: flex;
  justify-content: center;
  align-items: center;

  &:before {
    content: ' ';
    position: absolute;
    top: 0;
    left: 90px;
    width: 50%;
    height: 100%;
    text-decoration: none;
    background: linear-gradient(315deg, #ffbc00, #ff0058);
    border-radius: 8px;
    transform: skewX(15deg);
    transition: 0.5s;
  }

  &:hover:before {
    transform: skewX(0deg) scaleX(1.3);
  }

  span {
    /* 上下的小方块 */
    display: block;
    position: absolute;
    inset: 0;
    z-index: 5;
    pointer-events: none;

    &:before, &:after {
      content: '';
      position: absolute;
      border-radius: 8px;
      background: rgba(255, 255, 255, 0.1);
      backdrop-filter: blur(10px);
      box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
      width: 50px;
      height: 50px;
    }

    &:before {
      top: -40px;
      left: 40px;
    }

    &:after {
      bottom: -40px;
      right: 40px;
    }
  }

  .content {
    position: relative;
    width: 190px;
    height: 254px;
    padding: 20px 40px;
    background: rgba(255, 255, 255, 0.05);
    backdrop-filter: blur(10px);
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    color: #fff;
    text-align: center;

    h2 {
      font-size: 20px;
      color: #fff;
      margin-bottom: 10px;
    }
  }
}
</style>
