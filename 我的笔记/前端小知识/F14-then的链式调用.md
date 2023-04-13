# then的链式调用

> 相信以前都听过promise可以使用then来链式调用

* 直接在then里面return,下一个then就能获得return的参数
* 注：简化语法`x=>x.d`，为`function(x){return x.d)`

```js
axios.put("adminLog", json)
    .then(resp => resp.data)
    .then(data => {
        console.log(data)
    })
```

* 这个then里的return也可以通过外部函数代劳
* 比如(这里就不展示了)：

```js
    // 外部函数1
function func1(str1) {
    return new Promise(resolve => {
        resolve(str1 + '=1=');
    })
}// 外部函数2
function func2(x) {
    return x + "=2=";
}// 外部函数3
function func3(x) {
    return x + "=3="
}// 进行链式调用
func1("str1")
    .then(res => func2(res))
    .then(res => func3(res))
    .then(res => {
        console.log(res);
    })
```