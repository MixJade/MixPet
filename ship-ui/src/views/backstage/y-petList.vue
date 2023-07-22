<template>
  <!--操作框-->
  <BackOpCol role="宠物" @query="queryB" @addRole="addRoleB" @delBatch="delBatchB">
    <el-input v-model="qp.petName" placeholder="宠物姓名" size="large"/>
    <el-input v-model="qp.clientName" placeholder="用户姓名" size="large"/>
  </BackOpCol>
  <p></p>
  <!--列表展示-->
  <el-table :data="petList.records"
            stripe
            row-key="petId"
            style="width: 100%"
            max-height="430"
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column label="图像">
      <el-avatar src="/picture/pet-ex.jpg"/>
    </el-table-column>
    <el-table-column prop="petName" label="宠物名"/>
    <el-table-column label="品种">
      <template #default="scope">
        <el-tag>{{ scope.row.petVariety }}</el-tag>
      </template>
    </el-table-column>
    <el-table-column label="性别">
      <template #default="scope">
        <TagSex :sex="scope.row.petSex"/>
      </template>
    </el-table-column>
    <el-table-column prop="petAge" label="年龄" sortable>
      <template #default="scope">{{ getAge(scope.row.petAge) }}岁</template>
    </el-table-column>
    <el-table-column label="健康状态">
      <template #default="scope">
        <el-tag>{{ scope.row.petStatus }}</el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="petInfo" label="近况"/>
    <el-table-column prop="clientName" label="主人"/>
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
      :total="petList.total"
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
import {YPetList} from "@/modal/VO/BackQuery";
import {examplePetBack} from "@/modal/DO/PetDto";
import {getAge} from "@/utils/TimeUtil";
import {Pet} from "@/modal/entiy/Pet";
import TagSex from "@/components/TagSex.vue";

// 查询的参数
const qp: YPetList = reactive({
  petName: '',
  clientName: '',
  numPage: 1,
  pageSize: 7
})
const queryB = (): void => {
  console.log("查询，参数1：", qp.petName, "参数2", qp.clientName)
  sendQuery()
}
const addRoleB = (): void => {
  console.log("添加宠物")
  modalTit.value = "新增宠物"
  modalView.value = true
}
const delBatchB = (): void => {
  console.log("批量删除")
}
// 列表展示
const petList = reactive(examplePetBack())
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: Pet[]): void => {
  roleIdList.value = val.map(obj => obj.petId)
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
  用户名：${qp.clientName},宠物：${qp.petName},
  页码：${qp.numPage},大小：${qp.pageSize}`)
}
// 模态框
const modalView = ref(false)
const modalTit = ref<"新增宠物" | "修改宠物">("修改宠物")
// 修改时展示模态框
const showDialog = () => {
  modalView.value = true
  modalTit.value = "修改宠物"
}
</script>

<style scoped>

</style>