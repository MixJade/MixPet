<template>
  <el-upload
      :before-upload="beforeAvatarUpload"
      :on-success="handleAvatarSuccess"
      :show-file-list="false"
      action="/api/common/upload"
      class="avatar-uploader"
      name="myFile"
  >
    <img v-if="imageUrl" :src="'/api/common/download?name='+imageUrl" alt="头像" class="avatar"/>
    <el-icon v-else>
      <Plus/>
    </el-icon>
  </el-upload>
</template>

<script lang="ts" setup>
/*=====图片上传框=====*/
import {Plus} from "@element-plus/icons-vue"
import {ref, watch} from "vue";
import {ElMessage, UploadRawFile} from "element-plus";

const props = defineProps<{
  photoNm: string
}>()
const emit = defineEmits<{
  (e: "upPhoto", val: string): void
}>()
watch(() => props.photoNm, (newValue) => {
  imageUrl.value = newValue
})
// 头像上传框
const imageUrl = ref<string>('')
imageUrl.value = props.photoNm
/**
 * 上传成功之后的回显
 * @param response 图片名称
 */
const handleAvatarSuccess = (response: string): void => {
  imageUrl.value = response
  emit("upPhoto", response)
}

/**
 * 上传前的校验
 * @param rawFile 上传的头像
 */
const beforeAvatarUpload = (rawFile: UploadRawFile) => {
  if (rawFile.type !== 'image/png' && rawFile.type !== 'image/jpeg') {
    ElMessage.error('请上传JPG、PNG图片')
    return false
  } else if (rawFile.size > 3145000) {
    ElMessage.error('图片大小不能大于3MB')
    return false
  }
  return true
}
</script>

<style lang="scss" scoped>
/*整个上传框*/
$primary: #0d6efd;
$info: #6c757d;
.avatar-uploader {
  border: 2px dashed $info;
  width: 128px;
  height: 128px;
  /*响应式定位*/
  @media (min-width: 768px) {
    position: absolute;
    right: 15%;
    top: 15%;
  }
  @media (max-width: 768px) {
    position: relative;
    left: 50%;
    margin-left: -64px;
    margin-bottom: 10px;
  }
  /*悬停改变边框及加号颜色*/
  &:hover {
    border-color: $primary;
    /*悬停加号颜色*/
    .el-icon {
      color: $primary;
    }
  }

  /*上传的组件*/
  .el-upload {
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: 0.2s;
  }

  /*回显的头像*/
  .avatar {
    width: 128px;
    height: 128px;
    display: block;
  }

  /*加号*/
  .el-icon {
    font-size: 28px;
    color: $info;
    width: 128px;
    height: 128px;
    text-align: center;
  }
}

</style>