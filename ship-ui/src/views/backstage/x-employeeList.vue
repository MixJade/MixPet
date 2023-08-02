<template>
  <!--操作框-->
  <BackOpCol role="管理员" @addRole="addRoleB" @delBatch="delBatchB" @query="sendQuery">
    <el-input v-model="qp.employeeName" placeholder="管理员姓名" size="large"/>
  </BackOpCol>

  <!--列表展示-->
  <el-table :data="employeeList.records"
            max-height="80vh"
            row-key="employeeId"
            stripe
            style="width: 100%"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column label="图像">
      <template #default="scope">
        <el-avatar :src="'/api/common/download?name='+scope.row.employeePhoto"/>
      </template>
    </el-table-column>
    <el-table-column label="账号" prop="employeeUsername"/>
    <el-table-column label="姓名" prop="employeeName"/>
    <el-table-column label="工龄" prop="createTime" sortable>
      <template #default="scope">{{ getDaysFromToday(scope.row.createTime) }}</template>
    </el-table-column>
    <el-table-column label="联系方式" prop="employeeTel"/>
    <el-table-column label="职位">
      <template #default="scope">
        <el-tag>{{ getJob(scope.row.employeeLevel) }}</el-tag>
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
  <BackPage :total="employeeList.total" @changePu="changePuB"/>
  <!--修改、新增时的模态框-->
  <el-dialog v-model="modalView" :title="modalTit" draggable width="30%">
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
import {PageQuery, XEmployeeList} from "@/model/VO/BackQuery";
import {getDaysFromToday} from "@/utils/TimeUtil";
import {getJob} from "@/utils/JobUtil";
import {Employee} from "@/model/entiy/Employee";
import {reqEmployeeList} from "@/request/EmployeeApi";
import {Page} from "@/model/DO/Page";

onMounted(() => {
  sendQuery()
})
// 查询的参数
const qp: XEmployeeList = reactive({
  employeeName: '',
  numPage: 1,
  pageSize: 7
})
const addRoleB = (): void => {
  console.log("添加管理员")
  modalTit.value = "新增管理员"
  modalView.value = true
}
const delBatchB = (): void => {
  console.log("批量删除")
}
// 列表展示
const employeeList = ref<Page<Employee>>({records: [], total: 0})
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Employee[]): void => {
  roleIdList.value = val.map(obj => obj.employeeId)
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
  reqEmployeeList(qp).then(res => {
    employeeList.value = res
  })
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