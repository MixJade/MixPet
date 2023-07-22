/**
 * 个人信息
 */
import {Page} from "@/modal/DO/Page";

export interface Client {
    clientId: number;
    clientUsername: string;
    clientName: string;
    clientGender: boolean;
    clientTel: string;
    clientAge: string;
    clientInfo: string;
    clientPhoto: string;
}
/**
 * 管理员列表
 */
export const exampleClientBack = (): Page<Client> => {
    return {
        "records": [
            {
                "clientId": 1,
                "clientUsername": "ying",
                "clientName": "莹",
                "clientGender": false,
                "clientTel": "sanyu0201@163.com",
                "clientAge": "2001-01-03",
                "clientInfo": "来自提瓦特❥(^_-)",
                "clientPhoto": "ying.jpg",
            },
            {
                "clientId": 2,
                "clientUsername": "lei-jun",
                "clientName": "雷军",
                "clientGender": false,
                "clientTel": "sanyu0201@163.com",
                "clientAge": "1990-01-01",
                "clientInfo": "喜欢宅家撸猫",
                "clientPhoto": "lei-jun.jpg",
            },
            {
                "clientId": 3,
                "clientUsername": "sanyu",
                "clientName": "散玉",
                "clientGender": true,
                "clientTel": "sanyu0201@163.com",
                "clientAge": "2000-01-01",
                "clientInfo": "作者",
                "clientPhoto": "san-yu.jpg",
            },
            {
                "clientId": 4,
                "clientUsername": "fox",
                "clientName": "狐狸",
                "clientGender": false,
                "clientTel": "sanyu0201@163.com",
                "clientAge": "2000-01-07",
                "clientInfo": "粉毛狐狸",
                "clientPhoto": "ba-chong.jpg",
            },
            {
                "clientId": 5,
                "clientUsername": "hong",
                "clientName": "红中",
                "clientGender": true,
                "clientTel": "sanyu0201@163.com",
                "clientAge": "2023-01-22",
                "clientInfo": "一个新用户",
                "clientPhoto": "zs.jpg",
            },
            {
                "clientId": 6,
                "clientUsername": "bei",
                "clientName": "北风",
                "clientGender": false,
                "clientTel": "sanyu0201@163.com",
                "clientAge": "2000-01-03",
                "clientInfo": "黜聪明，拙肢体，同于大道",
                "clientPhoto": "zs.jpg",
            },
            {
                "clientId": 9,
                "clientUsername": "wang",
                "clientName": "骰子",
                "clientGender": true,
                "clientTel": "sanyu0201@163.com",
                "clientAge": "2023-01-22",
                "clientInfo": "一个新用户",
                "clientPhoto": "zs.jpg",
            }
        ],
        "total": 11
    }
}