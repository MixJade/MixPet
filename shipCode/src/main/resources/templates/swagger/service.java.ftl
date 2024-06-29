package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
 * ${table.comment!} 服务类
 *
 * @author ${author}
 * @date ${date}
 */
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

   /**
   * 按照编号查询
   *
   * @author: ${author}
   * @date: ${date}
   */
   ${entity} detailById(String id);
}
