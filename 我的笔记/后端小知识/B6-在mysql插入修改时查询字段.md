# B6-在mysql插入修改时查询字段

> * 有些时候，我们要通过账号插入外键id
> * 可以边插入，边通过账号查询
> * 但是这种很不方便，根据python之禅所说，简单的比复杂好
> * 但我们要会。
> * (1月15日更新)这个被淘汰了，因为我知道了shiro可以直接存储序列化后的对象

## 在更新时查询

* 一是新建一个临时表

```xml
<!--修改公告信息-->
<update id="updateNotice">
    UPDATE notice a,(SELECT b.employee_id FROM employee b WHERE b.employee_username = #{username}) b2
    SET a.notice_title=#{noticeTitle},
    a.update_id=b2.employee_id,
    a.update_time=now()
    WHERE a.notice_id = #{noticeId}
</update>
```

* 或者直接当成更新对象（可以不对其插入）
```xml
<!--审核通过-->
<update id="adoptAdopt">
    UPDATE pet AS a,adopt AS b
    SET a.client_id=b.client_id,
        a.pet_info=#{petInfo},
        b.in_adopt=1
    WHERE a.pet_id = b.pet_id
      AND b.adopt_id = #{adoptId}
</update>
```

## 在插入时查询

```xml
<!--添加公告-->
<insert id="addNotice">
    insert into notice
    (notice_title, notice_file, creat_id, create_time, update_id, update_time)
    value (#{noticeTitle},
    #{noticeFile},
    (select employee_id
    from employee
    where employee_username = #{username}),
    now(),
    creat_id,
    create_time)
</insert>
```