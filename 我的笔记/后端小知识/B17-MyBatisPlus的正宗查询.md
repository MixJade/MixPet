# B17-MyBatisPlus的正宗查询

## 一、baseMapper的使用

事实上，在MP的IService的实现类中，可以直接使用**baseMapper**字段，

这个字段所对应的就是**我们自己写的**Mapper文件。

```text
例如：在PetServiceImpl实现类中，baseMapper其实就是PetMapper，我们可以通过它调用自己定义的Mapper方法
```

这字段是在实现类所继承的父类中完成了注入，并使用protect所修饰，

所以MP的Service实现层可以直接调用，不必在重复注入，

## 二、正宗的LamdaWapper

是的，通过Service实现类可以调用正宗的LamdaWapper。

形式如下：

*(下面是更新操作，最后的update是Service中的方法，原来往里面传入条件构造器的那个)*

*(但我想你应该会举一反三，知道查询操作怎么弄)*

```java
// 其它类的Service调用，需要注入对应Service之后，通过字段进行调用。
otherService.lambdaUpdate()
    .eq(SysUserInfo::getBlngDeptId, sysDeptInfo.getDeptNo())
    .set(SysUserInfo::getBlngDeptNm, sysDeptInfo.getDeptNm()).update();
// 在自己类中调用，只需要一个this即可
this.lambdaUpdate()
    .eq(SysDeptInfo::getSuprDeptId, sysDeptInfo.getDeptNo())
    .set(SysDeptInfo::getSuprDeptNm, sysDeptInfo.getDeptNm()).update();
```

