import axios from "axios";
import {ElMessage} from "element-plus";


//创建axios实例
let reqApi = axios.create({
    baseURL: "/api/",
    timeout: 5000,
    withCredentials: true
})

//请求拦截器
reqApi.interceptors.request.use(config => {
    return config;
});
//响应拦截器
reqApi.interceptors.response.use(resp => {
    if (resp.data["code"] != undefined && resp.data["code"] != null) {
        const code = resp.data["code"]
        if (code === 401) {
            location.href = "/#/"
        } else if (code === 403) {
            ElMessage.warning("未持有操作权限")
        } else if (code === 1) {
            ElMessage.success(resp.data["msg"])
        } else if (code === 0) {
            ElMessage.error(resp.data["msg"])
        }
    }
    return resp.data;
}, error => {
    ElMessage.error("发生未知错误")
    return Promise.reject(error);
});

export default reqApi;
