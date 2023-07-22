import {Pet} from "@/modal/entiy/Pet";
import {Page} from "@/modal/DO/Page";

/**
 * 后台管理页面的宠物
 */
export interface PetDto extends Pet {
    clientName: string | null
}

/**
 * 后台管理页面的宠物样例
 */
export const examplePetBack = (): Page<PetDto> => {
    return {
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
        "total": 28
    }
}