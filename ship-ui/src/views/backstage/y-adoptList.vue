<template>
  <!--操作框-->
  <BackOpCol role="领养" @addRole="addRoleB" @delBatch="delBatchB" @query="sendQuery">
    <el-input v-model="qp.clientName" placeholder="申请人" size="large" @keyup.enter="sendQuery"/>
    <el-input v-model="qp.petName" placeholder="宠物名" size="large" @keyup.enter="sendQuery"/>
  </BackOpCol>

  <!--列表展示-->
  <el-table :data="adoptList.records"
            max-height="80vh"
            row-key="adoptId"
            stripe
            style="width: 100%"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column label="简介" prop="adoptInfo"/>
    <el-table-column label="定金" prop="adoptMoney" sortable/>
    <el-table-column :formatter="removeT" label="创建时间" prop="createTime" sortable/>
    <el-table-column label="宠物名" prop="petName"/>
    <el-table-column label="申请人" prop="clientName"/>
    <el-table-column label="是否通过" prop="inAdopt" sortable>
      <template #default="scope">
        <el-tag v-if="scope.row.inAdopt===2">待审核</el-tag>
        <el-tag v-else-if="scope.row.inAdopt===1" type="success">已通过</el-tag>
        <el-tag v-else type="danger">已拒绝</el-tag>
      </template>
    </el-table-column>
    <el-table-column fixed="right" label="操作">
      <template #default="scope">
        <el-button-group>
          <el-button v-if="scope.row.inAdopt===2" :icon="Check" circle type="success"/>
          <el-button v-if="scope.row.inAdopt===2" :icon="Close" circle type="info"/>
          <el-button v-if="scope.row.inAdopt===1" :icon="Edit" circle type="warning" @click="showDialog"/>
          <el-button :icon="Delete" circle type="danger"/>
        </el-button-group>
      </template>
    </el-table-column>
  </el-table>
  <!--分页条-->
  <BackPage :total="adoptList.total" @changePu="changePuB"/>
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
import {Check, Close, Delete, Edit} from '@element-plus/icons-vue'
import BackOpCol from "@/components/BackOpCol.vue";
import BackPage from "@/components/BackPage.vue";
import {PageQuery, YAdoptList} from "@/model/VO/BackQuery";
import {Adopt} from "@/model/entiy/Adopt";
import {AdoptDto} from "@/model/DO/AdoptDto";
import {moveT} from "@/utils/TimeUtil";
import {reqAdoptList} from "@/request/AdoptApi";
import {Page} from "@/model/DO/Page";

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