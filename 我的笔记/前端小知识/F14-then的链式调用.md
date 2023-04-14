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
* 这里是`const fun1 = x => x + "[1]"`为const定义函数方式，箭头后面直接返回
* 比如：

```js
const fun1 = x => x + "[1]"
const fun2 = x => x + "[2]"
// 进行链式调用
new Promise(resolve => resolve("str1"))
    .then(res => fun1(res))
    .then(res => fun2(res))
    .then(res => console.log(res))
```