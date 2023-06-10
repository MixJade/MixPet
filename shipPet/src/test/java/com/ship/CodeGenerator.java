package com.ship;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

/**
 * 通过MP来逆向生成代码
 * 现在设置的是不覆盖已有文件
 * 如果想覆盖文件，在每个策略后面添加enableFileOverride
 */
public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/pet_ship?useSSL=true", "root", "root")
                .globalConfig(builder -> {
                    builder.author("MixJade")
                            .disableOpenDir()
                            .outputDir("./src/main/java"); // 指定输出目录
                }).packageConfig(builder -> builder.parent("com.ship")
                        // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                        .pathInfo(Collections.singletonMap(OutputFile.xml, "./src/main/resources/com/ship/mapper"))).templateEngine(new FreemarkerTemplateEngine())
                .strategyConfig(builder -> builder.entityBuilder().enableFileOverride()
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("update_time", FieldFill.INSERT_UPDATE)
                        )
                        .mapperBuilder().enableMapperAnnotation()
                        .serviceBuilder()
                        .controllerBuilder().enableRestStyle())
                .execute();
    }
}
