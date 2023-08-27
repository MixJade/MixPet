<template>
  <div class="chat-panel">
    <PageHead>与{{ roleName }}{{ opType }}的聊天</PageHead>
    <section>
      <aside>
        <h4>当前聊天</h4>
        <ul>
          <li>{{ roleName }}</li>
        </ul>
        <h4 v-if="groupList.length>1">其它{{ opType }}</h4>
        <ul v-if="groupList.length>1">
          <li v-for="gItem in groupList.slice(1)" :key="gItem.roleId"
              @click="cutOtherB(gItem.roleId)">{{ gItem.roleName }}
          </li>
        </ul>
      </aside>
      <div class="chat-right">
        <main>
          <ChatMain :msg-list="msgList"/>
        </main>
        <footer>
          <el-icon size="36">
            <PictureRounded/>
          </el-icon>
          <el-input
              v-model="textarea1"
              autosize
              placeholder="请输入消息"
              type="textarea"
          />
          <el-button :icon="Position" type="success" @click="sendMsgB">发送</el-button>
        </footer>
      </div>
    </section>
  </div>
</template>

<script lang="ts" setup>
/*=====封装的聊天页面，医生与用户只是传递的参数不同=====*/
import {PictureRounded, Position} from "@element-plus/icons-vue"
import PageHead from "@/components/PageHead.vue";
import {ref} from "vue";
import {ChatGroup} from "@/model/VO/ChatGroup";
import {MsgVo} from "@/model/VO/MsgVo";
import ChatMain from "@/components/chat/ChatMain.vue";

defineProps<{
  opType: "用户" | "医生",
  roleName: string,
  groupList: ChatGroup[],
  msgList: MsgVo[]
}>()

// 待发送的消息
const textarea1 = ref("")
const emit = defineEmits<{
  (e: "sendMsg", value: string): void,
  (e: "cutOther", value: number): void
}>()
const sendMsgB = (): void => {
  emit("sendMsg", textarea1.value)
}
const cutOtherB = (roleId: number) => {
  emit("cutOther", roleId)
}
</script>

<style lang="scss" scoped>
/*聊天主体页面*/
.chat-panel {
  width: 98vw;
  height: 88vh;
  /*页头下面*/
  section {
    display: flex;
    height: 100%;

    aside {
      background-color: lightgray;
      width: 15vw;
      @media (max-width: 576px) {
        width: 0;
      }
      /*人员列表*/
      ul {
        list-style: none;
        cursor: pointer;
        /*设置间距*/
        li {
          padding-bottom: 5px;
        }
      }
    }

    /*侧边栏右边*/
    .chat-right {
      width: 100%;
      /*信息展示区域*/
      main {
        box-sizing: border-box;
        padding: 8px;
        height: calc(100% - 50px);
        background-color: whitesmoke;
      }

      /*输入区域*/
      footer {
        box-sizing: border-box;
        padding: 8px;
        display: flex;
        height: 50px;
        background-color: #909399;
      }
    }
  }
}
</style>
