"use strict";
// 网页滚动到底部进行分页查询
let pageNum = 1, maxNum = 1;
window.onscroll = debounce(scrollFn, 200);//设置每隔一段时间触发函数
function debounce(fn, delay) {
    let timeout;
    return function () {
        clearTimeout(timeout);
        timeout = setTimeout(() => fn.apply(this, arguments), delay)
    }
}//判断网页到底部
function scrollFn() {
    //获取网页的总高度
    const htmlHeight = document.documentElement.scrollHeight;
    //网页在浏览器中的可视高度
    const clientHeight = document.documentElement.clientHeight;
    //浏览器滚动条的top位置
    const scrollTop = document.documentElement.scrollTop;
    //判断到底部了
    if (scrollTop + clientHeight + 2 > htmlHeight && pageNum < maxNum) {
        pageNum++; // 页码+1
        getAll(pageNum);
    }
}