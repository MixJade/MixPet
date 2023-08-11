# B12-增强for与foreach

* 增强的for循环：

```
for (NoticeDto noticeDto : noticeList) {
    String text = getText(noticeDto.getNoticeFile());
    noticeDto.setTextNotice(text);
}
```

* 使用foreach:
* 据说性能并无太大差异，arrayList之中比增强for要慢一点点，但在linkList之中的性能更快。

```
noticeList.forEach(noticeDto -> noticeDto.setTextNotice(getText(noticeDto.getNoticeFile())));
```