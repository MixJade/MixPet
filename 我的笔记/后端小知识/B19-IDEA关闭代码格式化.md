# B19-IDEA关闭代码格式化

IDEA可以关闭部分代码的格式化，不过要先去设置里面开启

~~完了，我格式化了好多公司前辈的代码，大部分都是打了不格式化的标签的~~

## IDEA设置

【编辑器】 ==》【代码样式】==》【格式化程序】==》【在代码注释中使用标记关闭、打开格式化程序】

## 关闭与开启格式化

主要是注释中有`@formatter:off`或`@formatter:on`就行，JAVA的代码也是在注释中放这两句

```xml
<!-- @formatter:off-->
<select id="selectImgAvatar" resultType="String">
...一段有特定韵律的SQL语句
</select>
<!-- @formatter:on-->
```

