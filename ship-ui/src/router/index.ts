import {createRouter, createWebHashHistory, RouteRecordRaw} from "vue-router"
import {reception} from "@/router/reception";
import {backstage} from "@/router/backstage";
import {doctorPage} from "@/router/doctorPage";

const routes: RouteRecordRaw[] = [
    {
        path: '/',
        component: () => import('@/views/Login.vue')
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
    }
]
export const router = createRouter({
    history: createWebHashHistory(),
    routes: routes
})

export default router