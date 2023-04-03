# mybatis的choose的相等

* 里面的等号是`==`
* 并且会自动判断类型，如果你放一个长得像`int`的String数据，就按照int来判定
* 就像下面的判断，seaType实际上是String，但是必须按照int判断

```xml

<if test="seaName!=null and seaName!=''">
    <choose>
        <when test="seaType==0">
            and b.client_name like #{seaName}
        </when>
        <when test="seaType==1">
            and c.pet_Name like #{seaName}
        </when>
        <when test="seaType==2">
            and e.doctor_name like #{seaName}
        </when>
        <otherwise>
            and d.department_name like #{seaName}
        </otherwise>
    </choose>
</if>
```