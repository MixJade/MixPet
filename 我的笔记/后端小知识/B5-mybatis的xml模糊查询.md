# B5-mybatis的xml模糊查询

> * 我们应该尽量减少代码量，既然xml那里的字符串非空的判断无法避免
> * 不如我们就将模糊查询的%%放在xml里
> * (除了只查询一个条件但是可能查询不同表的，那种还是提前拼接比较方便)

* 注意：xml里的%要加上双引号表示这是字符串

```xml
<!--查询分页的条目数-->
<select id="selectPetCount" resultType="int">
    select
    count(*)
    from pet AS a
    LEFT JOIN client AS b
    ON a.client_id=b.client_id
    WHERE a.is_del='0'
    <if test="petName!=null and petName!=''">
        and pet_name like "%"#{petName}"%"
    </if>
    <if test="clientName!=null and clientName!=''">
        and b.client_name like "%"#{clientName}"%"
    </if>
</select>
```