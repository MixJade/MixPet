<template>
  <div class="vue-foot">
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
  </div>
</template>

<script lang="ts" setup>
</script>

<style lang="scss" scoped>
.vue-foot {
  width: 100%;
  text-align: center;
  /* 简介外包装 */
  .features {
    border-top: 1px solid #eaecef;
    transition: border-color .3s ease;;
    padding: 1.2rem 0;
    margin-top: 2.5rem;
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
  padding: 2.5rem;
  border-top: 1px solid #eaecef;
  text-align: center;
  color: #3a5169;
  transition: border-color .3s ease;
}


</style>