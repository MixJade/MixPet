# mybatis的Mapper参数不一定需要别名

mybatis的Mapper参数不一定需要别名，只要mapper文件中的参数名称一样就行。

如果拿不准的话，就在mapper.xml文件中用一个不存在的名字，mybatis会在报错的日志里面提示用哪些参数。

## 代码示例

* 可以看到这里的第二个参数没有使用别名，在xml中使用`queryProInfo`进行查询。
* 第一个是mybatis-plus设置的分页，这个使用了默认名`"param1"`，即：mapper的第一个参数

```java
public interface ProInfoMapper extends BaseMapper<ProInfo> {
    IPage<ProInfoDTO> queryProInfo(Page<ProInfoDTO> page, QueryProInfo queryProInfo);
}
```

## 更简单的代码示例

```java
/**
 * 像这种，xml文件中的占位符名称与接口参数名称一样的
 * <p>可以不用通过Param注解起别名</p>
 *
 * @param page       mp的分页插件
 * @param clientName 用户名
 * @param petName    宠物名
 * @return 领养信息
 */
IPage<AdoptDto> selectAdoptPage(IPage<AdoptDto> page, String clientName, String petName);
```

```xml
<!--分页查询-->
<select id="selectAdoptPage" resultType="com.ship.model.dto.AdoptDto">
    SELECT
    adopt_id, adopt_code,a.pet_id, a.client_id, adopt_money,
    adopt_info,in_adopt,a.create_time,b.pet_name,c.client_name
    FROM adopt AS a,pet AS b,client AS c
    WHERE a.is_del = 0
    AND a.pet_id = b.pet_id
    AND a.client_id = c.client_id
    <if test="clientName!=null and clientName!=''">
        <bind name="clientName" value="'%'+clientName+'%'"/>
        and c.client_name like #{clientName}
    </if>
    <if test="petName!=null and petName!=''">
        <bind name="petName" value="'%'+petName+'%'"/>
        and b.pet_name like #{petName}
    </if>
    ORDER BY create_time DESC
</select>
```

