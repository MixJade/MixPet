<template>
  <!--操作框-->
  <BackOpCol role="用户" @query="sendQuery" @addRole="addRoleB" @delBatch="delBatchB">
    <el-input v-model="qp.clientName" placeholder="用户姓名" size="large"/>
  </BackOpCol>

  <!--列表展示-->
  <el-table :data="clientList.records"
            stripe
            row-key="clientId"
            style="width: 100%"
            max-height="430"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column label="图像">
      <template #default="scope">
        <el-avatar :src="'/api/common/download?name='+scope.row.clientPhoto"/>
      </template>
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
  <!--分页条-->
  <BackPage :total="clientList.total" @changePu="changePuB"/>
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
import {PageQuery, YClientList} from "@/modal/VO/BackQuery";
import {getAge} from "@/utils/TimeUtil";
import {Client} from "@/modal/entiy/Client";
import TagSex from "@/components/TagSex.vue";
import {reqClientList} from "@/request/ClientApi";
import {Page} from "@/modal/DO/Page";

onMounted(() => {
  sendQuery()
})
// 查询的参数
const qp: YClientList = reactive({
  clientName: '',
  numPage: 1,
  pageSize: 6
})
const addRoleB = (): void => {
  console.log("添加用户")
  modalTit.value = "新增用户"
  modalView.value = true
}
const delBatchB = (): void => {
  console.log("批量删除")
}
// 列表展示
const clientList = ref<Page<Client>>({records: [], total: 0})
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Client[]): void => {
  roleIdList.value = val.map(obj => obj.clientId)
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
  reqClientList(qp).then(res => {
    clientList.value = res
  })
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