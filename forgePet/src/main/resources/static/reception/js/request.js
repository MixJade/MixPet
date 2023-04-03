"use strict";
axios.defaults.baseURL = "http://localhost:8080/";
axios.interceptors.response.use(resp => {
    if (resp.data["code"]===401) window.top.location.href = "/login.html"
    return resp;
}, error => {
    window.top.location.href = "/login.html"
    return Promise.reject(error);
});