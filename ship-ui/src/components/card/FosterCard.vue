<template>
  <MyRow>
    <div v-for="pet in cardList"
         :key="pet.fosterId"
         class="card">
      <div class="card">
        <img :src="'/api/common/download?name='+pet.petPhoto" alt="头像">
        <div class="card__content">
          <p class="card__title">{{ pet.petName }}</p>
          <div class="card__description">
            <div>
              {{ getAge(pet.petAge) }}岁&nbsp;&nbsp;
              {{ pet.petSex ? "公" : "母" }}&nbsp;&nbsp;
              {{ pet.petVariety }}
            </div>
            <div><span>{{ pet.petInfo }}</span></div>
            <div>备注：<span>{{ pet.fosterInfo }}</span></div>
            <div>寄养人：<span>{{ pet.clientName }}</span></div>
            <div>到期时间：<span>{{ pet.fosterTerm }}</span></div>
          </div>
        </div>
      </div>
    </div>
  </MyRow>
</template>

<script lang="ts" setup>
/*=====前台的寄养卡片批量展示=====*/
import {getAge} from "@/utils/TimeUtil";
import {FosterCardDto} from "@/model/DO/FosterCardDto";
import MyRow from "@/components/show/MyRow.vue";

// 接收参数：宠物卡片
defineProps<{
  readonly cardList: FosterCardDto[]
}>()
</script>

<style lang="scss" scoped>

.card {
  position: relative;
  width: 370px;
  max-width: 90vw;
  height: 180px;
  box-sizing: border-box;
  background-color: #FAFAFA;
  border-radius: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  perspective: 1000px;
  box-shadow: 5px 5px 10px 0 rgba(0, 0, 0, 0.3);
  transition: all 0.6s cubic-bezier(0.175, 0.885, 0.32, 1.275);
  -webkit-user-select: none;
  user-select: none;

  &:hover {
    transform: scale(1.05);
    box-shadow: 5px 5px 15px #eebe77;

    .card__content {
      transform: rotateX(0deg);
    }

    img {
      transform: scale(0);
    }
  }

  img {
    /* 头像 */
    width: 128px;
    height: 128px;
    border-radius: 50%;
    border: 3px solid;
    border-color: #f89898 #eebe77 #79bbff #95d475;
    transition: all 0.6s cubic-bezier(0.175, 0.885, 0.32, 1.275);
  }

  .card__content {
    position: absolute;
    inset: 0;
    padding: 20px;
    box-sizing: border-box;
    transform: rotateX(-90deg);
    transform-origin: bottom;
    transition: all 0.6s cubic-bezier(0.175, 0.885, 0.32, 1.275);
  }

  .card__title {
    margin: 0;
    font-size: 24px;
    color: #333;
    font-weight: 700;
  }

  .card__description {
    margin: 10px 0 0;
    font-size: 14px;
    line-height: 1.4;

    > div {
      /* 卡片中的五行 */
      height: 23px;
      width: 100%;
      overflow-x: hidden;
      white-space: nowrap;

      span {
        color: #E6A23C;

        &:hover {
          color: #b88230;
        }
      }
    }
  }
}
</style>