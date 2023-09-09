<template>
  <div ref="innerRef" style="overflow: auto;height: 100%" @click="rollBottom">
    <!--信息主体-->
    <div v-for="m in msgList" :class="{'right-align':m.isMine}" class="msg-card">
      <MyAvatar :name="m.rolePhoto"/>
      <div class="msg">
        <span>{{ m.roleName }}&nbsp;{{ getDisplayTime(m.createTime) }}</span>
        <div :class="m.isMine?'bg-success':'bg-light'" class="msgText">
          <img v-if="m.isImg" :src="'/api/common/downChat?name='+m.msgContent" alt="聊天图片"/>
          <div v-else style="font-family: serif;">
            {{ m.msgContent }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
/*=====前台的聊天记录展示，会自动将滚动条划到底=====*/
import {MsgDo} from "@/model/DO/MsgDo";
import {onBeforeUpdate, onMounted, ref} from "vue";
import {getDisplayTime} from "@/utils/TimeUtil";
import MyAvatar from "@/components/show/MyAvatar.vue";

defineProps<{
  msgList: MsgDo[]
}>()

// 滚动条到底
const innerRef = ref<HTMLDivElement>()
const rollBottom = () => {
  setTimeout(() => {
    innerRef.value!.scrollTop = innerRef.value!.scrollHeight
  }, 500)
}
onBeforeUpdate(() => {
  rollBottom()
})
onMounted(() => {
  rollBottom()
})
</script>

<style lang="scss" scoped>
/*信息外壳*/
.msg-card {
  display: flex;
  padding-bottom: 5px;
  /*消息卡片*/
  .msg {
    max-width: 50%;
    /*用户名与时间*/
    span {
      margin-left: 10px;
      margin-right: 10px;
      font-size: small;
    }

    /*消息框*/
    .msgText {
      padding: 5px;
      margin: 5px;
      border-radius: 8px;
      text-align: left;
      /* 聊天图片 */
      img {
        width: 100%;
        height: auto;
        object-fit: cover;
      }
    }

    /*用户信息背景*/
    .bg-success {
      background-color: #67C23A;
    }

    /*医生信息背景*/
    .bg-light {
      background-color: #cccccc;
    }
  }
}

/*用户信息靠右*/
.right-align {
  /*flex布局的镜像对称之意*/
  flex-direction: row-reverse;
  text-align: right;
}
</style>
