# F10-两种增强for循环

* `for...of`会取出数组里的值，就是具体的元素

```js
const group = [sss.img(petPhoto), petName, petVariety, petSex ? "公" : "母", sss.age(petAge), petStatus, petInfo, clientName];
for (const key of group) {
    row.appendChild(sss.cell(key))
}
```

* `for...in`会取出键，就是`0,1,2,3,4,5`

```js
for (const key in group) {
    row.appendChild(sss.cell(key))
}
```