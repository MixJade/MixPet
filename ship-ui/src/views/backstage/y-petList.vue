<template>
  <div>
    <!--操作框-->
    <BackOpCol role="宠物" @addRole="addRoleB" @delBatch="delBatchB" @query="sendQuery">
      <el-input v-model="qp.petName" placeholder="宠物姓名" size="large" @keyup.enter="sendQuery"/>
      <el-input v-model="qp.clientName" placeholder="用户姓名" size="large" @keyup.enter="sendQuery"/>
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
        <template #default="scope">
          <el-button-group>
            <el-button :icon="Edit" circle type="warning" @click="showDialog(scope.row)"/>
            <el-button :icon="Delete" circle type="danger" @click="delOne(scope.row.petId)"/>
          </el-button-group>
        </template>
      </el-table-column>
    </el-table>
    <!--分页条-->
    <BackPage :total="petList.total" @changePu="changePuB"/>
    <!--修改、新增时的模态框-->
    <el-dialog v-model="modalView" :title="modalTit" draggable width="60%">
      <!--头像上传框-->
      <UpImg :photoNm="form.petPhoto" @upPhoto="changePhoto"/>
      <!--表单-->
      <el-form ref="myFormRef" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="宠物姓名" prop="petName">
          <el-input v-model="form.petName" clearable placeholder="宠物姓名"/>
        </el-form-item>
        <el-form-item label="宠物品种" prop="petVariety">
          <el-input v-model="form.petVariety" clearable placeholder="宠物品种"/>
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
              format="YYYY-MM-DD"
              placeholder="选择出生日期"
              style="width: 50%"
              type="date"
              value-format="YYYY-MM-DD"
          />
        </el-form-item>
        <el-form-item label="健康状况">
          <el-input v-model="form.petStatus" clearable placeholder="健康状况"/>
        </el-form-item>
        <el-form-item label="宠物主人">
          <el-select v-model="form.clientId" filterable placeholder="选择用户">
            <el-option v-for="c in clientNameL" :key="c.roleId" :label="c.roleName" :value="c.roleId"/>
          </el-select>
        </el-form-item>
        <el-form-item label="宠物近况">
          <el-input v-model="form.petInfo" clearable placeholder="宠物近况"/>
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
  </div>
</template>

<script lang="ts" setup>
import {Delete, Edit} from "@element-plus/icons-vue"
import {onMounted, reactive, ref} from 'vue'
import BackOpCol from "@/components/BackOpCol.vue";
import {PageQuery, YPetList} from "@/model/VO/BackQuery";
import {PetDto} from "@/model/DO/PetDto";
import {getAge} from "@/utils/TimeUtil";
import {examplePet, Pet} from "@/model/entiy/Pet";
import TagSex from "@/components/TagSex.vue";
import BackPage from "@/components/BackPage.vue";
import {reqAddPet, reqDelPet, reqDelPetBatch, reqPetList, reqUpdatePet} from "@/request/PetApi";
import {Page} from "@/model/DO/Page";
import {NameVo} from "@/model/VO/NameVo";
import {reqClientName} from "@/request/ClientApi";
import {ElMessageBox, FormInstance, FormRules} from "element-plus";
import UpImg from "@/components/UpImg.vue";
import {Res} from "@/request/Res";

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
// 点击添加按钮
const addRoleB = (): void => {
  modalTit.value = "新增宠物"
  form.value = examplePet()
  myFormRef.value?.resetFields()
  modalView.value = true
}
// 列表展示
const petList = ref<Page<PetDto>>({records: [], total: 0})
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Pet[]): void => {
  roleIdList.value = val.map(obj => obj.petId)
}
// 批量删除
const delBatchB = (): void => {
  if (roleIdList.value.length == 0) return
  reqDelPetBatch(roleIdList.value).then(res => sureFlush(res))
}
// 删除单个
const delOne = (id: number): void => {
  reqDelPet(id).then(res => sureFlush(res))
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
const showDialog = (row: Pet): void => {
  myFormRef.value?.clearValidate()
  modalTit.value = "修改宠物"
  form.value = row
  modalView.value = true
}
// 表单的数据
const clientNameL = ref<NameVo[]>([]) // 下拉框用户名
const form = ref<Pet>(examplePet()) // 空的默认值
const myFormRef = ref<FormInstance>()
// 校验表单并提交
const formSubmit = async (): Promise<void> => {
  await myFormRef.value?.validate((valid: boolean): void => {
    if (valid) {
      // 校验通过，提交
      modalView.value = false
      if (modalTit.value == "新增宠物") reqAddPet(form.value).then(res => sureFlush(res))
      else if (modalTit.value === "修改宠物") reqUpdatePet(form.value).then(res => sureFlush(res))
      else ElMessageBox.alert('模块框出错')
    }
  })
}
// 确定请求的返回值，然后刷新
const sureFlush = (res: Res): void => {
  if (res.code === 1) sendQuery()
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
// 头像参数的改变:子传父
const changePhoto = (val: string): void => {
  form.value.petPhoto = val
}
</script>

<style scoped>
</style>