# 宠物医院管理系统

> 毕设设计项目，所有代码均靠自己编写。
> * 开始选题时间：2022-12-9(确定毕设题目)
> * 初次完成时间：2023-1-24(完成用户及管理员的网页端及java后端)
> * 二次完成时间：2023-3-09(完成医生的网页端，实现医生与客户在线聊天)
> * 最终完成时间：2023-3-28(完成用户端从网页到小程序的移植)

* 注册或者找回密码时的邮箱应该填写真实的邮箱，因为要发送验证码
* 本项目基于java17所运行，里面大量使用了java17的新特性 ~~(相对于jdk1.8)~~

| 开发工具    | 版本        | 简介          |
|:--------|:----------|:------------|
| java    | 17.0.2    | 许多方便的新特性    |
| git     | 2.38.0    | 监控、回滚代码     |
| maven   | 3.8.6     | 管理java的第三方库 |
| IDEA    | 2022.2.1  | 编写、运行本项目    |
| mysql   | 5.5.40    | 数据库，存储数据    |
| navicat | V8.2.1    | 可视化工具，mysql |
| 微信开发者工具 | 调试库2.19.4 | 开发、运行微信小程序  |

---

## 部署教程

> 如果想在其他的电脑运行，有三点要注意，分别是：数据库、资源文件夹、邮箱

### 可能需要修改的参数

> 打开项目中`forgePet/src/main/resources`文件夹的`application.yml`文件，以下使用"配置文件"来指代该文件

*配置文件中可能需要修改的参数*

```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/pet_forge?useSSL=true
    username: root # mysql用户名
    password: root # mysql密码
  mail:
    host: smtp.qq.com
    port: 587
    username: 2231973602@qq.com # 发送者的邮箱，必须是QQ邮箱
    password: dspwtwceobsfeafa # 发送者邮箱的授权码
    default-encoding: UTF-8
```

*以及资源文件夹的路径：*

```yml
pet-forge:
  images-path: C:\MixJade\MixPet\images\ # 照片存储路径
  notice-path: C:\MixJade\MixPet\notice\ # 公告存储路径
  chatImg-path: C:\MixJade\MixPet\chatImg\ # 聊天图片存储路径
```

*以下为修改教程：*

1. 数据源的设置
    * 配置文件中：`spring.datasource`下的`username`以及`password`分别为mysql的账号和密码，必须改成自己的
    * 然后去mysql中新建数据库，数据库建议命名为`pet_forge`，数据库属性如下：
        * 字符集为`utf8 -- UTF-8 Unicode`(为了节省空间，我不打算为了emoji而使用utf8mb4)
        * 排序规则为`utf8_general_ci`(意为：不区分大小写，这样比较快)
    * 接着在`pet_forge`数据库下，运行本项目中`数据库备份`文件夹下的`pet_forge.sql`文件，以导入项目数据
    * 注意：配置文件中`spring.datasource.url`为数据源，其中`pet_forge`为数据库名,
    * 如果你不将数据库命名为`pet_forge`，这里记得修改
2. 资源文件夹的路径(绝对路径):
    * 如果不想改配置文件，只需在C盘下新建名为`MixJade`的文件夹，
    * 再在`MixJade`中新建名为`MixPet`的文件夹，然后将本项目放入`MixPet`即可
    * 如果需要自己配置资源文件夹：
        * 将本项目中的`chatImg`、`notice`、`images`文件夹放在自己配置的资源文件夹中
        * 然后修改配置文件中`pet-forge`属性之下的三个文件夹路径为自己的资源路径即可(一定要用绝对路径)
3. 验证码的发送邮箱(mail.username)与对应授权码(mail.password)
    * 登录QQ邮箱网页版，点击`设置`-`账户`-`开启服务： POP3/SMTP服务`
    * 按照提示操作，开启服务并得到授权码
    * 之后将配置文件下的`spring.mail`中的`username`和`password`改成自己的邮箱与授权码即可

---

### 运行微信小程序

> * 在启动微信小程序之前，记得先启动本项目的java后端
> * AppID自行去微信公众平台注册获取

* 打开微信开发者工具，选择`导入项目`，选择本项目之下的`forgePetWX`文件夹
* 输入自己的AppId，勾选`不使用云服务`。(建议百度：`微信小程序appid如何申请`)
* 在本地java项目的运行状态下，在微信开发者工具中编译运行小程序即可
* 注：如果一直无法登录，在微信开发者工具中，打开`详情`--`本地设置`，勾选`不校验合法域名`

---

## 运行教程

> 请确保你已安装了IDEA与maven，并且成功部署本项目

### 启动Java后端

1. 在IDEA中打开本项目中的forgePet文件夹
2. 点开`forgePet`文件夹下的`pom.xml`文件，确保里面的依赖都已安装，如果存在标红字体，请点击maven刷新
3. 部署完成之后，进入本项目下的`forgePet/src/main`文件夹，运行`ForgePetApplication.java`文件即可。
4. 当控制台打印出`宠物医院管理系统启动`的字样时，即是启动成功

### 启动网页前端

1. 在Java后端已启动的情况下，打开浏览器
2. 在浏览器地址栏输入`http://localhost:8083/login.html`
3. 如果一起正常，此时会看到用户登录的页面。

#### 用户登录

1. 在用户登录界面，输入账号`ying`，密码为`123456`，即可体验用户端功能
2. 也可以不输入账号密码，点击左下角的游客登录，直接进入主页浏览，但许多敏感信息无权观看。
3. 用户登录之后会进入主页，将鼠标悬停在网页上方的正中间，可以看到**退出登录**按钮。（如果此时是**游客登录**或已点击**
   退出登录**按钮，该按钮会换成**点击登录**)。

#### 管理员登录

1. 在用户登录界面，点击输入框上面的猫猫图标，即可翻转到管理员登录界面。
2. 输入账号`ra9`，密码`123456`，以体验**普通员工**功能，该角色仅具有部分表的新增权限。
3. 输入账号`yun`，密码`yun`，以体验**普通管理员**功能，该角色具有大部分（除部门表与员工表）修改与删除权限。
4. 输入账号`admin`，密码`123456`，以体验**超级管理员**功能，该角色具有所有权限。
5. 点击网页上方中间写有当前**操作者名字**的**绿色按钮**，或者左上角的**”宠物医院“**Logo，即可唤出功能菜单。

#### 医生登录

1. 在管理员登录界面，点击左下角的**医生登录**，即可翻转到医生登录界面。
2. 输入工号`32312221`，密码`123456`，即可访问到**童德统**医生的账号。
3. 输入工号`32301063`，密码`123456`，即可访问到**汤姆**医生的账号。
4. 目前所有医生的密码都是`123456`，欲访问不同医生的账号请自行登录。

## 文件夹结构

> 更新与2023年5月25日18:27:14
>
> 这里只展示文件夹

```text
├─chatImg 【聊天图片】
├─forgePet【 Java后端】
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─com
│      │  │      └─forge
│      │  │          ├─common 【部分通用逻辑的封装类】
│      │  │          ├─controller 【前端控制器】
│      │  │          ├─dto 【数据库多表查询的结果封装】
│      │  │          ├─entity 【与数据库表字段对应】
│      │  │          ├─mapper 【mapper映射文件接口】
│      │  │          ├─service 【service层接口及其实现类】
│      │  │          │  └─impl
│      │  │          ├─shiro 【Shiro权限校验模块】
│      │  │          │  ├─authentic 【重写的权限校验，可以实现不同表的登录】
│      │  │          │  └─realm 【Shiro的realm，包括授权与登录】
│      │  │          ├─util 【部分工具类】
│      │  │          ├─vo 【前端通过POST请求传递的参数，使用了record特性】
│      │  │          └─ws 【WebSocket的聊天模块】
│      │  └─resources
│      │      ├─com
│      │      │  └─forge
│      │      │      └─mapper 【mapper映射文件的XML，主要用于多表操作】
│      │      ├─static
│      │          ├─assets 【放置了BootStrap、axios】
│      │          ├─backstage 【后台管理页面】
│      │          ├─doctorPage 【医生的页面】
│      │          ├─js 【前端的通用JS文件】
│      │          ├─picture 【轮播图、默认图片】
│      │          └─reception 【用户的前端】
│      └─test
│          └─java
│              └─com
│                  └─forge 【测试类，有一个用于裁剪图片的工具】
├─forgePetWX 【微信小程序】
│  ├─images 【静态图片】
│  │  ├─swiper 【轮播图】
│  │  └─tab 【下方用于切换的图标】
│  ├─miniprogram_npm
│  │  └─vant-weapp 【第三方库】
│  ├─node_modules
│  │  └─vant-weapp【第三方库】
│  ├─pages 【小程序的各种页面】
│  │  ├─appointment 【用户挂号页面】
│  │  ├─chatClient 【用户聊天页面】
│  │  ├─clientCenter 【用户个人中心，卡片展示信息，其它页面的入口】
│  │  ├─clientDetail 【用户详情】
│  │  ├─clientPet 【用户的宠物】
│  │  ├─clientUpdate 【用户修改资料】
│  │  ├─doctorAppoint 【一个医生的信息展示】
│  │  ├─doctorSee 【全部医生的信息列表】
│  │  ├─findPwd 【密码找回页面】
│  │  ├─index 【小程序主页】
│  │  ├─login 【登录页面】
│  │  ├─petFoster 【寄养的宠物信息查看】
│  │  ├─petOne 【单个待领养的宠物】
│  │  ├─petSee 【所有待领养的宠物】
│  │  └─register 【账号注册页面】
│  └─utils 【工具类，封装了请求与日期格式化】
├─images 【头像图片】
├─notice 【网站公告文本】
├─我的笔记
├─数据库备份
└─系统说明图 【做论文与PPT时所做的图】
```
