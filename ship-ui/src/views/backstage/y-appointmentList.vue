<template>
  <!--操作框-->
  <BackOpCol role="挂号" @addRole="addRoleB" @delBatch="delBatchB" @query="sendQuery">
    <el-select v-model="qp.seaType" placeholder="Select" size="large">
      <el-option :value="0" label="挂号人"/>
      <el-option :value="1" label="宠物"/>
      <el-option :value="2" label="医生"/>
      <el-option :value="3" label="科室"/>
    </el-select>
    <el-input v-model="qp.seaName" placeholder="名称" size="large" @keyup.enter="sendQuery"/>
  </BackOpCol>

  <!--列表展示-->
  <el-table :data="appointList.records"
            max-height="80vh"
            row-key="appointId"
            stripe
            style="width: 100%"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column :formatter="removeT_1" label="就诊时间" prop="appointmentDate" sortable/>
    <el-table-column label="简介" prop="appointmentInfo"/>
    <el-table-column :formatter="removeT_2" label="创建时间" prop="createTime" sortable/>
    <el-table-column label="宠物名" prop="petName"/>
    <el-table-column label="挂号人" prop="clientName"/>
    <el-table-column label="医生" prop="doctorName"/>
    <el-table-column label="科室">
      <template #default="scope">
        <el-tag>{{ scope.row.departmentName }}</el-tag>
      </template>
    </el-table-column>
    <el-table-column fixed="right" label="操作">
      <template #default="scope">
        <el-button-group>
          <el-button :icon="Edit" circle type="warning" @click="showDialog(scope.row)"/>
          <el-button :icon="Delete" circle type="danger" @click="delOne(scope.row.appointmentId)"/>
        </el-button-group>
      </template>
    </el-table-column>
  </el-table>
  <!--分页条-->
  <BackPage :total="appointList.total" @changePu="changePuB"/>
  <!--修改、新增时的模态框-->
  <el-dialog v-model="modalView" :title="modalTit" draggable width="60%">
    <!--表单-->
    <el-form ref="myFormRef" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="简介" prop="appointmentInfo">
        <el-input v-model="form.appointmentInfo" clearable placeholder="无"/>
      </el-form-item>
      <el-form-item label="就诊时间" prop="appointmentDate">
        <el-date-picker
            v-model="form.appointmentDate"
            :value-format="'YYYY-MM-DD'+'T'+'HH:mm:ss'"
            format="YYYY-MM-DD HH:mm"
            placeholder="选择就诊时间"
            style="width: 50%"
            type="datetime"
        />
      </el-form-item>
      <el-form-item label="主人" prop="clientId">
        <el-select v-model="form.clientId" filterable placeholder="选择用户">
          <el-option v-for="d in clientNameL" :key="d.roleId" :label="d.roleName" :value="d.roleId"/>
        </el-select>
      </el-form-item>
      <el-form-item label="宠物" prop="petId">
        <el-select v-model="form.petId" filterable placeholder="选择宠物">
          <el-option v-for="d in petNameL" :key="d.roleId" :label="d.roleName" :value="d.roleId"/>
        </el-select>
      </el-form-item>
      <el-form-item label="科室" prop="departmentId">
        <el-select v-model="form.departmentId" filterable placeholder="选择科室">
          <el-option v-for="d in departmentNameL" :key="d.roleId" :label="d.roleName" :value="d.roleId"/>
        </el-select>
      </el-form-item>
      <el-form-item label="医生" prop="doctorId">
        <el-select v-model="form.doctorId" filterable placeholder="选择医生">
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
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref, watch} from 'vue'
import {Delete, Edit} from '@element-plus/icons-vue'
import BackOpCol from "@/components/BackOpCol.vue";
import BackPage from "@/components/BackPage.vue";
import {PageQuery, YAppointList} from "@/model/VO/BackQuery";
import {Appoint, exampleAppoint} from "@/model/entiy/Appoint";
import {AppointDto} from "@/model/DO/AppointDto";
import {reqAddAppoint, reqAppointList, reqDelAppoint, reqDelAppointBatch, reqUpdateAppoint} from "@/request/AppointApi";
import {Page} from "@/model/DO/Page";
import {moveT} from "@/utils/TimeUtil";
import {Res} from "@/request/Res";
import {reqDepartName} from "@/request/DepartApi";
import {NameVo} from "@/model/VO/NameVo";
import {ElMessageBox, FormInstance, FormRules} from "element-plus";
import {reqClientName} from "@/request/ClientApi";
import {reqPetNameByClientId} from "@/request/PetApi";
import {reqDoctorName, reqDoctorNameByDepartId} from "@/request/DoctorApi";

onMounted(() => {
  sendQuery()
  reqClientName().then(res => {
    clientNameL.value = res;
  })
  reqDepartName().then(res => {
    departmentNameL.value = res;
  })
})
// 查询的参数
const qp: YAppointList = reactive({
  seaName: '',
  seaType: 0,
  numPage: 1,
  pageSize: 6
})
const addRoleB = (): void => {
  form.value = exampleAppoint()
  myFormRef.value?.resetFields()
  modalTit.value = "新增挂号"
  modalView.value = true
}
// 列表展示
const appointList = ref<Page<AppointDto>>({records: [], total: 0})
const removeT_1 = (row: Appoint) => {
  return moveT(row.appointmentDate)
}
const removeT_2 = (row: Appoint) => {
  return moveT(<string>row.createTime)
}
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Appoint[]): void => {
  roleIdList.value = val.map(obj => obj.appointmentId)
  console.log(roleIdList.value)
}
// 分页条
const changePuB = (val: PageQuery) => {
  qp.numPage = val.numPage
  qp.pageSize = val.pageSize
  sendQuery()
}
// 数据总览
const sendQuery = (): void => {
  reqAppointList(qp).then(res => {
    appointList.value = res
  })
}
// 模态框
const modalView = ref(false)
const modalTit = ref<"新增挂号" | "修改挂号">("修改挂号")
// 修改时展示模态框
const showDialog = (row: Appoint) => {
  myFormRef.value?.clearValidate()
  form.value = row
  modalView.value = true
  modalTit.value = "修改挂号"
}

// 确定请求的返回值，然后刷新
const sureFlush = (res: Res): void => {
  if (res.code === 1) sendQuery()
}
// 批量删除
const delBatchB = (): void => {
  if (roleIdList.value.length == 0) return
  reqDelAppointBatch(roleIdList.value).then(res => sureFlush(res))
}
// 删除单个
const delOne = (id: number): void => {
  reqDelAppoint(id).then(res => sureFlush(res))
}
// 表单的数据
const clientNameL = ref<NameVo[]>([]) // 下拉框用户名
const petNameL = ref<NameVo[]>([])
const doctorNameL = ref<NameVo[]>([])
const departmentNameL = ref<NameVo[]>([])
const form = ref<Appoint>(exampleAppoint()) // 空的默认值
const myFormRef = ref<FormInstance>()
// 校验表单并提交
const formSubmit = async (): Promise<void> => {
  await myFormRef.value?.validate((valid: boolean): void => {
    if (valid) {
      // 校验通过，提交
      modalView.value = false
      if (modalTit.value == "新增挂号") reqAddAppoint(form.value).then(res => sureFlush(res))
      else if (modalTit.value === "修改挂号") reqUpdateAppoint(form.value).then(res => sureFlush(res))
      else ElMessageBox.alert('模块框出错')
    }
  })
}
// 表单校验规则
const rules = reactive<FormRules>({
  "appointmentInfo": [
    {required: true, message: '请输入描述', trigger: 'blur'},
  ],
  "appointmentDate": [
    {required: true, message: '请输入日期', trigger: 'blur'},
  ],
  "clientId": [
    {required: true, message: '请选择用户', trigger: 'blur'},
  ],
  "petId": [
    {required: true, message: '请选择宠物', trigger: 'blur'},
  ],
  "departmentId": [
    {required: true, message: '请选择科室', trigger: 'blur'},
  ],
  "doctorId": [
    {required: true, message: '请选择医生', trigger: 'blur'},
  ],
})

// 在用户改变时，查询宠物名
watch(() => form.value.clientId, (newValue): void => {
  if (newValue == 0 || newValue == null) return;
  reqPetNameByClientId(newValue).then(res => {
    petNameL.value = res
  })
})
// 在科室改变时，查询医生名
watch(() => form.value.departmentId, (newValue) => {
  if (newValue == 0 || newValue == null) {
    reqDoctorName().then(res => {
      doctorNameL.value = res
    })
  } else {
    reqDoctorNameByDepartId(newValue).then(res => {
      doctorNameL.value = res
    })
  }
})
</script>

<style scoped>

</style>
