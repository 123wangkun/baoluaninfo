package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author ${author}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends AbstractServiceImpl<${entity},String> implements ${table.serviceName} {

    @Autowired
    private ${table.mapperName} mapper;

    public ${table.serviceImplName}(${table.mapperName} mapper) {
        super(mapper);
        this.mapper = mapper;
    }
}
</#if>
