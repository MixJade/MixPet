# 论使用js造轮子

> * 虽然我一开始只是打算使用bootstrap与axios，其它地方都使用原生js
> * 但是随着写的越来越多同质化的网页，我将一些函数给封装到类中，又有一些通用函数给迁移到js文件中
> * 然后我发现，代码量甚至比用正经的框架还少
> * 毕竟正规框架不会规定某个标签必须叫特定的id,也不会规定变量名必须是特定格式
> * 但总归极大的节省了代码量(从500行降到267行，其中65行有iframe的功劳)，是这次毕业设计之中的意外收获

## js文件

```
"use strict";
axios.defaults.baseURL = "http://localhost:8080/";

// 简化表格与表单
class MixRole {
    constructor(role, roleName) {
        this._role = role;
        this._roleName = roleName
        document.getElementById("submitForm").onclick = () => this.upAndAdd()
    };// 一个参数的分页查询
    getAll(numPage = 1) {
        const search1 = this._role + "Name"
        const json = {"numPage": numPage, "pageSize": this.getPageSize()}
        json[search1] = document.getElementById("searchInput").value;
        axios.get(this._role + "/page", {
            params: json
        }).then(resp => {
            this.addTableRow(resp.data["data"]);
            this.pageStripWrite(resp.data["len"], numPage)
        })
    }// 表格主体
    tbody = document.getElementById('tb-main');//传来的json变成表格
    addTableRow(json) {
        this.tbody.innerHTML = ''
        for (let i = 0; i < json.length; i++) { //遍历一下json数据
            const trow = getDataRow(json[i]); //定义一个方法,返回tr数据
            this.tbody.appendChild(trow);
        }
    }//删除与修改按钮
    opCell(roleId, h) {
        const role = this._role;
        const delCell = document.createElement('td');
        const btnDel = document.createElement('input');
        btnDel.setAttribute('type', 'button');
        btnDel.setAttribute('class', 'btn btn-warning ms-1');
        btnDel.setAttribute('value', '删除');
        delCell.appendChild(btnDel);
        const btnUpd = document.createElement('input');
        btnUpd.setAttribute('type', 'button');
        btnUpd.setAttribute('class', 'btn btn-info ms-1');
        btnUpd.setAttribute("data-bs-toggle", "modal");
        btnUpd.setAttribute("data-bs-target", "#addRolePop");
        btnUpd.setAttribute('value', '修改');
        delCell.appendChild(btnUpd);
        //删除操作
        btnDel.onclick = async () => {
            if (await confirmDel()) {
                axios.delete(role + "/" + roleId).then(resp => this.commonResp(resp))
            }
        }// 修改操作
        btnUpd.onclick = () => this.upTit(h)
        return delCell
    }//创建行
    cell(key) {
        const jsCell = document.createElement('td')
        jsCell.innerHTML = key
        return jsCell
    }// 表格图片
    img(rolePhoto) {
        if (rolePhoto != null) {
            return '<img src="/common/download?name=' + rolePhoto + '" height="45px" width="45px" alt="' + rolePhoto + '"/>'
        } else {
            return ''
        }
    }// 添加复选框
    check(roleId) {
        const checkRow = document.createElement('td');
        const checkA = document.createElement('input');
        checkA.type = 'checkbox';
        checkA.className = 'form-check-input ms-2';
        checkA.setAttribute('name', 'checkA');
        checkA.setAttribute('placeholder', roleId);
        checkRow.appendChild(checkA);
        return checkRow
    }// 添加单个按钮
    btn(text, color) {
        const btnOne = document.createElement('input');
        btnOne.setAttribute('type', 'button');
        btnOne.setAttribute('class', 'ms-1 btn ' + color)
        btnOne.setAttribute('value', text);
        return btnOne
    }// 根据出生日期算年龄(简化版)
    nowYear = new Date().getFullYear()//获取年数差
    age(birthday) {
        const birthYear = birthday.split("-")[0]
        return this.nowYear - birthYear
    }//批量删除
    deleteGroup(checkAS) {
        const delGroup = [];
        for (let i = 0; i < checkAS.length; i++) {
            if (checkAS[i].checked) {
                delGroup.push(checkAS[i].placeholder)
            }
        }
        if (delGroup.length === 0) {
            return
        }
        axios.delete(this._role + "/batch/" + delGroup).then(resp => this.commonResp(resp))
    }//修改与添加
    upAndAdd() {
        if (document.getElementById('addRolePopLa').innerHTML === "修改" + this._roleName + "信息") {
            axios.put(this._role, formToJson()).then(resp => this.commonResp(resp))
        } else {
            axios.post(this._role, formToJson()).then(resp => this.commonResp(resp))
        }
    }//设置添加弹出框标题
    addTit() {
        const form = document.getElementById('addRoleForm')
        form.reset();
        const hidIds = form.querySelectorAll('input[type="hidden"]')
        for (let i = 0; i < hidIds.length; i++) {
            hidIds[i].value = ""
        }
        document.getElementById('addRolePopLa').innerHTML = "添加" + this._roleName
    }//设置修改弹出框标题
    upTit(h) {
        document.getElementById('addRolePopLa').innerHTML = "修改" + this._roleName + "信息";
        jsonToForm(h)
    }//json转表单(只限value操作)
    setVal(group, h) {
        try {
            for (let i = 0; i < group.length; i++) {
                document.getElementById(group[i]).value = h[group[i]]
            }
        } catch (e) {
            console.log("setVal:有value不符合标准")
        }
    }//json的性别转表单(只限checked操作)
    setSex(sex, field) {
        if (sex) {
            document.getElementById(field + "_1").checked = true
        } else {
            document.getElementById(field + "_2").checked = true
        }
    }//表单转json(只限value操作)
    getVal(group) {
        try {
            const json = {};
            for (let i = 0; i < group.length; i++) {
                json[group[i]] = document.getElementById(group[i]).value
            }
            return json
        } catch (e) {
            console.log("getVal:有value不符合标准")
        }
    }// 删改增操作通用解析
    commonResp(resp) {
        const currentPage = this.pageStrip.querySelector(".active").getElementsByTagName("a")[0].innerHTML;
        if (resp.data["code"] === 1) {
            this.sucToast(resp.data["msg"])
            this.getAll(currentPage);
        } else if (resp.data["code"] === 0) {
            this.errToast(resp.data["msg"])
        } else {
            this.errToast("服务器无响应")
        }
    }// 吐司消息
    sucToast(msg) {
        const toast01 = window.parent.document.getElementById("toast01");// 成功消息
        toast01.querySelector(".toast-body").innerHTML = msg;
        new bootstrap.Toast(toast01).show();
    }// 失败消息
    errToast(msg) {
        const toast02 = window.parent.document.getElementById("toast02");// 失败消息
        toast02.querySelector(".toast-body").innerHTML = msg;
        new bootstrap.Toast(toast02).show();
    }

    pageStrip = document.getElementById("pageStrip");// 分页条
    getPageSize() {
        return document.getElementById("pageSize").value
    }// 分页条向前向后翻
    addPageNum(isAdd) {
        const currentPage = parseInt(this.pageStrip.querySelector(".active").getElementsByTagName("a")[0].innerHTML);
        if (isAdd && currentPage !== 1) {
            this.getAll(currentPage - 1)
        } else if (!isAdd && currentPage !== this.pageStrip.getElementsByTagName("li").length) {
            this.getAll(currentPage + 1)
        }
    }// 分页条设置长度与选中
    pageStripWrite(dataLen, numPage) {
        const pageNum = dataLen / (this.getPageSize());
        this.pageStrip.innerHTML = ''
        for (let i = 1; i < pageNum + 1; i++) {
            this.pageStrip.innerHTML += '<li class="page-item"><a class="page-link" onclick="sss.getAll(' + i + ')">' + i + '</a></li>'
        }
        try {
            this.pageStrip.getElementsByTagName("a")[numPage - 1].parentNode.classList.add("active");
        } catch (e) {
            console.log("页数过少")
        }
    }
}

// 图片上传与回显
class MixImg {
    constructor() {
        this.myFile = document.getElementById("myFile")
        this.myFile.onchange = () => this.imgChange()
        this._myImg = document.getElementById("rolePhoto");
        this.myFileText = document.getElementById("myFileText")
    }// 获取图片名
    get myImg() {
        return this._myImg.alt;
    }// 上传框变化时，检查图片大小
    imgChange() {
        const myFileText = this.myFileText.style
        const myFile = this.myFile.files[0]
        if (myFile.size > 1024000) {
            myFileText.display = "";
        } else {
            myFileText.display = "none";
            const formData = new FormData();
            formData.append("myFile", myFile)
            axios.post("common/upload", formData).then(resp => this.change(resp.data))
        }
    }// 改变表单图片
    change(imgName) {
        const myImg = this._myImg,
            myFileText = this.myFileText.style
        if (imgName === '' || imgName === null) {
            myFileText.display = "none";
            myImg.setAttribute("src", '../picture/none.png')
            myImg.setAttribute("alt", '')
        } else {
            myImg.setAttribute("src", '/common/download?name=' + imgName)
            myImg.setAttribute("alt", imgName)
        }
    }
}

// 确认删除框
try {
    const delModalFirst = window.parent.document.getElementById("delModal");
    const sureDelBtn = window.parent.document.getElementById("sureDelBtn");//确认删除
    window.confirmDel = () => {
        window.parent.document.getElementById("fatherDelBtn").click();//不这样会导致关闭按钮失效
        return new Promise(resolve => {
            delModalFirst.addEventListener('hidden.bs.modal', () => resolve(false));
            sureDelBtn.onclick = () => resolve(true);
        })
    }
} catch (e) {
    console.log("没找到确认删除框")
}

try {// 多选与反选
    const checkAS = document.getElementsByName("checkA");
    document.getElementById("checkALL").onclick = function () {
        for (let i = 0; i < checkAS.length; i++) {
            checkAS[i].checked = this.checked;//勿用箭头函数
        }
    }// 批量删除
    document.getElementById("delALL").onclick = async () => {
        if (await confirmDel()) {
            sss.deleteGroup(checkAS)
        }
    }
} catch (e) {
    console.log("批量删除的一些元素不在")
}
```

## 使用示例

* 部分元素在父类页面中，请参照文件夹“小知识”中的“Q2-前端废案iframe.md”
* 以下的iframe中的具体实现增删改查页面

```
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>宠物管理</title>
    <link rel="stylesheet" href="../assets/bootstrap.min.css">
</head>

<body>
<!-- 弹出框 -->
<div class="modal fade" id="addRolePop" tabindex="-1" aria-labelledby="addRolePopLa" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addRolePopLa">添加宠物</h5>
                <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form class="d-grid gap-3" id="addRoleForm">
                    <input type="hidden" id="petId">
                    <input type="hidden" id="clientId">
                    <div class="form-group row">
                        <label class="col-md-3 col-form-label" for="myFile">宠物头像</label>
                        <div class="col-md-8">
                            <label for="myFile"><img id="rolePhoto" src="../picture/none.png" alt=""
                                                     style="height: 60px;width: 60px"/></label>
                            <input type="file" id="myFile" hidden="hidden"/>
                            <label for="myFile" id="myFileText"
                                   style="color: red;display: none">上传文件大小应小于1MB</label>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 col-form-label" for="petName">宠物姓名</label>
                        <div class="col-md-8">
                            <input class="form-control" id="petName" type="text" placeholder="宠物姓名">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label" for="petVariety">宠物品种</label>
                        <div class="col-sm-8">
                            <input class="form-control" id="petVariety" type="text" placeholder="品种">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">性别</label>
                        <div class="radio col-sm-2">
                            <label>
                                <input class="form-check-input" name="petSexRad" id="petSexRad_1" type="radio"
                                       checked>公
                            </label>
                        </div>
                        <div class="radio col-sm-2">
                            <label>
                                <input class="form-check-input" name="petSexRad" id="petSexRad_2" type="radio">母
                            </label>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label" for="petAge">出生日期</label>
                        <div class="col-sm-8">
                            <input class="form-control" id="petAge" type="date">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label" for="petStatus">健康状态</label>
                        <div class="col-sm-8">
                            <input class="form-control" id="petStatus" type="text" placeholder="健康状态">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label" for="clientName">主人</label>
                        <div class="col-sm-8">
                            <input class="form-control" list="dataOp" id="clientName" placeholder="宠物主人"
                                   autocomplete="off">
                            <datalist id="dataOp"></datalist>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 col-form-label" for="petInfo">宠物近况</label>
                        <div class="col-md-8">
                            <input class="form-control" id="petInfo" type="text" placeholder="宠物近况">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" data-bs-dismiss="modal" type="button">关闭</button>
                <button class="btn btn-primary" data-bs-dismiss="modal" type="button" id="submitForm">确认提交</button>
            </div>
        </div>
    </div>
</div>
<!-- 搜索与添加与批量删除 -->
<nav class="navbar bg-light sticky-top">
    <div class="container-fluid">
        <form class="d-flex" role="search" onsubmit="return false">
            <button class="btn btn-success" id="changSearch" type="button">宠</button>
            <input class="form-control me-2" type="search" placeholder="宠物名字" aria-label="Search" id="searchInput">
            <button class="btn btn-outline-success" type="submit" onclick="sss.getAll()">Search</button>
        </form>
        <div class="d-flex flex-row-reverse bd-highlight">
            <button type="button" class="btn btn-danger ms-1" id="delALL">批量删除</button>
            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addRolePop"
                    onclick="sss.addTit()">添加宠物
            </button>
        </div>
    </div>
</nav>
<!-- 表格 -->
<table class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th><input class="form-check-input ms-3" type="checkbox" id="checkALL"><label for="checkALL"></label></th>
        <th>图片</th>
        <th>姓名</th>
        <th>品种</th>
        <th>性别</th>
        <th>年龄</th>
        <th>健康状况</th>
        <th>近况</th>
        <th>主人</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody id="tb-main">
    </tbody>
</table>
<!-- 分页条 -->
<nav>
    <ul class="pagination justify-content-center">
        <li class="page-item">
            <select class="form-select col-sm-3" id="pageSize" onchange="sss.getAll()">
                <option value="7" selected>7条/页</option>
                <option value="10">10条/页</option>
                <option value="13">13条/页</option>
            </select><label for="pageSize"></label>
        </li>
        <li class="page-item">
            <a class="page-link" onclick="sss.addPageNum(true)" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        <li class="pagination" id="pageStrip"></li>
        <li class="page-item">
            <a class="page-link" onclick="sss.addPageNum(false)" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>
</body>
<script src="../assets/bootstrap.bundle.min.js"></script>
<script src="../assets/axios.js"></script>
<script src="z-myJS.js"></script>
<script type="text/javascript">
    const bbb = new MixImg()//重写方法
    class MixRole2 extends MixRole {
        getAll(numPage = 1) {
            const searchInput = document.getElementById("searchInput")
            const json = {"numPage": numPage, "pageSize": this.getPageSize()}
            if (searchInput.placeholder === "宠物名字") {
                json["petName"] = searchInput.value
            } else {
                json["clientName"] = searchInput.value
            }
            axios.get(this._role + "/page", {
                params: json
            }).then(resp => {
                this.addTableRow(resp.data["data"]);
                this.pageStripWrite(resp.data["len"], numPage)
            })
        }// 添加标题
        addTit() {
            super.addTit()
            bbb.change('')
        }// 修改标题
        upTit(h) {
            bbb.change('')
            super.upTit(h);
        }
    }//建立对象
    const sss = new MixRole2("pet", "宠物")
    window.onload = function () {
        sss.getAll();
        getOther()
    }//更改搜索框的提示
    const searchInput = document.getElementById("searchInput")
    document.getElementById("changSearch").onclick = () => {
        searchInput.value = ''
        if (searchInput.placeholder === "宠物名字") {
            this.innerHTML = "主"
            this.className = "btn btn-danger"
            searchInput.placeholder = "主人姓名"
        } else {
            this.innerHTML = "宠"
            this.className = "btn btn-success"
            searchInput.placeholder = "宠物名字"
        }
    }// 向表格类动态添加数据
    function getDataRow(h) {
        const row = document.createElement('tr'),
            {petId, petName, petVariety, petSex, petAge, petStatus, petInfo, petPhoto, clientName} = h
        // 添加复选框
        row.appendChild(sss.check(petId));
        // 图片
        row.appendChild(sss.cell(sss.img(petPhoto)))
        // 添加其他列
        row.appendChild(sss.cell(petName))
        row.appendChild(sss.cell(petVariety))
        row.appendChild(sss.cell(petSex ? "公" : "母"))
        row.appendChild(sss.cell(sss.age(petAge)))
        row.appendChild(sss.cell(petStatus))
        row.appendChild(sss.cell(petInfo))
        row.appendChild(sss.cell(clientName))
        // 删除与修改按钮
        row.appendChild(sss.opCell(petId, h));
        return row;
    }//表单数据转json
    function formToJson() {
        const group = ["petId", "petName", "petVariety", "petAge", "petStatus", "clientId", "petInfo"],
            json = {
                "petPhoto": bbb.myImg,
                "petSex": document.getElementById("petSexRad_1").checked
            }
        return Object.assign(sss.getVal(group), json)
    }//json数据转表单
    function jsonToForm(h) {
        const {petSex, petPhoto} = h
        sss.setVal(["petId", "petName", "petVariety", "petAge", "petStatus", "clientId", "clientName", "petInfo"], h)
        bbb.change(petPhoto)
        sss.setSex(petSex, "petSexRad")
    }// 查询下拉框用户名
    function getOther() {
        axios.get("client").then(resp => changeSel(resp.data))
    }// 将用户名赋予下拉框
    function changeSel(json) {
        document.getElementById("dataOp").innerHTML = '<option value=null>无人</option>'
        for (let i = 0; i < json.length; i++) {
            const {roleName, roleId} = json[i]
            document.getElementById("dataOp").innerHTML += '<option value="' + roleId + '">' + roleName + '</option>'
        }
    }// 改变datalist选中效果
    (function () {
        const option = (document.getElementById("dataOp")).getElementsByTagName("option"),
            dataName = document.getElementById("clientName"),
            dataId = document.getElementById("clientId")//将id赋予隐藏栏
        dataName.onchange = () => {
            for (let i = 0; i < option.length; i++) {
                if (option[i].value === dataName.value) {
                    dataId.value = dataName.value
                    dataName.value = option[i].innerHTML
                }
            }
        }
    }());
</script>

</html>
```