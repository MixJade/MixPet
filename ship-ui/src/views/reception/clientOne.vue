<template>
  <DetailTable
      v-if="!modalView"
      :role-photo="client.clientPhoto"
      :tab-con='[
        {tit: "账号", con: client.clientUsername},
        {tit: "姓名", con: client.clientName},
        {tit: "性别", con: client.clientGender ? "男" : "女"},
        {tit: "联系方式", con: client.clientTel},
        {tit: "出生日期", con: client.clientAge},
        {tit: "简介", con: client.clientInfo},
      ]'
      @upPwd="updatePwd"
      @upRole="updateRole"
  />
  <!--修改页面-->
  <div v-show="modalView">
    <h2>修改个人资料</h2>
    <!--头像上传框-->
    <UpImg :photoNm="form.clientPhoto" @upPhoto="changePhoto"/>
    <!--表单-->
    <el-form ref="myFormRef" :model="form" :rules="rules" label-width="60px">
      <el-form-item label="姓名" prop="clientName">
        <el-input v-model="form.clientName" clearable placeholder="用户姓名"/>
      </el-form-item>
      <el-form-item label="帐号" prop="clientUsername">
        <el-input v-model="form.clientUsername" clearable placeholder="用户帐号"/>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="form.clientGender">
          <el-radio :label="true" border>男</el-radio>
          <el-radio :label="false" border>女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="生日">
        <el-date-picker
            v-model="form.clientAge"
            format="YYYY-MM-DD"
            placeholder="选择出生日期"
            type="date"
            value-format="YYYY-MM-DD"
        />
      </el-form-item>
      <el-form-item label="简介">
        <el-input v-model="form.clientInfo" clearable placeholder="用户近况"/>
      </el-form-item>
    </el-form>
    <span class="dialog-footer">
        <el-button @click="modalView = false">取消</el-button>
        <el-button type="primary" @click="formSubmit">
          确认
        </el-button>
      </span>
  </div>
</template>

<script lang="ts" setup>
import {Client, exampleClient} from "@/model/entiy/Client";
import DetailTable from "@/components/card/DetailTable.vue";
import {onMounted, reactive, ref} from "vue";
import {reqLoginUser} from "@/request/PowerApi";
import UpImg from "@/components/UpImg.vue";
import {FormInstance, FormRules} from "element-plus";
import {reqUpdateClientSelf} from "@/request/ClientApi";
// 设置展示信息
const client = ref<Client>(exampleClient())
// 获取用户信息
onMounted(() => {
  reqLoginUser().then(res => {
    client.value = res
  })
})

const updatePwd = () => {
  console.log("修改密码")
}
/**
 ┌───────────────────────────────────┐
 │=============修改个人资料============│
 └───────────────────────────────────┘
 */
// 模态框
const modalView = ref(false)
const form = reactive<Client>(exampleClient()) // 空的默认值
// 修改时展示模态框
const updateRole = () => {
  myFormRef.value?.clearValidate()
  // 赋值，防止响应式引用
  form.clientName = client.value.clientName
  form.clientUsername = client.value.clientUsername
  form.clientGender = client.value.clientGender
  form.clientAge = client.value.clientAge
  form.clientInfo = client.value.clientInfo
  form.clientPhoto = client.value.clientPhoto
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
      reqUpdateClientSelf(form).then(res => {
        if (res.code === 1) location.reload()
      })
    }
  })
}
// 表单校验规则
const rules = reactive<FormRules>({
  "clientName": [
    {required: true, message: '请输入姓名', trigger: 'blur'},
  ],
  "clientUsername": [
    {required: true, message: '请填写用户名', trigger: 'blur'},
  ]
})
// 头像参数的改变:子传父
const changePhoto = (val: string): void => {
  form.clientPhoto = val
}
</script>

<style lang="scss" scoped>
.el-form-item {
  width: 50%;
}
</style>
