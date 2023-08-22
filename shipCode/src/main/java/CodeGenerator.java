import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collections;

public class CodeGenerator {
    /**
     * 输出目录的路径，到项目名称为止
     */
    private static final String outputDir = "/home/mixjade/mySoft/MixPet/shipPet";

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/pet_ship?useSSL=true", "root", "root")
                // ============全局配置============
                .globalConfig(builder -> {
                    builder.author("MixJade")
                            .disableOpenDir()
                            .outputDir(outputDir + "/src/main/java"); // 指定输出目录
                })
                // ===========软件包配置============
                .packageConfig(builder -> builder.parent("com.ship").entity("model.entity")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, outputDir + "/src/main/resources/com/ship/mapper")))
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
                .strategyConfig(builder -> builder.entityBuilder().enableFileOverride()
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("update_time", FieldFill.INSERT_UPDATE)
                        )
                        .mapperBuilder().mapperAnnotation(Mapper.class)
                        .serviceBuilder()
                        .controllerBuilder().enableRestStyle())
                .execute();
    }
}
