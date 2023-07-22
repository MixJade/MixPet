import {RouteRecordRaw} from "vue-router";

/**
 * 后台页面的路由
 * 父路径：/backstage
 */
export const backstage: RouteRecordRaw[] = [
    {
        // 管理员个人中心
        path: "",
        component: () => import('@/views/backstage/w-employeeOne.vue')
    }, {
        // 部门表
        path: "x-departmentList",
        component: () => import('@/views/backstage/x-departmentList.vue')
    }, {
        // 员工表
        path: "x-employeeList",
        component: () => import('@/views/backstage/x-employeeList.vue')
    }, {
        // 领养表
        path: "y-adoptList",
        component: () => import('@/views/backstage/y-adoptList.vue')
    }, {
        // 挂号表
        path: "y-appointmentList",
        component: () => import('@/views/backstage/y-appointmentList.vue')
    }, {
        // 用户表
        path: "y-clientList",
        component: () => import('@/views/backstage/y-clientList.vue')
    }, {
        // 医生表
        path: "y-doctorList",
        component: () => import('@/views/backstage/y-doctorList.vue')
    }, {
        // 寄养表
        path: "y-fosterList",
        component: () => import('@/views/backstage/y-fosterList.vue')
    }, {
        // 公告表
        path: "y-noticeList",
        component: () => import('@/views/backstage/y-noticeList.vue')
    }, {
        // 宠物表
        path: "y-petList",
        component: () => import('@/views/backstage/y-petList.vue')
    }
]