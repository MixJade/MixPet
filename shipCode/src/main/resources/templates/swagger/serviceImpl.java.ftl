package ${package.ServiceImpl};

import ${superServiceImplClassPackage};
import ${package.Mapper}.${table.mapperName};
import ${package.Entity}.${entity};
<#if table.serviceInterface>
import ${package.Service}.${table.serviceName};
</#if>
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * ${table.comment!} 服务实现类
 *
 * @author ${author}
 * @date ${date}
 */
@Service
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}><#if table.serviceInterface> implements ${table.serviceName}</#if> {
    /**
     * 按照编号查询
     *
     * @author: ${author}
     * @date: ${date}
     */
    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public ${entity} detailById(String id){
        return getById(id);
    }
}
