<template>
  <!--操作框-->
  <BackOpCol role="部门" @query="sendQuery" @addRole="addRoleB" @delBatch="delBatchB">
    <el-input v-model="qp.departmentName" placeholder="部门名" size="large"/>
  </BackOpCol>

  <!--列表展示-->
  <el-table :data="departmentList.records"
            stripe
            row-key="departmentId"
            style="width: 100%"
            max-height="430"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column prop="departmentName" label="部门名"/>
    <el-table-column prop="departmentInfo" label="简介"/>
    <el-table-column prop="departmentAddress" label="地址"/>
    <el-table-column fixed="right" label="操作">
      <el-button-group>
        <el-button type="warning" :icon="Edit" @click="showDialog" circle/>
        <el-button type="danger" :icon="Delete" circle/>
      </el-button-group>
    </el-table-column>
  </el-table>
  <!--分页条-->
  <BackPage :total="departmentList.total" @changePu="changePuB"/>
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
import {PageQuery, XDepartmentList} from "@/modal/VO/BackQuery";
import {Department} from "@/modal/entiy/Department";
import {reqDepartList} from "@/request/DepartApi";
import {Page} from "@/modal/DO/Page";

onMounted(() => {
  sendQuery()
})
// 查询的参数
const qp: XDepartmentList = reactive({
  departmentName: '',
  numPage: 1,
  pageSize: 7
})
const addRoleB = (): void => {
  console.log("添加部门")
  modalTit.value = "新增部门"
  modalView.value = true
}
const delBatchB = (): void => {
  console.log("批量删除")
}
// 列表展示
const departmentList = ref<Page<Department>>({records: [], total: 0})
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Department[]): void => {
  roleIdList.value = val.map(obj => obj.departmentId)
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
  reqDepartList(qp).then(res => {
    departmentList.value = res
  })
}
// 模态框
const modalView = ref(false)
const modalTit = ref<"新增部门" | "修改部门">("修改部门")
// 修改时展示模态框
const showDialog = () => {
  modalView.value = true
  modalTit.value = "修改部门"
}
</script>

<style scoped>

</style>