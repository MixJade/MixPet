# B11-post方法接收参数

* 使用vo对象，可以建立一个record类

```java
public record AdoptVo(Long adoptId, Boolean isAdopt) {
}
```

```
@PutMapping("/adopt")
public Result toAdopt(@RequestBody AdoptVo adoptVo) {
    Boolean isAdopt=adoptVo.isAdopt();
    Long adoptId=adoptVo.adoptId();
}
```

* 不用vo的话，用一个map对象就好了，但是不如用vo对象方便

```
@PutMapping("/adopt")
public Result toAdopt(@RequestBody Map<String, Object> adoptMap) {
    Boolean isAdopt = (Boolean) adoptMap.get("isAdopt");
    Long adoptId = (Long) adoptMap.get("adoptId");
}
```