export interface Department {
    departmentId: number;
    departmentName: string;
    departmentInfo: string;
    headId: null | number;
    createTime?: null | string;
    updateTime?: null | string;
}

/**
 * 空数据
 */
export const exampleDepart = (): Department => {
    return {
        "departmentId": 0,
        "departmentName": "",
        "departmentInfo": "",
        "headId": null
    }
}