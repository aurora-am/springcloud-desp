# springcloud-desp
hospital-manage：医院接口模拟端（已开发，直接使用）

yygh-parent：根目录，管理子模块：

common：公共模块父节点

common-util：工具类模块，所有模块都可以依赖于它

  YyghException	自定义全局异常
  Result	API统一返回结果封装类
  ResultCodeEnum	API统一返回结果状态信息
  BeanUtils	实体bean copy工具类
  MD5	MD5加密工具类
  
service-util：service服务的工具包，包含service服务的公共配置类，所有				  service模块依赖于它

model：实体类模块

service：api接口服务父节点

service-hosp：医院api接口服务

service-cmn：公共api接口服务

service-user：用户api接口服务

service-order：订单api接口服务

service-cmn-client：公共api接口

service-hosp-client：医院api接口

service-user：用户api接口
