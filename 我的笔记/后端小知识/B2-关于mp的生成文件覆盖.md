# Q4-关于mp的文件覆盖.md

* mp逆向生成代码，可以不进行文件覆盖；
* 我现在设置的是不覆盖已有文件，
* 但是我会在这里留下覆盖文件的配置方法
* 即:在每个策略后面添加enableFileOverride

```java
public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/pet_forge?useSSL=true", "root", "root")
                .globalConfig(builder -> {
                    builder.author("MixJade")
                            .disableOpenDir()
                            .outputDir("./src/main/java"); // 指定输出目录
                }).packageConfig(builder -> builder.parent("com.forge")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, "./src/main/resources/com/forge/mapper"))).templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .strategyConfig(builder -> builder.entityBuilder().enableFileOverride()
                        .disableSerialVersionUID()
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("update_time", FieldFill.INSERT_UPDATE)
                        )
                        .mapperBuilder().enableFileOverride().enableMapperAnnotation()
                        .serviceBuilder().enableFileOverride()
                        .controllerBuilder().enableFileOverride().enableRestStyle())
                .execute();
    }
}
```