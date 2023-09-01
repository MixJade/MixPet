import {App as Vpp, Component} from 'vue'
// ElementPlus
import {
    ElButton,
    ElButtonGroup,
    ElDatePicker,
    ElDescriptions,
    ElDescriptionsItem,
    ElDialog,
    ElEmpty,
    ElForm,
    ElFormItem,
    ElIcon,
    ElInput,
    ElInputNumber,
    ElMenu,
    ElMenuItem,
    ElMenuItemGroup,
    ElMessage,
    ElMessageBox,
    ElOption,
    ElPagination,
    ElRadio,
    ElRadioGroup,
    ElSelect,
    ElSubMenu,
    ElTable,
    ElTableColumn,
    ElTag,
    ElUpload,
} from 'element-plus'
/* ======Element-Plus样式引入开始======= */
import "element-plus/es/components/button/style/css";
import "element-plus/es/components/button-group/style/css";
// 展示组件
import "element-plus/es/components/icon/style/css";
import "element-plus/es/components/tag/style/css";
import "element-plus/es/components/table/style/css";
import "element-plus/es/components/table-column/style/css";
import "element-plus/es/components/descriptions/style/css";
import "element-plus/es/components/descriptions-item/style/css";
import "element-plus/es/components/empty/style/css";
import "element-plus/es/components/pagination/style/css";
// 消息相关
import "element-plus/es/components/message/style/css";
import "element-plus/es/components/message-box/style/css";
import "element-plus/es/components/dialog/style/css";
// 表单
import "element-plus/es/components/form/style/css";
import "element-plus/es/components/form-item/style/css";
import "element-plus/es/components/input/style/css";
import "element-plus/es/components/input-number/style/css";
import "element-plus/es/components/upload/style/css";
import "element-plus/es/components/select/style/css";
import "element-plus/es/components/option/style/css";
import "element-plus/es/components/date-picker/style/css";
import "element-plus/es/components/radio/style/css";
import "element-plus/es/components/radio-group/style/css";
// 菜单
import "element-plus/es/components/menu/style/css";
import "element-plus/es/components/sub-menu/style/css";
import "element-plus/es/components/menu-item/style/css";
import "element-plus/es/components/menu-item-group/style/css";
/* ======Element-Plus样式引入结束======= */

export const pushElement = (app: Vpp) => {
    app.component("el-button", ElButton as Component)
        .component("el-button-group", ElButtonGroup as Component)
        // 展示组件
        .component("el-icon", ElIcon as Component)
        .component("el-tag", ElTag as Component)
        .component("el-table", ElTable as Component)
        .component("el-table-column", ElTableColumn as Component)
        .component("el-descriptions", ElDescriptions as Component)
        .component("el-descriptions-item", ElDescriptionsItem as Component)
        .component("el-empty", ElEmpty as Component)
        .component("el-pagination", ElPagination as Component)
        // 消息相关
        .component(ElMessage.name, ElMessage as Component)
        .component(ElMessageBox.name, ElMessageBox as Component)
        .component("el-dialog", ElDialog as Component)
        // 表单
        .component("el-form", ElForm as Component)
        .component("el-form-item", ElFormItem as Component)
        .component("el-input", ElInput as Component)
        .component("el-input-number", ElInputNumber as Component)
        .component("el-select", ElSelect as Component)
        .component("el-option", ElOption as Component)
        .component("el-upload", ElUpload as Component)
        .component("el-date-picker", ElDatePicker as Component)
        .component("el-radio-group", ElRadioGroup as Component)
        .component("el-radio", ElRadio as Component)
        // 菜单
        .component("el-menu", ElMenu as Component)
        .component("el-sub-menu", ElSubMenu as Component)
        .component("el-menu-item", ElMenuItem as Component)
        .component("el-menu-item-group", ElMenuItemGroup as Component)
}
