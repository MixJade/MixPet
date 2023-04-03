# axios的并行处理

* axios可以进行并行处理，就是axios.all
* 但是它的官网推荐使用Promise.all
* 然后我看了一下，axios.all实际上就是Promise.all

```
Promise.all([axios.get("http://localhost:8080/login/user"),
    axios.get("http://localhost:8080/doctor/page?numPage=1&pageSize=4"),
    axios.get("http://localhost:8080/pet/four"),
    axios.get("http://localhost:8080/notice/four")])
    .then(results => {
        getUser(results[0]["data"])
        getDoctor(results[1]["data"]["data"])
        getPet(results[2]["data"])
        getNotice(results[3]["data"])
    })
```

* 这样一起请求确实响应速度快了一点，没有出现网页在眼皮子底下加载的情况