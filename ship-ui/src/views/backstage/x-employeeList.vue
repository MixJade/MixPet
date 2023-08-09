<template>
  <!--操作框-->
  <BackOpCol role="管理员" @addRole="addRoleB" @delBatch="delBatchB" @query="sendQuery">
    <el-input v-model="qp.employeeName" placeholder="管理员姓名" size="large" @keyup.enter="sendQuery"/>
  </BackOpCol>

  <!--列表展示-->
  <el-table :data="employeeList.records"
            max-height="80vh"
            row-key="employeeId"
            stripe
            style="width: 100%"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column label="图像">
      <template #default="scope">
        <el-avatar :src="'/api/common/download?name='+scope.row.employeePhoto"/>
      </template>
    </el-table-column>
    <el-table-column label="账号" prop="employeeUsername"/>
    <el-table-column label="姓名" prop="employeeName"/>
    <el-table-column label="工龄" prop="createTime" sortable>
      <template #default="scope">{{ getDaysFromToday(scope.row.createTime) }}</template>
    </el-table-column>
    <el-table-column label="联系方式" prop="employeeTel"/>
    <el-table-column label="职位">
      <template #default="scope">
        <el-tag>{{ getJob(scope.row.employeeLevel) }}</el-tag>
      </template>
    </el-table-column>
    <el-table-column fixed="right" label="操作">
      <template #default="scope">
        <el-button-group>
          <el-button :icon="Edit" circle type="warning" @click="showDialog(scope.row)"/>
          <el-button :icon="Delete" circle type="danger" @click="delOne(scope.row.employeeId)"/>
        </el-button-group>
      </template>
    </el-table-column>
  </el-table>
  <!--分页条-->
  <BackPage :total="employeeList.total" @changePu="changePuB"/>
  <!--修改、新增时的模态框-->
  <el-dialog v-model="modalView" :title="modalTit" draggable width="60%">
    <!--头像上传框-->
    <UpImg :photoNm="form.employeePhoto" @upPhoto="changePhoto"/>
    <!--表单-->
    <el-form ref="myFormRef" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="姓名" prop="employeeName">
        <el-input v-model="form.employeeName" clearable placeholder="用户姓名"/>
      </el-form-item>
      <el-form-item label="帐号" prop="employeeUsername">
        <el-input v-model="form.employeeUsername" clearable placeholder="用户帐号"/>
      </el-form-item>
      <el-form-item label="权限">
        <el-select v-model="form.employeeLevel" filterable placeholder="权限等级">
          <el-option :value="6" label="院长"/>
          <el-option :value="4" label="主任"/>
          <el-option :value="2" label="护士"/>
          <el-option :value="0" label="临时工"/>
        </el-select>
      </el-form-item>
      <el-form-item label="联系方式">
        <el-input v-model="form.employeeTel" clearable placeholder="联系方式"/>
      </el-form-item>
      <el-form-item v-if="modalTit==='新增管理员'" label="密码">
        <el-input v-model="form.employeePassword" clearable placeholder="123456"/>
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
import {PageQuery, XEmployeeList} from "@/model/VO/BackQuery";
import {getDaysFromToday} from "@/utils/TimeUtil";
import {getJob} from "@/utils/JobUtil";
import {Employee, exampleEmployee} from "@/model/entiy/Employee";
import {
  reqAddEmployee,
  reqDelEmployee,
  reqDelEmployeeBatch,
  reqEmployeeList,
  reqUpdateEmployee
} from "@/request/EmployeeApi";
import {Page} from "@/model/DO/Page";
import {Res} from "@/request/Res";
import UpImg from "@/components/UpImg.vue";
import {ElMessageBox, FormInstance, FormRules} from "element-plus";

onMounted(() => {
  sendQuery()
})
// 查询的参数
const qp: XEmployeeList = reactive({
  employeeName: '',
  numPage: 1,
  pageSize: 7
})
// 添加模态框
const addRoleB = (): void => {
  form.value = exampleEmployee()
  myFormRef.value?.resetFields()
  modalTit.value = "新增管理员"
  modalView.value = true
}
// 列表展示
const employeeList = ref<Page<Employee>>({records: [], total: 0})
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Employee[]): void => {
  roleIdList.value = val.map(obj => obj.employeeId)
}
// 批量删除
const delBatchB = (): void => {
  if (roleIdList.value.length == 0) return
  reqDelEmployeeBatch(roleIdList.value).then(res => sureFlush(res))
}
// 分页条
const changePuB = (val: PageQuery) => {
  qp.numPage = val.numPage
  qp.pageSize = val.pageSize
  sendQuery()
}
// 数据总览
const sendQuery = (): void => {
  reqEmployeeList(qp).then(res => {
    employeeList.value = res
  })
}
// 模态框
const modalView = ref(false)
const modalTit = ref<"新增管理员" | "修改管理员">("修改管理员")
// 修改时展示模态框
const showDialog = (row: Employee) => {
  myFormRef.value?.clearValidate()
  form.value = row
  modalView.value = true
  modalTit.value = "修改管理员"
}
// 确定请求的返回值，然后刷新
const sureFlush = (res: Res): void => {
  if (res.code === 1) sendQuery()
}
// 删除单个
const delOne = (id: number): void => {
  reqDelEmployee(id).then(res => sureFlush(res))
}
// 表单的数据
const form = ref<Employee>(exampleEmployee()) // 空的默认值
const myFormRef = ref<FormInstance>()
// 校验表单并提交
const formSubmit = async (formEl: FormInstance): Promise<void> => {
  if (!formEl) return
  await myFormRef.value?.validate((valid: boolean): void => {
    if (valid) {
      // 校验通过，提交
      modalView.value = false
      if (modalTit.value == "新增管理员") reqAddEmployee(form.value).then(res => sureFlush(res))
      else if (modalTit.value === "修改管理员") reqUpdateEmployee(form.value).then(res => sureFlush(res))
      else ElMessageBox.alert('模块框出错')
    }
  })
}
// 表单校验规则
const rules = reactive<FormRules>({
  "employeeName": [
    {required: true, message: '请输入姓名', trigger: 'blur'},
  ],
  "employeeUsername": [
    {required: true, message: '请填写帐号', trigger: 'blur'},
  ],
})
// 头像参数的改变:子传父
const changePhoto = (val: string): void => {
  form.value.employeePhoto = val
}
</script>

<style scoped>

</style>