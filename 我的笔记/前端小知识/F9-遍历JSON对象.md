# F9-遍历JSON对象

直接使用增强循环，所取得的就是key值，然后用key获取value

```
//根据json键值进行赋值
function jVal(json) {
    for (const key in json) {
        json[key] = document.getElementById(json[key]).value;
    }
    return json;
}
```