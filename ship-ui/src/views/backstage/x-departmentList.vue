<template>
  <!--操作框-->
  <BackOpCol role="部门" @query="queryB" @addRole="addRoleB" @delBatch="delBatchB">
    <el-input v-model="qp.departmentName" placeholder="部门名" size="large"/>
  </BackOpCol>
  <p></p>
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
  <p></p>
  <!--分页条-->
  <el-pagination
      background
      layout="total, sizes,prev, pager, next"
      hide-on-single-page
      :page-sizes="[7, 10, 13, 16]"
      :page-size="qp.pageSize"
      :current-page="qp.numPage"
      :total="departmentList.total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
  />
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
import {reactive, ref} from 'vue'
import {Delete, Edit} from '@element-plus/icons-vue'
import BackOpCol from "@/components/BackOpCol.vue";
import {XDepartmentList} from "@/modal/VO/BackQuery";
import {Department} from "@/modal/entiy/Department";
import {exampleDepartBack} from "@/modal/entiy/Department";

// 查询的参数
const qp: XDepartmentList = reactive({
  departmentName: '',
  numPage: 1,
  pageSize: 7
})
const queryB = (): void => {
  console.log("查询,参数1", qp.departmentName)
  sendQuery()
}
const addRoleB = (): void => {
  console.log("添加部门")
  modalTit.value = "新增部门"
  modalView.value = true
}
const delBatchB = (): void => {
  console.log("批量删除")
}
// 列表展示
const departmentList = reactive(exampleDepartBack())
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Department[]): void => {
  roleIdList.value = val.map(obj => obj.departmentId)
  console.log(roleIdList.value)
}
// 分页条
const handleSizeChange = (val: number) => {
  console.log(`${val} items per page`)
  sendQuery()
}
const handleCurrentChange = (val: number) => {
  console.log(`current page: ${val}`)
  sendQuery()
}
// 数据总览
const sendQuery = (): void => {
  console.log(`当前的页面参数，
  页码：${qp.numPage},大小：${qp.pageSize}`)
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