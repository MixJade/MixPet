# B13-try-resource语法

* 使用这个语法糖，可以少两行代码
* 一行是新建对象，与try合为一行
* 一行是close语句，可以自动关闭
* (close语句应该写在finally块内，因为如果try中语句调用return，finally中的代码也会执行)
* (故，可以在try语言中提前return)

```
try (FileWriter writer = new FileWriter(noticePath + noticeFile)) {
    writer.write("");//清空原文件内容
    writer.write(text);
    writer.flush();
} catch (IOException e) {
    log.warn("公告文本写入失败" + e);
}
```