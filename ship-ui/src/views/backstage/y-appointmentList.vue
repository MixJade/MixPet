<template>
  <!--操作框-->
  <BackOpCol role="挂号" @query="sendQuery" @addRole="addRoleB" @delBatch="delBatchB">
    <el-select v-model="qp.seaType" placeholder="Select" size="large">
      <el-option label="挂号人" :value="0"/>
      <el-option label="宠物" :value="1"/>
      <el-option label="医生" :value="2"/>
      <el-option label="科室" :value="3"/>
    </el-select>
    <el-input v-model="qp.seaName" placeholder="名称" size="large"/>
  </BackOpCol>

  <!--列表展示-->
  <el-table :data="appointList.records"
            stripe
            row-key="appointId"
            style="width: 100%"
            max-height="430"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column prop="appointmentDate" label="就诊时间" sortable/>
    <el-table-column prop="appointmentInfo" label="简介"/>
    <el-table-column prop="createTime" label="创建时间" sortable/>
    <el-table-column prop="petName" label="宠物名"/>
    <el-table-column prop="clientName" label="挂号人"/>
    <el-table-column prop="doctorName" label="医生"/>
    <el-table-column label="科室">
      <template #default="scope">
        <el-tag>{{ scope.row.departmentName }}</el-tag>
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
  <BackPage :total="appointList.total" @changePu="changePuB"/>
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
import {PageQuery, YAppointList} from "@/modal/VO/BackQuery";
import {Appoint} from "@/modal/entiy/Appoint";
import {AppointDto} from "@/modal/DO/AppointDto";
import {reqAppointList} from "@/request/AppointApi";
import {Page} from "@/modal/DO/Page";

onMounted(() => {
  sendQuery()
})
// 查询的参数
const qp: YAppointList = reactive({
  seaName: '',
  seaType: 0,
  numPage: 1,
  pageSize: 7
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