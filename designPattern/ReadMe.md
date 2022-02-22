## 设计模式
各种设计模式对比及编程思想总结如下表所示。

设计模式	一句话归纳	目 的	生活案例	框架源码举例
工厂模式（Factory Pattern）	产品标准化，<br>生产更高效	封装创建细节	实体工厂	LoggerFactory、Calender
单例模式（Singleton Pattern）	世上只有一个我	保证独一无二	CEO	BeanFactory、Runtime
原型模式（Prototype Pattern）	拔一根猴毛，<br>吹出千万个	高效创建对象	克隆	ArrayList、PrototypeBean
建造者模式（Builder Pattern）	高配中配与低配，<br>想选哪配就哪配	开放个性配置步骤	选配	StringBuilder、<br>BeanDefinitionBuilder
代理模式（Proxy Pattern）	没有资源没时间，<br>得找媒婆来帮忙	增强职责	媒婆	ProxyFactoryBean、<br>JdkDynamicAopProxy、CglibAopProxy
门面模式（Facade Pattern）	打开一扇门，<br>通向全世界	统一访问入口	前台	JdbcUtils、RequestFacade
装饰器模式（Decorator Pattern）	他大舅他二舅，<br>都是他舅	灵活扩展、<br>同宗同源	煎饼	BufferedReader、InputStream
享元模式（Flyweight Pattern）	优化资源配置，<br>减少重复浪费	共享资源池	全国社保联网	String、Integer、ObjectPool
组合模式（Composite Pattern）	人在一起叫团伙，<br>心在一起叫团队	统一整体和个体	组织架构树	HashMap、SqlNode
适配器模式（Adapter Pattern）	万能充电器	兼容转换 电源适配	AdvisorAdapter、HandlerAdapter	
桥接模式（Bridge Pattern）	约定优于配置	不允许用继承	桥	DriverManager
委派模式（Delegate Pattern）	这个需求很简单，<br>怎么实现我不管	只对结果负责	授权委托书	ClassLoader、<br>BeanDefinitionParserDelegate
模板模式（Template Pattern）	流程全部标准化，<br>需要微调请覆盖	逻辑复用	把大象装进冰箱	JdbcTemplate、HttpServlet
策略模式（Strategy Pattern）	条条大道通北京，<br>具体哪条你来定	把选择权交给用户	选择支付方式	Comparator、<br>InstantiationStrategy
责任链模式（Chain of Responsibility Pattern）	各人自扫门前雪，<br>莫管他人瓦上霜	解耦处理逻辑	踢皮球	FilterChain、Pipeline
迭代器模式（Iterator Pattern）	流水线上坐一天，<br>每个包裹扫一遍	统一对集合的访问方式	逐个检票进站	Iterator
命令模式（Command Pattern）	运筹帷幄之中，<br>决胜千里之外	解耦请求和处理	遥控器	Runnable、TestCase
状态模式（State Pattern）	状态驱动行为，<br>行为决定状态	绑定状态和行为	订单状态跟踪	Lifecycle
备忘录模式（Memento Pattern）	失足不成千古恨，<br>想重来时就重来	备份，后悔机制	草稿箱	StateManageableMessageContext
中介者模式（Mediator Pattern）	联系方式我给你，<br>怎么搞定我不管	统一管理网状资源	朋友圈	Timer
解释器模式（Interpreter Pattern）	我想说“方言”，<br>一切解释权归我	实现特定语法解析	摩斯密码	Pattern、ExpressionParser
观察者模式（Observer Pattern）	到点就通知我	解耦观察者与被观察者	闹钟	ContextLoaderListener
访问者模式（Visitor Pattern）	横看成岭侧成峰，<br>远近高低各不同	解耦数据结构和数据操作	KPI考核	FileVisitor、BeanDefinitionVisitor
不管是面试也好，还是日常开发也好，相信大家都已经胸有成竹、信心满满了。但是从笔者的架构经验和教学经验总结来看，还有很多小伙伴对一些设计模式经常混淆难懂。以下内容将是我对设计模式的最精华总结，收集了我在教学过程中很多来自学员的疑问，对各种容易混淆的设计模式进行比较，并总结整理了以下内容，希望帮助大家在以后的设计选型中能够披荆斩棘，如履平地。如果你在阅读本书之前，对设计模式较为熟悉，本章内容可以帮助你巩固加深理解。

