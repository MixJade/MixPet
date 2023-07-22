<template>
  <!--操作框-->
  <BackOpCol role="用户" @query="queryB" @addRole="addRoleB" @delBatch="delBatchB">
    <el-input v-model="qp.clientName" placeholder="用户姓名" size="large"/>
  </BackOpCol>
  <p></p>
  <!--列表展示-->
  <el-table :data="clientList.records"
            stripe
            row-key="clientId"
            style="width: 100%"
            max-height="430"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column label="图像">
      <el-avatar src="/picture/lei-jun.jpg"/>
    </el-table-column>
    <el-table-column prop="clientName" label="用户名"/>
    <el-table-column prop="clientUsername" label="账号"/>
    <el-table-column label="性别">
      <template #default="scope">
        <TagSex :sex="scope.row.clientGender"/>
      </template>
    </el-table-column>
    <el-table-column prop="clientAge" label="年龄" sortable>
      <template #default="scope">{{ getAge(scope.row.clientAge) }}岁</template>
    </el-table-column>
    <el-table-column prop="clientTel" label="联系方式"/>
    <el-table-column prop="clientInfo" label="简介"/>
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
      :total="clientList.total"
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
import {YClientList} from "@/modal/VO/BackQuery";
import {getAge} from "@/utils/TimeUtil";
import {Client} from "@/modal/entiy/Client";
import {exampleClientBack} from "@/modal/entiy/Client";
import TagSex from "@/components/TagSex.vue";

// 查询的参数
const qp: YClientList = reactive({
  clientName: '',
  numPage: 1,
  pageSize: 7
})
const queryB = (): void => {
  console.log("查询,参数1", qp.clientName)
  sendQuery()
}
const addRoleB = (): void => {
  console.log("添加用户")
  modalTit.value = "新增用户"
  modalView.value = true
}
const delBatchB = (): void => {
  console.log("批量删除")
}
// 列表展示
const clientList = reactive(exampleClientBack())
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Client[]): void => {
  roleIdList.value = val.map(obj => obj.clientId)
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
  用户名：${qp.clientName},
  页码：${qp.numPage},大小：${qp.pageSize}`)
}
// 模态框
const modalView = ref(false)
const modalTit = ref<"新增用户" | "修改用户">("修改用户")
// 修改时展示模态框
const showDialog = () => {
  modalView.value = true
  modalTit.value = "修改用户"
}
</script>

<style scoped>

</style>