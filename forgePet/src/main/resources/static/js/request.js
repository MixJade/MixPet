"use strict";
axios.defaults.baseURL = "http://localhost:8083/";
const BACK_URL="http://localhost:8083/";
axios.interceptors.response.use(resp => {
    if (resp.data["code"] === 401) window.top.location.href = "/login.html"
    return resp;
}, error => {
    window.top.location.href = "/login.html"
    return Promise.reject(error);
});