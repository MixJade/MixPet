import {Adopt} from "@/modal/entiy/Adopt";
import {Page} from "@/modal/DO/Page";

export interface AdoptDto extends Adopt {
    petName: string;
    clientName: string;
}

/**
 * 后台管理，领养列表
 */
export const exampleAdoptBack = (): Page<AdoptDto> => {
    return {
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
        "total": 8
    }
}