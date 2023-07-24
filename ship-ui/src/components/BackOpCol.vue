<template>
  <div class="op-col">
    <div class="op-input">
      <slot></slot>
      <el-button plain size="large" type="primary" @click="queryB">搜索</el-button>
    </div>
    <el-button plain size="large" type="success" @click="addRoleB">新增{{ role }}</el-button>
    <el-button plain size="large" type="danger" @click="delBatchB">批量删除</el-button>
  </div>
</template>

<script lang="ts" setup>
defineProps<{
  role: string
}>()
const emit = defineEmits<{
  (e: "query"): void,
  (e: "addRole"): void,
  (e: "delBatch"): void
}>()
const queryB = (): void => {
  emit("query")
}
const addRoleB = (): void => {
  emit("addRole")
}
const delBatchB = (): void => {
  emit("delBatch")
}
</script>

<style lang="scss" scoped>
.op-col {
  display: flex;
  justify-content: flex-end;
  /*搜索框*/
  .op-input {
    flex-grow: 1;
    /* 所有插槽内的元素设置宽度（都是输入框） */
    :not(:last-child) {
      width: 128px;
    }
  }
}
</style>