# LambdaQueryWrapper的使用

> 这个可以动态获取列名

```java
@Transactional(rollbackFor = Exception.class, readOnly = true)
public RestfulResponse<Page<AuthBatchInfo>, GeneralMeta> queryByLike(Integer pageNum, Integer pageSize, AuthBatchInfo authBatchInfo) {
    Page<AuthBatchInfo> page = new Page(pageNum, pageSize);
    LambdaQueryWrapper<AuthBatchInfo> queryWrapper = Wrappers.lambdaQuery();
    queryWrapper.orderByDesc(AuthBatchInfo::getBatchId);
    queryWrapper.eq(StringUtils.isNotBlank(authBatchInfo.getBatchTp()), AuthBatchInfo::getBatchTp, authBatchInfo.getBatchTp());
    queryWrapper.like(StringUtils.isNotBlank(authBatchInfo.getDeptNo()), AuthBatchInfo::getDeptNo, authBatchInfo.getDeptNo());
    Page<AuthBatchInfo> result = page(page, queryWrapper);
    return RestfulResponse.success(result);
}
```
