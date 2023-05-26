# 宠物医院管理系统的后端及网页前端

> 当前时间：2023-4-3 20:22:00

## 后端框架:

> 基于java17

| 名称            | 版本     | 描述                  |
|:--------------|:-------|:--------------------|
| SpringBoot    | 2.7.5  | 集成了web、邮件、websocket |
| mybatis-plus  | 3.5.2  | 操作数据库、生成模板代码        |
| shiro         | 1.10.1 | 安全校验、权限控制           |
| thumbnailator | 0.4.18 | 压缩图片                |

## 网页前端框架

> 原生js、es6语法

| 名称        | 版本      | 描述                 |
|:----------|:--------|:-------------------|
| axios     | v0.18.0 | 封装ajax，基于Promise   |
| bootstrap | v5.1.3  | 响应式框架，v5版不依赖jQuery |

## 文件结构

```text
├─src
  ├─main
  │  ├─java
  │  │  └─com
  │  │      └─forge
  │  │          ├─common 【部分通用逻辑的封装类】
  │  │          ├─controller 【前端控制器】
  │  │          ├─dto 【数据库多表查询的结果封装】
  │  │          ├─entity 【与数据库表字段对应】
  │  │          ├─mapper 【mapper映射文件接口】
  │  │          ├─service 【service层接口及其实现类】
  │  │          │  └─impl
  │  │          ├─shiro 【Shiro权限校验模块】
  │  │          │  ├─authentic 【重写的权限校验，可以实现不同表的登录】
  │  │          │  └─realm 【Shiro的realm，包括授权与登录】
  │  │          ├─util 【部分工具类】
  │  │          ├─vo 【前端通过POST请求传递的参数，使用了record特性】
  │  │          └─ws 【WebSocket的聊天模块】
  │  └─resources
  │      ├─com
  │      │  └─forge
  │      │      └─mapper 【mapper映射文件的XML，主要用于多表操作】
  │      └─static
  │          ├─assets 【放置了BootStrap、axios】
  │          ├─backstage 【后台管理页面】
  │          ├─doctorPage 【医生的页面】
  │          ├─js 【前端的通用JS文件】
  │          ├─picture 【轮播图、默认图片】
  │          └─reception 【用户的前端】
  └─test
      └─java
          └─com
              └─forge 【测试类，有一个用于裁剪图片的工具】
```
