<template>
  <!--操作框-->
  <BackOpCol role="科室" @addRole="addRoleB" @delBatch="delBatchB" @query="sendQuery">
    <el-input v-model="qp.departmentName" placeholder="科室名" size="large" @keyup.enter="sendQuery"/>
  </BackOpCol>

  <!--列表展示-->
  <el-table :data="departmentList.records"
            max-height="80vh"
            row-key="departmentId"
            stripe
            style="width: 100%"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column label="科室名" prop="departmentName"/>
    <el-table-column label="简介" prop="departmentInfo"/>
    <el-table-column label="地址" prop="departmentAddress"/>
    <el-table-column :formatter="doctorNumStr" label="人数" prop="doctorNum" sortable/>
    <el-table-column fixed="right" label="操作">
      <template #default="scope">
        <el-button-group>
          <el-button :icon="Edit" circle type="warning" @click="showDialog(scope.row)"/>
          <el-button :icon="Delete" circle type="danger" @click="delOne(scope.row.departmentId)"/>
        </el-button-group>
      </template>
    </el-table-column>
  </el-table>
  <!--分页条-->
  <BackPage :total="departmentList.total" @changePu="changePuB"/>
  <!--修改、新增时的模态框-->
  <el-dialog v-model="modalView" :title="modalTit" draggable width="60%">
    <!--表单-->
    <el-form ref="myFormRef" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="科室名称" prop="departmentName">
        <el-input v-model="form.departmentName" clearable placeholder="某某科"/>
      </el-form-item>
      <el-form-item label="简介" prop="departmentInfo">
        <el-input v-model="form.departmentInfo" clearable placeholder="科室简介"/>
      </el-form-item>
      <el-form-item label="地址" prop="departmentAddress">
        <el-input v-model="form.departmentAddress" clearable placeholder="一楼114"/>
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
import {PageQuery, XDepartmentList} from "@/model/VO/BackQuery";
import {Department, exampleDepart} from "@/model/entiy/Department";
import {reqAddDepart, reqDelDepart, reqDelDepartBatch, reqDepartList, reqUpdateDepart} from "@/request/DepartApi";
import {Page} from "@/model/DO/Page";
import {DepartDto} from "@/model/DO/DepartDto";
import {ElMessageBox, FormInstance, FormRules} from "element-plus";
import {Res} from "@/request/Res";

onMounted(() => {
  sendQuery()
})
// 格式化人数方法
const doctorNumStr = (row: DepartDto): string => {
  if (row.doctorNum > 0) return row.doctorNum + "人"
  else return "无"
}
// 查询的参数
const qp: XDepartmentList = reactive({
  departmentName: '',
  numPage: 1,
  pageSize: 6
})
const addRoleB = (): void => {
  form.value = exampleDepart()
  myFormRef.value?.resetFields()
  modalTit.value = "新增科室"
  modalView.value = true
}
const delBatchB = (): void => {
  if (roleIdList.value.length == 0) return
  reqDelDepartBatch(roleIdList.value).then(res => sureFlush(res))
}
// 列表展示
const departmentList = ref<Page<DepartDto>>({records: [], total: 0})
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Department[]): void => {
  roleIdList.value = val.map(obj => obj.departmentId)
}
// 分页条
const changePuB = (val: PageQuery) => {
  qp.numPage = val.numPage
  qp.pageSize = val.pageSize
  sendQuery()
}
// 数据总览
const sendQuery = (): void => {
  reqDepartList(qp).then(res => {
    departmentList.value = res
  })
}
// 模态框
const modalView = ref(false)
const modalTit = ref<"新增科室" | "修改科室">("修改科室")
// 修改时展示模态框
const showDialog = (row: Department) => {
  myFormRef.value?.clearValidate()
  form.value = row
  modalView.value = true
  modalTit.value = "修改科室"
}
// 确定请求的返回值，然后刷新
const sureFlush = (res: Res): void => {
  if (res.code === 1) sendQuery()
}
// 删除单个
const delOne = (id: number): void => {
  reqDelDepart(id).then(res => sureFlush(res))
}
// 表单的数据
const form = ref<Department>(exampleDepart()) // 空的默认值
const myFormRef = ref<FormInstance>()
// 校验表单并提交
const formSubmit = async (formEl: FormInstance): Promise<void> => {
  if (!formEl) return
  await myFormRef.value?.validate((valid: boolean): void => {
    if (valid) {
      // 校验通过，提交
      modalView.value = false
      if (modalTit.value == "新增科室") reqAddDepart(form.value).then(res => sureFlush(res))
      else if (modalTit.value === "修改科室") reqUpdateDepart(form.value).then(res => sureFlush(res))
      else ElMessageBox.alert('模块框出错')
    }
  })
}
// 表单校验规则
const rules = reactive<FormRules>({
  "departmentName": [
    {required: true, message: '请输入科室名', trigger: 'blur'},
  ],
  "departmentInfo": [
    {required: true, message: '请填写简介', trigger: 'blur'},
  ],
  "departmentAddress": [
    {required: true, message: '请填写地址', trigger: 'blur'},
  ],
})
</script>

<style scoped>

</style>