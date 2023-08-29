import {createRouter, createWebHashHistory, RouteRecordRaw} from "vue-router"
import {reception} from "@/router/reception";
import {backstage} from "@/router/backstage";
import {doctorPage} from "@/router/doctorPage";

const routes: RouteRecordRaw[] = [
    {
        path: '/',
        component: () => import('@/views/login.vue')
    }, {
        path: '/findPwd',
        component: () => import('@/views/findPwd.vue')
    }, {
        path: '/register',
        component: () => import('@/views/register.vue')
    }, {
        path: '/reception',
        children: [...reception],
    }, {
        path: '/backstage',
        component: () => import('@/views/backstage/index.vue'),
        children: [...backstage],
    }, {
        path: '/doctorPage',
        children: [...doctorPage],
    }, {
        path: '/:pathMatch(.*)', // 404路由
        name: 'not-found',
        component: () => import('@/views/page404.vue')
    }
]
export const router = createRouter({
    history: createWebHashHistory(),
    routes: routes
})

export default router
