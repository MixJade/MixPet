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
      <el-button-group>
        <el-button :icon="Edit" circle type="warning" @click="showDialog"/>
        <el-button :icon="Delete" circle type="danger"/>
      </el-button-group>
    </el-table-column>
  </el-table>
  <!--分页条-->
  <BackPage :total="appointList.total" @changePu="changePuB"/>
  <!--修改、新增时的模态框-->
  <el-dialog v-model="modalView" :title="modalTit" draggable width="60%">
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

<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue'
import {Delete, Edit} from '@element-plus/icons-vue'
import BackOpCol from "@/components/BackOpCol.vue";
import BackPage from "@/components/BackPage.vue";
import {PageQuery, YAppointList} from "@/model/VO/BackQuery";
import {Appoint} from "@/model/entiy/Appoint";
import {AppointDto} from "@/model/DO/AppointDto";
import {reqAppointList} from "@/request/AppointApi";
import {Page} from "@/model/DO/Page";
import {moveT} from "@/utils/TimeUtil";

onMounted(() => {
  sendQuery()
})
// 查询的参数
const qp: YAppointList = reactive({
  seaName: '',
  seaType: 0,
  numPage: 1,
  pageSize: 6
})
const addRoleB = (): void => {
  console.log("添加挂号")
  modalTit.value = "新增挂号"
  modalView.value = true
}
const delBatchB = (): void => {
  console.log("批量删除")
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
const showDialog = () => {
  modalView.value = true
  modalTit.value = "修改挂号"
}
</script>

<style scoped>

</style>
