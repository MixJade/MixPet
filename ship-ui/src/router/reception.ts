import {RouteRecordRaw} from "vue-router";

/**
 * 用户页面的路由
 * 父路径：/reception
 */
export const reception: RouteRecordRaw[] = [
    {
        // 网站主页
        path: "",
        component: () => import('@/views/reception/Index.vue'),
    }, {
        // 查看所有待领养宠物
        path: "petSee",
        component: () => import('@/views/reception/petSee.vue')
    }, {
        // 查看所有医生
        path: "doctorSee",
        component: () => import('@/views/reception/doctorSee.vue')
    }, {
        // 查看单个宠物
        path: "petOne/:petId",
        props: true,
        component: () => import('@/views/reception/petOne.vue')
    }, {
        // 查看单个医生
        path: "doctorOne/:doctorId",
        props: true,
        component: () => import('@/views/reception/doctorOne.vue')
    }, {
        // 查看全部寄养宠物
        path: "fosterSee",
        component: () => import('@/views/reception/fosterSee.vue')
    }, {
        // 个人中心
        path: "clientCenter",
        component: () => import('@/views/reception/clientCenter.vue'),
        children: [
            {
                // 个人中心的个人资料
                path: "",
                component: () => import('@/views/reception/clientOne.vue')
            }, {
                // 个人中心的宠物信息
                path: "clientAppoint",
                component: () => import('@/views/reception/clientAppoint.vue')
            }, {
                // 个人中心的挂号信息
                path: "clientPet",
                component: () => import('@/views/reception/clientPet.vue')
            },
        ],
    }, {
        // 聊天页面：自医生处跳转
        path: "chat/:doctorId?",
        props: true,
        component: () => import('@/views/reception/chat.vue')
    },
]