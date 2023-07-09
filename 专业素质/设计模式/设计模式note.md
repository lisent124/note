# 绪论

在 1994 年，由 Erich Gamma、Richard Helm、Ralph Johnson 和 John Vlissides 四人合著出版了一本名为 **Design Patterns - Elements of Reusable Object-Oriented Software（中文译名：设计模式 - 可复用的面向对象软件元素）** 的书，该书首次提到了软件开发中设计模式的概念。

四位作者合称 **GOF（四人帮，全拼 Gang of Four）**。他们所提出的设计模式主要是基于以下的面向对象设计原则。

- 对接口编程而不是对实现编程。
- 优先使用对象组合而不是继承。

传统一共有**24种**设计模式





## 六大原则

### 开闭原则

开闭原则的意思是：**对扩展开放，对修改关闭**。在程序需要进行拓展的时候，不能去修改原有的代码，实现一个热插拔的效果。简言之，是为了使程序的扩展性好，易于维护和升级。想要达到这样的效果，我们需要使用接口和抽象类，后面的具体设计中我们会提到这点。

### 里氏替换原则

里氏代换原则是面向对象设计的基本原则之一。 里氏代换原则中说，任何基类可以出现的地方，子类一定可以出现。LSP 是继承复用的基石，只有当派生类可以替换掉基类，且软件单位的功能不受到影响时，基类才能真正被复用，而派生类也能够在基类的基础上增加新的行为。里氏代换原则是对开闭原则的补充。实现开闭原则的关键步骤就是抽象化，而基类与子类的继承关系就是抽象化的具体实现，所以里氏代换原则是对实现抽象化的具体步骤的规范。

### 依赖倒转原则

这个原则是开闭原则的基础，具体内容：针对接口编程，依赖于抽象而不依赖于具体。

### 接口隔离原则

这个原则的意思是：使用多个隔离的接口，比使用单个接口要好。它还有另外一个意思是：降低类之间的耦合度。由此可见，其实设计模式就是从大型软件架构出发、便于升级和维护的软件设计思想，它强调降低依赖，降低耦合。

### 迪米特法则

又称最少知道原则。指：一个实体应当尽量少地与其他实体之间发生相互作用，使得系统功能模块相对独立。

### 合成复用原则

合成复用原则是指：尽量使用合成/聚合的方式，而不是使用继承。

# 创建型模式

这些设计模式提供了一种在创建对象的同时隐藏创建逻辑的方式，而不是使用 new 运算符直接实例化对象。这使得程序在判断针对某个给定实例需要创建哪些对象时更加灵活。

## 工厂模式

![image-20230523163526398](image\工厂模式.png)

## 抽象工厂

![image-20230523163657454](image\抽象工厂.png)



## 单例模式

![image-20230523163732181](image\image-20230523163732181.png)

## 建造者模式

![image-20230523163918749](image\image-20230523163918749.png)

## 原型模式

![image-20230523164436852](image\image-20230523164436852.png)



# 结构型模式

这些设计模式关注类和对象的组合。继承的概念被用来组合接口和定义组合对象获得新功能的方式



## 适配器模式

![image-20230523164649155](image\image-20230523164649155.png)

## 桥接模式

![image-20230523164732730](image\image-20230523164732730.png)

## 过滤器模式

![过滤器模式的 UML 图](image\20230510-filter.svg)



## 组合模式

![组合模式的 UML 图](image\20210817-composite-composite.svg)



## 装饰器模式

![装饰器模式的 UML 图](image\20210420-decorator-1-decorator-decorator.svg)



## 外观模式

![外观模式的 UML 图](image\20201015-facade.svg)

## 享元模式

![享元模式的 UML 图](image\20201015-fiyweight.svg)

## 代理模式

![代理模式的 UML 图](image\20211025-proxy.svg)

# 行为模式

这些设计模式特别关注对象之间的通信。

## 责任链模式

![责任链模式的 UML 图](image\2021-chain-of-responsibility.svg)

## 命令模式

![img](https://www.runoob.com/wp-content/uploads/2014/08/20220427-command-1-command-1.svg)

## 解释器模式

![解释器模式的 UML 图](image\interpreter_pattern_uml_diagram.jpg)

## 迭代器模式

![迭代器模式的 UML 图](image\202107-23-iterator-pattern.png)

## 中介者模式

![中介者模式的 UML 图](image\mediator_pattern_uml_diagram.jpg)



## 备忘录模式

![备忘录模式的 UML 图](image\memento-20220601-memento.svg)



## 观察者模式

![观察者模式的 UML 图](image\observer_pattern_uml_diagram.jpg)

## 状态模式

![状态模式的 UML 图](image\state_pattern_uml_diagram.png)



## 空对象模式

![空对象模式的 UML 图](image\null_pattern_uml_diagram.jpg)

## 策略模式

![策略模式的 UML 图](image\strategy_pattern_uml_diagram.jpg)

## 模板模式

![模板模式的 UML 图](image\template_pattern_uml_diagram.jpg)



## 访问者模式

![访问者模式的 UML 图](image\visitor_pattern_uml_diagram.jpg)