# 静态语言 & 动态语言
###  静态语言

 - 与动态语言相对应，运行时结构不可改变的语言就是静态语言，如：Java、C、C++、C#
 - Java不是动态语言，但Java可以称之为“准动态语言”
 - Java有一定的动态性，我们可以利用反射机制获得类似动态语言的特性
 - Java的动态性让编程的时候更加灵活

###  动态语言
 - 是一类在运行时可以改变其结构的语言：例如新的函数、对象、设置代码可以被引进，已有的函数可以被删除或是其他结构上的变化
 - 通俗点说就是在运行时代码可以根据某些条件改变自身结构
 - 主要的动态语言：JavaScript、PHP、python等
  
# 1、 Java 反射机制概述

## 1.1 Java Reflection
Reflection（反射）是Java被视为动态语言的关键，反射机制允许程序在执行期借助于ReflectionAPI获取任何类的内部信息，并能直接操作任意对象的内部属性及方法

<center>
<img src="https://img-blog.csdnimg.cn/20200512121110608.png" width="80%" alt=""/>
</center>

加载完类之后，在`方法区` 中就产生了一个Class类型的对象（一个类只有一个Class对象），这个对象就包含了完整的类的结构信息。我们可以通过这个对象看到类的结构。这个对象就像一面镜子，透过这个镜子看到类的结构，所以称为：反射

<center>
<img src="https://img-blog.csdnimg.cn/20200512163048771.png" width="80%" alt=""/>
</center>


## 1.2 Java 反射机制研究及应用
> Java 反射机制提供的功能

 - 在运行时判断任意一个对象所属的类
 - 在运行时构造任意一个类的对象
 - 在运行时判断任意一个类所具有的成员变量和方法
 - 在运行时获取泛型信息
 - 在运行时调用任意一个对象的成员变量和方法
 - 在运行时处理注解
 - 生成动态代理 AOP

## 1.3 Java 反射优点和缺点
<font color=red>**优点**</font>

 - 可以实现动态创建对象和编译，体现出很大的灵活性

 <font color=red>**缺点**</font>

 - 对性能有影响
 - 使用反射基本上是一种解释操作，我们可以告诉JVM，我们希望做什么并且它满足我们的要求
 - 这类操作总是慢于直接执行的相同操作

## 1.4  反射相关主要的API

 - java.lang.Class ：代表一个类
 - java.lang.reflect.Method ：代表类的方法
 - java.lang.reflect.Field ：代表类的成员变量
 - java.lang.reflect.Construct ：代表类的构造器

# 2、 理解Class类并获取Class实例
## 2.1 Object 类
在Object类中定义了一下方法，此方法将被所有子类继承

```java
public final native Class<?> getClass();
```
> 以上方法返回值类型你一个Class类，此类是Java反射的源头，所谓的反射从程序的运行结果来看也很好理解，即：`可以通过对象求出类的名字`


## 2.2 实体类 与 Class类

```java
package com.ctra.reflection;
//什么叫反射
public class test01 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class c1 = Class.forName("com.ctra.reflection.test01");

        Class c2 = Class.forName("com.ctra.reflection.test01");

        Class c3 = Class.forName("com.ctra.reflection.test01");
        System.out.println(c1.hashCode());
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());

    }
}

// 实体类：pojo entity
class User{
    private String name;
    private int id;
    private int age;

    public User() {
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public User(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }
}
```
输出结果：

<center>
<img src="https://img-blog.csdnimg.cn/20200513092807796.png" width="80%" alt=""/>
</center>


 - 实例类对象照镜子（获取Class类）后可以得到的信息：某个类的属性、方法和构造器、某个类到底实现了哪些接口
 - 对于每个类而言，JRE都为其保留一个不变的Class类型的对象
 - 一个Class对象包含了特定某个结构（class/interface/enum/annotation/primitive type/void/[]）有关信息

## 2.3	Class类

 - Class 本身也是一个类
 - Class 对象只能由系统建立对象
 - 一个加载的类在JVM中只会有一个Class实例
 - 一个Class对象对应的是一个加载到JVM中的一个.class文件
 - 每个类的实例都会记得自己是由哪个Class实例所生成
 - 通过Class可以完整地得到一个类中的所有被夹在的结构
 - Class类是Reflection的根源，针对任何逆向动态加载、运行的类、唯有先获得相应的Class对象
 
## 2.4	Class类 常用方法
|方法名| 功能说明 |
|:--|--|
| static ClassforName(String name) | 返回指定类名name的Class对象 |
| Object newInstance() | 调用缺省构造函数，返回Class对象的一个实例 |
| getName() | 返回此Class对象所表示的实体（类，接口，数组类或void）的名称 |
| Class getSuperClass() | 返回当前Class对象的父类的Class对象 |
| Class[] getInterfaces() | 获取当前Class对象的接口 |
| ClassLoader getClassLoader() | 返回该类的类加载器 |
| Constructor[] getConstructors() | 返回一个包含某些 Constructor 对象的数组|
|  Method getMethod(String name, Class<?>... parameterTypes) | 返回一个Method对象，此对象的形参类型为 paramType |
|Field[] getDeclaredFields()|返回Field对象的一个数组|

## 2.5	获取 Class类的实例

#### 1）根据 .class 获取
若已知具体的类，通过类的 class 属性获取，该方法最为安全可靠，程序性能最高

```java
Class userClass = User.class;
```
#### 2）根据 getClass() 方法获取
已知某个类的实例，调用该实例的getClass() 方法获取Class 对象
```java
Class c = userClass.getClass();
```
#### 3）根据 静态方法 forName() 方法获取
已知一个类的全类名，且该类在类路径下，可通过Class类的静态方法forName()获取，可能抛出ClassNotFoundException
```java
Class c1 = Class.forName("com.ctra.reflection.test01");
```
####  4）.Type
内置基本数据类型可以直接使用类名 .Type
####  5）ClassLoader
还可以利用ClassLoader

> 上述几个方式的实战源码
```java
package com.ctra.reflection;

public class test02  {
    public static void main(String[] args) throws ClassNotFoundException {
        Person person =new Student();
        System.out.println("这个人是："+person.name);
        //  方式一：通过对象 getClass() 获得
        Class c1 = person.getClass();
        System.out.println(c1.hashCode());
        //  方式二：通过 静态方法 forname获得
        Class c2 = Class.forName("com.ctra.reflection.Person"); // 继承要看创建的是谁的对象（这里是反例）
        Class c3 = Class.forName("com.ctra.reflection.Student");
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());
        //  方式三：通过类名 .class 获得
        Class c4 =Student.class;
        System.out.println(c4.hashCode());
        //  方式四：基本内置类型的包装类都有一个Type属性
        Class c5 =Integer.TYPE;
        System.out.println(c5);
        // 获得父类类型
        Class c6 = c1.getSuperclass();
        System.out.println(c6);


    }
}

class Person{
    public String name;
    public Person() {
    }
    public Person(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Student extends Person{
    public Student(){
        this.name = "学生";
    }
}

class Teacher extends  Person{
    public Teacher(){
        this.name="老师";
    }
}
```
	输出：
	这个人是：学生
	856419764
	621009875
	856419764
	856419764
	int
	class com.ctra.reflection.Person
## 2.6	哪些类型可以有 Class 对象？

 - class：外部类，成员（成员内部类，静态内部类），局部内部类，匿名内部类
 - interface：接口
 - []：数组
 - enum：枚举
 - annotation：注解@interface
 - primitive type：基本数据类型
 - void

# 3、 类的加载与ClassLoader
## 3.1 类的加载过程
当程序主动使用某个类时，如果该类还未被加载到内存中，则系统会通过如下三个步骤来对该类进行初始化

<center>
<img src="https://img-blog.csdnimg.cn/20200513105106458.png" width="100%" alt=""/>
</center>

<center>
<img src="https://img-blog.csdnimg.cn/20200525102809667.png" width="100%" alt=""/>
</center>

## 3.1 类的加载与ClassLoader的理解
####  step1：加载
将class文件字节码内容加载到内存中，并将这些静态数据转换成方法区的运行时数据结构，然后生成一个代表这个类的 java.lang.Class 对象
####  step2：链接
将 Java类的`二进制代码`合并到JVM的运行状态之中的过程

 - 验证：确保加载的类信息符合JVM规范，没有安全方面的问题
 - 准备：正式为类变量（static）分配内存并设置类变量默认初始值的阶段，这些内存都将在方法区中进行分配
 - 解析：虚拟机常量池内的符号引用（常量名）替换为直接饮用（地址）的过程

####  step3：初始化

 - 执行类构造器 < clinit> ()方法的过程。类构造器< clinit> ()方法是由编译器自动收集类中所有变量的赋值动作和静态代码块中的语句合并产生的。（类构造器是构造类信息的，不是构造该类对象的构造器）
 - 当初始化一个类的时候，如果发现其父类还没有进行初始化，则需要先触发其父类的初始化
 - 虚拟机会保证一个类的< clinit> ()方法在多线程环境中被正确加锁和同步

## 3.3 类加载器的作用

<center>
<img src="https://img-blog.csdnimg.cn/20200514151923414.png" width="100%" alt=""/>
</center>

 - 类加载的作用：<font color=red>将class文件字节码内容加载到内存中</font>，并将这些静态数据转换成方法区的运行时数据结构，然后在堆中生成一个代表这个类的 java.lang.Class 对象，作为方法区中类数据的访问入口
 - 类缓存：标准的 JavaSE 类加载器可以按要求查找类，但一旦某个类被加载到类加载器中，它将维持加载（缓存）一段时间。不过JVM垃圾回收机制可以回收这些Class对象

## 3.4 类的加载器  ClassLoader
系统类的加载器、拓展类加载器、根加载器（C++）
```java
package com.ctra.reflection;

public class test07 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 获取系统类的加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        // 获取系统类加载器的父类加载器-->拓展类加载器
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);

        // 获取拓展类加载器的父类加载器-->根加载器（C++）
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);

        // 测试当前类是哪个加载器加载的
        ClassLoader classLoader = Class.forName("com.ctra.reflection.test07").getClassLoader();
        System.out.println(classLoader);

        // 测试 JDK 内置的类 是谁加载  由于根加载器，无法加载
        classLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println(classLoader);

    }
}
```
	输出：
	sun.misc.Launcher$AppClassLoader@18b4aac2
	sun.misc.Launcher$ExtClassLoader@330bedb4
	null
	sun.misc.Launcher$AppClassLoader@18b4aac2
	null

### 获得系统类加载器可以加载的路径

```java
System.out.println(System.getProperty("java.class.path"));
```

>  *D:\Program Files\Java\jdk1.8.0_211\jre\lib\charsets.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\deploy.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\access-bridge-64.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\cldrdata.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\dnsns.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\jaccess.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\jfxrt.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\localedata.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\nashorn.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\sunec.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\sunjce_provider.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\sunmscapi.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\sunpkcs11.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\zipfs.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\javaws.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\jce.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\jfr.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\jfxswt.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\jsse.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\management-agent.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\plugin.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\resources.jar;
        * D:\Program Files\Java\jdk1.8.0_211\jre\lib\rt.jar;
        * `E:\idea\mybatis\reflection\target\classes;`
        * C:\Users\Administrator\.m2\repository\mysql\mysql-connector-java\5.1.47\mysql-connector-java-5.1.47.jar;
        * C:\Users\Administrator\.m2\repository\org\mybatis\mybatis\3.5.2\mybatis-3.5.2.jar;
        * C:\Users\Administrator\.m2\repository\junit\junit\4.12\junit-4.12.jar;
        * C:\Users\Administrator\.m2\repository\org\hamcrest\hamcrest-core\2.1\hamcrest-core-2.1.jar;
        * C:\Users\Administrator\.m2\repository\org\hamcrest\hamcrest\2.1\hamcrest-2.1.jar;
        * D:\Program Files\JetBrains\IntelliJ IDEA 2019.3.3\lib\idea_rt.jar

一般在 rt.jar 后，则为我们当前的类        

## 3.5 类的初始化 
### 1、<font color=red>类的主动引用（一定会发生类的初始化）</font>
>这里参考了一下这个文章，大家感觉可以再阅读一下
文章地址：[https://blog.csdn.net/weixin_42636552/article/details/82949999](https://blog.csdn.net/weixin_42636552/article/details/82949999)
1. 遇到 **new，getstatic，putstatic，invokestatic**这4条字节码指令时，假如类还没进行初始化，则马上对其进行初始化工作。
	> `其实就是3种情况：`
	1>	用new实例化一个类时
	2>	读取或者设置类的静态字段时（不包括被final修饰的静态字段，因为他们已经被塞进常量池了）
	3>	执行静态方法的时候。
    
3. 使用java.lang.reflect 包中的方法对类进行反射调用的时候，如果类还没有进行过初始化，马上对其进行初始化工作。
    
4. 初始化一个类的时候，如果他父类还没有被初始化，则先去初始化其父类。
    
5. 当jvm启动时，用户指定一个要执行的主类（包含入口方法 static void main(String[] args)的那个类），则jvm会先去初始化这个类。

直接上代码：
```java
package com.ctra.reflection;

public class test06 {
    static{
        System.out.println("Main类被加载");
    }
    public static void main(String[] args) {
        // 1、 new 主动引用
        Son s= new Son();
        // 2、反射也会产生主动引用
        // Class c1 = Class.forName("com.ctra.reflection.Son");
    }
}

class Father{
    static int b =2;
    static{
        System.out.println("父类被加载");
    }
}

class Son extends Father{
    static{
        System.out.println("子类被加载");
        m =300;
    }
    static int m =100;
    static final int M=1;
}
```
	输出：
	Main类被加载
	父类被加载
	子类被夹在

 

### 2、类的被动引用（不会发生类的初始化）
 - 当访问一个静态域时，只有真正声明这个域的类才会被初始化
 - 如：当通过子类引用父类的静态变量，不会导致子类初始化（但是子类会被加载）
 - 通过数组定义类引用，不会触发此类的初始化
 - 引用常量不会触发此类的初始化（常量在链接阶段就存入调用类的常量池中了）

代码如下：
1） 当通过子类引用父类的静态变量
```java
package com.ctra.reflection;

public class test06 {
    static{
        System.out.println("Main类被加载");
    }
    public static void main(String[] args) throws ClassNotFoundException {
//        不会产生类的引用的方法
        System.out.println(Son.b);
    }
}

class Father{
    static int b =2;
    static{
        System.out.println("父类被加载");
    }
}

class Son extends Father{
    static{
        System.out.println("子类被加载");
        m =300;
    }
    static int m =100;
    static final int M=1;
}
```
	输出：
	Main类被加载
	父类被加载
	2

2）通过数组定义类引用
```java
package com.ctra.reflection;

public class test06 {
    static{
        System.out.println("Main类被加载");
    }
    public static void main(String[] args) throws ClassNotFoundException {
        Son[] array = new Son[5];
    }
}
class Father{
    static int b =2;
    static{
        System.out.println("父类被加载");
    }
}
class Son extends Father{
    static{
        System.out.println("子类被夹在");
        m =300;
    }
    static int m =100;
    static final int M=1;
}
```
	输出：
	Main类被加载

3）引用常量不会触发此类的初始化
```java
package com.ctra.reflection;

import java.util.zip.CheckedOutputStream;

public class test06 {
    static{
        System.out.println("Main类被加载");
    }
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(Son.M);
    }

}

class Father{
    static int b =2;
    static{
        System.out.println("父类被加载");
    }
}

class Son extends Father{
    static{
        System.out.println("子类被夹在");
        m =300;
    }
    static int m =100;
    static final int M=1;
}
```
	输出：
	Main类被加载
	1



## 3.6 方法区
这是一段摘自《Java虚拟机规范(Java SE 8版)》的说明
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200513223333802.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhbmdsZWkxOTg5MTIxMA==,size_16,color_FFFFFF,t_70)

# 4、 创建运行时类的对象
## 4.1 获取运行时类的完整结构
>通过反射获取运行时类的完整结构
>Field、Method、Constructor、Superclass、Interface、Annotation

在Class方法中 （如：getMethods、getDeclaredMethods）有`Declared`则为获取全部，没有则为获取公有

```java
package com.ctra.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class test08 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class c1 = Class.forName("com.ctra.reflection.User");

        // 获得类的名字
        System.out.println("================获得类的名字 Simple & 非Simple============");
        System.out.println(c1.getName()); // 包名+类名
        System.out.println(c1.getSimpleName());//获得类名

        // 获得类的属性
        System.out.println("=============获得类的属性 Declared &非Declared===============");
        Field[] fields = c1.getFields(); // 只能找到 public属性
        for (Field field : fields) {
            System.out.println(field);
        }

        Field[] declaredFields = c1.getDeclaredFields(); // 找到全部的属性
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }

//        Field name = c1.getField("name");
//        System.out.println(name);

        // 获得指定属性
        System.out.println("=============获得类的属性 getDeclaredField===============");
        Field name1 = c1.getDeclaredField("name");
        System.out.println(name1);

        // 获得类的方法
        System.out.println("==============获得类的方法 Declared &非Declared============");
        Method[] methods = c1.getMethods(); // 获得奔雷及其弗雷德全部 public 方法

        for (Method method : methods) {
            System.out.println("正常的："+method);
        }

        methods = c1.getDeclaredMethods(); // 获得本类的所有方法
        for (Method method : methods) {
            System.out.println("getDeclaredMethods:"+method);
        }


        // 获得指定方法
        // 重载
        System.out.println("=================获得 重载 get、set===================");
        Method getName = c1.getMethod("getName", null);
        System.out.println(getName);

        Method setName = c1.getMethod("setName", String.class);
        System.out.println(setName);


        // 获得指定的构造器
        System.out.println("=================获得指定的构造器 Declared &非Declared ===================");
        Constructor[] constructors = c1.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
        constructors = c1.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("#"+constructor);
        }

        // 获得指定的构造器
        System.out.println("===================获得指定的构造器=================");
        Constructor declaredConstructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        System.out.println("指定："+declaredConstructor);
    }
}
```
	输出：
	================获得类的名字 Simple & 非Simple============
	com.ctra.reflection.User
	User
	=============获得类的属性 Declared &非Declared===============
	private java.lang.String com.ctra.reflection.User.name
	private int com.ctra.reflection.User.id
	private int com.ctra.reflection.User.age
	=============获得类的属性 getDeclaredField===============
	private java.lang.String com.ctra.reflection.User.name
	==============获得类的方法 Declared &非Declared============
	正常的：public java.lang.String com.ctra.reflection.User.toString()
	正常的：public java.lang.String com.ctra.reflection.User.getName()
	正常的：public int com.ctra.reflection.User.getId()
	正常的：public void com.ctra.reflection.User.setName(java.lang.String)
	正常的：public void com.ctra.reflection.User.setAge(int)
	正常的：public int com.ctra.reflection.User.getAge()
	正常的：public void com.ctra.reflection.User.setId(int)
	正常的：public final void java.lang.Object.wait() throws java.lang.InterruptedException
	正常的：public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
	正常的：public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
	正常的：public boolean java.lang.Object.equals(java.lang.Object)
	正常的：public native int java.lang.Object.hashCode()
	正常的：public final native java.lang.Class java.lang.Object.getClass()
	正常的：public final native void java.lang.Object.notify()
	正常的：public final native void java.lang.Object.notifyAll()
	getDeclaredMethods:public java.lang.String com.ctra.reflection.User.toString()
	getDeclaredMethods:public java.lang.String com.ctra.reflection.User.getName()
	getDeclaredMethods:public int com.ctra.reflection.User.getId()
	getDeclaredMethods:public void com.ctra.reflection.User.setName(java.lang.String)
	getDeclaredMethods:private void com.ctra.reflection.User.test()
	getDeclaredMethods:public void com.ctra.reflection.User.setAge(int)
	getDeclaredMethods:public int com.ctra.reflection.User.getAge()
	getDeclaredMethods:public void com.ctra.reflection.User.setId(int)
	=================获得 重载 get、set===================
	public java.lang.String com.ctra.reflection.User.getName()
	public void com.ctra.reflection.User.setName(java.lang.String)
	=================获得指定的构造器 Declared &非Declared ===================
	public com.ctra.reflection.User()
	public com.ctra.reflection.User(java.lang.String,int,int)
	#public com.ctra.reflection.User()
	#public com.ctra.reflection.User(java.lang.String,int,int)
	===================获得指定的构造器=================
	指定：public com.ctra.reflection.User(java.lang.String,int,int)
	

 - 在实际操作中，取得类的信息的操作代码，并不会经常开发
 - 一定要熟悉 Java.lang.reflect 包的作用，反射机制
 - 如何获得属性、方法、构造起的名称，修饰符等

# 5、 获取运行时类的完整结构
### 有了Class对象，能做什么？
创建类的对象：调用Class 对象的newInstance()方法

 - 类必须有一个无参数的构造器
 - 类的构造器的访问权限需要足够

如何以上2个条件都不满足，如何创建对象？

 1. 通过Class类的getDeclaredConstructor(Class ... parameterTypes)取得本类的指定形参类型的构造器
 2. 向构造器的形参中传递一个对象数组进去，里面包含了构造器中所需的各个参数
 3. 通过Constructor实例化对象


### 调用指定的方法
通过反射，调用类中的方法，通过Method类完成

 1. 通过Class类的getMethod(String name,Class...parameterTypes)方法取得一个Method对象，并设置此方法操作时所需要的参数类型
 2. 之后使用Object invoke(Object obj,Object[] args)进行调用，并向方法中传递要设置的obj对象的参数信息




#### 实体类 User （供下面代码使用）

```java
// 实体类：pojo entity
class User{
    private String name;
    private int id;
    private int age;

    public User() {
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public User(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void test(){}

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }
}
```

#### 反射创建对象 方式一：通过newInstance()  
>前提：使用newInstance() ，实体类必须存在无参构造函数
```java
package com.ctra.reflection;

import javax.jws.soap.SOAPBinding;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//动态的创建对象，通过反射
public class test09 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 获得 Class 对象
        Class c1 = Class.forName("com.ctra.reflection.User");

        // 构造一个实体对象
        // jdk9之后
        // User user1 = (User)c1.getDeclaredConstructor().newInstance();
        // jdk8
        User user = (User)c1.newInstance(); // 本质是调用了类的无参构造器
        System.out.println(user);
    
    }
}
```
	输出：User{name='null', id=0, age=0}

####  反射创建对象 方拾二：通过构造器getDeclaredConstructor() 
前提：如果实体类没有无参的构造函数，则可以使用getDeclaredConstructor() 获取一个构造器，然后
```java
package com.ctra.reflection;

import javax.jws.soap.SOAPBinding;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//动态的创建对象，通过反射
public class test09 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 获得 Class 对象
        Class c1 = Class.forName("com.ctra.reflection.User");

        // 此时注释掉 无参构造方法
        // 通过构造器创建对象    (调用有参构造函数）
        Constructor constructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        User user2 = (User)constructor.newInstance("ctra", 001, 18);
        System.out.println(user2);

    }
}
```
	输出：User{name='ctra', id=1, age=18}
	
#### 反射调用普通方法
<font color=red>Object invoke(Object obj,Object .. args)</font>

 - Object 对应原方法的返回值，若原方法无返回值，此时返回null
 - 若原方法为静态方法，此时形参 Object obj可为null
 - 若原方法形参列表为空，则Object[] args为null
 - 若原方法声明为 private,则需要在调用此invoke()方法前，显式调用方法对象的 setAccessible(true)方法，将可访问private的方法
```java
package com.ctra.reflection;

import javax.jws.soap.SOAPBinding;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//动态的创建对象，通过反射
public class test09 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 获得 Class 对象
        Class c1 = Class.forName("com.ctra.reflection.User");

        User user3 = (User)c1.newInstance(); // 获取对象
        //通过反射获取一个方法
        Method setName = c1.getDeclaredMethod("setName", String.class);
        //  invoke：激活
        setName.invoke(user3,"ctra"); // （对象，方法的值）
        System.out.println(user3.getName());

    }
}
```
	输出：ctra

通过反射，调用类中的方法，送过Method类完成

 1. 通过Class类中的getMethod(String name,Class...parameterTypes)方法取得一个Method对象，并设置此方法操作时所需要的参数类型
 2. 之后使用 Object invoke(Object obje,Object[] args)进行调用，并向方法中传递要设置的obj对象的参数信息

#### 反射操作属性
当访问私有属性时，使用setAccessible(true)，关闭程序安全监测
```java
package com.ctra.reflection;

import javax.jws.soap.SOAPBinding;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//动态的创建对象，通过反射
public class test09 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 获得 Class 对象
        Class c1 = Class.forName("com.ctra.reflection.User");

        // 通过反射操作属性
        User user4 = (User)c1.newInstance();
        Field name = c1.getDeclaredField("name");
        // 不能直接操作私有属性，关闭程序安全监测可以
        name.setAccessible(true);
        name.set(user4,"CTRA_WL");
        System.out.println(user4.getName());
    }
}
```

#### <font color=red> setAccessible</font>

 - Method 和 Field、Constructor 对象都有 setAccessible() 方法
 - setAccessible作用是启动和禁用访问安全检查的开关
 - 参数值为 true 则只是反射的对象在使用时应该取消Java语言访问检查
  >提高反射的效率。如果代码中必须使用反射，而该句代码需要频繁的被调用，那么请设置为true
 是的原本无法访问的私有成员也可以访问
 
 - 参数值为false则指示反射的对象应该实施Java语言访问检查


#### 性能对比

```java
package com.ctra;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//分析性能问题
public class test0 {
    // 普通方式调用
    public static void test01() {
        User user = new User();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 100000000; i++) {
            user.getName();
        }
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + "ms");
    }

    // 反射方式调用
    public static void test02() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        Class c1 = user.getClass();
        Method getName = c1.getDeclaredMethod("getName", null);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 100000000; i++) {
            getName.invoke(user, null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + "ms");
    }

    // 反射方式调用
    // 并且关闭监测
    public static void test03() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        Class c1 = user.getClass();
        Method getName = c1.getDeclaredMethod("getName", null);
        // 关闭监测
        getName.setAccessible(true);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user, null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + "ms");
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        test01();
        test02();
        test03();
    }

}


class User {
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
```

	输出：
	理论上： test1-->直接调用最快
		    test2-->反射调用最慢
			test3--> 关闭 setAccessible 快于反射 慢于直接调用

#### 反射操作泛型

 - Java采用泛型擦除的机制来引入泛型，Java中的泛型仅仅是给编译器javac使用的，确保数据的安全性和免去强制类型转换问题，但是，一旦编译完成，所有和泛型有关的类型全部擦除
 - 为了通过反射操作这些类型，Java新增了ParameterizedType，GenericArrayType，TypeVariable和 WildcardType 几种类型来代表不能被归一到Class类中的类型但是又和原始类型齐名的类型

---

 1. ParameterizedType：表示一种参数化类型，比如Collection< String>
 2. GenericArrayType：表示一种元素类型是参数化类型挥着类型变量的数组类型
 3. TypeVariable：是各种类型变量的公共父接口
 4. WildcardType ：代表一种通配符类型表达式

#### 反射操作注解

 - getAnnotations
 - getAnnotation


#### ORM
什么是ORM？
 objec relationship Mapping -->对象关系映射

```java
package com.ctra.reflection;

import java.lang.annotation.*;

public class Test12 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class<?> c1 = Class.forName("com.ctra.reflection.Student2");

        // 通过反射获得注解
        Annotation[] annotations = c1.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }


        // 获得注解的 value 的值
        Table annotation = (Table) c1.getAnnotation(Table.class);
        String value = annotation.value();
        System.out.println(value);


        //  获得类指定的注解
        java.lang.reflect.Field f =  c1.getDeclaredField("name");
        Field annotation1 = f.getAnnotation(Field.class);
        System.out.println(annotation1.columnName());
        System.out.println(annotation1.length());
        System.out.println(annotation1.type());


    }
}

@Table("db_student")
class Student2{
    @Field(columnName = "db_age",type="int",length = 10)
    private int age;
    @Field(columnName = "db_id",type="int",length = 8)
    private int id;
    @Field(columnName = "db_name",type="int",length = 9)
    public String name;

    public Student2() {
    }

    public Student2(int age, int id, String name) {
        this.age = age;
        this.id = id;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "age=" + age +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Table{
    String value();
}

//属性的注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Field{
    String columnName();
    String type();
    int length();
}
```

