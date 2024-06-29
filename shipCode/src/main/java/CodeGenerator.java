import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collections;

interface MyConfig {
    /**
     * 需要生成的表名
     */
    String[] tableNames = new String[]{"Adopt"};

    /**
     * 数据库链接、账号、密码
     */
    String url = "jdbc:mysql://localhost:3306/pet_ship?useSSL=true",
            username = "root",
            password = "root";

    /**
     * 输出目录的路径，到项目名称为止
     */
    String outputDir = "/home/mixjade/mySoft/MixPet/shipPet";

    /**
     * 软件包、entity文件的包路径
     */
    String parentPack = "com.ship",
            entityPack = "model.entity";

    /**
     * MapperXml文件的路径
     */
    String mapperXmlDir = "/src/main/resources/com/ship/mapper";

    /**
     * 文件作者
     */
    String author = "MixJade";
}


public class CodeGenerator {

    public static void main(String[] args) {
        FastAutoGenerator.create(MyConfig.url, MyConfig.username, MyConfig.password)
                // ============全局配置============
                .globalConfig(builder -> {
                    builder.author(MyConfig.author)
                            .disableOpenDir()
                            .outputDir(MyConfig.outputDir + "/src/main/java"); // 指定输出目录
                })
                // ===========软件包配置============
                .packageConfig(builder -> builder.parent(MyConfig.parentPack).entity(MyConfig.entityPack)
                        .pathInfo(Collections.singletonMap(OutputFile.xml, MyConfig.outputDir + MyConfig.mapperXmlDir)))
                // 使用Freemarker引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                // ============模板配置============
                .templateConfig(builder -> builder.disable(TemplateType.ENTITY)
                        .disable(TemplateType.ENTITY)
                        .entity("/templates/entity.java")
                        .service("/templates/service.java")
                        .serviceImpl("/templates/serviceImpl.java")
                        .mapper("/templates/mapper.java")
                        .xml("/templates/mapper.xml")
                        .controller("/templates/controller.java"))
                // ============策略配置============
                .strategyConfig(builder -> builder
                        // 需要生成的表名,注掉则为生成所有表
                        .addInclude(MyConfig.tableNames)
                        // 覆盖已经生成的entity文件
                        .entityBuilder().enableFileOverride()
                        // entity的字段自动填充策略,最好关掉
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("update_time", FieldFill.INSERT_UPDATE)
                        )
                        // mapper接口打上Mapper注解
                        .mapperBuilder().mapperAnnotation(Mapper.class)
                        .serviceBuilder()
                        .controllerBuilder().enableRestStyle())
                .execute();
    }
}
