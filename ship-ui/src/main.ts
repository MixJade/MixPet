import {App as Vpp, createApp} from 'vue'
import './style.css'
import App from './App.vue'
// ElementPlus
import {
    ElAside,
    ElAvatar,
    ElButton,
    ElButtonGroup,
    ElCard,
    ElCol,
    ElContainer,
    ElDatePicker,
    ElDescriptions,
    ElDescriptionsItem,
    ElDialog,
    ElEmpty,
    ElFooter,
    ElForm,
    ElFormItem,
    ElHeader,
    ElIcon,
    ElImage,
    ElInput,
    ElInputNumber,
    ElMain,
    ElMenu,
    ElMenuItem,
    ElMenuItemGroup,
    ElMessage,
    ElOption,
    ElPageHeader,
    ElPagination,
    ElRadio,
    ElRadioGroup,
    ElRow,
    ElSelect,
    ElSubMenu,
    ElTable,
    ElTableColumn,
    ElTag,
    ElUpload,
} from 'element-plus'
import 'element-plus/dist/index.css'
// 路由
import router from './router'
import {Component} from "@vue/runtime-core";

const app: Vpp = createApp(App)
app.use(router)
/* ======Element-Plus引入开始======= */
// 新增代码：注册特定组件
app.component("el-button", ElButton as Component)
    .component("el-button-group", ElButtonGroup as Component)
    // 展示组件
    .component("el-card", ElCard as Component)
    .component("el-icon", ElIcon as Component)
    .component("el-image", ElImage as Component)
    .component("el-avatar", ElAvatar as Component)
    .component("el-tag", ElTag as Component)
    .component("el-table", ElTable as Component)
    .component("el-table-column", ElTableColumn as Component)
    .component("el-descriptions", ElDescriptions as Component)
    .component("el-descriptions-item", ElDescriptionsItem as Component)
    .component("el-empty", ElEmpty as Component)
    .component("el-pagination", ElPagination as Component)
    // 消息相关
    .component(ElMessage.name, ElMessage as Component)
    .component("el-dialog", ElDialog as Component)
    // 表单
    .component("el-form", ElForm as Component)
    .component("el-form-item", ElFormItem as Component)
    .component("el-input", ElInput as Component)
    .component("el-input-number", ElInputNumber as Component)
    .component("el-date-picker", ElDatePicker as Component)
    .component("el-radio-group", ElRadioGroup as Component)
    .component("el-radio", ElRadio as Component)
    .component("el-upload", ElUpload as Component)
    .component("el-select", ElSelect as Component)
    .component("el-option", ElOption as Component)
    // 布局相关
    .component("el-container", ElContainer as Component)
    .component("el-header", ElHeader as Component)
    .component("el-footer", ElFooter as Component)
    .component("el-main", ElMain as Component)
    .component("el-aside", ElAside! as Component)
    .component("el-col", ElCol as Component)
    .component("el-row", ElRow as Component)
    .component("el-page-header", ElPageHeader as Component)
    // 菜单
    .component("el-menu", ElMenu as Component)
    .component("el-sub-menu", ElSubMenu as Component)
    .component("el-menu-item", ElMenuItem as Component)
    .component("el-menu-item-group", ElMenuItemGroup as Component)
/* ======Element-Plus引入结束======= */
app.mount('#app')
