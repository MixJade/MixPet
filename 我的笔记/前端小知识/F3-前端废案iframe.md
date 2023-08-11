# F3-前端废案iframe

> 不推荐使用iframe，因为:
> * 会将对应网页的css与js加载一遍，导致内存爆炸
> * 部分有锚定屏幕的效果会失效，毕竟它再怎么锚定，也只是一个窗口
> * 但是可以通过`window.parent.`解决，代价就是子网页完全失去独立运行能力

## iframe获取父页面的元素

> 在dom操作前面加`window.parent.`

```js
// 吐司消息
const toastText = window.parent.document.querySelector(".toast-body");
const toastColor = window.parent.document.querySelector('.toast');
// 确认删除框
const delModalFirst = window.parent.document.getElementById("delModal")
const delModal = new bootstrap.Modal(delModalFirst, {keyboard: false})
const sureDelBtn = window.parent.document.getElementById("sureDelBtn")
```

## iframe代码

> * 部分浏览器可能不兼容
> * 至于内存爆炸，瑞吉外卖9mb的资源都用iframe

```html
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>宠物医院后台管理系统</title>
    <link rel="stylesheet" href="../assets/bootstrap.min.css">
    <style>
        .a-jade {
            cursor: pointer;
        }

        .a-hide {
            border: 3px solid green;
            border-radius: 5px;
            color: green;
        }
    </style>
</head>

<body>
<!-- 导航 -->
<ul class="nav justify-content-end navbar bg-dark sticky-top" id="navHid">
    <li class="nav-item me-2">
        <a class="btn btn-success" data-bs-toggle="offcanvas" href="#popLeft" role="button" aria-controls="popLeft"
           id="admin01">
            功能列表
        </a>
    </li>
    <li class="nav-item"><a class="a-jade nav-link a-hide" onclick="getEle(0)">用户管理</a></li>
    <li class="nav-item"><a class="a-jade nav-link" onclick="getEle(1)">医生管理</a></li>
    <li class="nav-item"><a class="a-jade nav-link" onclick="getEle(2)">宠物管理</a></li>
    <li class="nav-item"><a class="a-jade nav-link" onclick="getEle(3)">挂号管理</a></li>
    <li class="nav-item"><a class="a-jade nav-link" onclick="getEle(4)">领养管理</a></li>
    <li class="nav-item"><a class="a-jade nav-link" onclick="getEle(5)">寄养管理</a></li>
    <li class="nav-item"><a class="a-jade nav-link" onclick="getEle(6)">网站公告</a></li>
</ul>
<!-- 左弹出框 -->
<div class="offcanvas offcanvas-start" tabindex="-1" id="popLeft" aria-labelledby="popLeftTitle">
    <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="popLeftTitle">功能菜单</h5>
        <button class="btn-close text-reset" type="button" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
        欢迎您!<a class="text-decoration-none" id="admin02">管理员</a>
        <div class="list-group">
            <a class="a-jade list-group-item list-group-item-primary" onclick="getEle(7)">员工信息</a>
            <a class="a-jade list-group-item list-group-item-primary" onclick="getEle(8)">科室信息</a>
            <a class="list-group-item list-group-item-warning" data-bs-toggle="modal"
               data-bs-target="#putModal">修改密码</a>
            <a class="list-group-item list-group-item-danger" href="http://localhost:8080/logout">退出登录</a>
        </div>
    </div>
</div>
<!--弹出框-->
<div class="modal fade" id="putModal" tabindex="-1" aria-labelledby="putModalLabel" aria-hidden="true"
     style="z-index: 1300;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="putModalLabel">修改密码</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form class="d-grid gap-3" id="putForm">
                    <input type="hidden" id="employeeId">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label" for="employeeUsername">账号</label>
                        <div class="col-sm-8">
                            <input class="form-control" id="employeeUsername" type="text" placeholder="员工账号"
                                   disabled readonly>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label" for="employeeName">姓名</label>
                        <div class="col-sm-8">
                            <input class="form-control" id="employeeName" type="text" placeholder="员工名称"
                                   disabled readonly>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label" for="employeeTel">联系方式</label>
                        <div class="col-sm-8">
                            <input class="form-control" id="employeeTel" type="text" placeholder="联系方式">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label" for="employeePassword">密码</label>
                        <div class="col-sm-8">
                            <input class="form-control" id="employeePassword" type="password">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" data-bs-dismiss="modal" type="button">关闭</button>
                <button class="btn btn-primary" data-bs-dismiss="modal" type="button" onclick="formSub()">确认提交</button>
            </div>
        </div>
    </div>
</div>
<!-- 吐司消息 -->
<div class="position-fixed bottom-50 p-3" style="z-index: 5;left: 30%;">
    <div class="toast hide text-white bg-danger" id="liveToast" data-bs-delay="1200" role="alert"
         aria-live="assertive" aria-atomic="true">
        <div class="toast-body">
            你有一条短消息！
        </div>
    </div>
</div>
<!-- 确认删除框 -->
<div class="modal fade" id="delModal" tabindex="-1" aria-labelledby="delModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="delModalLabel">删除确认</h5>
                <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                一旦删除，数据无法找回，是否删除
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" data-bs-dismiss="modal">取消</button>
                <button class="btn btn-danger" id="sureDelBtn" type="button">删除</button>
            </div>
        </div>
    </div>
</div>
<!--网页框架-->
<iframe src="y-clientList.html" id="myFrame" title="myFrame" onload="changeFrameHeight()"
        style="width: 100%;border:0">
</iframe>
<!--页面底部-->
<footer><p style="text-align: center;color:#C0C0C0">@宠物医院后台管理系统</p></footer>
</body>
<script src="../assets/bootstrap.bundle.min.js"></script>
<script src="../assets/axios.js"></script>
<script type="text/javascript">
    const srcFrame = ["y-clientList.html", "y-doctorList.html", "y-petList.html", "y-appointmentList.html",
        "y-adoptList.html", "y-fosterList.html", "y-noticeList.html", "x-employeeList.html", "x-departmentList.html"
    ];
    const aJade = document.getElementsByClassName("a-jade");
    const ifm = document.getElementById("myFrame");
    const toastText = document.querySelector(".toast-body");
    const toastColor = document.querySelector('.toast');
    let nowHave = 0;

    // 改变iframe高度
    function changeFrameHeight() {
        ifm.height = document.documentElement.clientHeight + 80;
    }// 动态改变iframe高度
    window.onresize = function () {
        changeFrameHeight();
    }// 导航选中效果
    function getEle(index) {
        aJade[nowHave].classList.remove("a-hide")
        nowHave = index
        aJade[index].classList.add("a-hide")
        ifm.src = srcFrame[index]
    }// 获取当前登录用户
    window.onload = function () {
        axios.get("http://localhost:8080/login/admin").then(resp => jsonToForm(resp.data))
    }//将json变成表单
    function jsonToForm(h) {
        const {employeeId, employeeUsername, employeeName, employeeTel} = h
        // 名字
        document.getElementById("admin01").innerHTML = employeeName
        document.getElementById("admin02").innerHTML = employeeName
        // 表单
        document.getElementById('employeeId').value = employeeId
        document.getElementById('employeeUsername').value = employeeUsername
        document.getElementById('employeeName').value = employeeName
        document.getElementById("employeeTel").value = employeeTel
    }//提交表单
    function formSub() {
        axios.put("http://localhost:8080/login/admin", {
            "employeeId": document.getElementById('employeeId').value,
            "employeeUsername": document.getElementById('employeeUsername').value,
            "employeeName": document.getElementById('employeeName').value,
            "employeeTel": document.getElementById("employeeTel").value,
            "employeePassword": document.getElementById("employeePassword").value
        }).then(resp => commonResp(resp))
        document.getElementById("employeePassword").value = ''
    }// 删改增操作通用解析
    function commonResp(resp) {
        if (resp.data["code"] === 1) {
            sucToast(resp.data["msg"])
        } else if (resp.data["code"] === 0) {
            errToast(resp.data["msg"])
        } else {
            errToast("服务器无响应")
        }
    }//成功消息
    function sucToast(msg) {
        toastColor.classList.remove("bg-danger")
        toastColor.classList.add("bg-success")
        toastText.innerHTML = msg;
        new bootstrap.Toast(toastColor).show();
    }//失败消息
    function errToast(msg) {
        toastColor.classList.remove("bg-success")
        toastColor.classList.add("bg-danger")
        toastText.innerHTML = msg;
        new bootstrap.Toast(toastColor).show();
    }
</script>

</html>
```