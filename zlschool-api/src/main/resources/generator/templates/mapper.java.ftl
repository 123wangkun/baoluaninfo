package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import com.zl.school.business.dao.BaseDao;
/**
 * @author ${author}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends BaseDao<${entity},String> {

}
</#if>
