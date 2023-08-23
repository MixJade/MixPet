<template>
  <!--操作框-->
  <BackOpCol role="医生" @addRole="addRoleB" @delBatch="delBatchB" @query="sendQuery">
    <el-input v-model="qp.doctorName" placeholder="医生姓名" size="large" @keyup.enter="sendQuery"/>
    <el-input v-model="qp.departmentName" placeholder="科室名" size="large" @keyup.enter="sendQuery"/>
  </BackOpCol>

  <!--列表展示-->
  <el-table :data="doctorList.records"
            max-height="80vh"
            row-key="doctorId"
            stripe
            style="width: 100%"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column label="图像">
      <template #default="scope">
        <el-avatar :src="'/api/common/download?name='+scope.row.doctorPhoto"/>
      </template>
    </el-table-column>
    <el-table-column label="医生名" prop="doctorName"/>
    <el-table-column label="帐号" prop="username"/>
    <el-table-column label="性别">
      <template #default="scope">
        <TagSex :sex="scope.row.doctorGender"/>
      </template>
    </el-table-column>
    <el-table-column label="年龄" prop="doctorAge" sortable>
      <template #default="scope">{{ getAge(scope.row.doctorAge) }}岁</template>
    </el-table-column>
    <el-table-column label="联系方式" prop="doctorTel"/>
    <el-table-column label="简介" prop="doctorInfo"/>
    <el-table-column label="科室">
      <template #default="scope">
        <el-tag>{{ scope.row.departmentName }}</el-tag>
      </template>
    </el-table-column>
    <el-table-column label="职位" prop="authLv" sortable>
      <template #default="scope">
        <el-tag>{{ getJob(scope.row.authLv) }}</el-tag>
      </template>
    </el-table-column>
    <el-table-column label="工龄" prop="createTime" sortable>
      <template #default="scope">{{ getDaysFromToday(scope.row.createTime) }}</template>
    </el-table-column>
    <el-table-column fixed="right" label="操作">
      <template #default="scope">
        <el-button-group>
          <el-button :icon="Edit" circle type="warning" @click="showDialog(scope.row)"/>
          <el-button :icon="Delete" circle type="danger" @click="delOne(scope.row.doctorId)"/>
        </el-button-group>
      </template>
    </el-table-column>
  </el-table>
  <!--分页条-->
  <BackPage :total="doctorList.total" @changePu="changePuB"/>
  <!--修改、新增时的模态框-->
  <el-dialog v-model="modalView" :title="modalTit" draggable width="60%">
    <!--头像上传框-->
    <UpImg :photoNm="form.doctorPhoto" @upPhoto="changePhoto"/>
    <!--表单-->
    <el-form ref="myFormRef" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="帐号" prop="username">
        <el-input v-model="form.username" clearable placeholder="医生帐号"/>
      </el-form-item>
      <el-form-item label="姓名" prop="doctorName">
        <el-input v-model="form.doctorName" clearable placeholder="医生姓名"/>
      </el-form-item>
      <el-form-item label="科室" prop="departmentId">
        <el-select v-model="form.departmentId" filterable placeholder="科室">
          <el-option v-for="c in departNameL" :key="c.roleId" :label="c.roleName" :value="c.roleId"/>
        </el-select>
      </el-form-item>
      <el-form-item label="职位" prop="doctorJob">
        <el-select v-model="form.authLv" placeholder="医生">
          <el-option :value="8" label="院长"/>
          <el-option :value="6" label="主任"/>
          <el-option :value="4" label="医生"/>
          <el-option :value="2" label="护士"/>
          <el-option :value="0" label="临时工"/>
        </el-select>
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
            style="width: 50%"
            type="date"
            value-format="YYYY-MM-DD"
        />
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.doctorTel" clearable placeholder="联系方式"/>
      </el-form-item>
      <el-form-item label="简介">
        <el-input v-model="form.doctorInfo" clearable placeholder="医生简介"/>
      </el-form-item>
      <el-form-item v-if="modalTit==='新增医生'" label="密码">
        <el-input v-model="form.doctorPassword" clearable placeholder="123456"/>
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
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue'
import {Delete, Edit} from '@element-plus/icons-vue'
import BackOpCol from "@/components/BackOpCol.vue";
import BackPage from "@/components/BackPage.vue";
import UpImg from "@/components/UpImg.vue";
import {PageQuery, YDoctorList} from "@/model/VO/BackQuery";
import {getAge, getDaysFromToday} from "@/utils/TimeUtil";
import {getJob} from "@/utils/JobUtil";
import {DoctorDto} from "@/model/DO/DoctorDto";
import TagSex from "@/components/TagSex.vue";
import {Page} from "@/model/DO/Page";
import {reqAddDoctor, reqDelDoctor, reqDelDoctorBatch, reqDoctorList, reqUpdateDoctor} from "@/request/DoctorApi";
import {Res} from "@/request/Res";
import {Doctor, exampleDoctor} from "@/model/entiy/Doctor";
import {ElMessageBox, FormInstance, FormRules} from "element-plus";
import {reqDepartName} from "@/request/DepartApi";
import {NameVo} from "@/model/VO/NameVo";

onMounted(() => {
  sendQuery()
  reqDepartName().then(res => {
    departNameL.value = res
  })
})
// 查询的参数
const qp: YDoctorList = reactive({
  doctorName: '',
  departmentName: '',
  numPage: 1,
  pageSize: 6
})
const addRoleB = (): void => {
  form.value = exampleDoctor()
  myFormRef.value?.resetFields()
  modalTit.value = "新增医生"
  modalView.value = true
}
// 列表展示
const doctorList = ref<Page<DoctorDto>>({records: [], total: 0})
// 分页条
const changePuB = (val: PageQuery) => {
  qp.numPage = val.numPage
  qp.pageSize = val.pageSize
  sendQuery()
}
// 数据总览
const sendQuery = (): void => {
  reqDoctorList(qp).then(res => {
    doctorList.value = res
  })
}
// 模态框
const modalView = ref(false)
const modalTit = ref<"新增医生" | "修改医生">("修改医生")
// 修改时展示模态框
const showDialog = (row: Doctor) => {
  myFormRef.value?.clearValidate()
  form.value = row
  modalView.value = true
  modalTit.value = "修改医生"
}
// 确定请求的返回值，然后刷新
const sureFlush = (res: Res): void => {
  if (res.code === 1) sendQuery()
}
// 删除单个
const delOne = (id: number): void => {
  reqDelDoctor(id).then(res => sureFlush(res))
}
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Doctor[]): void => {
  roleIdList.value = val.map(obj => obj.doctorId)
}
// 批量删除
const delBatchB = (): void => {
  if (roleIdList.value.length == 0) return
  reqDelDoctorBatch(roleIdList.value).then(res => sureFlush(res));
}
// 表单的数据
const departNameL = ref<NameVo[]>([]) // 下拉框部门名称
const form = ref<Doctor>(exampleDoctor()) // 空的默认值
const myFormRef = ref<FormInstance>()
// 校验表单并提交
const formSubmit = async (): Promise<void> => {
  await myFormRef.value?.validate((valid: boolean): void => {
    if (valid) {
      // 校验通过，提交
      modalView.value = false
      if (modalTit.value == "新增医生") reqAddDoctor(form.value).then(res => sureFlush(res))
      else if (modalTit.value === "修改医生") reqUpdateDoctor(form.value).then(res => sureFlush(res))
      else ElMessageBox.alert('模块框出错')
    }
  })
}
// 表单校验规则
const rules = reactive<FormRules>({
  "username": [
    {required: true, message: '帐号不能为空', trigger: 'blur'},
  ],
  "doctorName": [
    {required: true, message: '请输入姓名', trigger: 'blur'},
  ],
  "doctorAge": [
    {required: true, message: '请选择出生日期', trigger: 'blur'},
  ],
  "departmentId": [
    {required: true, message: '请选择科室', trigger: 'blur'},
  ],
})
// 头像参数的改变:子传父
const changePhoto = (val: string): void => {
  form.value.doctorPhoto = val
}
</script>

<style scoped>
</style>