# ShipPet管理员接口文档

## 用户列表

> http://localhost:8085/client/page?numPage=1&pageSize=7&clientName=

```json
{
    "records": [
        {
            "clientId": 1,
            "clientUsername": "ying",
            "clientPassword": null,
            "clientName": "莹",
            "clientGender": false,
            "clientTel": "sanyu0201@163.com",
            "clientAge": "2001-01-03",
            "clientInfo": "来自提瓦特❥(^_-)",
            "clientPhoto": "ying.jpg",
            "createTime": null,
            "updateTime": null,
            "isDel": null
        },
        {
            "clientId": 2,
            "clientUsername": "lei-jun",
            "clientPassword": null,
            "clientName": "雷军",
            "clientGender": false,
            "clientTel": "sanyu0201@163.com",
            "clientAge": "1990-01-01",
            "clientInfo": "喜欢宅家撸猫",
            "clientPhoto": "lei-jun.jpg",
            "createTime": null,
            "updateTime": null,
            "isDel": null
        },
        {
            "clientId": 3,
            "clientUsername": "sanyu",
            "clientPassword": null,
            "clientName": "散玉",
            "clientGender": true,
            "clientTel": "sanyu0201@163.com",
            "clientAge": "2000-01-01",
            "clientInfo": "作者",
            "clientPhoto": "san-yu.jpg",
            "createTime": null,
            "updateTime": null,
            "isDel": null
        },
        {
            "clientId": 4,
            "clientUsername": "fox",
            "clientPassword": null,
            "clientName": "狐狸",
            "clientGender": false,
            "clientTel": "sanyu0201@163.com",
            "clientAge": "2000-01-07",
            "clientInfo": "粉毛狐狸",
            "clientPhoto": "ba-chong.jpg",
            "createTime": null,
            "updateTime": null,
            "isDel": null
        },
        {
            "clientId": 5,
            "clientUsername": "hong",
            "clientPassword": null,
            "clientName": "红中",
            "clientGender": true,
            "clientTel": "sanyu0201@163.com",
            "clientAge": "2023-01-22",
            "clientInfo": "一个新用户",
            "clientPhoto": "zs.jpg",
            "createTime": null,
            "updateTime": null,
            "isDel": null
        },
        {
            "clientId": 6,
            "clientUsername": "bei",
            "clientPassword": null,
            "clientName": "北风",
            "clientGender": false,
            "clientTel": "sanyu0201@163.com",
            "clientAge": "2000-01-03",
            "clientInfo": "黜聪明，拙肢体，同于大道",
            "clientPhoto": "zs.jpg",
            "createTime": null,
            "updateTime": null,
            "isDel": null
        },
        {
            "clientId": 9,
            "clientUsername": "wang",
            "clientPassword": null,
            "clientName": "骰子",
            "clientGender": true,
            "clientTel": "sanyu0201@163.com",
            "clientAge": "2023-01-22",
            "clientInfo": "一个新用户",
            "clientPhoto": "zs.jpg",
            "createTime": null,
            "updateTime": null,
            "isDel": null
        }
    ],
    "total": 11,
    "size": 7,
    "current": 1,
    "orders": [],
    "optimizeCountSql": true,
    "searchCount": true,
    "maxLimit": null,
    "countId": null,
    "pages": 2
}
```

## 管理员信息

> /adminLog

```json
{
    "employeeId": 2,
    "employeeUsername": "yun",
    "employeePassword": "$2a$10$isx1czTSQaNBZiYiwo/TjuEb40VFhH6VqAXOsHfFK39ls.DxziPye",
    "employeeName": "李云",
    "employeeLevel": 4,
    "employeeTel": "13882244666",
    "employeePhoto": "doctor-1.jpg",
    "createTime": "2023-01-05T21:34:23",
    "updateTime": "2023-03-02T23:01:21",
    "isDel": "0"
}
```

## 医生列表

> /doctor/page?numPage=1&pageSize=7&doctorName=

```json
{
    "records": [
        {
            "doctorId": 1,
            "departmentId": 1,
            "doctorCode": "32312221",
            "doctorName": "童德统",
            "doctorGender": true,
            "doctorAge": "1990-10-10",
            "doctorPhoto": "doctor-1.jpg",
            "doctorTel": "1114185977@qq.com",
            "doctorJob": "副院长",
            "doctorInfo": "国内著名医学专家，擅长绝育",
            "doctorPassword": null,
            "createTime": null,
            "updateTime": null,
            "isDel": null,
            "departmentName": "犬猫科"
        },
        {
            "doctorId": 2,
            "departmentId": 4,
            "doctorCode": "32301062",
            "doctorName": "爱丽丝",
            "doctorGender": false,
            "doctorAge": "2003-02-01",
            "doctorPhoto": "doctor-2.jpg",
            "doctorTel": "mix_pet_doctor@sina.com",
            "doctorJob": "麻醉科主任",
            "doctorInfo": "擅长麻醉",
            "doctorPassword": null,
            "createTime": null,
            "updateTime": null,
            "isDel": null,
            "departmentName": "美容室"
        },
        {
            "doctorId": 4,
            "departmentId": 8,
            "doctorCode": "32301063",
            "doctorName": "汤姆",
            "doctorGender": true,
            "doctorAge": "2000-01-11",
            "doctorPhoto": "doctor-3.jpg",
            "doctorTel": "1114185977@qq.com",
            "doctorJob": "医生",
            "doctorInfo": "擅长x光拍片",
            "doctorPassword": null,
            "createTime": null,
            "updateTime": null,
            "isDel": null,
            "departmentName": "中兽科"
        },
        {
            "doctorId": 5,
            "departmentId": 4,
            "doctorCode": "32301064",
            "doctorName": "张大嘴",
            "doctorGender": false,
            "doctorAge": "2002-01-05",
            "doctorPhoto": "doctor-4.jpg",
            "doctorTel": "mix_pet_doctor@sina.com",
            "doctorJob": "医生",
            "doctorInfo": "著名宠物医美专家",
            "doctorPassword": null,
            "createTime": null,
            "updateTime": null,
            "isDel": null,
            "departmentName": "美容室"
        },
        {
            "doctorId": 6,
            "departmentId": 3,
            "doctorCode": "32301065",
            "doctorName": "李妙手",
            "doctorGender": true,
            "doctorAge": "2001-01-06",
            "doctorPhoto": "3b161abe-7142-449c-ab36-07a689da65de.jpg",
            "doctorTel": "1114185977@qq.com",
            "doctorJob": "医生",
            "doctorInfo": "国外著名医学专家",
            "doctorPassword": null,
            "createTime": null,
            "updateTime": null,
            "isDel": null,
            "departmentName": "化验室"
        },
        {
            "doctorId": 8,
            "departmentId": 2,
            "doctorCode": "32301076",
            "doctorName": "李正骨",
            "doctorGender": false,
            "doctorAge": "2000-01-28",
            "doctorPhoto": "3cd8e749-e177-4e5b-846c-930efab7bd38.jpg",
            "doctorTel": "1114185977@qq.com",
            "doctorJob": "医生",
            "doctorInfo": "国内外著名骨科专家",
            "doctorPassword": null,
            "createTime": null,
            "updateTime": null,
            "isDel": null,
            "departmentName": "骨科"
        },
        {
            "doctorId": 9,
            "departmentId": 8,
            "doctorCode": "32302189",
            "doctorName": "钟回春",
            "doctorGender": true,
            "doctorAge": "2000-02-10",
            "doctorPhoto": "92833192-79d4-42d6-a657-d000d8ca6295.jpg",
            "doctorTel": "mix_pet_doctor@sina.com",
            "doctorJob": "医生",
            "doctorInfo": "多年老中医",
            "doctorPassword": null,
            "createTime": null,
            "updateTime": null,
            "isDel": null,
            "departmentName": "中兽科"
        }
    ],
    "total": 7,
    "size": 7,
    "current": 1,
    "orders": [],
    "optimizeCountSql": true,
    "searchCount": true,
    "maxLimit": null,
    "countId": null,
    "pages": 1
}
```

## 部门名称列表

> http://localhost:8085/department

```json
[
    {
        "roleId": 1,
        "roleName": "犬猫科"
    },
    {
        "roleId": 2,
        "roleName": "骨科"
    },
    {
        "roleId": 3,
        "roleName": "化验室"
    },
    {
        "roleId": 4,
        "roleName": "美容室"
    },
    {
        "roleId": 8,
        "roleName": "中兽科"
    }
]
```

## 宠物列表

> http://localhost:8085/pet/page?numPage=1&pageSize=7&petName=

```json
{
    "records": [
        {
            "petId": 3,
            "petName": "心海",
            "petVariety": "观赏鱼",
            "petSex": false,
            "petAge": "2000-01-13",
            "petStatus": "非常健康",
            "petInfo": "心海十分开心",
            "clientId": 1,
            "petPhoto": "f66eb858-e13c-438a-b235-8889d2d416be.png",
            "createTime": null,
            "updateTime": null,
            "isDel": null,
            "clientName": "莹"
        },
        {
            "petId": 5,
            "petName": "雷电小兵",
            "petVariety": "吉娃娃",
            "petSex": true,
            "petAge": "1990-08-09",
            "petStatus": "绝育手术",
            "petInfo": "这宠物十分开心",
            "clientId": 4,
            "petPhoto": "a78d73bc-a557-41e0-8a8f-8ddc5c22e7ce.png",
            "createTime": null,
            "updateTime": null,
            "isDel": null,
            "clientName": "狐狸"
        },
        {
            "petId": 7,
            "petName": "波一斯",
            "petVariety": "波斯猫",
            "petSex": false,
            "petAge": "2016-12-15",
            "petStatus": "频繁掉毛",
            "petInfo": "等待有缘人领养",
            "clientId": null,
            "petPhoto": "boss-cat.jpg",
            "createTime": null,
            "updateTime": null,
            "isDel": null,
            "clientName": null
        },
        {
            "petId": 8,
            "petName": "波二斯",
            "petVariety": "波斯猫",
            "petSex": true,
            "petAge": "2016-12-17",
            "petStatus": "良好",
            "petInfo": "等待有缘人领养",
            "clientId": null,
            "petPhoto": "fd1cdd8a-3411-4b95-a20d-5ad3993c94af.jpg",
            "createTime": null,
            "updateTime": null,
            "isDel": null,
            "clientName": null
        },
        {
            "petId": 9,
            "petName": "波三斯",
            "petVariety": "波斯猫",
            "petSex": false,
            "petAge": "2013-12-23",
            "petStatus": "频繁掉毛",
            "petInfo": "等待有缘人领养",
            "clientId": null,
            "petPhoto": "a943938f-7d21-49d3-a909-22f2c2dc2c9b.jpg",
            "createTime": null,
            "updateTime": null,
            "isDel": null,
            "clientName": null
        },
        {
            "petId": 10,
            "petName": "波四斯",
            "petVariety": "波斯猫",
            "petSex": false,
            "petAge": "2012-12-03",
            "petStatus": "良好",
            "petInfo": "这宠物十分开心",
            "clientId": 2,
            "petPhoto": "92d90558-d92f-4dd8-bca3-5088825e0729.jpg",
            "createTime": null,
            "updateTime": null,
            "isDel": null,
            "clientName": "雷军"
        },
        {
            "petId": 11,
            "petName": "大橘莹",
            "petVariety": "橘猫",
            "petSex": false,
            "petAge": "2007-12-23",
            "petStatus": "体重超标",
            "petInfo": "等待有缘人领养",
            "clientId": null,
            "petPhoto": "ju-cat.jpg",
            "createTime": null,
            "updateTime": null,
            "isDel": null,
            "clientName": null
        }
    ],
    "total": 28,
    "size": 7,
    "current": 1,
    "orders": [],
    "optimizeCountSql": true,
    "searchCount": true,
    "maxLimit": null,
    "countId": null,
    "pages": 4
}
```

## 用户列表

> http://localhost:8085/client

```json
[
    {
        "roleId": 1,
        "roleName": "莹"
    },
    {
        "roleId": 2,
        "roleName": "雷军"
    },
    {
        "roleId": 3,
        "roleName": "散玉"
    },
    {
        "roleId": 4,
        "roleName": "狐狸"
    },
    {
        "roleId": 5,
        "roleName": "红中"
    },
    {
        "roleId": 6,
        "roleName": "北风"
    },
    {
        "roleId": 9,
        "roleName": "骰子"
    },
    {
        "roleId": 10,
        "roleName": "六饼"
    },
    {
        "roleId": 11,
        "roleName": "五饼"
    },
    {
        "roleId": 12,
        "roleName": "六条"
    },
    {
        "roleId": 13,
        "roleName": "八条"
    }
]
```

## 挂号单列表

> http://localhost:8085/appointment/page?numPage=1&pageSize=7&seaName=&seaType=0

```json
{
    "records": [
        {
            "appointmentId": 7,
            "clientId": 3,
            "petId": 12,
            "appointmentDate": "2023-02-18T14:30:00",
            "departmentId": 8,
            "doctorId": 4,
            "appointmentInfo": "测试挂号",
            "isSuc": false,
            "createTime": "2023-02-18T14:30:21",
            "updateTime": null,
            "isDel": null,
            "clientName": "散玉",
            "petName": "二橘",
            "departmentName": "中兽科",
            "doctorName": "汤姆"
        },
        {
            "appointmentId": 6,
            "clientId": 3,
            "petId": 12,
            "appointmentDate": "2023-02-13T23:49:00",
            "departmentId": 8,
            "doctorId": 4,
            "appointmentInfo": "熬夜写网页",
            "isSuc": true,
            "createTime": "2023-02-13T21:49:47",
            "updateTime": null,
            "isDel": null,
            "clientName": "散玉",
            "petName": "二橘",
            "departmentName": "中兽科",
            "doctorName": "汤姆"
        },
        {
            "appointmentId": 5,
            "clientId": 3,
            "petId": 12,
            "appointmentDate": "2023-01-17T20:37:00",
            "departmentId": 4,
            "doctorId": 5,
            "appointmentInfo": "测试挂号",
            "isSuc": false,
            "createTime": "2023-01-17T20:37:12",
            "updateTime": null,
            "isDel": null,
            "clientName": "散玉",
            "petName": "二橘",
            "departmentName": "美容室",
            "doctorName": "张大嘴"
        },
        {
            "appointmentId": 4,
            "clientId": 1,
            "petId": 3,
            "appointmentDate": "2023-01-16T21:24:00",
            "departmentId": 8,
            "doctorId": 4,
            "appointmentInfo": "查看骨骼",
            "isSuc": true,
            "createTime": "2023-01-16T21:24:51",
            "updateTime": null,
            "isDel": null,
            "clientName": "莹",
            "petName": "心海",
            "departmentName": "中兽科",
            "doctorName": "汤姆"
        },
        {
            "appointmentId": 3,
            "clientId": 3,
            "petId": 12,
            "appointmentDate": "2023-01-08T23:30:00",
            "departmentId": 8,
            "doctorId": 4,
            "appointmentInfo": "熬夜写网页",
            "isSuc": true,
            "createTime": "2023-01-08T23:31:23",
            "updateTime": null,
            "isDel": null,
            "clientName": "散玉",
            "petName": "二橘",
            "departmentName": "中兽科",
            "doctorName": "汤姆"
        },
        {
            "appointmentId": 2,
            "clientId": 4,
            "petId": 5,
            "appointmentDate": "2023-01-08T21:03:00",
            "departmentId": 4,
            "doctorId": 5,
            "appointmentInfo": "尝试第一次挂号",
            "isSuc": false,
            "createTime": "2023-01-08T21:04:35",
            "updateTime": null,
            "isDel": null,
            "clientName": "狐狸",
            "petName": "雷电小兵",
            "departmentName": "美容室",
            "doctorName": "张大嘴"
        },
        {
            "appointmentId": 1,
            "clientId": 1,
            "petId": 5,
            "appointmentDate": "2022-12-22T11:17:54",
            "departmentId": 1,
            "doctorId": 1,
            "appointmentInfo": "严重的肛裂",
            "isSuc": true,
            "createTime": "2022-12-22T11:18:04",
            "updateTime": null,
            "isDel": null,
            "clientName": "莹",
            "petName": "雷电小兵",
            "departmentName": "犬猫科",
            "doctorName": "童德统"
        }
    ],
    "total": 7,
    "size": 7,
    "current": 1,
    "orders": [],
    "optimizeCountSql": true,
    "searchCount": true,
    "maxLimit": null,
    "countId": null,
    "pages": 1
}
```

## 领养列表

> http://localhost:8085/adopt/page?numPage=1&pageSize=7&adoptName=

```json
{
    "records": [
        {
            "adoptId": 8,
            "adoptCode": "22302188",
            "petId": 24,
            "clientId": 6,
            "adoptMoney": 233,
            "adoptInfo": "金毛嘿嘿嘿",
            "inAdopt": 2,
            "createTime": "2023-02-18T14:17:15",
            "updateTime": null,
            "isDel": null,
            "petName": "金大毛",
            "clientName": "北风"
        },
        {
            "adoptId": 7,
            "adoptCode": "22302137",
            "petId": 19,
            "clientId": 2,
            "adoptMoney": 2333,
            "adoptInfo": "狸二花，名花有主",
            "inAdopt": 2,
            "createTime": "2023-02-13T21:39:15",
            "updateTime": null,
            "isDel": null,
            "petName": "狸二花",
            "clientName": "雷军"
        },
        {
            "adoptId": 6,
            "adoptCode": "22302136",
            "petId": 23,
            "clientId": 3,
            "adoptMoney": 32323,
            "adoptInfo": "测试",
            "inAdopt": 2,
            "createTime": "2023-02-13T21:25:05",
            "updateTime": null,
            "isDel": null,
            "petName": "狸六花",
            "clientName": "散玉"
        },
        {
            "adoptId": 5,
            "adoptCode": "22301225",
            "petId": 28,
            "clientId": 6,
            "adoptMoney": 200,
            "adoptInfo": "养着玩",
            "inAdopt": 1,
            "createTime": "2023-01-22T18:58:42",
            "updateTime": null,
            "isDel": null,
            "petName": "金五毛",
            "clientName": "北风"
        },
        {
            "adoptId": 4,
            "adoptCode": "22301194",
            "petId": 19,
            "clientId": 1,
            "adoptMoney": 888,
            "adoptInfo": "喵喵喵",
            "inAdopt": 0,
            "createTime": "2023-01-19T12:12:01",
            "updateTime": null,
            "isDel": null,
            "petName": "狸二花",
            "clientName": "莹"
        },
        {
            "adoptId": 3,
            "adoptCode": "22301123",
            "petId": 22,
            "clientId": 3,
            "adoptMoney": 10,
            "adoptInfo": "五花比四花多一花",
            "inAdopt": 1,
            "createTime": "2023-01-12T21:51:59",
            "updateTime": null,
            "isDel": null,
            "petName": "狸五花",
            "clientName": "散玉"
        },
        {
            "adoptId": 2,
            "adoptCode": "22301122",
            "petId": 34,
            "clientId": 3,
            "adoptMoney": 300,
            "adoptInfo": "小狐狸^_^",
            "inAdopt": 1,
            "createTime": "2023-01-12T21:38:33",
            "updateTime": null,
            "isDel": null,
            "petName": "九重狐狸",
            "clientName": "散玉"
        }
    ],
    "total": 8,
    "size": 7,
    "current": 1,
    "orders": [],
    "optimizeCountSql": true,
    "searchCount": true,
    "maxLimit": null,
    "countId": null,
    "pages": 2
}
```

## 无主的宠物

> http://localhost:8085/pet/noClient

```json
[
    {
        "roleId": 7,
        "roleName": "波一斯"
    },
    {
        "roleId": 8,
        "roleName": "波二斯"
    },
    {
        "roleId": 9,
        "roleName": "波三斯"
    },
    {
        "roleId": 11,
        "roleName": "大橘莹"
    },
    {
        "roleId": 13,
        "roleName": "三橘"
    },
    {
        "roleId": 14,
        "roleName": "四橘"
    },
    {
        "roleId": 16,
        "roleName": "五橘"
    },
    {
        "roleId": 17,
        "roleName": "六橘"
    },
    {
        "roleId": 18,
        "roleName": "狸大花"
    },
    {
        "roleId": 20,
        "roleName": "狸三花"
    },
    {
        "roleId": 21,
        "roleName": "狸四花"
    },
    {
        "roleId": 22,
        "roleName": "狸五花"
    },
    {
        "roleId": 23,
        "roleName": "狸六花"
    },
    {
        "roleId": 24,
        "roleName": "金大毛"
    },
    {
        "roleId": 25,
        "roleName": "金二毛"
    },
    {
        "roleId": 26,
        "roleName": "金三毛"
    },
    {
        "roleId": 27,
        "roleName": "金四毛"
    },
    {
        "roleId": 29,
        "roleName": "布大偶"
    },
    {
        "roleId": 31,
        "roleName": "布小偶"
    },
    {
        "roleId": 34,
        "roleName": "九重狐狸"
    }
]
```

## 寄养列表

> http://localhost:8085/foster/page?numPage=1&pageSize=7&fosterName=

```json
{
    "records": [
        {
            "fosterId": 1,
            "fosterCode": "12301121",
            "petId": 8,
            "clientId": 1,
            "fosterTerm": "2023-01-28",
            "fosterMoney": 100,
            "fosterInfo": "寄养波斯",
            "createTime": null,
            "updateTime": null,
            "isDel": null,
            "petName": "波二斯",
            "clientName": "莹"
        },
        {
            "fosterId": 2,
            "fosterCode": "12301122",
            "petId": 32,
            "clientId": 3,
            "fosterTerm": "2023-01-13",
            "fosterMoney": 800,
            "fosterInfo": "好好照顾",
            "createTime": null,
            "updateTime": null,
            "isDel": null,
            "petName": "布偶二",
            "clientName": "散玉"
        },
        {
            "fosterId": 3,
            "fosterCode": "12301123",
            "petId": 33,
            "clientId": 2,
            "fosterTerm": "2023-07-28",
            "fosterMoney": 20000,
            "fosterInfo": "调教ing",
            "createTime": null,
            "updateTime": null,
            "isDel": null,
            "petName": "八重神子",
            "clientName": "雷军"
        },
        {
            "fosterId": 4,
            "fosterCode": "12301124",
            "petId": 5,
            "clientId": 4,
            "fosterTerm": "2023-07-13",
            "fosterMoney": 300,
            "fosterInfo": "",
            "createTime": null,
            "updateTime": null,
            "isDel": null,
            "petName": "雷电小兵",
            "clientName": "狐狸"
        },
        {
            "fosterId": 5,
            "fosterCode": "12301125",
            "petId": 32,
            "clientId": 3,
            "fosterTerm": "2023-09-15",
            "fosterMoney": 200,
            "fosterInfo": "有事外出",
            "createTime": null,
            "updateTime": null,
            "isDel": null,
            "petName": "布偶二",
            "clientName": "散玉"
        }
    ],
    "total": 5,
    "size": 7,
    "current": 1,
    "orders": [],
    "optimizeCountSql": true,
    "searchCount": true,
    "maxLimit": null,
    "countId": null,
    "pages": 1
}
```

## 公告列表

> http://localhost:8085/notice/page?numPage=1&pageSize=7&noticeName=

```json
{
    "records": [
        {
            "noticeId": 1,
            "noticeTitle": "宠物医院开业公告",
            "noticeFile": "notice-1.txt",
            "creatId": 1,
            "createTime": "2023-01-02T17:22:55",
            "updateId": 1,
            "updateTime": "2023-04-04T13:58:38",
            "isDisable": false,
            "isDel": "0",
            "createName": "炒鸡管理员",
            "updateName": "炒鸡管理员",
            "textNotice": "尊敬的客户，我们非常高兴地宣布宠物医院正式开业！\n作为专业的宠物健康护理机构，我们致力于提供最优质的服务。无论您需要给爱宠接种疫苗、进行体检还是治疗疾病，我们都将竭诚为您服务。\n此外，本医院还提供方便快捷的在线咨询，让您随时随地向专业兽医咨询问题，并得到及时解决。\n感谢您的信任与支持，期待在未来的日子里，能够为您和您的宝贝们带来更多的关爱和帮助。"
        },
        {
            "noticeId": 4,
            "noticeTitle": "论纯白",
            "noticeFile": "4d7dcc2e-7465-49aa-b476-c85a255162a9.txt",
            "creatId": 2,
            "createTime": "2023-01-11T17:52:06",
            "updateId": 2,
            "updateTime": "2023-01-11T17:52:58",
            "isDisable": true,
            "isDel": "0",
            "createName": "李云",
            "updateName": "李云",
            "textNotice": "若仔细研究本书，可以发现其内容似乎是一种美学理论，又或者是对绘画之危险的一系列警告。\n\n赫舍在书中心烦意乱地写下他对颜色的愤恨和对死亡的渴求。\n他还略微提及他正在构思，或已开始实践的那件大作——“淡白至极的绘画”将令世界为之着迷。\n他一遍遍重复着某几句引人注目的话，他称之为“冬之密文”。"
        },
        {
            "noticeId": 5,
            "noticeTitle": "做好宠物的疾病防治",
            "noticeFile": "f91d4670-9d59-44c8-af5d-b74ef6f4dc76.txt",
            "creatId": 2,
            "createTime": "2023-01-11T17:53:56",
            "updateId": 2,
            "updateTime": "2023-04-04T11:46:40",
            "isDisable": false,
            "isDel": "0",
            "createName": "李云",
            "updateName": "李云",
            "textNotice": "尊敬的宠物主人们，近期我院发现多例疾病病例，为保障您的爱宠健康，特向广大宠主发出以下防治提示：\n\n1、定期接种疫苗：请务必按时为您的宠物接种各类疫苗，包括犬、猫等常用疫苗，以免感染疾病。\n\n2、定期体检：每年至少带宠物进行一次体检，及时发现疾病问题，采取有效措施。\n\n3、干净卫生：家庭环境及饮食卫生十分重要，不要给宠物乱喂食，经常清洁宠物周围环境，避免细菌繁殖。\n\n4、慎选宠物美容店：选择正规合法的宠物美容店，确保使用安全卫生的美容器具和产品。\n\n5、注意传染性疾病：狗瘟、犬小病毒等具有较高传染性的疾病，请尽量避免带宠物到人流密集区域或与患病动物接触。\n\n我们将始终致力于为您的爱宠提供最优质的医疗服务，感谢您的支持。"
        },
        {
            "noticeId": 7,
            "noticeTitle": "开始采用宠物医院管理系统",
            "noticeFile": "4b50890c-a992-4d15-b5da-1dc26306df12.txt",
            "creatId": 3,
            "createTime": "2023-01-11T19:08:19",
            "updateId": 2,
            "updateTime": "2023-04-04T11:46:49",
            "isDisable": false,
            "isDel": "0",
            "createName": "马库斯",
            "updateName": "李云",
            "textNotice": "尊敬的宠物主人们：\n\n为了更好地服务广大宠物主人，我们宠物医院将于本周开始实施全新的管理系统。该系统旨在提高宠物就诊效率、加强医患沟通与管理，并保障宠物隐私安全。\n\n具体实施方案如下：\n\n1.采用电子病历，全面记录宠物健康档案，便于医生追踪诊疗进程和制定个性化治疗方案。\n2.增设医生挂号信息查看，方便宠物主人预约医生门诊时间，避免排队等候。\n3.加强信息安全保护，确保宠物隐私安全。\n希望广大宠物主人积极配合和支持，如有任何问题或建议，请随时联系我们的客服人员。感谢您对我们工作的信任和支持！\n\n\n重塑宠物医院\n\n日期：2023年4月4日"
        },
        {
            "noticeId": 8,
            "noticeTitle": "加强节粮减损，杜绝粮食浪费",
            "noticeFile": "9a9214e5-333c-4146-9f96-4a5c00b52215.txt",
            "creatId": 3,
            "createTime": "2023-01-11T19:09:20",
            "updateId": 3,
            "updateTime": "2023-01-11T19:43:22",
            "isDisable": false,
            "isDel": "0",
            "createName": "马库斯",
            "updateName": "马库斯",
            "textNotice": "  据测算，我国每年仅在储藏、运输、加工等环节浪费的粮食总量就达700亿斤以上（3500万吨），接近粮食大省吉林省一年的产量，远远超过餐桌上浪费的1800万吨，在运输、仓储、加工上造成的粮食浪费，不容小视。\r\n\r\n  其实以前有一段时间在倡导“光盘行动”，是说要杜绝舌尖上的浪费，并声称中国每年在餐桌上所浪费的食物高达1800万吨，可供5000万人一年的食物量。但之后有调查报告显示，大部分的粮食浪费发生在仓储上。\r\n长期以来，我国粮食运输多采用包粮运输方式，在储存环节拆包散储，到中转和运输环节又转为包装，导致在装卸、运输过程中损失率高达5%以上，浪费严重。\r\n  国家粮食和物资储备局发出《关于创新举措加大力度进一步做好节粮减损工作的通知》，要求各级粮食和物资储备部门深入落实总体国家安全观，扛稳粮食安全重任，着力推进节粮减损反对粮食浪费，全力保障国家粮食安全。\r\n  我们应该加强仓储、运输、加工方面的科技与制度。\r\n如果粮食浪费是因为仓储的条件不足，导致发霉或者是虫吃鼠咬，就使用温控设备、烘干装置防止发霉，以及定期消杀防止虫害，使用特殊的材料建造仓库防止老鼠进入；如果是因为运输时间过长导致粮食过期变质，就应该改进相应的运输设备或者运输方式；如果粮食浪费是因为制度的问题，让粮食在端上餐桌之前，因为层层转手，四处倒腾导致粮食损耗，就应该改进相应的制度，让粮食从仓储到餐桌能“一站到底”。\r\n  要加强节粮减损，防止粮食浪费。\r\n不只是减少仓储、运输、加工环节的粮食损耗，我们在平时也应该自觉的杜绝粮食浪费，在外面吃饭做到点多少吃多少，在家里也要有意识的减少粮食浪费。\r\n  而一些提供饭菜的集体单位，也应该尽量的将饭菜做的美味，比如曾经有新闻报道一家中学因为食堂的“营养餐”过于难吃而被中小学生集体倒掉，并配有一张潲水桶被倒满，连带着旁边的垃圾桶也跟着被倒满的图片。而那所谓的“营养餐”就是凉面拌豆芽，没有一点油水荤腥，做出这种难吃的饭菜，本身就是对粮食的浪费。\r\n  因此，加强节粮减损，也是在保障中国粮食安全的一项举措，我们要做到在仓储、运输、加工、被端上餐桌的环节上，尽可能的减少粮食的浪费。"
        },
        {
            "noticeId": 9,
            "noticeTitle": "绅士笑话集",
            "noticeFile": "af588adb-9a60-4506-b058-98012ad9ea2c.txt",
            "creatId": 2,
            "createTime": "2023-01-15T19:48:42",
            "updateId": 2,
            "updateTime": "2023-01-15T19:48:42",
            "isDisable": true,
            "isDel": "0",
            "createName": "李云",
            "updateName": "李云",
            "textNotice": "在第一幕结尾，琅优向施梓透露居屋的墙上有秘门，而乌亚则开始惯于进食蠕虫。\n\n这部戏剧即粗俗间或又令人捧腹。戏中角色相互对立而又反复无常。\n其中多句更加意有所指的台词或可用于施展强力的法术，尽管它们藏于平平无奇的场景之中。"
        }
    ],
    "total": 6,
    "size": 7,
    "current": 1,
    "orders": [],
    "optimizeCountSql": true,
    "searchCount": true,
    "maxLimit": null,
    "countId": null,
    "pages": 1
}
```

## 管理员列表

> http://localhost:8085/employee/page?numPage=1&pageSize=7&employeeName=

```json
{
    "records": [
        {
            "employeeId": 1,
            "employeeUsername": "admin",
            "employeePassword": null,
            "employeeName": "炒鸡管理员",
            "employeeLevel": 6,
            "employeeTel": "13882244666",
            "employeePhoto": "admm.jpg",
            "createTime": "2022-12-22T11:08:31",
            "updateTime": null,
            "isDel": null
        },
        {
            "employeeId": 2,
            "employeeUsername": "yun",
            "employeePassword": null,
            "employeeName": "李云",
            "employeeLevel": 4,
            "employeeTel": "13882244666",
            "employeePhoto": "doctor-1.jpg",
            "createTime": "2023-01-05T21:34:23",
            "updateTime": null,
            "isDel": null
        },
        {
            "employeeId": 4,
            "employeeUsername": "wheat",
            "employeePassword": null,
            "employeeName": "作者",
            "employeeLevel": 4,
            "employeeTel": "13882244666",
            "employeePhoto": "san-yu.jpg",
            "createTime": "2023-01-07T20:25:08",
            "updateTime": null,
            "isDel": null
        },
        {
            "employeeId": 3,
            "employeeUsername": "ra9",
            "employeePassword": null,
            "employeeName": "马库斯",
            "employeeLevel": 2,
            "employeeTel": "13882244666",
            "employeePhoto": "ju-cat.jpg",
            "createTime": "2023-01-05T22:02:30",
            "updateTime": null,
            "isDel": null
        },
        {
            "employeeId": 5,
            "employeeUsername": "zs",
            "employeePassword": null,
            "employeeName": "张三",
            "employeeLevel": 0,
            "employeeTel": "13882244666",
            "employeePhoto": "zs.jpg",
            "createTime": "2023-01-12T12:25:41",
            "updateTime": null,
            "isDel": null
        }
    ],
    "total": 5,
    "size": 7,
    "current": 1,
    "orders": [],
    "optimizeCountSql": true,
    "searchCount": true,
    "maxLimit": null,
    "countId": null,
    "pages": 1
}
```

## 部门列表

> http://localhost:8085/department/page?numPage=1&pageSize=7&departmentName=

```json
{
    "records": [
        {
            "departmentId": 1,
            "departmentName": "犬猫科",
            "departmentInfo": "主要整治猫猫狗狗",
            "departmentAddress": "一楼227",
            "createTime": null,
            "updateTime": null,
            "isDel": null
        },
        {
            "departmentId": 2,
            "departmentName": "骨科",
            "departmentInfo": "专治跌打损伤",
            "departmentAddress": "三楼238",
            "createTime": null,
            "updateTime": null,
            "isDel": null
        },
        {
            "departmentId": 3,
            "departmentName": "化验室",
            "departmentInfo": "进行化验",
            "departmentAddress": "三楼226",
            "createTime": null,
            "updateTime": null,
            "isDel": null
        },
        {
            "departmentId": 4,
            "departmentName": "美容室",
            "departmentInfo": "对宠物进行美容",
            "departmentAddress": "三楼327",
            "createTime": null,
            "updateTime": null,
            "isDel": null
        },
        {
            "departmentId": 8,
            "departmentName": "中兽科",
            "departmentInfo": "传统中医治兽",
            "departmentAddress": "一楼125",
            "createTime": null,
            "updateTime": null,
            "isDel": null
        }
    ],
    "total": 5,
    "size": 7,
    "current": 1,
    "orders": [],
    "optimizeCountSql": true,
    "searchCount": true,
    "maxLimit": null,
    "countId": null,
    "pages": 1
}
```

