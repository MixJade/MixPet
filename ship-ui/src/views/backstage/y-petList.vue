<template>
  <!--操作框-->
  <BackOpCol role="宠物" @addRole="addRoleB" @delBatch="delBatchB" @query="sendQuery">
    <el-input v-model="qp.petName" placeholder="宠物姓名" size="large"/>
    <el-input v-model="qp.clientName" placeholder="用户姓名" size="large"/>
  </BackOpCol>
  <!--列表展示-->
  <el-table :data="petList.records"
            max-height="80vh"
            row-key="petId"
            stripe
            style="width: 100%"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column label="图像">
      <template #default="scope">
        <el-avatar :src="'/api/common/download?name='+scope.row.petPhoto"/>
      </template>
    </el-table-column>
    <el-table-column label="宠物名" prop="petName"/>
    <el-table-column label="品种">
      <template #default="scope">
        <el-tag>{{ scope.row.petVariety }}</el-tag>
      </template>
    </el-table-column>
    <el-table-column label="性别">
      <template #default="scope">
        <TagSex :sex="scope.row.petSex"/>
      </template>
    </el-table-column>
    <el-table-column label="年龄" prop="petAge" sortable>
      <template #default="scope">{{ getAge(scope.row.petAge) }}岁</template>
    </el-table-column>
    <el-table-column label="健康状态">
      <template #default="scope">
        <el-tag>{{ scope.row.petStatus }}</el-tag>
      </template>
    </el-table-column>
    <el-table-column label="近况" prop="petInfo"/>
    <el-table-column label="主人" prop="clientName"/>
    <el-table-column fixed="right" label="操作">
      <el-button-group>
        <el-button :icon="Edit" circle type="warning" @click="showDialog"/>
        <el-button :icon="Delete" circle type="danger"/>
      </el-button-group>
    </el-table-column>
  </el-table>
  <!--分页条-->
  <BackPage :total="petList.total" @changePu="changePuB"/>
  <!--修改、新增时的模态框-->
  <el-dialog v-model="modalView" :title="modalTit" draggable width="60%">
    <!--表单-->
    <el-form ref="myFormRef" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="宠物姓名" prop="petName">
        <el-input v-model="form.petName" placeholder="宠物姓名" clearable/>
      </el-form-item>
      <el-form-item label="宠物品种" prop="petVariety">
        <el-input v-model="form.petVariety" placeholder="宠物品种" clearable/>
      </el-form-item>
      <el-form-item label="宠物性别">
        <el-radio-group v-model="form.petSex">
          <el-radio :label="true" border>公</el-radio>
          <el-radio :label="false" border>母</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="出生日期">
        <el-date-picker
            v-model="form.petAge"
            type="date"
            placeholder="选择出生日期"
            style="width: 50%"
        />
      </el-form-item>
      <el-form-item label="健康状况">
        <el-input v-model="form.petStatus" placeholder="健康状况" clearable/>
      </el-form-item>
      <el-form-item label="宠物主人">
        <el-select v-model="form.clientId" filterable placeholder="选择用户">
          <el-option v-for="c in clientNameL" :key="c.roleId" :label="c.roleName" :value="c.roleId"/>
        </el-select>
      </el-form-item>
      <el-form-item label="宠物近况">
        <el-input v-model="form.petInfo" placeholder="宠物近况" clearable/>
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
import {PageQuery, YPetList} from "@/modal/VO/BackQuery";
import {PetDto} from "@/modal/DO/PetDto";
import {getAge} from "@/utils/TimeUtil";
import {examplePet, Pet} from "@/modal/entiy/Pet";
import TagSex from "@/components/TagSex.vue";
import BackPage from "@/components/BackPage.vue";
import {reqPetList} from "@/request/PetApi";
import {Page} from "@/modal/DO/Page";
import {Name} from "@/modal/entiy/Name";
import {reqClientName} from "@/request/ClientApi";
import {FormInstance, FormRules} from "element-plus";

onMounted(() => {
  sendQuery()
  reqClientName().then(res => {
    clientNameL.value = res
  })
})
// 查询的参数
const qp = reactive<YPetList>({
  petName: '',
  clientName: '',
  numPage: 1,
  pageSize: 6
})
const addRoleB = (): void => {
  console.log("添加宠物")
  modalTit.value = "新增宠物"
  form.value = examplePet()
  myFormRef.value?.resetFields()
  modalView.value = true
}
const delBatchB = (): void => {
  console.log("批量删除")
}
// 列表展示
const petList = ref<Page<PetDto>>({records: [], total: 0})
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Pet[]): void => {
  roleIdList.value = val.map(obj => obj.petId)
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
  reqPetList(qp).then(res => {
    petList.value = res
  })
}
// 模态框
const modalView = ref(false)
const modalTit = ref<"新增宠物" | "修改宠物">("修改宠物")
// 修改时展示模态框
const showDialog = (): void => {
  modalView.value = true
  modalTit.value = "修改宠物"
}
// 表单的数据
const clientNameL = ref<Name[]>([]) // 下拉框用户名
const form = ref<Pet>(examplePet()) // 空的默认值
const myFormRef = ref<FormInstance>()
const formSubmit = async (formEl: FormInstance): Promise<void> => {
  if (!formEl) return
  await myFormRef.value?.validate((valid, fields) => {
    if (valid) {
      console.log('submit!')
      modalView.value = false
    } else {
      console.log('error submit!', fields)
    }
  })
  console.log(form)
}
// 表单校验规则
const rules = reactive<FormRules>({
  "petName": [
    {required: true, message: '请给宠物起一个名字', trigger: 'blur'},
  ],
  "petVariety": [
    {required: true, message: '请输入宠物的品种', trigger: 'blur'},
  ],
})
</script>

<style scoped>
</style>