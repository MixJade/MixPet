/**
 * 挂号单信息
 */
import {Appoint} from "@/modal/entiy/Appoint";
import {Page} from "@/modal/DO/Page";

export interface AppointDto extends Appoint {
    clientName: string | null;
    petName: string;
    departmentName: string | null;
    doctorName: string | null;
}

/**
 * 个人中心的挂号信息
 */
export const exampleClientAppoint = (): AppointDto[] => {
    return [
        {
            "appointmentId": 4,
            "clientId": 1,
            "petId": 3,
            "appointmentDate": "2023-01-16T21:24:00",
            "departmentId": null,
            "doctorId": 4,
            "appointmentInfo": "查看骨骼",
            "isSuc": null,
            "createTime": "2023-01-16T21:24:51",
            "updateTime": null,
            "isDel": null,
            "clientName": null,
            "petName": "心海",
            "departmentName": "中兽科",
            "doctorName": "汤姆"
        },
        {
            "appointmentId": 1,
            "clientId": 1,
            "petId": 5,
            "appointmentDate": "2022-12-22T11:17:54",
            "departmentId": null,
            "doctorId": 1,
            "appointmentInfo": "严重的肛裂",
            "isSuc": null,
            "createTime": "2022-12-22T11:18:04",
            "updateTime": null,
            "isDel": null,
            "clientName": null,
            "petName": "雷电小兵",
            "departmentName": "犬猫科",
            "doctorName": "童德统"
        }
    ]
}
/**
 * 后台的挂号单
 */
export const exampleAppointBack = (): Page<AppointDto> => {
    return {
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
        "total": 7
    }
}