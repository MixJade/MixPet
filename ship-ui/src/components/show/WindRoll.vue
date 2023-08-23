<template>
  <section ref="myRoll">
    <img id="wind" ref="wind" alt="神像" src="/picture/wind.png">
    <img id="wall-1" alt="围墙1" src="/picture/wall.png">
    <img id="wall-2" alt="围墙2" src="/picture/wall.png">
    <img id="star" ref="star" alt="流星" src="/picture/star.png">
    <img id="cloud" ref="cloud" alt="云朵" src="/picture/cloud.png">
    <div id="text" ref="text">HELLO_WORLD</div>
    <router-link :to="isLogin?'/reception/clientCenter':'/'">
      <MoveBtn id="btn">{{ isLogin ? "个人中心" : "前往登录" }}</MoveBtn>
    </router-link>
  </section>
</template>

<script lang="ts" setup>
import {onBeforeUnmount, onMounted, ref} from "vue";
import MoveBtn from "@/components/button/MoveBtn.vue";

// 接收参数：是否登陆
defineProps<{
  readonly isLogin: boolean
}>()

const star = ref<HTMLDivElement>()
const wind = ref<HTMLDivElement>()
const cloud = ref<HTMLDivElement>()
const text = ref<HTMLDivElement>()
const myRoll = ref<HTMLDivElement>()
let rollHeight: number = 880;

const scrollWind = () => {
  let scrollY = window.scrollY
  if (scrollY < rollHeight) {
    star.value!.style.top = scrollY * 1.2 + 'px';
    star.value!.style.left = scrollY * 1.2 + 'px';
    wind.value!.style.bottom = -scrollY * 0.5 + 'px';
    cloud.value!.style.marginLeft = -scrollY + 'px';
    cloud.value!.style.marginTop = scrollY * 0.4 + 'px';
    text.value!.style.marginLeft = -scrollY * 4 + 'px';
    text.value!.style.marginTop = scrollY * 0.5 + 'px';
  }
}

onMounted(() => {
  if (myRoll.value?.offsetHeight !== undefined) {
    rollHeight = myRoll.value!.offsetHeight
  }
  window.addEventListener('scroll', scrollWind)
})

onBeforeUnmount(() => {
  window.removeEventListener('scroll', scrollWind)
})
</script>

<style lang="scss" scoped>
/* 滚动视觉差 */
section {
  position: relative;
  width: 100%;
  height: 90vh;
  overflow: hidden;
  background-image: linear-gradient(#3CB371, transparent);

  img {
    position: absolute;
    pointer-events: none;

    &#wind {
      height: 80%;
      width: auto;
      object-fit: contain;
      bottom: 0;
      left: 50%;
      transform: translateX(-50%);
      z-index: 40;
    }

    &#wall-1 {
      height: auto;
      width: 40%;
      bottom: 0;
      z-index: 50;
    }

    &#wall-2 {
      height: auto;
      width: 40%;
      right: 0;
      bottom: 0;
      transform: scaleX(-1);
      z-index: 50;
    }

    &#star {
      height: 20%;
      width: auto;
      z-index: 10;
      top: 0;
      left: 0;
    }

    &#cloud {
      mix-blend-mode: screen; /* 设置背景融合 */
      height: 20%;
      width: auto;
      z-index: 20;
      top: 25%;
      left: 50%;
    }
  }

  #text {
    position: absolute;
    color: #FFFAF0;
    font-size: 6vw;
    white-space: nowrap;
    z-index: 30;
    transform: translateY(100px);
    left: 100%;
  }
}

/* 导航栏 */
header {
  box-sizing: border-box;
  width: 100%;
  padding: 0 60px;
  background-color: #228B22;
  display: flex;
  justify-content: space-between;
  align-items: center;
  z-index: 100;

  & > a:first-child {
    color: #fff;
    text-decoration: none;
    font-size: 1.5em;
    font-weight: 700;
    letter-spacing: 2px;
  }

  > ul {
    display: flex;
    justify-content: center;
    align-items: center;

    > li {
      list-style: none;
      margin-left: 20px;

      a {
        text-decoration: none;
        color: #fff;
        padding: 6px 25px;
        border-radius: 20px;

        &:hover, &:active {
          background-color: #fff;
          color: #000;
        }
      }
    }
  }
}

/* 导航下拉框 */
.drop {
  cursor: pointer;
  color: #fff;

  span {
    display: inline-block;
    transform: rotate(0deg);
    transition: all 500ms;
  }

  &:hover {
    color: #FFD700;

    span {
      transform: rotate(180deg);
    }

    .drop-list {
      visibility: visible;
      height: 128px;
    }
  }

  .drop-list {
    position: absolute;
    width: 136px;
    margin-left: -28px;
    background: #3CB371;
    box-shadow: 0 1px 2px #333333;
    border: dashed seashell;
    border-radius: 20px;
    list-style: none;
    text-align: center;
    padding: 5px 5px;
    z-index: 70;
    transition: height 500ms;
    height: 0;
    overflow-y: hidden;
    visibility: hidden;

    li {
      padding-top: 4px;
      padding-bottom: 4px;
    }
  }
}

/* 滚动视觉差 */
section {
  position: relative;
  width: 100%;
  height: 90vh;
  overflow: hidden;
  background-image: linear-gradient(#3CB371, transparent);

  img {
    position: absolute;
    pointer-events: none;

    &#wind {
      height: 80%;
      width: auto;
      object-fit: contain;
      bottom: 0;
      left: 50%;
      transform: translateX(-50%);
      z-index: 40;
    }

    &#wall-1 {
      height: auto;
      width: 40%;
      bottom: 0;
      z-index: 50;
    }

    &#wall-2 {
      height: auto;
      width: 40%;
      right: 0;
      bottom: 0;
      transform: scaleX(-1);
      z-index: 50;
    }

    &#star {
      height: 20%;
      width: auto;
      z-index: 10;
      top: 0;
      left: 0;
    }

    &#cloud {
      mix-blend-mode: screen; /* 设置背景融合 */
      height: 20%;
      width: auto;
      z-index: 20;
      top: 25%;
      left: 50%;
    }
  }

  #text {
    position: absolute;
    color: #FFFAF0;
    font-size: 6vw;
    white-space: nowrap;
    z-index: 30;
    transform: translateY(100px);
    left: 100%;
  }

  #btn {
    position: absolute;
    left: 50%;
    top: 50%;
    z-index: 60;
  }
}
</style>
