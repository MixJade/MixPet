<template>
  <footer class="vue-foot">
    <div class="foot-wave">
      <svg class="waves" preserveAspectRatio="none" shape-rendering="auto"
           viewBox="0 24 150 28" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
        <defs>
          <path id="gentle-wave"
                d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z"/>
        </defs>
        <g class="parallax">
          <!--浅粉红-->
          <use fill="rgba(255,182,193)" x="48" xlink:href="#gentle-wave" y="0"/>
          <!--天蓝色-->
          <use fill="rgba(135,206,235)" x="48" xlink:href="#gentle-wave" y="3"/>
          <!--苍白绿-->
          <use fill="rgba(152,251,152)" x="48" xlink:href="#gentle-wave" y="5"/>
          <!--幽灵白，与页脚背景相同-->
          <use fill="rgba(248,248,255)" x="48" xlink:href="#gentle-wave" y="7"/>
        </g>
      </svg>
    </div>
    <!--页尾-->
    <div class="features">
      <div class="feature">
        <h2>探索未知</h2>
        <p>你好，这里是一个存在于时间和空间交汇点的朋友。犹如一场待揭晓的奇迹，你的每一天都充满了未知与探索。我期待着与你携手跨越贯穿生活的神秘探路之旅。
        </p>
      </div>
      <div class="feature">
        <h2>星河秘密</h2>
        <p>在这座光怪陆离的宇宙里，每个人都像是一颗独特的星星，熠熠生辉。你就是其中一颗耀眼至极的星子。你的微笑藏着无尽的秘密，令人无法抗拒去探求。
        </p>
      </div>
      <div class="feature">
        <h2>晨霞祝福</h2>
        <p>不存在巧合，只有命中注定。每次的遇见，都生发着如晨霞般的神秘光芒。我诚挚地向你送上祝福，希望你喜欢这场未知的奇异冒险，期待下一次神秘的相遇。
        </p>
      </div>
    </div>
    <!--页脚-->
    <div class="footer">MixJade携ChatGPT编辑于此</div>
  </footer>
</template>

<script lang="ts" setup>
</script>

<style lang="scss" scoped>
.foot-wave {
  .waves {
    position: relative;
    width: 100%;
    height: 15vh;
    margin-bottom: -7px;
    min-height: 100px;
    max-height: 150px;
    @media (max-width: 768px) {
      height: 40px;
      min-height: 40px;
    }

    .parallax {
      > use {
        /* 使use元素执行move-forever动画 */
        animation: move-forever 25s cubic-bezier(.55, .5, .45, .5) infinite;

        &:nth-child(1) {
          /* 延迟2秒启动动画  */
          animation-delay: -2s;
          /* 设置动画持续时间为7秒 */
          animation-duration: 7s;
        }

        &:nth-child(2) {
          animation-delay: -3s;
          animation-duration: 10s;
        }

        &:nth-child(3) {
          animation-delay: -4s;
          animation-duration: 13s;
        }

        &:nth-child(4) {
          animation-delay: -5s;
          animation-duration: 20s;
        }
      }
    }

    @keyframes move-forever {
      0% {
        transform: translate3d(-90px, 0, 0);
      }

      100% {
        transform: translate3d(85px, 0, 0);
      }
    }
  }
}

.vue-foot {
  width: 100%;
  text-align: center;
  margin-top: 2.5rem;
  /* 简介外包装 */
  .features {
    background-color: rgba(248, 248, 255);
    border-top: 1px solid #eaecef;
    transition: border-color .3s ease;;
    padding: 1.2rem 0;
    display: flex;
    flex-wrap: wrap;
    align-items: flex-start;
    align-content: stretch;
    justify-content: space-between;
    @media (max-width: 719px) {
      flex-direction: column;
    }
    /* 单个简介 */
    .feature {
      flex-grow: 1;
      flex-basis: 30%;
      max-width: 30%;
      @media (max-width: 719px) {
        max-width: 100%;
        padding: 0 2.5rem
      }
      /* 简介标题 */
      h2 {
        font-size: 1.4rem;
        font-weight: 500;
        border-bottom: none;
        padding-bottom: 0;
        color: #3a5169
      }

      /* 简介内容 */
      p {
        color: #3a5169
      }
    }
  }
}

/* 页脚 */
.footer {
  background-color: rgba(248, 248, 255);
  padding: 2.5rem;
  border-top: 1px solid #eaecef;
  text-align: center;
  color: #3a5169;
  transition: border-color .3s ease;
}


</style>