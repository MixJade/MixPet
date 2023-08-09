<template>
  <!--操作框-->
  <BackOpCol role="部门" @addRole="addRoleB" @delBatch="delBatchB" @query="sendQuery">
    <el-input v-model="qp.departmentName" placeholder="部门名" size="large" @keyup.enter="sendQuery"/>
  </BackOpCol>

  <!--列表展示-->
  <el-table :data="departmentList.records"
            max-height="80vh"
            row-key="departmentId"
            stripe
            style="width: 100%"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column label="部门名" prop="departmentName"/>
    <el-table-column label="简介" prop="departmentInfo"/>
    <el-table-column label="地址" prop="departmentAddress"/>
    <el-table-column fixed="right" label="操作">
      <el-button-group>
        <el-button :icon="Edit" circle type="warning" @click="showDialog"/>
        <el-button :icon="Delete" circle type="danger"/>
      </el-button-group>
    </el-table-column>
  </el-table>
  <!--分页条-->
  <BackPage :total="departmentList.total" @changePu="changePuB"/>
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
import {PageQuery, XDepartmentList} from "@/model/VO/BackQuery";
import {Department} from "@/model/entiy/Department";
import {reqDepartList} from "@/request/DepartApi";
import {Page} from "@/model/DO/Page";

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