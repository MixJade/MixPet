/**
 * 管理员信息表
 */
import {Page} from "@/modal/DO/Page";

export interface Employee {
    employeeId: number;
    employeeUsername: string;
    employeePassword: string | null;
    employeeName: string;
    employeeLevel: number;
    employeeTel: string;
    employeePhoto: string;
    createTime: string;
    updateTime: string | null;
    isDel: string | null;
}

/**
 * 登录的管理员信息
 */
export const exampleEmployee = (): Employee => {
    return {
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
}
/**
 * 后台列表的管理员
 */
export const exampleEmployeeBack = (): Page<Employee> => {
    return {
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
        "total": 5
    }
}