import {Page} from "@/modal/DO/Page";
import {Foster} from "@/modal/entiy/Foster";

export interface FosterDto extends Foster{
    petName: string;
    clientName: string;
}

/**
 * 寄养表后台展示
 */
export const exampleFosterBack = (): Page<FosterDto> => {
    return {
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
        "total": 5
    }
}