<template>
  <!--操作框-->
  <BackOpCol role="公告" @addRole="addRoleB" @delBatch="delBatchB" @query="sendQuery">
    <el-input v-model="qp.noticeName" placeholder="公告名" size="large" @keyup.enter="sendQuery"/>
    <template #right>
      <el-button plain size="large" type="primary" @click="delImg">清理垃圾</el-button>
    </template>
  </BackOpCol>
  <!--列表展示-->
  <el-table :data="noticeList.records"
            max-height="80vh"
            row-key="noticeId"
            stripe
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="30"/>
    <el-table-column label="标题" prop="noticeTitle"/>
    <el-table-column :formatter="removeT_1" label="修改时间" prop="noticeDate" sortable/>
    <el-table-column label="修改人" prop="updateName"/>
    <el-table-column :formatter="removeT_2" label="创建时间" prop="createTime" sortable/>
    <el-table-column label="创建人" prop="createName"/>
    <el-table-column label="是否禁用" prop="clientName">
      <template #default="scope">
        <el-tag v-if="scope.row.isDisable" type="danger">已禁用</el-tag>
        <el-tag v-else>已启用</el-tag>
      </template>
    </el-table-column>
    <el-table-column fixed="right" label="操作">
      <template #default="scope">
        <el-button-group>
          <el-button :icon="Edit" circle type="warning" @click="showDialog(scope.row)"/>
          <el-button v-if="scope.row.isDisable" :icon="Check" circle type="success"
                     @click="disableSet(scope.row.noticeId,true)"/>
          <el-button v-else :icon="Close" circle type="info"
                     @click="disableSet(scope.row.noticeId,false)"/>
          <el-button :icon="Delete" circle type="danger" @click="delOne(scope.row.noticeId)"/>
        </el-button-group>
      </template>
    </el-table-column>
  </el-table>
  <!--分页条-->
  <BackPage :total="noticeList.total" @changePu="changePuB"/>
  <!--修改、新增时的模态框-->
  <el-dialog v-model="modalView" :title="modalTit" draggable width="60%">
    <!--表单-->
    <el-form ref="myFormRef" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="标题" prop="noticeTitle">
        <el-input v-model="form.noticeTitle" clearable placeholder="无"/>
      </el-form-item>
      <el-form-item label="状态">
        <el-tag v-if="form.isDisable" type="danger">已禁用</el-tag>
        <el-tag v-else>已启用</el-tag>
      </el-form-item>
      <el-form-item label="内容" prop="textNotice" style="width: 80%">
        <el-input type="textarea" :rows="9" v-model="form.textNotice" clearable placeholder="无"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="modalView = false">取消</el-button>
        <el-button type="primary" @click="formSubmit">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue'
import {Check, Close, Delete, Edit} from '@element-plus/icons-vue'
import BackOpCol from "@/components/row/BackOpCol.vue";
import BackPage from "@/components/row/BackPage.vue";
import {PageQuery, YNoticeList} from "@/model/VO/BackQuery";
import {exampleNotice, NoticeDto} from "@/model/DO/NoticeDto";
import {
  reqAddNotice,
  reqDelImg,
  reqDelNotice,
  reqDelNoticeBatch,
  reqDisableSet,
  reqNoticeList,
  reqUpdateNotice
} from "@/request/NoticeApi";
import {Page} from "@/model/DO/Page";
import {moveT} from "@/utils/TimeUtil";
import {Res} from "@/request/Res";
import {ElMessage, ElMessageBox, FormInstance, FormRules} from "element-plus";

/**
 ┌───────────────────────────────────┐
 │=============生命周期相关============│
 └───────────────────────────────────┘
 */
onMounted(() => {
  sendQuery()
})

/**
 ┌───────────────────────────────────┐
 │=============表格查询相关============│
 └───────────────────────────────────┘
 */
// 查询的参数
const qp: YNoticeList = reactive({
  noticeName: '',
  numPage: 1,
  pageSize: 6
})
// 列表展示
const noticeList = ref<Page<NoticeDto>>({records: [], total: 0})
const removeT_1 = (row: NoticeDto) => {
  return moveT(<string>row.updateTime)
}
const removeT_2 = (row: NoticeDto) => {
  return moveT(<string>row.createTime)
}
// 分页条
const changePuB = (val: PageQuery) => {
  qp.numPage = val.numPage
  qp.pageSize = val.pageSize
  sendQuery()
}
// 数据总览
const sendQuery = (): void => {
  reqNoticeList(qp).then(res => {
    noticeList.value = res
  })
}

/**
 ┌───────────────────────────────────┐
 │=============数据删除相关============│
 └───────────────────────────────────┘
 */
// 多选与反选
const roleIdList = ref<number[]>([])
const handleSelectionChange = (val: NoticeDto[]): void => {
  roleIdList.value = val.map(obj => obj.noticeId)
}
// 批量删除
const delBatchB = (): void => {
  if (roleIdList.value.length == 0) return
  ElMessageBox.confirm(
      '在您做出指示之前，我还是要再次确认，您确定要焚毁这些的订单吗？',
      '删除多个确认',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(() => {
    reqDelNoticeBatch(roleIdList.value).then(res => sureFlush(res))
  }).catch(() => {
    ElMessage.info('删除取消')
  })
}
// 删除单个
const delOne = (id: number): void => {
  ElMessageBox.confirm(
      `解脱一纸束缚的契约，赐予其尘归尘的命运。`,
      '删除单个确认',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(() => {
    reqDelNotice(id).then(res => sureFlush(res))
  }).catch(() => {
    ElMessage.info('删除取消')
  })
}
// 确定请求的返回值，然后刷新
const sureFlush = (res: Res): void => {
  if (res.code === 1) sendQuery()
}
// 清除冗余资源
const delImg = () => {
  reqDelImg()
}

/**
 ┌───────────────────────────────────┐
 │=============新增修改按钮============│
 └───────────────────────────────────┘
 */
// 模态框
const modalView = ref(false)
const modalTit = ref<"新增公告" | "修改公告">("修改公告")
// 新增时展示模态框
const addRoleB = (): void => {
  form.value = exampleNotice()
  myFormRef.value?.resetFields()
  modalTit.value = "新增公告"
  modalView.value = true
}
// 修改时展示模态框
const showDialog = (row: NoticeDto) => {
  myFormRef.value?.clearValidate()
  // 赋值，防止响应式引用
  form.value.noticeId = row.noticeId
  form.value.noticeTitle = row.noticeTitle
  form.value.isDisable = row.isDisable
  form.value.noticeFile = row.noticeFile
  form.value.textNotice = row.textNotice
  // ===赋值完成===
  modalView.value = true
  modalTit.value = "修改公告"
}
// 禁用启用
const disableSet = (id: number, isDis: boolean) => {
  reqDisableSet(id, isDis).then(res => {
    sureFlush(res)
  })
}
/**
 ┌───────────────────────────────────┐
 │=============表单校验相关============│
 └───────────────────────────────────┘
 */
// 表单的数据
const form = ref<NoticeDto>(exampleNotice()) // 空的默认值
const myFormRef = ref<FormInstance>()
// 校验表单并提交
const formSubmit = async (): Promise<void> => {
  await myFormRef.value?.validate((valid: boolean): void => {
    if (valid) {
      // 校验通过，提交
      modalView.value = false
      if (modalTit.value == "新增公告") reqAddNotice(form.value).then(res => sureFlush(res))
      else if (modalTit.value === "修改公告") reqUpdateNotice(form.value).then(res => sureFlush(res))
      else ElMessageBox.alert('模块框出错')
    }
  })
}
// 表单校验规则
const rules = reactive<FormRules>({
  "noticeTitle": [
    {required: true, message: '请输入标题', trigger: 'blur'},
  ],
  "textNotice": [
    {required: true, message: '请输入内容', trigger: 'blur'},
  ]
})
</script>

<style scoped>

</style>
