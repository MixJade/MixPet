package ${package.Controller};

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${table.comment!} 前端控制器
 *
 * @author ${author}
 * @date ${date}
 */
@RestController
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@Tag(name = "${table.comment!}操作接口")
public class ${table.controllerName} {
    private final ${table.serviceName} ${table.serviceName?uncap_first};

    @Autowired
    public ${table.controllerName}(${table.serviceName} ${table.serviceName?uncap_first}) {
        this.${table.serviceName?uncap_first} = ${table.serviceName?uncap_first};
    }

    /**
     * 按照编号查询
     *
     * @author: ${author}
     * @date: ${date}
     */
    @Operation(summary = "按照编号查询")
    @Parameter(name = "id", description = "ID", required = true, in = ParameterIn.QUERY)
    @RequestMapping(value = "/detail-by-id", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public ${entity} detailById(@RequestParam String id){
        return ${table.serviceName?uncap_first}.detailById(id);
    }
}
