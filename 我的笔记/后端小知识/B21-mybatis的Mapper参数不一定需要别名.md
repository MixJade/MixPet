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

* 以下是xml中的sql

```xml
<!-- 通用查询映射结果 -->
<resultMap id="BaseResultMap" type="com.model.entity.ProInfo">
    <id column="PRO_ID" property="proId"/>
    <result column="START_DATE" property="startDate"/>
    <result column="END_DATE" property="endDate"/>
    <result column="CANCEL_DATE" property="cancelDate"/>
    <result column="USE_STATE" property="useState"/>
</resultMap>

<resultMap id="BaseResultMapDTO" type="com.model.domain.ProInfoDTO" extends="BaseResultMap">
    <result column="PRO_REL_NO" property="proRelNo"/>
    <result column="PRO_REL_NM" property="proRelNm"/>
</resultMap>

    <!-- @formatter:off -->
    <!-- 别名查询结果列 -->
    <sql id="Alias_Column_List">
        <if test="true"/>
        ${alias}.PRO_ID,
        ${alias}.START_DATE, 
        ${alias}.END_DATE, 
        ${alias}.CANCEL_DATE, 
        ${alias}.USE_STATE
    </sql>
    <!-- @formatter:on -->
    <!-- 聚合查询 -->
    <select id="queryProInfo" resultType="com.model.domain.ProInfoDTO">
        SELECT
        <include refid="Alias_Column_List">
            <property name="alias" value="pi"/>
        </include>
        ,
        LISTAGG(DISTINCT agi.GROUP_NM, ',') WITHIN GROUP ( ORDER BY pi.PRO_ID ) AS GROUP_NMS,
        LISTAGG(DISTINCT pr.USR_NM, ',') WITHIN GROUP ( ORDER BY pi.PRO_ID ) AS PRO_REL_NMS
        FROM _PRO_INFO pi
        JOIN _UGROUP_REL aur ON aur.PRO_ID = pi.PRO_ID
        JOIN _GROUP_INFO agi ON agi.GROUP_ID = aur.GROUP_ID
        LEFT JOIN _PRO_REL pr ON pr.PRO_ID = pi.PRO_ID
        GROUP BY
        <include refid="Alias_Column_List">
            <property name="alias" value="pi"/>
        </include>
        HAVING
        pi.USE_STATE = '1'
        <if test="queryProInfo.prjNm != null and queryProInfo.prjNm != '' ">
            <bind name="prjNm" value="'%'+queryProInfo.prjNm+'%'"/>
            AND LISTAGG ( DISTINCT agi.GROUP_NM, ',' ) WITHIN GROUP ( ORDER BY pi.PRO_ID ) LIKE #{prjNm}
        </if>
        <if test="queryProInfo.usrNm != null and queryProInfo.usrNm != '' ">
            <bind name="usrNm" value="'%'+queryProInfo.usrNm+'%'"/>
            AND LISTAGG ( DISTINCT pr.USR_NM, ',' ) WITHIN GROUP ( ORDER BY pi.PRO_ID ) LIKE #{usrNm}
        </if>
    </select>
</mapper>
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

