<template>
  <div class="base-page">
    <!-- 导航栏-->
    <PageHead>医生详情</PageHead>
    <MyRow2>
      <img :src="'/api/common/download?name='+doctorDetail.doctorPhoto" alt="头像" class="my-avatar"/>
      <ul>
        <li><span style="font-weight: bolder">{{ doctorDetail.doctorName }}</span></li>
        <li>{{ doctorDetail.username }}</li>
      </ul>
      <ul class="hide-narrow">
        <li>{{ getAge(doctorDetail.doctorAge) }}岁&nbsp;&nbsp;&nbsp;{{ doctorDetail.doctorGender ? "男" : "女" }}</li>
        <li>{{ getJob(doctorDetail.authLv) }}&nbsp;&nbsp;&nbsp;{{ doctorDetail.departmentName }}</li>
        <li>{{ doctorDetail.doctorTel }}</li>
        <li>{{ doctorDetail.doctorInfo }}</li>
        <li>
          <router-link :to="'/reception/chat/'+doctorDetail.doctorId">
            <el-button type="primary">咨询</el-button>
          </router-link>
        </li>
      </ul>
      <template #right>
        <table class="appoint">
          <thead>
          <tr>
            <th>挂号人</th>
            <th>宠物</th>
            <th>描述</th>
            <th>时间</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="a in appointDetail" :key="a.appointmentId">
            <td>{{ a.clientName }}</td>
            <td>{{ a.petName }}</td>
            <td>{{ a.appointmentInfo }}</td>
            <td>{{ moveT(a.appointmentDate) }}</td>
          </tr>
          </tbody>
        </table>
      </template>
    </MyRow2>
  </div>
</template>

<script lang="ts" setup>
import PageHead from "@/components/row/PageHead.vue";
import {getAge, moveT} from "@/utils/TimeUtil";
import {getJob} from "@/utils/JobUtil";
import {DoctorDto} from "@/model/DO/DoctorDto";
import {AppointDto} from "@/model/DO/AppointDto";
import {onMounted, ref} from "vue";
import {reqDoctorOne} from "@/request/DoctorApi";
import {reqDoctorOneA} from "@/request/AppointApi";
import MyRow2 from "@/components/row/MyRow2.vue";
// 如此获取传参
const props = defineProps<{
  doctorId: string
}>()
const doctorDetail = ref<DoctorDto>({
  "doctorId": 0,
  "username": "",
  "doctorName": "",
  "doctorGender": true,
  "doctorAge": "",
  "doctorPhoto": "",
  "doctorTel": "",
  "authLv": 0,
  "doctorInfo": "",
  "departmentName": ""
})
const appointDetail = ref<AppointDto[]>([]);
onMounted(() => {
  reqDoctorOne(parseInt(props.doctorId)).then(res => {
    doctorDetail.value = res
  })
  reqDoctorOneA(parseInt(props.doctorId)).then(res => {
    appointDetail.value = res
  })
})
</script>

<style lang="scss" scoped>
/*响应式*/
@media (min-width: 768px) {
  .base-page {
    width: 70vw;
    left: 15vw;

    img.my-avatar {
      border-radius: 50%;
      margin-top: 10px;
      width: 128px;
      height: 128px;
    }
  }
}

@media (max-width: 768px) {
  .base-page {
    width: 80vw;
    left: 10vw;

    img.my-avatar {
      border-radius: 50%;
      width: 64px;
      height: 64px;
    }

    .hide-narrow {
      display: none;
    }
  }
}


/*设置大致布局*/
.base-page {
  position: absolute;
  top: 15vh;
  background-color: whitesmoke;
  box-shadow: 5px 5px 10px 0 rgba(0, 0, 0, 0.5);
  padding: 16px;
  border-radius: 12px;
  /*医生卡片的信息*/
  ul {
    list-style: none;
    margin-left: -24px;
  }
}

/* 挂号的表格 */
table.appoint {
  width: 100%;

  thead {
    background-color: lightblue;
  }
}
</style>