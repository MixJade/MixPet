<template>
  <dialog ref="dialog" :class="{ shake: disabled }">
    <header>
      <h2>{{ tit }}</h2>
    </header>
    <main>
      {{ content }}
    </main>
    <footer>
      点击按钮确认
      <button type="button" @click="closeMe">
        <svg height="24" width="24" fill="#FFFFFF" viewBox="0 0 24 24" id="sparkle">
          <path
              d="M10,21.2,6.7,14.7.2,11.5,6.7,8.2,10,1.7l3.2,6.4L19.7,11.5l-6.4,3.2ZM18,21l1.5,3L21,21l3-1.5L21,18l-1.5-3L18,18l-3,1.5ZM19.3,4.6,20.5,7l1.1-2.3L24,3.5,21.6,2.3,20.5,0,19.3,2.3,17,3.5Z"></path>
        </svg>
        <span id="text">确认</span>
      </button>
    </footer>
  </dialog>
</template>

<script lang="ts" setup>
import {ref} from "vue";

defineProps<{
  tit: string
  content: string
}>()
const dialog = ref<HTMLDialogElement>()
const disabled = ref(false)
const showMe = () => {
  dialog.value?.showModal()
  disabled.value = true
  setTimeout(() => {
    disabled.value = false
  }, 1500)
}
const closeMe = () => dialog.value?.close()
defineExpose({showMe})

</script>

<style lang="scss" scoped>
dialog {
  border-radius: 20px;
  border: 6px solid;
  border-color: #337ecc #b88230 #529b2e #c45656;
  box-shadow: 0 0 180px 0 #fcd611;
  font-size: 16px;
  line-height: 2; /* 2倍行高 */
  background-color: #EBEDF0;
  max-width: 90%;
  max-height: 80%;
  -webkit-user-select: none;
  user-select: none;

  header {
    text-align: center;
  }

  main {
    white-space: pre-wrap;
    word-break: break-all;
  }

  footer {
    display: flex;
    justify-content: right;
    padding-top: 8px;

    button {
      $back-col: #3b91ea;
      $txt-act-col: #FFFF00;
      display: flex;
      justify-content: center;
      align-items: center;
      gap: 8px;
      width: 100px;
      height: 40px;
      border: none;
      border-radius: 24px;
      background: $back-col;
      cursor: pointer;
      transition: all 450ms ease-in-out;
      /* svg图片 */
      #sparkle {
        fill: #FFD700;
        transition: all 800ms ease;
      }

      /* 按钮文本 */
      #text {
        font-weight: 600;
        color: white;
        font-size: medium;
      }

      &:hover {
        background: linear-gradient(0deg, #3CB371, #4a9cf1);
        box-shadow: inset 0 1px 0 0 rgba(255, 255, 255, 0.4),
        inset 0px -4px 0px 0px rgba(0, 0, 0, 0.2),
        0px 0px 180px 0px #fcd611;
        transform: translateY(-2px);

        #text {
          color: $txt-act-col;
        }

        #sparkle {
          fill: $txt-act-col;
          transform: scale(1.2);
        }
      }
    }
  }
}

/* 动画类 */
.shake {
  animation: shake 0.82s cubic-bezier(0.36, 0.07, 0.19, 0.97) both;
  transform: translate3d(0, 0, 0);
  @keyframes shake {
    10%, 90% {
      transform: translate3d(-1px, 0, 0);
    }
    20%, 80% {
      transform: translate3d(2px, 0, 0);
    }
    30%, 50%, 70% {
      transform: translate3d(-4px, 0, 0);
    }
    40%, 60% {
      transform: translate3d(4px, 0, 0);
    }
  }
}
</style>
