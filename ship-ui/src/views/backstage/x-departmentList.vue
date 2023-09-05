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
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column label="科室名" prop="departmentName"/>
    <el-table-column label="简介" prop="departmentInfo"/>
    <el-table-column label="吉祥物" prop="headName"/>
    <el-table-column :formatter="doctorNumStr" label="人数" prop="doctorNum" sortable/>
    <el-table-column fixed="right" label="操作">
      <template #default="scope">
        <el-button-group>
          <el-button :icon="Edit" circle type="warning" @click="showDialog(scope.row)"/>
          <el-button :icon="View" circle type="primary" @click="showDoctor(scope.row)"/>
          <el-button :icon="Delete" circle type="danger" @click="delOne(scope.row)"/>
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
      <el-form-item label="吉祥物">
        <el-select v-model="form.headId" filterable placeholder="选择用户">
          <el-option label="无" value=""/>
          <el-option v-for="d in doctorNameL" :key="d.roleId" :label="d.roleName" :value="d.roleId"/>
        </el-select>
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
  <!--查看科室人员的模态框-->
  <el-dialog v-model="isDoctorShow" :title="departmentTit+'人员详情'" draggable width="60%">
    <el-table :data="doctorList" row-key="doctorId" stripe>
      <el-table-column label="医生名" prop="doctorName"/>
      <el-table-column label="帐号" prop="username"/>
      <el-table-column label="性别">
        <template #default="scope">
          {{ scope.row.doctorGender ? "男" : "女" }}
        </template>
      </el-table-column>
      <el-table-column label="年龄" prop="doctorAge">
        <template #default="scope">{{ getAge(scope.row.doctorAge) }}岁</template>
      </el-table-column>
      <el-table-column label="联系方式" prop="doctorTel" width="220"/>
      <el-table-column label="职位" prop="authLv">
        <template #default="scope">
          <TagJob :auth-lv="scope.row.authLv"/>
        </template>
      </el-table-column>
    </el-table>
  </el-dialog>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue'
import {Delete, Edit, View} from '@element-plus/icons-vue'
import BackOpCol from "@/components/row/BackOpCol.vue";
import BackPage from "@/components/row/BackPage.vue";
import {PageQuery, XDepartmentList} from "@/model/VO/BackQuery";
import {Department, exampleDepart} from "@/model/entiy/Department";
import {reqAddDepart, reqDelDepart, reqDelDepartBatch, reqDepartList, reqUpdateDepart} from "@/request/DepartApi";
import {Page} from "@/model/DO/Page";
import {DepartDto} from "@/model/DO/DepartDto";
import {ElMessage, ElMessageBox, FormInstance, FormRules} from "element-plus";
import {Res} from "@/request/Res";
import {reqDoctorByDepartId, reqDoctorName} from "@/request/DoctorApi";
import {NameVo} from "@/model/VO/NameVo";
import {getAge} from "@/utils/TimeUtil";
import {Doctor} from "@/model/entiy/Doctor";
import TagJob from "@/components/show/TagJob.vue";

/**
 ┌───────────────────────────────────┐
 │=============生命周期相关============│
 └───────────────────────────────────┘
 */
onMounted(() => {
  sendQuery()
  reqDoctorName().then(res => {
    doctorNameL.value = res
  })
})

/**
 ┌───────────────────────────────────┐
 │=============表格查询相关============│
 └───────────────────────────────────┘
 */
// 查询的参数
const qp: XDepartmentList = reactive({
  departmentName: '',
  numPage: 1,
  pageSize: 6
})
// 列表展示
const departmentList = ref<Page<DepartDto>>({records: [], total: 0})
// 格式化人数方法
const doctorNumStr = (row: DepartDto): string => {
  if (row.doctorNum > 0) return row.doctorNum + "人"
  else return "无"
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

// 查看科室人员的模态框
const isDoctorShow = ref(false)
const departmentTit = ref("某科")
const doctorList = ref<Doctor[]>([])
const showDoctor = (row: Department) => {
  departmentTit.value = row.departmentName
  reqDoctorByDepartId(row.departmentId).then(res => {
    doctorList.value = res
  })
  isDoctorShow.value = true
}
/**
 ┌───────────────────────────────────┐
 │=============数据删除相关============│
 └───────────────────────────────────┘
 */
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Department[]): void => {
  roleIdList.value = val.map(obj => obj.departmentId)
}
// 批量删除
const delBatchB = (): void => {
  if (roleIdList.value.length == 0) return
  ElMessageBox.confirm(
      '对于这些乱我朝纲的叛乱组织，臣定当以毫不留情的决心和无比坚定的信念去镇压。',
      '删除多个确认',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(() => {
    reqDelDepartBatch(roleIdList.value).then(res => sureFlush(res))
  }).catch(() => {
    ElMessage.info('删除取消')
  })
}
// 删除单个
const delOne = (row: Department): void => {
  ElMessageBox.confirm(
      `在最后行动之前，我有必要再次确认您的决策，是否决定真的撤销这个【${row.departmentName}】？`,
      '删除单个确认',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(() => {
    reqDelDepart(row.departmentId).then(res => sureFlush(res))
  }).catch(() => {
    ElMessage.info('删除取消')
  })
}
// 确定请求的返回值，然后刷新
const sureFlush = (res: Res): void => {
  if (res.code === 1) sendQuery()
}

/**
 ┌───────────────────────────────────┐
 │=============新增修改按钮============│
 └───────────────────────────────────┘
 */
// 模态框
const modalView = ref(false)
const modalTit = ref<"新增科室" | "修改科室">("修改科室")
// 新增
const addRoleB = (): void => {
  form.value = exampleDepart()
  myFormRef.value?.resetFields()
  modalTit.value = "新增科室"
  modalView.value = true
}
// 修改时展示模态框
const showDialog = (row: Department) => {
  myFormRef.value?.clearValidate()
  // 赋值，防止响应式引用
  form.value.departmentId = row.departmentId
  form.value.departmentInfo = row.departmentInfo
  form.value.departmentName = row.departmentName
  form.value.headId = row.headId
  // 显示
  modalView.value = true
  modalTit.value = "修改科室"
}

/**
 ┌───────────────────────────────────┐
 │=============表单校验相关============│
 └───────────────────────────────────┘
 */
// 表单的数据
const doctorNameL = ref<NameVo[]>([]) // 下拉框用户名
const form = ref<Department>(exampleDepart()) // 空的默认值
const myFormRef = ref<FormInstance>()
// 校验表单并提交
const formSubmit = async (): Promise<void> => {
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
  ]
})
</script>

<style scoped>

</style>
