package com.springboot.swagger;
/**
 * Swagger2默认将所有的Controller中的RequestMapping方法都会暴露，然而在实际开发中，我们并不一定需要把所有API都提现在文档中查看，这种情况下，使用注解@ApiIgnore来解决，如果应用在Controller范围上，则当前Controller中的所有方法都会被忽略，如果应用在方法上，则对应用的方法忽略暴露API。

 注解@ApiOperation和@ApiParam可以理解为API说明，多动手尝试就很容易理解了。
 如果我们不使用这样注解进行说明，Swagger2也是有默认值的，没什么可说的试试就知道了。

 在 http://localhost:8080/swagger-ui.html 显示页面的右上角有api_key ，springfox-swagger 2.2.2 版本并没有进行处理，我们可以自己添加拦截器拦截 /v2/api-docs来处理我们API文档的访问权限，如果要更严格更灵活的控制，可能需要修改源码来实现了。相信 springfox-swagger 的后期版本应该会支持更全面的应用需求的。
 *
 */

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * 增加api注解的控制类
 *
 * @author jiasx
 * @create 2017-09-09 14:57
 **/
@RequestMapping("/api")
@RestController
@Api(tags = {"Api接口"},description="api接口服务")
public class ApiCityController {

    @PostMapping(value = "/show")// 这里指定RequestMethod，如果不指定Swagger会把所有RequestMethod都输出，在实际应用中，具体指定请求类型也使接口更为严谨。
    @ApiOperation(value="测试接口", notes="根据名称查询城市测试接口")
    public String testStr(
            @ApiParam(name="name", value="城市姓名",required=true)
            @RequestParam(name = "name") String cityName){
        return "根据名称查询城市成功";
    }

    /**
     * 查询城市
     * @param city
     * @return
     */
    @PostMapping("get")
    @ApiOperation(value="查询城市", notes="根据条件查询单个城市",httpMethod = "POST", response = City.class)
    public City getCity(@RequestBody City city) {
        city.setProvience("中国分省");
        return city;
    }

    /**
     * 查询城市
     * @param id
     * @param name
     * @param province
     * @return
     */
    @PostMapping("insert")
    @ApiOperation(value="新增城市", notes="新增城市",httpMethod = "POST", response = City.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "城市名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "province", value = "所属省份", required = true, dataType = "String")
    })
    public City insert(String id,String name,String province) {
        return new City(id,name,province);
    }
}
