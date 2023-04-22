document.write(`<script src="../js/request.js"></script>`);

class FrontUntil {
    // 根据出生日期算年龄(简化版)
    nowYear = new Date().getFullYear()//获取年数差
    age(birthday) {
        const birthYear = birthday.split("-")[0]
        return this.nowYear - birthYear
    }// 写入卡片
    writeCard(roleCard, name, photoName, info, age, sex, stats, link) {
        roleCard.innerHTML +=
            `<div class="col-md-6 col-lg-3">
            <div class="card bg-light">
            <div class="card-body text-center">
            <img src="http://localhost:8083/common/download?name=${photoName}" class="mb-3 rounded-circle" alt="卡片"/>
            <h4 class="card-title">${name}</h4>
            <h6 class="card-subtitle mb-2 text-muted">${this.age(age)}岁 ${sex} ${stats}</h6>
            <p class="card-text">${info}</p>
            <a href="${link}" class="link-info">详情</a>
            </div></div></div>`
    }//设置展示信息
    setInnerText(h, group) {
        for (let i = 0; i < group.length; i++) {
            document.getElementById(group[i]).innerText = h[group[i]]
        }
    }// 表格主体
    addTable(json, group) {
        const tbody = document.getElementById('tb-main');//传来的json变成表格
        tbody.innerHTML = ''
        for (let i = 0; i < json.length; i++) { //遍历一下json数据
            const row = document.createElement('tr');
            for (let j = 0; j < group.length; j++) {
                row.appendChild(this.cell(json[i][group[j]]))
            }
            tbody.appendChild(row);
        }
    }//创建列
    cell(key) {
        const jsCell = document.createElement('td')
        jsCell.innerHTML = key
        return jsCell
    }
}