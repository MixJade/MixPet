# F1-论使用js造轮子

> * 虽然我一开始只是打算使用bootstrap与axios，其它地方都使用原生js
> * 但是随着写的越来越多同质化的网页，我将一些函数给封装到类中，又有一些通用函数给迁移到js文件中
> * 然后我发现，代码量甚至比用正经的框架还少
> * 毕竟正规框架不会规定某个标签必须叫特定的id,也不会规定变量名必须是特定格式
> * 但总归极大的节省了代码量(从500行降到267行，其中65行有iframe的功劳)，是这次毕业设计之中的意外收获

## js文件

文件过大，请移步："MixPet\forgePet\src\main\resources\static\backstage\z-myJS.js"

## 使用示例

* 部分元素在父类页面中，请参照文件夹“小知识”中的“Q2-前端废案iframe.md”
* 以下的是iframe中的具体实现增删改查页面
* 文件路径："MixPet\forgePet\src\main\resources\static\backstage\x-departmentList.html"
