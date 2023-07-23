<template>
  <!--操作框-->
  <BackOpCol role="寄养" @query="sendQuery" @addRole="addRoleB" @delBatch="delBatchB">
    <el-input v-model="qp.petName" placeholder="宠物名" size="large"/>
    <el-input v-model="qp.clientName" placeholder="寄养人" size="large"/>
  </BackOpCol>
  <!--列表展示-->
  <el-table :data="fosterList.records"
            stripe
            row-key="fosterId"
            style="width: 100%"
            max-height="430"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column prop="fosterCode" label="订单号"/>
    <el-table-column prop="fosterTerm" label="到期时间" sortable/>
    <el-table-column prop="fosterMoney" label="定金" sortable/>
    <el-table-column prop="fosterInfo" label="订单备注"/>
    <el-table-column prop="petName" label="宠物名"/>
    <el-table-column prop="clientName" label="寄养人"/>
    <el-table-column prop="fosterTerm" label="是否过期" sortable>
      <template #default="scope">
        <el-tag v-if="calculateDays(scope.row.fosterTerm)==='已过期'" type="danger">已过期</el-tag>
        <span v-else>{{ calculateDays(scope.row.fosterTerm) }}</span>
      </template>
    </el-table-column>
    <el-table-column fixed="right" label="操作">
      <el-button-group>
        <el-button type="warning" :icon="Edit" @click="showDialog" circle/>
        <el-button type="danger" :icon="Delete" circle/>
      </el-button-group>
    </el-table-column>
  </el-table>
  <!--分页条-->
  <BackPage :total="fosterList.total" @changePu="changePuB"/>
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
import {PageQuery, YFosterList} from "@/modal/VO/BackQuery";
import {Foster} from "@/modal/entiy/Foster";
import {FosterDto} from "@/modal/DO/FosterDto";
import {Page} from "@/modal/DO/Page";
import {reqFosterList} from "@/request/FosterApi";

onMounted(() => {
  sendQuery()
})
// 查询的参数
const qp: YFosterList = reactive({
  petName: '',
  clientName: '',
  numPage: 1,
  pageSize: 7
})
const addRoleB = (): void => {
  console.log("添加寄养")
  modalTit.value = "新增寄养"
  modalView.value = true
}
const delBatchB = (): void => {
  console.log("批量删除")
}
// 列表展示
const fosterList = ref<Page<FosterDto>>({records: [], total: 0})
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Foster[]): void => {
  roleIdList.value = val.map(obj => obj.fosterId)
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
  reqFosterList(qp).then(res => {
    fosterList.value = res
  })
}
// 模态框
const modalView = ref(false)
const modalTit = ref<"新增寄养" | "修改寄养">("修改寄养")
// 修改时展示模态框
const showDialog = () => {
  modalView.value = true
  modalTit.value = "修改寄养"
}
// 数据格式化
const calculateDays = (inputDate: string): string => {
  const today = new Date();
  const date = new Date(inputDate);
  // 确保输入的是有效的日期
  if (isNaN(date.getTime())) {
    return '日期格式不对';
  }
  if (today < date) {
    const diffTime = Math.abs(date.getTime() - today.getTime());
    return Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + "天";
  } else {
    return '已过期';
  }
}
</script>

<style scoped>

</style>