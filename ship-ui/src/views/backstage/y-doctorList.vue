<template>
  <!--操作框-->
  <BackOpCol role="医生" @query="sendQuery" @addRole="addRoleB" @delBatch="delBatchB">
    <el-input v-model="qp.doctorName" placeholder="医生姓名" size="large"/>
    <el-input v-model="qp.departmentName" placeholder="科室名" size="large"/>
  </BackOpCol>

  <!--列表展示-->
  <el-table :data="doctorList.records"
            stripe
            row-key="doctorId"
            style="width: 100%"
            max-height="430"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column label="图像">
      <template #default="scope">
        <el-avatar :src="'/api/common/download?name='+scope.row.doctorPhoto"/>
      </template>
    </el-table-column>
    <el-table-column prop="doctorName" label="医生名"/>
    <el-table-column prop="doctorCode" label="工号"/>
    <el-table-column label="性别">
      <template #default="scope">
        <TagSex :sex="scope.row.doctorGender"/>
      </template>
    </el-table-column>
    <el-table-column prop="doctorAge" label="年龄" sortable>
      <template #default="scope">{{ getAge(scope.row.doctorAge) }}岁</template>
    </el-table-column>
    <el-table-column prop="doctorTel" label="联系方式"/>
    <el-table-column prop="doctorInfo" label="简介"/>
    <el-table-column label="科室">
      <template #default="scope">
        <el-tag>{{ scope.row.departmentName }}</el-tag>
      </template>
    </el-table-column>
    <el-table-column label="职位">
      <template #default="scope">
        <el-tag>{{ scope.row.doctorJob }}</el-tag>
      </template>
    </el-table-column>
    <el-table-column fixed="right" label="操作">
      <el-button-group>
        <el-button type="warning" :icon="Edit" @click="showDialog" circle/>
        <el-button type="danger" :icon="Delete" circle/>
      </el-button-group>
    </el-table-column>
  </el-table>
  <!--分页条-->
  <BackPage :total="doctorList.total" @changePu="changePuB"/>
  <!--修改、新增时的模态框-->
  <el-dialog v-model="modalView" :title="modalTit" width="30%" draggable>
    <span>It's a draggable Dialog</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="modalView = false">取消</el-button>
        <el-button type="primary" @click="modalView = false">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import {onMounted, reactive, ref} from 'vue'
import {Delete, Edit} from '@element-plus/icons-vue'
import BackOpCol from "@/components/BackOpCol.vue";
import BackPage from "@/components/BackPage.vue";
import {PageQuery, YDoctorList} from "@/modal/VO/BackQuery";
import {getAge} from "@/utils/TimeUtil";
import {Doctor} from "@/modal/entiy/Doctor";
import {DoctorDto} from "@/modal/DO/DoctorDto";
import TagSex from "@/components/TagSex.vue";
import {Page} from "@/modal/DO/Page";
import {reqDoctorList} from "@/request/DoctorApi";

onMounted(() => {
  sendQuery()
})
// 查询的参数
const qp: YDoctorList = reactive({
  doctorName: '',
  departmentName: '',
  numPage: 1,
  pageSize: 7
})
const addRoleB = (): void => {
  console.log("添加医生")
  modalTit.value = "新增医生"
  modalView.value = true
}
const delBatchB = (): void => {
  console.log("批量删除")
}
// 列表展示
const doctorList = ref<Page<DoctorDto>>({records: [], total: 0})
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Doctor[]): void => {
  roleIdList.value = val.map(obj => obj.doctorId)
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
  reqDoctorList(qp).then(res => {
    doctorList.value = res
  })
}
// 模态框
const modalView = ref(false)
const modalTit = ref<"新增医生" | "修改医生">("修改医生")
// 修改时展示模态框
const showDialog = () => {
  modalView.value = true
  modalTit.value = "修改医生"
}
</script>

<style scoped>

</style>