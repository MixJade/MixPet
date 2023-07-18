# ShipPet医生文档

## 医生个人信息

> http://localhost:8085/doctorLog

```json
{
    "doctorId": 1,
    "departmentId": null,
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
}
```

## 医生查看挂号信息

> http://localhost:8085/doctorLog/appoint

```json
[
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
        "doctorName": null,
        "petPhoto": "a78d73bc-a557-41e0-8a8f-8ddc5c22e7ce.png",
        "petVariety": "吉娃娃",
        "petSex": true,
        "petAge": "1990-08-09"
    }
]
```


## 医生查看聊天记录

> http://localhost:8085/msg/aClient?clientId=1

```json
[
    {
        "msgId": 9,
        "clientId": 1,
        "doctorId": 1,
        "isClient": true,
        "msgContent": "童医生，你好",
        "isImg": false,
        "updateTime": null,
        "createTime": "2023-03-03T22:55:24",
        "isDel": null,
        "clientName": "莹",
        "clientPhoto": "ying.jpg",
        "doctorName": "童德统",
        "doctorPhoto": "doctor-1.jpg"
    },
    {
        "msgId": 13,
        "clientId": 1,
        "doctorId": 1,
        "isClient": false,
        "msgContent": "你好",
        "isImg": false,
        "updateTime": null,
        "createTime": "2023-03-06T13:15:36",
        "isDel": null,
        "clientName": "莹",
        "clientPhoto": "ying.jpg",
        "doctorName": "童德统",
        "doctorPhoto": "doctor-1.jpg"
    },
    {
        "msgId": 14,
        "clientId": 1,
        "doctorId": 1,
        "isClient": true,
        "msgContent": "我的鱼的鳞片为什么都竖起来了",
        "isImg": false,
        "updateTime": null,
        "createTime": "2023-03-06T21:54:27",
        "isDel": null,
        "clientName": "莹",
        "clientPhoto": "ying.jpg",
        "doctorName": "童德统",
        "doctorPhoto": "doctor-1.jpg"
    },
    {
        "msgId": 15,
        "clientId": 1,
        "doctorId": 1,
        "isClient": true,
        "msgContent": "前两天还好好的",
        "isImg": false,
        "updateTime": null,
        "createTime": "2023-03-06T21:54:57",
        "isDel": null,
        "clientName": "莹",
        "clientPhoto": "ying.jpg",
        "doctorName": "童德统",
        "doctorPhoto": "doctor-1.jpg"
    },
    {
        "msgId": 16,
        "clientId": 1,
        "doctorId": 1,
        "isClient": false,
        "msgContent": "可能染上了立鳞病",
        "isImg": false,
        "updateTime": null,
        "createTime": "2023-03-06T21:56:26",
        "isDel": null,
        "clientName": "莹",
        "clientPhoto": "ying.jpg",
        "doctorName": "童德统",
        "doctorPhoto": "doctor-1.jpg"
    },
    {
        "msgId": 17,
        "clientId": 1,
        "doctorId": 1,
        "isClient": true,
        "msgContent": "立鳞病？那是什么",
        "isImg": false,
        "updateTime": null,
        "createTime": "2023-03-07T16:09:39",
        "isDel": null,
        "clientName": "莹",
        "clientPhoto": "ying.jpg",
        "doctorName": "童德统",
        "doctorPhoto": "doctor-1.jpg"
    },
    {
        "msgId": 18,
        "clientId": 1,
        "doctorId": 1,
        "isClient": false,
        "msgContent": "其会使病鱼体表肿胀，部分鳞片向外张开使鳞片竖起。",
        "isImg": false,
        "updateTime": null,
        "createTime": "2023-03-07T16:11:48",
        "isDel": null,
        "clientName": "莹",
        "clientPhoto": "ying.jpg",
        "doctorName": "童德统",
        "doctorPhoto": "doctor-1.jpg"
    },
    {
        "msgId": 19,
        "clientId": 1,
        "doctorId": 1,
        "isClient": true,
        "msgContent": "真的吗",
        "isImg": false,
        "updateTime": null,
        "createTime": "2023-03-07T16:12:06",
        "isDel": null,
        "clientName": "莹",
        "clientPhoto": "ying.jpg",
        "doctorName": "童德统",
        "doctorPhoto": "doctor-1.jpg"
    },
    {
        "msgId": 20,
        "clientId": 1,
        "doctorId": 1,
        "isClient": true,
        "msgContent": "那怎么办啊",
        "isImg": false,
        "updateTime": null,
        "createTime": "2023-03-07T16:14:37",
        "isDel": null,
        "clientName": "莹",
        "clientPhoto": "ying.jpg",
        "doctorName": "童德统",
        "doctorPhoto": "doctor-1.jpg"
    },
    {
        "msgId": 24,
        "clientId": 1,
        "doctorId": 1,
        "isClient": false,
        "msgContent": "你发张照片我看看",
        "isImg": false,
        "updateTime": null,
        "createTime": "2023-03-07T17:16:24",
        "isDel": null,
        "clientName": "莹",
        "clientPhoto": "ying.jpg",
        "doctorName": "童德统",
        "doctorPhoto": "doctor-1.jpg"
    },
    {
        "msgId": 25,
        "clientId": 1,
        "doctorId": 1,
        "isClient": true,
        "msgContent": "这样行吗？",
        "isImg": false,
        "updateTime": null,
        "createTime": "2023-03-08T13:30:33",
        "isDel": null,
        "clientName": "莹",
        "clientPhoto": "ying.jpg",
        "doctorName": "童德统",
        "doctorPhoto": "doctor-1.jpg"
    },
    {
        "msgId": 26,
        "clientId": 1,
        "doctorId": 1,
        "isClient": true,
        "msgContent": "ms-img-01.png",
        "isImg": true,
        "updateTime": null,
        "createTime": "2023-03-09T12:58:19",
        "isDel": null,
        "clientName": "莹",
        "clientPhoto": "ying.jpg",
        "doctorName": "童德统",
        "doctorPhoto": "doctor-1.jpg"
    },
    {
        "msgId": 27,
        "clientId": 1,
        "doctorId": 1,
        "isClient": false,
        "msgContent": "ms-img-02.png",
        "isImg": true,
        "updateTime": null,
        "createTime": "2023-03-09T12:58:51",
        "isDel": null,
        "clientName": "莹",
        "clientPhoto": "ying.jpg",
        "doctorName": "童德统",
        "doctorPhoto": "doctor-1.jpg"
    },
    {
        "msgId": 28,
        "clientId": 1,
        "doctorId": 1,
        "isClient": true,
        "msgContent": "ms-img-03.png",
        "isImg": true,
        "updateTime": null,
        "createTime": "2023-03-09T12:59:03",
        "isDel": null,
        "clientName": "莹",
        "clientPhoto": "ying.jpg",
        "doctorName": "童德统",
        "doctorPhoto": "doctor-1.jpg"
    }
]
```


## 医生个人信息

> http://localhost:8085/msg/d/group?clientId=1

```json
[{"flag":0,"clientId":1,"clientName":"莹"}]
```
