# 微信小程序

> 微信小程序原生语法，辅以vant组件库

## 注意事项

**注意：**我于2023-7-12 将miniprogram_npm与node_modules文件夹从代码中删除（因为是第三方库）

请后面拉取代码者，进行如下操作解锁：

1. 在终端输入：

2. ```basic
   npm install
   ```

3. 打开微信开发者工具，点击 **工具 -> 构建 npm**，并勾选 **使用 npm 模块** 选项，构建完成后，即可引入组件

## 依赖环境

| 名称      | 版本        | 描述                  |
|:--------|:----------|:--------------------|
| 微信开发者工具 | 调试库2.19.4 | 开发、运行微信小程序          |
| Node.js | v16.18.0  | 下载vant-weapp所需      |
| Java    | 17.0.2    | 运行forgePet的Java后端所需 |

## 目录结构

```text
├─images 【静态图片】
│  ├─swiper 【轮播图】
│  └─tab 【下方用于切换的图标】
├─miniprogram_npm
│  └─vant-weapp 【第三方库，于2023-7-12从代码仓库中移除】
├─node_modules
│  └─vant-weapp【第三方库，于2023-7-12从代码仓库中移除】
├─pages 【小程序的各种页面】
│  ├─appointment 【用户挂号页面】
│  ├─chatClient 【用户聊天页面】
│  ├─clientCenter 【用户个人中心，卡片展示信息，其它页面的入口】
│  ├─clientDetail 【用户详情】
│  ├─clientPet 【用户的宠物】
│  ├─clientUpdate 【用户修改资料】
│  ├─doctorAppoint 【一个医生的信息展示】
│  ├─doctorSee 【全部医生的信息列表】
│  ├─findPwd 【密码找回页面】
│  ├─index 【小程序主页】
│  ├─login 【登录页面】
│  ├─petFoster 【寄养的宠物信息查看】
│  ├─petOne 【单个待领养的宠物】
│  ├─petSee 【所有待领养的宠物】
│  └─register 【账号注册页面】
└─utils 【工具类，封装了请求与日期格式化】
```