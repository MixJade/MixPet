<template>
  <!--操作框-->
  <BackOpCol role="寄养" @addRole="addRoleB" @delBatch="delBatchB" @query="sendQuery">
    <el-input v-model="qp.petName" placeholder="宠物名" size="large" @keyup.enter="sendQuery"/>
    <el-input v-model="qp.clientName" placeholder="寄养人" size="large" @keyup.enter="sendQuery"/>
  </BackOpCol>
  <!--列表展示-->
  <el-table :data="fosterList.records"
            max-height="80vh"
            row-key="fosterId"
            stripe
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column label="订单号" prop="fosterCode"/>
    <el-table-column label="到期时间" prop="fosterTerm" sortable/>
    <el-table-column label="定金" prop="fosterMoney" sortable/>
    <el-table-column label="订单备注" prop="fosterInfo"/>
    <el-table-column label="宠物名" prop="petName"/>
    <el-table-column label="寄养人" prop="clientName"/>
    <el-table-column label="是否过期" prop="fosterTerm" sortable>
      <template #default="scope">
        <el-tag v-if="calculateDays(scope.row.fosterTerm)==='已过期'" type="danger">已过期</el-tag>
        <span v-else>{{ calculateDays(scope.row.fosterTerm) }}</span>
      </template>
    </el-table-column>
    <el-table-column fixed="right" label="操作">
      <template #default="scope">
        <el-button-group>
          <el-button :icon="Edit" circle type="warning" @click="showDialog(scope.row)"/>
          <el-button :icon="Delete" circle type="danger" @click="delOne(scope.row.fosterId)"/>
        </el-button-group>
      </template>
    </el-table-column>
  </el-table>
  <!--分页条-->
  <BackPage :total="fosterList.total" @changePu="changePuB"/>
  <!--修改、新增时的模态框-->
  <el-dialog v-model="modalView" :title="modalTit" draggable width="60%">
    <!--表单-->
    <el-form ref="myFormRef" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="订单备注" prop="fosterInfo">
        <el-input v-model="form.fosterInfo" clearable placeholder="无"/>
      </el-form-item>
      <el-form-item label="到期时间" prop="fosterTerm">
        <el-date-picker
            v-model="form.fosterTerm"
            format="YYYY-MM-DD"
            placeholder="寄养截止日期"
            type="date"
            value-format="YYYY-MM-DD"
        />
      </el-form-item>
      <el-form-item label="定金" prop="fosterMoney">
        <el-input-number
            v-model="form.fosterMoney"
            :max="5000"
            :min="100"
            :step="100"
        />
      </el-form-item>
      <el-form-item label="寄养人" prop="clientId">
        <el-select v-model="form.clientId" filterable placeholder="选择用户" @change="form.petId=null">
          <el-option v-for="d in clientNameL" :key="d.roleId" :label="d.roleName" :value="d.roleId"/>
        </el-select>
      </el-form-item>
      <el-form-item label="宠物" prop="petId">
        <el-select v-model="form.petId" filterable placeholder="选择宠物">
          <el-option v-for="d in petNameL" :key="d.roleId" :label="d.roleName" :value="d.roleId"/>
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
import {PageQuery, YFosterList} from "@/model/VO/BackQuery";
import {exampleFoster, Foster} from "@/model/entiy/Foster";
import {FosterDto} from "@/model/DO/FosterDto";
import {Page} from "@/model/DO/Page";
import {reqAddFoster, reqDelFoster, reqDelFosterBatch, reqFosterList, reqUpdateFoster} from "@/request/FosterApi";
import {ElMessage, ElMessageBox, FormInstance, FormRules} from "element-plus";
import {Res} from "@/request/Res";
import {reqClientNameHavePet} from "@/request/ClientApi";
import {NameVo} from "@/model/VO/NameVo";
import {reqPetNameByClientId} from "@/request/PetApi";

/**
 ┌───────────────────────────────────┐
 │=============生命周期相关============│
 └───────────────────────────────────┘
 */
onMounted(() => {
  sendQuery()
})

/**
 ┌───────────────────────────────────┐
 │=============表格查询相关============│
 └───────────────────────────────────┘
 */
// 查询的参数
const qp: YFosterList = reactive({
  petName: '',
  clientName: '',
  numPage: 1,
  pageSize: 6
})
// 列表展示
const fosterList = ref<Page<FosterDto>>({records: [], total: 0})
// 分页条
const changePuB = (val: PageQuery) => {
  qp.numPage = val.numPage
  qp.pageSize = val.pageSize
  sendQuery()
}
// 数据格式化
const calculateDays = (inputDate: string): string => {
  const today = new Date();
  const date = new Date(inputDate);
  // 确保输入的是有效的日期
  if (isNaN(date.getTime())) {
    return '日期格式不对';
  }
  if (today < date) {
    const diffTime = Math.abs(date.getTime() - today.getTime());
    return Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + "天";
  } else {
    return '已过期';
  }
}
// 数据总览
const sendQuery = (): void => {
  reqFosterList(qp).then(res => {
    fosterList.value = res
  })
}

/**
 ┌───────────────────────────────────┐
 │=============数据删除相关============│
 └───────────────────────────────────┘
 */
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Foster[]): void => {
  roleIdList.value = val.map(obj => obj.fosterId)
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
    reqDelFosterBatch(roleIdList.value).then(res => sureFlush(res))
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
    reqDelFoster(id).then(res => sureFlush(res))
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
const modalTit = ref<"新增寄养" | "修改寄养">("修改寄养")
// 添加
const addRoleB = (): void => {
  form.value = exampleFoster()
  myFormRef.value?.resetFields()
  reqClientNameHavePet(null).then(res => {
    clientNameL.value = res;
  })
  modalTit.value = "新增寄养"
  modalView.value = true
}
// 修改时展示模态框
const showDialog = (row: Foster) => {
  myFormRef.value?.clearValidate()
  // 赋值，防止响应式引用
  form.value.fosterId = row.fosterId
  form.value.fosterInfo = row.fosterInfo
  form.value.fosterTerm = row.fosterTerm
  form.value.fosterMoney = row.fosterMoney
  form.value.clientId = row.clientId
  form.value.petId = row.petId
  // ===赋值完成===
  reqClientNameHavePet(form.value.clientId).then(res => {
    clientNameL.value = res;
  })
  modalView.value = true
  modalTit.value = "修改寄养"
}

/**
 ┌───────────────────────────────────┐
 │=============表单校验相关============│
 └───────────────────────────────────┘
 */
// 表单的数据
const clientNameL = ref<NameVo[]>([]) // 下拉框用户名
const petNameL = ref<NameVo[]>([])
const form = ref<Foster>(exampleFoster()) // 空的默认值
const myFormRef = ref<FormInstance>()
// 校验表单并提交
const formSubmit = async (): Promise<void> => {
  await myFormRef.value?.validate((valid: boolean): void => {
    if (valid) {
      // 校验通过，提交
      modalView.value = false
      if (modalTit.value == "新增寄养") reqAddFoster(form.value).then(res => sureFlush(res))
      else if (modalTit.value === "修改寄养") reqUpdateFoster(form.value).then(res => sureFlush(res))
      else ElMessageBox.alert('模块框出错')
    }
  })
}
// 表单校验规则
const rules = reactive<FormRules>({
  "fosterInfo": [
    {required: true, message: '请输入描述', trigger: 'blur'},
  ],
  "fosterTerm": [
    {required: true, message: '请输入日期', trigger: 'blur'},
  ],
  "fosterMoney": [
    {required: true, message: '给钱', trigger: 'blur'},
  ],
  "clientId": [
    {required: true, message: '请选择用户', trigger: 'blur'},
  ],
  "petId": [
    {required: true, message: '请选择宠物', trigger: 'blur'},
  ]
})

// 在用户改变时，查询宠物名
watch(() => form.value.clientId, (newValue): void => {
  if (newValue == 0 || newValue == null) return;
  reqPetNameByClientId(newValue, <number>form.value.petId).then(res => {
    petNameL.value = res
  })
})
</script>

<style scoped>

</style>
