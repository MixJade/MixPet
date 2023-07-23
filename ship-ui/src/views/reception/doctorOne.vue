<template>
  <el-container class="base-page">
    <!-- 导航栏-->
    <el-header>
      <PageHead>医生详情</PageHead>
    </el-header>
    <el-main>
      <el-row>
        <!-- 左边div：医生卡片-->
        <el-col :xs="24" :sm="10" :md="8">
          <div class="left-div">
            <el-avatar :src="'/api/common/download?name='+doctorDetail.doctorPhoto"/>
            <table class="my-show-tab">
              <tr>
                <td colspan="2"><span style="font-weight: bolder">{{ doctorDetail.doctorName }}</span></td>
              </tr>
              <tr>
                <td colspan="2">工号：{{ doctorDetail.doctorCode }}</td>
              </tr>
              <tr class="hide-narrow">
                <td class="right-align">{{ getAge(doctorDetail.doctorAge) }}岁&nbsp;</td>
                <td class="left-align">&nbsp;{{ doctorDetail.doctorGender ? "男" : "女" }}</td>
              </tr>
              <tr class="hide-narrow">
                <td class="right-align">{{ doctorDetail.doctorJob }}&nbsp;</td>
                <td class="left-align">&nbsp;{{ doctorDetail.departmentName }}</td>
              </tr>
              <tr class="hide-narrow">
                <td colspan="2">{{ doctorDetail.doctorTel }}</td>
              </tr>
              <tr class="hide-narrow">
                <td colspan="2">{{ doctorDetail.doctorInfo }}</td>
              </tr>
              <tr>
                <td colspan="2">
                  <el-button type="primary" @click="this.$router.push('/reception/chat/'+doctorDetail.doctorId)">咨询
                  </el-button>
                </td>
              </tr>
            </table>
          </div>
        </el-col>
        <!-- 右边div，挂号信息-->
        <el-col :xs="24" :sm="14" :md="16" style="text-align: center">
          <el-table :data="appointDetail" height="300px" stripe style="width: 100%">
            <el-table-column prop="clientName" label="挂号人"/>
            <el-table-column prop="petName" label="宠物"/>
            <el-table-column prop="appointmentInfo" label="描述"/>
            <el-table-column prop="appointmentDate" label="时间" width="180px"/>
          </el-table>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import PageHead from "@/components/PageHead.vue";
import {getAge, moveT} from "@/utils/TimeUtil";
import {DoctorDto} from "@/modal/DO/DoctorDto";
import {AppointDto} from "@/modal/DO/AppointDto";
import {onMounted, ref} from "vue";
import {reqDoctorOne} from "@/request/DoctorApi";
import {reqDoctorOneA} from "@/request/AppointApi";

// 如此获取传参
const props = defineProps<{
  doctorId: Number
}>()
const doctorDetail = ref<DoctorDto>({
  "doctorId": 0,
  "doctorCode": "",
  "doctorName": "",
  "doctorGender": true,
  "doctorAge": "",
  "doctorPhoto": "",
  "doctorTel": "",
  "doctorJob": "",
  "doctorInfo": "",
  "departmentName": ""
})
const appointDetail = ref<AppointDto[]>([]);
for (let i = 0; i < appointDetail.value.length; i++) {
  appointDetail[i].appointmentDate = moveT(appointDetail[i].appointmentDate)
}
onMounted(() => {
  reqDoctorOne(props.doctorId).then(res => {
    doctorDetail.value = res
  })
  reqDoctorOneA(props.doctorId).then(res => {
    appointDetail.value = res
  })
})
</script>

<style scoped lang="scss">
/*响应式*/
@media (min-width: 768px) {
  .base-page {
    width: 70vw;
    left: 15vw;

    .el-avatar {
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

    .el-avatar {
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

  .el-header {
    height: 5vh;
  }

  .left-div {
    text-align: center;
    /*医生卡片的表格*/
    .my-show-tab {
      text-align: center;
      width: 100%;

      .right-align {
        text-align: right;
      }

      .left-align {
        text-align: left;
      }
    }
  }
}
</style>