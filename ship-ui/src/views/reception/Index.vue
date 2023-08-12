<template>
  <el-container>
    <el-header>
      <!-- 导航栏-->
      <el-menu
          :ellipsis="false"
          background-color="#545c64"
          mode="horizontal"
          text-color="#fff"
      >
        <el-menu-item index="1">
          <img alt="商标" height="30" src="/mia.svg" width="30">
          <router-link to="reception/clientCenter">宠物医院</router-link>
        </el-menu-item>
        <div class="flex-grow"/>
        <el-menu-item index="2" @click="openDialog(notices[0])">
          <el-icon>
            <DataBoard/>
          </el-icon>
          网站公告
        </el-menu-item>
        <el-menu-item index="3">
          <el-icon>
            <Phone/>
          </el-icon>
          <router-link to="reception/chat">咨询医生</router-link>
        </el-menu-item>
        <el-sub-menu v-if="isLogin" index="4">
          <template #title>
            <el-icon>
              <User/>
            </el-icon>
            个人中心
          </template>
          <el-menu-item index="4-1">
            <el-icon>
              <EditPen/>
            </el-icon>
            <router-link to="reception/clientCenter">个人资料</router-link>
          </el-menu-item>
          <el-menu-item index="4-2">
            <el-icon>
              <Football/>
            </el-icon>
            <router-link to="reception/clientCenter/clientPet">宠物信息</router-link>
          </el-menu-item>
          <el-menu-item index="4-3">
            <el-icon>
              <Tickets/>
            </el-icon>
            <router-link to="reception/clientCenter/clientAppoint">挂号信息</router-link>
          </el-menu-item>
          <el-menu-item index="4-4" style="color: #F56C6C" @click="myLogout">
            <el-icon>
              <SwitchButton/>
            </el-icon>
            退出登录
          </el-menu-item>
        </el-sub-menu>
        <el-menu-item v-else index="5" style="color: #ffc107">
          <el-icon>
            <SwitchButton/>
          </el-icon>
          <router-link to="/">前往登录</router-link>
        </el-menu-item>
      </el-menu>
    </el-header>
    <el-main>
      <!--轮播图-->
      <el-carousel :interval="4000" height="200px" type="card">
        <el-carousel-item v-for="item in lun" :key="item">
          <el-image :src="item.image" alt="轮播图" fit="scale-down"/>
          <div class="carousel-caption">
            <h5>{{ item.tit }}</h5>
            <p>{{ item.text }}</p>
          </div>
        </el-carousel-item>
      </el-carousel>
      <!-- 公告栏-->
      <h2>网站公告</h2>
      <p>网站的最新公告</p>
      <el-row :gutter="12">
        <el-col v-for="notice in notices" :key="notice.noticeId" :md="8" :sm="12" :xs="24">
          <el-card shadow="hover" @click="openDialog(notice)">{{ notice.noticeTitle }}</el-card>
        </el-col>
      </el-row>
      <!--公告的对话框-->
      <el-dialog
          v-model="showDialog"
          :title="noticeDialog.noticeTitle"
          width="50%"
      >
        <div class="textNotice">{{ noticeDialog.textNotice }}</div>
        <template #footer>
            <span class="dialog-footer">
              <el-button type="primary" @click="showDialog = false">
                确认
              </el-button>
            </span>
        </template>
      </el-dialog>
      <!-- 用户卡片-->
      <h2>用户入口</h2>
      <p>一些与用户相关的功能入口，也可以前往中心查看。
        <router-link class="my-warn" to="reception/clientCenter">点击跳转</router-link>
      </p>
      <el-row :gutter="12">
        <el-col v-for="card in userCard" :md="8" :sm="12" :xs="24">
          <el-card class="box-card">
            <template #header>
              <div class="card-header">
                <span>{{ card.tit }}</span>
                <router-link :to="card.routerPath">
                  <el-button :type="card.btnType">{{ card.btnText }}</el-button>
                </router-link>
              </div>
            </template>
            <div>
              {{ card.describe }}
            </div>
          </el-card>
        </el-col>
      </el-row>
      <!-- 待领养宠物-->
      <h2>待领养宠物</h2>
      <p>它们等待一个温暖的家。
        <router-link class="my-suc" to="/reception/petSee">查看全部</router-link>
      </p>
      <PetCard :card-list="petCardTxt.records"/>
      <!-- 医生卡片-->
      <h2>医生展示</h2>
      <p>我们拥有虚拟的医生。
        <router-link class="my-pri" to="/reception/doctorSee">查看全部</router-link>
      </p>
      <DoctorCard :card-list="doctorCardTxt.records"/>
      <!-- 寄养卡片-->
      <h2>寄养宠物展示</h2>
      <p>我们提供寄养服务。
        <router-link class="my-warn" to="/reception/fosterSee">查看全部</router-link>
      </p>
      <FosterCard :card-list="fosterCardTxt.records"/>
      <!-- 折叠筐-->
      <h2>小小知识</h2>
      <el-collapse accordion>
        <el-collapse-item v-for="know in knowledgeList" :title="know.tit">
          <div>{{ know.txt }}</div>
        </el-collapse-item>
      </el-collapse>
    </el-main>
  </el-container>
</template>

<script lang="ts" setup>
import {DataBoard, EditPen, Football, Phone, SwitchButton, Tickets, User} from "@element-plus/icons-vue"
import {onBeforeMount, reactive, ref} from "vue";
import {Pet} from "@/model/entiy/Pet";
import {Page} from "@/model/DO/Page";
import PetCard from "@/components/PetCard.vue";
import DoctorCard from "@/components/DoctorCard.vue";
import FosterCard from "@/components/FosterCard.vue";
import {DoctorDto} from "@/model/DO/DoctorDto";
import {FosterCardDto} from "@/model/DO/FosterCardDto";
import {NoticeDto} from "@/model/DO/NoticeDto";
import {reqLoginUser, reqLogout} from "@/request/PowerApi";
import {reqFourDoctor} from "@/request/DoctorApi";
import {reqFourPet} from "@/request/PetApi";
import {reqFosterPet} from "@/request/FosterApi";
import {reqFourNotice} from "@/request/NoticeApi";
import {useRouter} from "vue-router";

// 判断是否登录
const isLogin = ref<boolean>(false)
onBeforeMount(() => {
  reqFourDoctor().then(res => {
    doctorCardTxt.records = res.records
  })
  reqFourPet().then(res => {
    petCardTxt.records = res.records
  })
  reqFosterPet(3).then(res => {
    fosterCardTxt.records = res.records
  })
  reqFourNotice().then(res => {
    notices.value = res
  })
  reqLoginUser().then(res => {
    isLogin.value = (res.clientId != null)
  })
})

// 主页轮播图
interface Lun {
  image: string;
  tit: string;
  text: string;
}// 轮播图
const lun: Lun[] = [
  {
    image: "/picture/lun-1.jpg",
    tit: "医院宗旨",
    text: "致力于为每一只宠物提供最优质的医疗服务，关注宠物健康与福利。"
  },
  {
    image: "/picture/lun-2.jpg",
    tit: "服务理念",
    text: "始终坚持“宠爱无限，服务永远“五心”服务----用心、细心、贴心的服务，让您的爱宠舒心，让您放心”"
  },
  {
    image: "/picture/lun-3.jpg",
    tit: "狗狗会“察言观色”",
    text: "人类对于狗狗的训练行为，其实并不是狗狗能“听懂”人类语言，而是会“察言观色”，根据一些行为指示来完成某种动作，进而牢记心中。"
  }
]

// 知识
interface knowledge {
  tit: string,
  txt: string
}// 知识样例
const knowledgeList: knowledge[] = [
  {tit: "狗子总是晚上叫怎么办？", txt: "多半是发情期，送来进行绝育手术"},
  {tit: "猫猫总是到处乱抓怎么办？", txt: "多半是发情期，送来进行绝育手术"},
  {tit: "为什么我养的羊不产奶", txt: "你养的是公羊"},
]

// 公告栏
const notices = ref<NoticeDto[]>([])
// 公告弹出框
const showDialog = ref(false);
const noticeDialog = reactive({noticeTitle: "标题", textNotice: "文本内容"})
const openDialog = (notice: NoticeDto) => {
  noticeDialog.noticeTitle = notice.noticeTitle
  noticeDialog.textNotice = notice.textNotice
  showDialog.value = true
}

// 常量：用户卡片
interface UserCard {
  tit: string;
  describe: string;
  btnType: "primary" | "success" | "info";
  btnText: string;
  routerPath: string;
}// 用户卡片的配置信息
const userCard: UserCard[] = [
  {
    "tit": "挂号信息",
    "describe": "在此查看自己的历史挂号",
    "btnType": "primary",
    "btnText": "查看历史挂号",
    "routerPath": "/reception/clientCenter/clientAppoint"
  }, {
    "tit": "宠物信息",
    "describe": "查看自己的名下宠物",
    "btnType": "success",
    "btnText": "查看当前宠物",
    "routerPath": "/reception/clientCenter/clientPet"
  }, {
    "tit": "咨询医生",
    "describe": "向医生发起咨询",
    "btnType": "info",
    "btnText": "咨询医生",
    "routerPath": "/reception/chat"
  },
]

// 宠物的卡片信息
const petCardTxt: Page<Pet> = reactive({records: [], total: 4})
// 医生的卡片信息
const doctorCardTxt: Page<DoctorDto> = reactive({records: [], total: 4})
// 寄养宠物的卡片信息
const fosterCardTxt: Page<FosterCardDto> = reactive({records: [], total: 3})
// 退出登录
const router = useRouter()
const myLogout = () => {
  reqLogout().then(res => {
    if (res.code === 204) router.push("/")
  })
}
</script>
<style lang="scss" scoped>
.el-container {
  background-color: #e9e9f5;
  /*将导航栏除Logo以外放在旁边*/
  .flex-grow {
    flex-grow: 1;
  }

  /*主要内容*/
  .el-main {
    /*轮播图的文本*/
    .carousel-caption {
      background-color: black;
      opacity: 0.5;
      position: absolute;
      right: 15%;
      bottom: 1.25rem;
      left: 15%;
      color: #fff;
      text-align: center;
    }

    /*小标题与介绍文字*/
    h2, p {
      text-align: center;
    }

    /*公告换行*/
    .textNotice {
      white-space: pre-wrap;
      word-break: break-all;
    }

    /* 卡片 */
    .card-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    /* 折叠框 */
    .el-collapse {
      box-shadow: 5px 5px 10px 0 rgba(0, 0, 0, 0.5);
      width: 60%;
      margin-left: 20%;;
      /* 公告栏标题 */
      :deep(.el-collapse-item__header) {
        font-size: medium;
        padding-left: 30px;
      }
    }
  }
}
</style>