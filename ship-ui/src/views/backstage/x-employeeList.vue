<template>
  <!--操作框-->
  <BackOpCol role="管理员" @query="queryB" @addRole="addRoleB" @delBatch="delBatchB">
    <el-input v-model="qp.employeeName" placeholder="管理员姓名" size="large"/>
  </BackOpCol>
  <p></p>
  <!--列表展示-->
  <el-table :data="employeeList.records"
            stripe
            row-key="employeeId"
            style="width: 100%"
            max-height="430"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column label="图像">
      <el-avatar src="/picture/lei-jun.jpg"/>
    </el-table-column>
    <el-table-column prop="employeeUsername" label="账号"/>
    <el-table-column prop="employeeName" label="姓名"/>
    <el-table-column prop="createTime" label="工龄" sortable>
      <template #default="scope">{{ getDaysFromToday(scope.row.createTime) }}</template>
    </el-table-column>
    <el-table-column prop="employeeTel" label="联系方式"/>
    <el-table-column label="职位">
      <template #default="scope">
        <el-tag>{{ getJob(scope.row.employeeLevel) }}</el-tag>
      </template>
    </el-table-column>
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
      :total="employeeList.total"
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
import {XEmployeeList} from "@/modal/VO/BackQuery";
import {getDaysFromToday} from "@/utils/TimeUtil";
import {getJob} from "@/utils/JobUtil";
import {Employee} from "@/modal/entiy/Employee";
import {exampleEmployeeBack} from "@/modal/entiy/Employee";

// 查询的参数
const qp: XEmployeeList = reactive({
  employeeName: '',
  numPage: 1,
  pageSize: 7
})
const queryB = (): void => {
  console.log("查询,参数1", qp.employeeName)
  sendQuery()
}
const addRoleB = (): void => {
  console.log("添加管理员")
  modalTit.value = "新增管理员"
  modalView.value = true
}
const delBatchB = (): void => {
  console.log("批量删除")
}
// 列表展示
const employeeList = reactive(exampleEmployeeBack())
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Employee[]): void => {
  roleIdList.value = val.map(obj => obj.employeeId)
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
const modalTit = ref<"新增管理员" | "修改管理员">("修改管理员")
// 修改时展示模态框
const showDialog = () => {
  modalView.value = true
  modalTit.value = "修改管理员"
}
</script>

<style scoped>

</style>