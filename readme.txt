项目技术：restful+dubbo+spring+mybatis+mysql
启动方式：1.tomcat 2.jetty
一、新建maven项目,选择maven-archetype-site,maven父级工程创建完毕,工程名restful-mybatis,删除其他目录,保留pom.xml,在pom.xml中加上<package>pom</package>,执行maven的update操作
二、创建maven子项目。
选择maven-Module,输入名称restful-consumer,选择maven-archetype-webapp,右击项目选择properties，然后选择java build path，然后选择add folder,在create new folder,在创建src/main/java
选择maven-Module,输入名称restful-api,选择maven-archetype-webapp,右击项目选择properties，然后选择java build path，然后选择add folder,在create new folder,在创建src/main/java
选择maven-Module,输入名称restful-provider,选择maven-archetype-webapp,右击项目选择properties，然后选择java build path，然后选择add folder,在create new folder,在创建src/main/java
三、添加依赖，将restful-api引入restful-consumer和restful-provider中。
四、启动方式的jar包依赖，jetty相关jar包，引用jersey相关jar包，启动类不在是sping的dispatcherservlet，改成org.glassfish.jersey.servlet.ServletContainer
五、编写restful-consumer,创建restful文件夹，创建接口引用restful的一些注解,创建IManualInfoRestful接口
创建ManualInfoController实现IManualInfoRestful接口,引入api的接口provider,所以需要编写项目restful-api,创建IManualInfoProvider,因为spring也需要@service，所以提前创建好IManualInfoService接口
六、引入dubbo的provider，所以需要用到@Reference注解，所以需要添加dubbo的配置文件，以及jar包
七、引入spring的jar包，识别sping的注解，添加spring的配置
测试restful-consumer客户端，一开始报错，找不到spring的某个类，应该是jersey里面含的spring4是4.3版本，导致他会先找spring4.3的版本的spring的jar，解决方案，把父级项目中的spring的依赖放到jersey前面，
由于将applicationContext.xml改成了spring.xml，监听类ContextLoaderListener会报找不到applicationContext这个文件，改回来就好了，
将spring中的controller注释，访问会报找不到Could not find a suitable constructor in cn.ffcs.tsp.restful.IManualInfoRestful class.原因是因为他找不到他的实现类
改回来再次正常启动，启动后正常，restful-consumer服务配置成功，可是找不到provider，所以会报空指针。
八、编写restful-provider项目，首先引入restful-api依赖，由于需要提供provider，所以需要引入dubbo的jar包，spring的jar包也不可少。
创建ManualInfoProviderImpl类实现provider接口，并且引入duboo注解@Service和dubbo配置文件
测试是否注入成功，启动报错java.lang.ClassNotFoundException: org.I0Itec.zkclient.exception.ZkNoNodeException
dubbo需要注册到zookeeper中，所以需要引入zk的节点jar包（consumer服务和provider服务都需要引入这个jar包），再次启动
<dependency>
		<groupId>com.101tec</groupId>
        <artifactId>zkclient</artifactId>
       <version>0.4</version>
</dependency>
//相同的问题又出现了，reference找不到service，此次不同的是，明显可以看到zookeeper命令行中多了一个node节点，但是用tomcat启动却注入不到，于是将provider和consumer分别用两个tomcat运行，此问题得以解决。
集成mybatis服务
思路：mybatis关键要素：mapper类，返回结果集entity，mapping.xml文件，mybatis配置文件
引入jar，spring-mybatis，mybatis
数据源，所以需要引入数据源包和mysql驱动包
转成json串返回，引入com.alibaba.fastjson包
 
     <!-- json 转换  start-->
     	<dependency>
		    <groupId>com.alibaba</groupId>
		    <artifactId>fastjson</artifactId>
		    <version>1.2.4</version>
		</dependency>
测试成功，访问路径http://localhost:8081/restful-consumer/manualInfo/rest

new 两个server，然后修改server端口

第二种方式jetty启动，
需要注意，jetty的jar包需要放在jersey相关包的上面，否则会报java.lang.NoSuchMethodError: javax.servlet.ServletContext.getFilterRegistration错误
还有要注意，要加一个监听，让他去加载applicationContext文件，否则会报Could not find a suitable constructor in cn.ffcs.tsp.restful.IManualInfoRestful class,其实就是没有扫描到@Controller注解

不建议用tomcat运行，过了一个月回头来测试的时候，发现有时候还是会报空指针，
而且consumer的这个tomcat只能运行一次就得不停地切换端口，猜测原因是因为用的同一个tomcat，new的两个server
再次测试，如果是一个tomcat，无论怎么改项目名称，即使先加载了provider还是会报空指针

所以应该采用jetty方式启动

jetty部署--->利用maven+ant打包


