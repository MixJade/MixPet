import {Page} from "@/modal/DO/Page";

export interface Department {
    departmentId: number;
    departmentName: string;
    departmentInfo: string;
    departmentAddress: string;
    createTime?: null | string;
    updateTime?: null | string;
    isDel?: null | string;
}

/**
 * 后台列表的部门
 */
export const exampleDepartBack = (): Page<Department> => {
    return {
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
        "total": 5
    }
}