<template>
  <!--操作框-->
  <BackOpCol role="领养" @query="sendQuery" @addRole="addRoleB" @delBatch="delBatchB">
    <el-input v-model="qp.clientName" placeholder="申请人" size="large"/>
    <el-input v-model="qp.petName" placeholder="宠物名" size="large"/>
  </BackOpCol>

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
      <template #default="scope">
        <el-button-group>
          <el-button v-if="scope.row.inAdopt===2" type="success" :icon="Check" circle/>
          <el-button v-if="scope.row.inAdopt===2" type="info" :icon="Close" circle/>
          <el-button v-if="scope.row.inAdopt===1" type="warning" :icon="Edit" @click="showDialog" circle/>
          <el-button type="danger" :icon="Delete" circle/>
        </el-button-group>
      </template>
    </el-table-column>
  </el-table>
  <!--分页条-->
  <BackPage :total="adoptList.total" @changePu="changePuB"/>
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
import {Delete, Close, Check, Edit} from '@element-plus/icons-vue'
import BackOpCol from "@/components/BackOpCol.vue";
import BackPage from "@/components/BackPage.vue";
import {PageQuery, YAdoptList} from "@/modal/VO/BackQuery";
import {Adopt} from "@/modal/entiy/Adopt";
import {AdoptDto} from "@/modal/DO/AdoptDto";
import {moveT} from "@/utils/TimeUtil";
import {reqAdoptList} from "@/request/AdoptApi";
import {Page} from "@/modal/DO/Page";

onMounted(() => {
  sendQuery()
})
// 查询的参数
const qp: YAdoptList = reactive({
  petName: '',
  clientName: '',
  numPage: 1,
  pageSize: 6
})
const addRoleB = (): void => {
  console.log("添加领养")
  modalTit.value = "新增领养"
  modalView.value = true
}
const delBatchB = (): void => {
  console.log("批量删除")
}
// 列表展示
const adoptList = ref<Page<AdoptDto>>({records: [], total: 0})
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
const changePuB = (val: PageQuery) => {
  qp.numPage = val.numPage
  qp.pageSize = val.pageSize
  sendQuery()
}
// 数据总览
const sendQuery = (): void => {
  reqAdoptList(qp).then(res => {
    adoptList.value = res
  })
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