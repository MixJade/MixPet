# 配置SQL文件

1. 在对应mapper文件中

    ```xml
    <mapper namespace="com.forge.mapper.PetMapper">
        <select id="getAll" resultType="com.forge.entity.Pet">
            select * from pet where is_del=0
        </select>
    </mapper>
    ```

2. 对应mapper类
    ```java
    @Mapper
    public interface PetMapper extends BaseMapper<Pet> {
        List<Pet> getAll();
    }
    ```

3. 对应service
    ```java
    public interface IPetService extends IService<Pet> {
        List<Pet> getAll_01();
    }
    ```

4. 对应实现类
    ```java
    @Service
    public class PetServiceImpl extends ServiceImpl<PetMapper, Pet> implements IPetService {
    
        @Resource
        PetMapper petMapper;
    
        @Override
        public List<Pet> getAll_01() {
            return petMapper.getAll();
        }
    }
    ```