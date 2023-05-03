# LambdaQueryWrapper的使用

> 这个可以动态获取列名

```java
@Transactional(rollbackFor = Exception.class, readOnly = true)
public Result<Page<Enity>> queryByLike(Integer pageNum, Integer pageSize, Enity enity) {
    Page<Enity> page = new Page(pageNum, pageSize);
    LambdaQueryWrapper<Enity> queryWrapper = Wrappers.lambdaQuery();
    queryWrapper.orderByDesc(Enity::getBatchId);
    queryWrapper.eq(StringUtils.isNotBlank(enity.getBatchTp()), Enity::getBatchTp, enity.getBatchTp());
    queryWrapper.like(StringUtils.isNotBlank(enity.getDeptNo()), Enity::getDeptNo, enity.getDeptNo());
    Page<Enity> result = page(page, queryWrapper);
    return Result.success(result);
}
```
