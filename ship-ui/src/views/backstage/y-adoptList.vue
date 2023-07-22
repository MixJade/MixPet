<template>
  <!--操作框-->
  <BackOpCol role="领养" @query="queryB" @addRole="addRoleB" @delBatch="delBatchB">
    <el-input v-model="qp.petName" placeholder="申请人" size="large"/>
    <el-input v-model="qp.clientName" placeholder="宠物名" size="large"/>
  </BackOpCol>
  <p></p>
  <!--列表展示-->
  <el-table :data="adoptList.records"
            stripe
            row-key="adoptId"
            style="width: 100%"
            max-height="430"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column prop="adoptCode" label="订单号"/>
    <el-table-column prop="adoptInfo" label="简介"/>
    <el-table-column prop="adoptMoney" label="定金" sortable/>
    <el-table-column prop="createTime" label="创建时间" :formatter="removeT" sortable/>
    <el-table-column prop="petName" label="宠物名"/>
    <el-table-column prop="clientName" label="申请人"/>
    <el-table-column prop="inAdopt" label="是否通过" sortable>
      <template #default="scope">
        <el-tag v-if="scope.row.inAdopt===2">待审核</el-tag>
        <el-tag v-else-if="scope.row.inAdopt===1" type="success">已通过</el-tag>
        <el-tag v-else type="danger">已拒绝</el-tag>
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
      :total="adoptList.total"
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
import {YAdoptList} from "@/modal/VO/BackQuery";
import {Adopt} from "@/modal/entiy/Adopt";
import {exampleAdoptBack} from "@/modal/DO/AdoptDto";
import {moveT} from "@/utils/TimeUtil";

// 查询的参数
const qp: YAdoptList = reactive({
  petName: '',
  clientName: '',
  numPage: 1,
  pageSize: 7
})
const queryB = (): void => {
  sendQuery()
}
const addRoleB = (): void => {
  console.log("添加领养")
  modalTit.value = "新增领养"
  modalView.value = true
}
const delBatchB = (): void => {
  console.log("批量删除")
}
// 列表展示
const adoptList = reactive(exampleAdoptBack())
const removeT = (row: Adopt) => {
  return moveT(row.createTime)
}
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Adopt[]): void => {
  roleIdList.value = val.map(obj => obj.adoptId)
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
const modalTit = ref<"新增领养" | "修改领养">("修改领养")
// 修改时展示模态框
const showDialog = () => {
  modalView.value = true
  modalTit.value = "修改领养"
}
</script>

<style scoped>

</style>