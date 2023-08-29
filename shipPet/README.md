# 宠物医院管理系统的后端

> 当前时间：2023-6-10 20:21:05

## 后端框架:

> 基于java17

| 名称             | 版本      | 描述                  |
|:---------------|:--------|:--------------------|
| SpringBoot     | 3.1.0   | 集成了web、邮件、websocket |
| mybatis-plus   | 3.5.3.1 | 操作数据库、生成模板代码        |
| SpringSecurity | 6.1.0   | 安全校验、权限控制           |

## 运行教程

1. 请确保MySQL已安装并处于运行状态，且MySQL的用户名与密码都是`root`，如果不是，请在`application.yml`中改成自己的。
2. 在MySQL中新建数据库`pet_ship`，然后在`pet_ship`数据库中运行`数据库备份`下`pet_ship.sql`文件。
3. 修改`application-dev.yml`中`pet-ship`选项下的文件夹路径，为本项目(MixPet)下的那三个资源文件夹的路径。
    比如改成下面这样(注意这是自己电脑上的路径)
    ```yaml
    pet-ship:
      images-path: D:\MyJS\MixPet\images\ # 照片存储路径
      notice-path: D:\MyJS\MixPet\notice\ # 公告存储路径
      chatImg-path: D:\MyJS\MixPet\chatImg\ # 聊天图片存储路径
    ```
4. 运行本后端，然后使用WebStorm启动前端`ship-ui`

