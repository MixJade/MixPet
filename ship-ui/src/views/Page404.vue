<template>
  <div class="panel"
       @click="focusIn"
       @keyup.space="start"
       @keyup.esc="stop"
       @keydown.left="moveLeft"
       @keydown.right="moveRight"
  >
    <input ref="myInput" class="myInput">
    <h1>{{ state.score }}</h1>
    <p>你来到了网站的尽头，这里只有一个小游戏</p>
    <p>按空格开始游戏，按ESC暂停</p>
    <p>按下左右方向键移动挡板</p>
    <!--游戏面板-->
    <div class="box">
      <div class="str">{{ state.str }}</div>
      <div class="brickBox">
        <div v-for="(item,index) in state.arr" :key="index" :ref="(el:Element)=>(brickRef[index]=el)"
             :style="{opacity :item.active ? '0':'1'}"
             class="brick"></div>
      </div>
      <div :style="{left :state.x + 'px', top : state.y + 'px'}" class="ball"></div>
      <div ref="bottomMove" :style="{left :state.mx + 'px' , top : state.my + 'px'}"
           class="bottomMove"></div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {onBeforeUnmount, onMounted, reactive, ref, watch} from 'vue'

// 绑定移动方块事件
onMounted(() => {
  focusIn()
})
// 移除移动方块事件
onBeforeUnmount(() => {
  clearInterval(timer)
})
// 隐藏的输入框
const myInput = ref<HTMLInputElement>();
const focusIn = () => {
  myInput.value!.focus()
}
const noTxt = ref<string>("")
watch(() => noTxt.value, () => noTxt.value = "")
// 挡板与小球的初始位置
const state = reactive({
  x: 238,  // 小球x轴，初始位置：面板宽度/2-小球宽度/2
  y: 270, // 小球Y轴,初始位置：面板高度-小球高度-挡板高度
  mx: 200, // 挡板x轴，初始位置：面板宽度/2-挡板宽度/2
  my: 294, // 挡板y轴，初始位置：面板高度-挡板高度
  // 被打击方块的数组
  arr: Array.from({length: 50}, (_, index) => {
    return {
      index,
      active: false
    }
  }),
  str: '', // 返回挑战成功字眼
  score: 404 // 分数
})

let timer: NodeJS.Timer, // 小球定时器
    speed = 3,// 小球速度
    map = {x: 10, y: 10},
    timer2: NodeJS.Timer, // 挑战成功字眼显示定时器
    index = 0 // 挑战成功字眼显示的索引值

// 挑战成功字眼续个显示的方法
const strFun = (strArr: string): void => {
  timer2 = setInterval(() => {
    // 如果文字放完了就结束定时器
    if (index === strArr.length) {
      clearInterval(timer2)
      window.location.href = '/'
    }
    // 放文字
    state.str += strArr.substring(index, index + 1)
    index++
  }, 500)
}


//移动小球的方法
// 1. 确定小球碰到 左右上 及移动方块是否回弹
const bottomMove = ref<HTMLDivElement>()
// 2. 循环砖块检测小球碰撞到砖块消失
const brickRef = ref<Element[]>([])
const moveBall = () => {
  const {offsetTop, offsetHeight, offsetLeft, offsetWidth} = bottomMove.value as HTMLDivElement
  if (state.x <= 0) {
    map.x = speed
  } else if (state.x > 500 - 24) {
    map.x = -speed
  }
  if (state.y <= 0) {
    map.y = speed
  }
  if (state.y + 19 >= offsetTop - offsetHeight &&
      state.y <= offsetTop + offsetHeight &&
      state.x + 24 >= offsetLeft &&
      state.x < offsetLeft + offsetWidth) {
    map.y = -speed
  }
  if (state.y > 300) {
    clearInterval(timer)
    strFun("游戏结束")
  }
  Array.from(state.arr).forEach((item, index) => {
    const {
      offsetLeft,
      offsetTop,
      offsetWidth,
      offsetHeight
    } = brickRef.value[index] as HTMLDivElement
    if (state.x + 24 > offsetLeft
        && state.x < offsetLeft + offsetWidth
        && state.y + 24 > offsetTop
        && state.y < offsetTop + offsetHeight) {
      if (!state.arr[index].active) {
        state.score += 1
      }
      state.arr[index].active = true
    }
  })
  // 游戏通关
  if (Array.from(state.arr).every(item => item.active)) {
    clearInterval(timer)
    strFun("挑战成功")
  }
  state.x = state.x += map.x
  state.y = state.y += map.y
}

// 方块左移
const moveLeft = () => {
  state.mx -= 100
  state.mx = state.mx < 0 ? 0 : state.mx
}
// 方块右移
const moveRight = () => {
  state.mx += 100
  state.mx = state.mx > 400 ? 400 : state.mx
}
// 暂停游戏
const stop = () => {
  clearInterval(timer)
}
// 开始游戏
const start = () => {
  timer = setInterval(moveBall, 20)
}
</script>

<style lang="scss">
/*整个界面*/
.panel {
  width: 100vw;
  height: 100vh;
  background-color: #f3d19e;
  text-align: center;
  /*隐藏的input*/
  .myInput {
    width: 0;
    height: 0;
    opacity: 0;
  }

  /*游戏面板*/
  .box {
    justify-content: center;
    width: 500px;
    height: 300px;
    margin: 0 auto;
    position: relative;
    border-radius: 8px;
    border: 8px solid #b88230;
    background-color: #CDD0D6;
    overflow: hidden;
    /*挑战成功的字眼*/
    .str {
      font-family: fangsong;
      font-size: 50px;
      -webkit-text-stroke: 2px #F56C6C;
      color: red;
    }

    /*小球*/
    .ball {
      width: 24px;
      height: 24px;
      background-image: radial-gradient(circle, red, #c45656, #f89898);
      border-radius: 50%;
      position: absolute;
    }

    /*砖块区域*/
    .brickBox {
      display: flex;
      flex-wrap: wrap;
      /*单个砖块*/
      .brick {
        width: 48px;
        height: 20px;
        margin: 1px;
        border-radius: 5px;
        transition-duration: 100ms;
        transition-timing-function: ease-in;
        /*第(除以3余0)个砖块*/
        &:nth-child(3n+0) {
          background: linear-gradient(#f89898, #337ecc);
        }

        /*第(除以3余1)个砖块*/
        &:nth-child(3n+1) {
          background: linear-gradient(#95d475, #337ecc);
        }

        /*第(除以3余2)个砖块*/
        &:nth-child(3n+2) {
          background: linear-gradient(#eebe77, #529b2e);
        }
      }
    }

    /*挡板*/
    .bottomMove {
      width: 100px;
      height: 6px;
      background: #529b2e;
      position: absolute;
      transition-duration: 100ms;
      transition-timing-function: ease-out;
    }
  }
}
</style>