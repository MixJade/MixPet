# B2-关于mp的生成文件覆盖

* mp逆向生成代码，可以不进行文件覆盖；
* 我现在设置的是不覆盖已有文件，
* 但是我会在这里留下覆盖文件的配置方法
* 即:在每个策略后面添加enableFileOverride

```java
public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/pet_forge?useSSL=true", "root", "root")
                // ... 其它代码
                .strategyConfig(builder -> builder.entityBuilder().enableFileOverride()
                        // ...其它代码
                        .mapperBuilder().enableFileOverride().enableMapperAnnotation()
                        .serviceBuilder().enableFileOverride()
 						// ...其它代码
                .execute());
    }
}
```