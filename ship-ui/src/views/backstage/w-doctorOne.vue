<template>
  <el-button plain style="position: absolute;margin-left: 24px" type="warning" @click="isShow=!isShow">
    {{ isShow ? "隐藏" : "显示" }}面板
  </el-button>
  <LightBox v-show="isShow">
    <router-link to="/doctorPage">
      <el-button style="position: absolute" type="success">悬壶济世</el-button>
    </router-link>
    <DetailTable
        :role-photo="doctor.doctorPhoto"
        :tab-con='[{tit: "账号", con: doctor.username},
          {tit: "姓名", con: doctor.doctorName},
          {tit: "简介", con: doctor.doctorInfo},
          {tit: "性别", con: doctor.doctorGender ? "男" : "女"},
          {tit: "职位", con: getJob(doctor.authLv)},
          {tit: "科室", con: doctor.departmentName},
          {tit: "联系方式", con: doctor.doctorTel},
          {tit: "年龄", con: getAge(doctor.doctorAge)}]'
        @upPwd="updatePwd"
        @upRole="updateRole"
    />
  </LightBox>
  <!--修改个人资料时的模态框-->
  <el-dialog v-model="modalView" draggable title="修改个人资料" width="60%">
    <!--头像上传框-->
    <UpImg :photoNm="form.doctorPhoto" @upPhoto="changePhoto"/>
    <!--表单-->
    <el-form ref="myFormRef" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="姓名" prop="doctorName">
        <el-input v-model="form.doctorName" clearable placeholder="医生姓名"/>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="form.doctorGender">
          <el-radio :label="true" border>男</el-radio>
          <el-radio :label="false" border>女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="生日" prop="doctorAge">
        <el-date-picker
            v-model="form.doctorAge"
            format="YYYY-MM-DD"
            placeholder="选择出生日期"
            type="date"
            value-format="YYYY-MM-DD"
        />
      </el-form-item>
      <el-form-item label="简介">
        <el-input v-model="form.doctorInfo" clearable placeholder="医生简介"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="modalView = false">取消</el-button>
        <el-button type="primary" @click="formSubmit">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>
  <!--重置密码时的模态框-->
  <el-dialog v-model="pwdModalView" draggable title="重置密码" width="60%">
    <!--表单-->
    <el-form ref="myFormRef2" :model="form2" :rules="rules2" label-width="120px">
      <el-form-item label="验证码" prop="checkCode">
        <el-input v-model="form2.checkCode" clearable placeholder="验证码"/>
        <el-button :disabled="mailBtn.dis" type="primary" @click="sendCheckMail">{{ mailBtn.btnTxt }}</el-button>
      </el-form-item>
      <el-form-item label="新密码" prop="password">
        <el-input v-model="form2.password" clearable placeholder="新密码"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="pwdModalView = false">取消</el-button>
        <el-button type="primary" @click="formSubmit2">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import DetailTable from "@/components/card/DetailTable.vue";
import {getJob} from "@/utils/JobUtil";
import {getAge} from "@/utils/TimeUtil";
import {onMounted, reactive, ref} from "vue";
import {DoctorDto, exampleDoctorOne} from "@/model/DO/DoctorDto";
import {reqDoctorResetPwd, reqDoctorSendMail, reqGetLoginD, reqUpdateLoginD} from "@/request/DoctorApi";
import LightBox from "@/components/card/LightBox.vue";
import {Doctor, exampleDoctor} from "@/model/entiy/Doctor";
import {FormInstance, FormRules} from "element-plus";
import UpImg from "@/components/UpImg.vue";
import {DoctorResetPwdVo} from "@/model/VO/DoctorResetPwdVo";
// 设置展示信息
const doctor = ref<DoctorDto>(exampleDoctorOne())
const isShow = ref<boolean>(true)
// 发起请求
onMounted(() => {
  reqGetLoginD().then(res => {
    doctor.value = res
  })
})

/**
 ┌───────────────────────────────────┐
 │=============修改个人资料============│
 └───────────────────────────────────┘
 */
const modalView = ref(false)
const form = ref<Doctor>(exampleDoctor()) // 空的默认值
const updateRole = () => {
  myFormRef.value?.clearValidate()
  // 赋值，防止响应式引用
  form.value.doctorAge = doctor.value.doctorAge
  form.value.doctorInfo = doctor.value.doctorInfo
  form.value.doctorPhoto = doctor.value.doctorPhoto
  form.value.doctorName = doctor.value.doctorName
  form.value.doctorGender = doctor.value.doctorGender
  // 显示
  modalView.value = true
}

// 表单的数据
const myFormRef = ref<FormInstance>()
// 校验表单并提交
const formSubmit = async (): Promise<void> => {
  await myFormRef.value?.validate((valid: boolean): void => {
    if (valid) {
      // 校验通过，提交
      modalView.value = false
      reqUpdateLoginD(form.value).then(res => {
        if (res.code === 1) location.reload();
      })
    }
  })
}
// 表单校验规则
const rules = ref<FormRules>({
  "doctorName": [
    {required: true, message: '请输入姓名', trigger: 'blur'},
  ],
  "doctorAge": [
    {required: true, message: '请选择出生日期', trigger: 'blur'},
  ],
})
// 头像参数的改变:子传父
const changePhoto = (val: string): void => {
  form.value.doctorPhoto = val
}

/**
 ┌───────────────────────────────────┐
 │=============重置个人密码============│
 └───────────────────────────────────┘
 */
const pwdModalView = ref(false)
const updatePwd = () => {
  pwdModalView.value = true;
  form2.password = "";
  form2.checkCode = "";
}
// 发送验证码的按钮数据
const mailBtn = reactive({btnTxt: "发送验证码", dis: false})
// 发送验证码邮件
const sendCheckMail = () => {
  mailBtn.dis = true;
  let countDown = 30;
  let intVal = setInterval(() => {
    if (countDown === 0) {
      clearInterval(intVal)
      mailBtn.dis = false;
      mailBtn.btnTxt = "发送验证码";
      return
    }
    mailBtn.btnTxt = "等待中(" + countDown + ")";
    countDown--;
  }, 1000)
  reqDoctorSendMail()
}
// 重置密码的数据
const form2 = reactive<DoctorResetPwdVo>({
  checkCode: "",
  password: ""
})
// 校验表单并提交
const myFormRef2 = ref<FormInstance>()
const formSubmit2 = async (): Promise<void> => {
  await myFormRef2.value?.validate((valid: boolean): void => {
    if (valid) {
      // 校验通过，提交
      reqDoctorResetPwd(form2)
      pwdModalView.value = false
    }
  })
}
// 表单校验规则
const rules2 = ref<FormRules>({
  "checkCode": [
    {required: true, message: '请输入验证码', trigger: 'blur'},
  ],
  "password": [
    {required: true, message: '请输入新密码', trigger: 'blur'},
  ],
})
</script>

<style scoped>
</style>
