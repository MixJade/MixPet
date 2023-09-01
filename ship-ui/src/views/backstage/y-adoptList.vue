<template>
  <!--操作框-->
  <BackOpCol role="领养" @addRole="addRoleB" @delBatch="delBatchB" @query="sendQuery">
    <el-input v-model="qp.clientName" placeholder="申请人" size="large" @keyup.enter="sendQuery"/>
    <el-input v-model="qp.petName" placeholder="宠物名" size="large" @keyup.enter="sendQuery"/>
  </BackOpCol>

  <!--列表展示-->
  <el-table :data="adoptList.records"
            max-height="80vh"
            row-key="adoptId"
            stripe
            style="width: 100%"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column label="简介" prop="adoptInfo"/>
    <el-table-column label="定金" prop="adoptMoney" sortable/>
    <el-table-column :formatter="removeT" label="创建时间" prop="createTime" sortable/>
    <el-table-column label="宠物名" prop="petName"/>
    <el-table-column label="申请人" prop="clientName"/>
    <el-table-column label="是否通过" prop="inAdopt" sortable>
      <template #default="scope">
        <el-tag v-if="scope.row.inAdopt===2">待审核</el-tag>
        <el-tag v-else-if="scope.row.inAdopt===1" type="success">已通过</el-tag>
        <el-tag v-else type="danger">已拒绝</el-tag>
      </template>
    </el-table-column>
    <el-table-column fixed="right" label="操作">
      <template #default="scope">
        <el-button-group>
          <el-button v-if="scope.row.inAdopt===2" :icon="Check" circle type="success"
                     @click="adoptAdopt(scope.row.adoptId,true)"/>
          <el-button v-if="scope.row.inAdopt===2" :icon="Close" circle type="info"
                     @click="adoptAdopt(scope.row.adoptId,false)"/>
          <el-button v-if="scope.row.inAdopt===1" :icon="Edit" circle type="warning" @click="showDialog(scope.row)"/>
          <el-button :icon="Delete" circle type="danger" @click="delOne(scope.row.adoptId)"/>
        </el-button-group>
      </template>
    </el-table-column>
  </el-table>
  <!--分页条-->
  <BackPage :total="adoptList.total" @changePu="changePuB"/>
  <!--修改、新增时的模态框-->
  <el-dialog v-model="modalView" :title="modalTit" draggable width="60%">
    <!--表单-->
    <el-form ref="myFormRef" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="简介" prop="adoptInfo">
        <el-input v-model="form.adoptInfo" clearable placeholder="无"/>
      </el-form-item>
      <el-form-item label="定金" prop="adoptMoney">
        <el-input-number
            v-model="form.adoptMoney"
            :max="5000"
            :min="100"
            :step="100"
        />
      </el-form-item>
      <el-form-item label="宠物" prop="petId">
        <el-select v-model="form.petId" filterable placeholder="选择宠物">
          <el-option v-for="d in petNameL" :key="d.roleId" :label="d.roleName" :value="d.roleId"/>
        </el-select>
      </el-form-item>
      <el-form-item label="申请人" prop="clientId">
        <el-select v-model="form.clientId" filterable placeholder="选择用户">
          <el-option v-for="d in clientNameL" :key="d.roleId" :label="d.roleName" :value="d.roleId"/>
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
import {onMounted, reactive, ref} from 'vue'
import {Check, Close, Delete, Edit} from '@element-plus/icons-vue'
import BackOpCol from "@/components/BackOpCol.vue";
import BackPage from "@/components/BackPage.vue";
import {PageQuery, YAdoptList} from "@/model/VO/BackQuery";
import {Adopt, exampleAdopt} from "@/model/entiy/Adopt";
import {AdoptDto} from "@/model/DO/AdoptDto";
import {moveT} from "@/utils/TimeUtil";
import {
  reqAddAdopt,
  reqAdoptAdopt,
  reqAdoptList,
  reqDelAdopt,
  reqDelAdoptBatch,
  reqUpdateAdopt
} from "@/request/AdoptApi";
import {Page} from "@/model/DO/Page";
import {ElMessage, ElMessageBox, FormInstance, FormRules} from "element-plus";
import {Res} from "@/request/Res";
import {NameVo} from "@/model/VO/NameVo";
import {reqPetNoClient} from "@/request/PetApi";
import {reqClientName} from "@/request/ClientApi";

/**
 ┌───────────────────────────────────┐
 │=============生命周期相关============│
 └───────────────────────────────────┘
 */
onMounted(() => {
  sendQuery()
  reqClientName().then(res => {
    clientNameL.value = res
  })
  reqPetNoClient().then(res => {
    petNameL.value = res
  })
})

/**
 ┌───────────────────────────────────┐
 │=============表格查询相关============│
 └───────────────────────────────────┘
 */
// 查询的参数
const qp: YAdoptList = reactive({
  petName: '',
  clientName: '',
  numPage: 1,
  pageSize: 6
})
// 列表展示
const adoptList = ref<Page<AdoptDto>>({records: [], total: 0})
const removeT = (row: Adopt) => {
  return moveT(row.createTime)
}
// 分页条
const changePuB = (val: PageQuery) => {
  qp.numPage = val.numPage
  qp.pageSize = val.pageSize
  sendQuery()
}
// 数据总览
const sendQuery = (): void => {
  reqAdoptList(qp).then(res => {
    adoptList.value = res
  })
}

/**
 ┌───────────────────────────────────┐
 │=============数据删除相关============│
 └───────────────────────────────────┘
 */
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Adopt[]): void => {
  roleIdList.value = val.map(obj => obj.adoptId)
}
// 批量删除
const delBatchB = (): void => {
  if (roleIdList.value.length == 0) return
  ElMessageBox.confirm(
      '在您做出指示之前，我还是要再次确认，您确定要焚毁这些的订单吗？',
      '删除多个确认',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(() => {
    reqDelAdoptBatch(roleIdList.value).then(res => sureFlush(res))
  }).catch(() => {
    ElMessage.info('删除取消')
  })
}
// 删除单个
const delOne = (id: number): void => {
  ElMessageBox.confirm(
      `解脱一纸束缚的契约，赐予其尘归尘的命运。`,
      '删除单个确认',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(() => {
    reqDelAdopt(id).then(res => sureFlush(res))
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
const modalTit = ref<"新增领养" | "修改领养">("修改领养")
// 新增
const addRoleB = (): void => {
  form.value = exampleAdopt()
  myFormRef.value?.resetFields()
  modalTit.value = "新增领养"
  modalView.value = true
}
// 修改时展示模态框
const showDialog = (row: Adopt) => {
  myFormRef.value?.clearValidate()
  // 赋值，防止响应式引用
  form.value.adoptId = row.adoptId
  form.value.adoptInfo = row.adoptInfo
  form.value.adoptMoney = row.adoptMoney
  form.value.petId = row.petId
  form.value.clientId = row.clientId
  // ===赋值完成===
  modalView.value = true
  modalTit.value = "修改领养"
}
// 审核通过
const adoptAdopt = (adoptId: number, isAdopt: boolean) => {
  reqAdoptAdopt({adoptId, isAdopt})
      .then(res => sureFlush(res))
}

/**
 ┌───────────────────────────────────┐
 │=============表单校验相关============│
 └───────────────────────────────────┘
 */
// 表单的数据
const clientNameL = ref<NameVo[]>([]) // 下拉框用户名
const petNameL = ref<NameVo[]>([])
const form = ref<Adopt>(exampleAdopt()) // 空的默认值
const myFormRef = ref<FormInstance>()
// 校验表单并提交
const formSubmit = async (): Promise<void> => {
  await myFormRef.value?.validate((valid: boolean): void => {
    if (valid) {
      // 校验通过，提交
      modalView.value = false
      if (modalTit.value == "新增领养") reqAddAdopt(form.value).then(res => sureFlush(res))
      else if (modalTit.value === "修改领养") reqUpdateAdopt(form.value).then(res => sureFlush(res))
      else ElMessageBox.alert('模块框出错')
    }
  })
}
// 表单校验规则
const rules = reactive<FormRules>({
  "adoptInfo": [
    {required: true, message: '请输入描述', trigger: 'blur'},
  ],
  "adoptMoney": [
    {required: true, message: '请给钱', trigger: 'blur'},
  ],
  "clientId": [
    {required: true, message: '请选择用户', trigger: 'blur'},
  ],
  "petId": [
    {required: true, message: '请选择宠物', trigger: 'blur'},
  ]
})
</script>

<style scoped>

</style>
