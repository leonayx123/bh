## 写在最前
时间确实太赶了.所以只写了一个第一个story的打样代码.

完全按照工序做的开发. 每个工序的实现类都有spock测试.
可以按 shift+command+t查看





<br>
<br>
<br>

### 进程内架构设计


 基于Onion Architecture，定义四层代码结构：Representation层、Application层、Domain层、Infrastructure层。各层使用独立Jar包或Module强隔离，各层依赖顺序按图所示从外向内。
  ![onion architecture](documents/onion.jpg)

### ui (user interface)层，提供对前端的接口
* controller：使用spring mvc提供http协议的restful接口. 使用swagger描述接口和参数信息.


### application层，应用层连接用户接口层和领域层，协调多个聚合完成服务的组合，只是很薄的一层
* applicationService：协调使用Domain层定义的的聚合model和domain interface的方法完成服务流程；事务处理也放在这一层；
直接依赖调用的domain interface, 通过spring的注入实现依赖倒置,间接依赖infrastructure层.

### domain 层,定义充血领域模型,实现领域模型的核心业务逻辑
* model：Aggregation、Entity、ValueObject等模型定义；
* domian interface : 用于依赖反转的接口定义, repository interface为数据库操作的接口, client interface为其他技术手段的接口定义

### infrastructure层,具体技术实现层,是数据库、消息服务、下游服务的能力提供者
* repositoryImpl 和 data object: implement实现domain层的的Repository接口定义,在repositoryImpl里根据所选的数据库技术来实现持久化等功能. data object是数据库操作时存储数据的贫血模型,如Jpa的@Entity.
* mqs  client和 message：implement domain interface的mq发送的具体的技术实现，发送rocketMq消息的Producer实现类，调用下游服务的feignClient等
* feing client 和 dto：对其他服务调用的feign client 实现.  dto为调用其他服务接口的参数和返回值贫血数据对象。