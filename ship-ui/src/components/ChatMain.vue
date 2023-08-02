<template>
  <div ref="innerRef" style="overflow: auto;height: 100%" @click="rollBottom">
    <!--信息主体-->
    <div v-for="m in msgList" :key="m.msgId" :class="{'right-align':m.isMine}" class="msg-card">
      <el-avatar :src="'/api/common/download?name='+m.rolePhoto"/>
      <div class="msg">
        <span>{{ m.roleName }}&nbsp;{{ getDisplayTime(m.createTime) }}</span>
        <div :class="m.isMine?'bg-success':'bg-light'" class="msgText">
          <el-image v-if="m.isImg" :src="'/api/common/downChat?name='+m.msgContent" fit="cover"/>
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
import {MsgVo} from "@/model/VO/MsgVo";
import {onBeforeUpdate, onMounted, ref} from "vue";
import {getDisplayTime} from "@/utils/TimeUtil";

const prop = defineProps<{
  msgList: MsgVo[]
}>()

// 滚动条到底
const innerRef = ref<HTMLDivElement>()
const rollBottom = () => {
  setTimeout(() => {
    innerRef.value!.scrollTop = innerRef.value!.scrollHeight
  }, 300)
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
