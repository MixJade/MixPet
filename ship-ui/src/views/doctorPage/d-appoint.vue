<template>
  <div>
    <PageHead>查看挂号</PageHead>
    <ShakeBtn style="margin-left: 80px" @click="addRoleB">添加挂号</ShakeBtn>
    <MyRow>
      <AppointCard v-for="a in appoint" :key="a.appointmentId" :a="a" @toSuc="toSuc(a.appointmentId)"/>
    </MyRow>
    <!--添加的模态框-->
    <el-dialog v-model="modalView" title="新增挂号" draggable width="60%">
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
              type="datetime"
          />
        </el-form-item>
        <el-form-item label="主人" prop="clientId">
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
  </div>
</template>

<script lang="ts" setup>
import PageHead from "@/components/PageHead.vue";
import MyRow from "@/components/show/MyRow.vue";
import {AppointDto2} from "@/model/DO/AppointDto2";
import AppointCard from "@/components/card/AppointCard.vue";
import ShakeBtn from "@/components/button/ShakeBtn.vue";
import {reqDealAppoint, reqDoctorAddAppoint, reqDoctorAppoint} from "@/request/DoctorApi";
import {onMounted, reactive, ref, watch} from "vue";
import {NameVo} from "@/model/VO/NameVo";
import {Appoint, exampleAppoint} from "@/model/entiy/Appoint";
import {FormInstance, FormRules} from "element-plus";
import {reqPetNameByClientId} from "@/request/PetApi";
import {reqClientNameHavePet} from "@/request/ClientApi";

const appoint = ref<AppointDto2[]>([])
onMounted(() => {
  queryAppoint()
  reqClientNameHavePet(null).then(res => {
    clientNameL.value = res;
  })
})
// 查询订单
const queryAppoint = () => {
  reqDoctorAppoint().then(res => {
    appoint.value = res
  })
}

// 处理挂号
const toSuc = (id: number) => {
  reqDealAppoint({appointId: id, isSuc: true}).then(res => {
    if (res.code === 1) queryAppoint()
  })
}

// 表单的数据
const modalView = ref(false)
const clientNameL = ref<NameVo[]>([]) // 下拉框用户名
const petNameL = ref<NameVo[]>([])
const form = ref<Appoint>(exampleAppoint()) // 空的默认值
const myFormRef = ref<FormInstance>()
// 新增
const addRoleB = (): void => {
  form.value = exampleAppoint()
  myFormRef.value?.resetFields()
  modalView.value = true
}
// 校验表单并提交
const formSubmit = async (): Promise<void> => {
  await myFormRef.value?.validate((valid: boolean): void => {
    if (valid) {
      // 校验通过，提交
      modalView.value = false
      reqDoctorAddAppoint(form.value).then(res => {
        if (res.code === 1) queryAppoint()
      })
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
})

// 在用户改变时，查询宠物名
watch(() => form.value.clientId, (newValue): void => {
  if (newValue == 0 || newValue == null) return;
  reqPetNameByClientId(newValue, <number>form.value.petId).then(res => {
    petNameL.value = res
  })
})
</script>

<style lang="scss" scoped>
</style>
