package ${ControllerPackage};
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import ${POJOClass};
import ${ServiceClass};

/**
* ${classInfo.classComment}
*
* Created by yrc on '${.now?string('yyyy-MM-dd HH:mm:ss')}'.
*/
@Controller
@RequstMapping("/${classInfo.className?uncap_first}")
public class  ${classInfo.className}Controller{

    @Resource
    private ${classInfo.className}Service ${classInfo.className?uncap_first}Service;

    /**
    * 新增
    */
    @RequestMapping("/insert")
    @ResponseBody
    public ReturnT<String> insert(${classInfo.className}POJO ${classInfo.className?uncap_first}){
        return ${classInfo.className?uncap_first}Service.insert(${classInfo.className?uncap_first});
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    @ResponseBody
    public ReturnT<String> delete(int id){
        return ${classInfo.className?uncap_first}Service.delete(id);
    }

    /**
    * 更新
    */
    @RequestMapping("/update")
    @ResponseBody
    public ReturnT<String> update(${classInfo.className}POJO ${classInfo.className?uncap_first}){
        return ${classInfo.className?uncap_first}Service.update(${classInfo.className?uncap_first});
    }

    /**
    * Load查询
    */
    @RequestMapping("/load")
    @ResponseBody
    public ReturnT<${classInfo.className}POJO> load(int id){
        ${classInfo.className}POJO  ${classInfo.className?uncap_first} = ${classInfo.className?uncap_first}Service.load(id);
        return new ReturnT< ${classInfo.className?uncap_first}>
    }

    /**
    * 分页查询
    */
    @RequestMapping("/pageList")
    @ResponseBody
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                        @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return ${classInfo.className?uncap_first}Service.pageList(offset, pagesize);
    }

}
