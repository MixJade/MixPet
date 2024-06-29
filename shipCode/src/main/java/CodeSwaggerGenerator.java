import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collections;

/**
 * 代码生成器，springdoc+lombok版
 *
 * @since 2024-6-29 14:39:09
 */
public class CodeSwaggerGenerator {

    public static void main(String[] args) {
        FastAutoGenerator.create(MyConfig.url, MyConfig.username, MyConfig.password)
                // ============全局配置============
                .globalConfig(builder -> {
                    builder.author(MyConfig.author)
                            .disableOpenDir()
                            .enableSpringdoc() // 开启 swagger 模式
                            .outputDir(MyConfig.outputDir + "/src/main/java"); // 指定输出目录
                })
                // ===========软件包配置============
                .packageConfig(builder -> builder.parent(MyConfig.parentPack)
                        .entity(MyConfig.entityPack)
                        .mapper(MyConfig.mapperPack)
                        .pathInfo(Collections.singletonMap(OutputFile.xml, MyConfig.outputDir + MyConfig.mapperXmlDir)))
                // 使用Freemarker引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                // ============模板配置============
                .templateConfig(builder -> builder.disable(TemplateType.ENTITY)
                        .disable(TemplateType.ENTITY)
                        .entity("/templates/swagger/entity.java")
                        .service("/templates/swagger/service.java")
                        .serviceImpl("/templates/swagger/serviceImpl.java")
                        .mapper("/templates/swagger/mapper.java")
                        .xml("/templates/swagger/mapper.xml")
                        .controller("/templates/swagger/controller.java"))
                // ============策略配置============
                .strategyConfig(builder -> builder
                        // 需要生成的表名,注掉则为生成所有表
                        .addInclude(MyConfig.tableNames)
                        // 覆盖已经生成的entity文件
                        .entityBuilder().enableFileOverride().enableLombok()
                        .naming(NamingStrategy.underline_to_camel).enableTableFieldAnnotation()// 下划线分割变为驼峰命名,且打上swagger注解
                        .mapperBuilder().mapperAnnotation(Mapper.class) // mapper接口打上Mapper注解
                        .enableBaseResultMap().enableBaseColumnList()// mapper.xml生成BaseResultMap
                        .serviceBuilder()
                        // 将IService变为Service
                        .formatServiceFileName("%sService")
                        .controllerBuilder().enableRestStyle())
                .execute();
    }
}
