<template>
  <!--操作框-->
  <BackOpCol role="用户" @addRole="addRoleB" @delBatch="delBatchB" @query="sendQuery">
    <el-input v-model="qp.clientName" placeholder="用户姓名" size="large"/>
  </BackOpCol>

  <!--列表展示-->
  <el-table :data="clientList.records"
            max-height="80vh"
            row-key="clientId"
            stripe
            style="width: 100%"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column label="图像">
      <template #default="scope">
        <el-avatar :src="'/api/common/download?name='+scope.row.clientPhoto"/>
      </template>
    </el-table-column>
    <el-table-column label="用户名" prop="clientName"/>
    <el-table-column label="账号" prop="clientUsername"/>
    <el-table-column label="性别">
      <template #default="scope">
        <TagSex :sex="scope.row.clientGender"/>
      </template>
    </el-table-column>
    <el-table-column label="年龄" prop="clientAge" sortable>
      <template #default="scope">{{ getAge(scope.row.clientAge) }}岁</template>
    </el-table-column>
    <el-table-column label="联系方式" prop="clientTel"/>
    <el-table-column label="简介" prop="clientInfo"/>
    <el-table-column fixed="right" label="操作">
      <template #default="scope">
        <el-button-group>
          <el-button :icon="Edit" circle type="warning" @click="showDialog(scope.row)"/>
          <el-button :icon="Delete" circle type="danger" @click="delOne(scope.row.clientId)"/>
        </el-button-group>
      </template>
    </el-table-column>
  </el-table>
  <!--分页条-->
  <BackPage :total="clientList.total" @changePu="changePuB"/>
  <!--修改、新增时的模态框-->
  <el-dialog v-model="modalView" :title="modalTit" draggable width="60%">
    <!--头像上传框-->
    <UpImg :photoNm="form.clientPhoto" @upPhoto="changePhoto"/>
    <!--表单-->
    <el-form ref="myFormRef" :model="form" :rules="rules" label-width="120px">
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
            style="width: 50%"
            type="date"
            value-format="YYYY-MM-DD"
        />
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.clientTel" clearable placeholder="联系方式"/>
      </el-form-item>
      <el-form-item label="简介">
        <el-input v-model="form.clientInfo" clearable placeholder="用户近况"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="modalView = false">取消</el-button>
        <el-button type="primary" @click="formSubmit(myFormRef)">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue'
import {Delete, Edit} from '@element-plus/icons-vue'
import BackOpCol from "@/components/BackOpCol.vue";
import BackPage from "@/components/BackPage.vue";
import {PageQuery, YClientList} from "@/model/VO/BackQuery";
import {getAge} from "@/utils/TimeUtil";
import {Client, exampleClient} from "@/model/entiy/Client";
import TagSex from "@/components/TagSex.vue";
import {reqAddClient, reqClientList, reqDelClient, reqDelClientBatch, reqUpdateClient} from "@/request/ClientApi";
import {Page} from "@/model/DO/Page";
import {ElMessageBox, FormInstance, FormRules} from "element-plus";
import {Res} from "@/request/Res";
import UpImg from "@/components/UpImg.vue";

onMounted(() => {
  sendQuery()
})
// 查询的参数
const qp: YClientList = reactive({
  clientName: '',
  numPage: 1,
  pageSize: 6
})
const addRoleB = (): void => {
  form.value = exampleClient()
  myFormRef.value?.resetFields()
  modalTit.value = "新增用户"
  modalView.value = true
}
// 列表展示
const clientList = ref<Page<Client>>({records: [], total: 0})
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Client[]): void => {
  roleIdList.value = val.map(obj => obj.clientId)
  console.log(roleIdList.value)
}
// 批量删除
const delBatchB = (): void => {
  if (roleIdList.value.length == 0) return
  reqDelClientBatch(roleIdList.value).then(res => sureFlush(res))
}
// 分页条
const changePuB = (val: PageQuery) => {
  qp.numPage = val.numPage
  qp.pageSize = val.pageSize
  sendQuery()
}
// 数据总览
const sendQuery = (): void => {
  reqClientList(qp).then(res => {
    clientList.value = res
  })
}
// 模态框
const modalView = ref(false)
const modalTit = ref<"新增用户" | "修改用户">("修改用户")
// 修改时展示模态框
const showDialog = (row: Client) => {
  myFormRef.value?.clearValidate()
  form.value = row
  modalView.value = true
  modalTit.value = "修改用户"
}
// 确定请求的返回值，然后刷新
const sureFlush = (res: Res): void => {
  if (res.code === 1) sendQuery()
}
// 删除单个
const delOne = (id: number): void => {
  reqDelClient(id).then(res => sureFlush(res))
}
// 表单的数据
const form = ref<Client>(exampleClient()) // 空的默认值
const myFormRef = ref<FormInstance>()
// 校验表单并提交
const formSubmit = async (formEl: FormInstance): Promise<void> => {
  if (!formEl) return
  await myFormRef.value?.validate((valid: boolean): void => {
    if (valid) {
      // 校验通过，提交
      modalView.value = false
      if (modalTit.value == "新增用户") reqAddClient(form.value).then(res => sureFlush(res))
      else if (modalTit.value === "修改用户") reqUpdateClient(form.value).then(res => sureFlush(res))
      else ElMessageBox.alert('模块框出错')
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
  ],
})
// 头像参数的改变:子传父
const changePhoto = (val: string): void => {
  form.value.clientPhoto = val
}
</script>

<style scoped>

</style>