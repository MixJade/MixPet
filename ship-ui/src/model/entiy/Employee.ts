/**
 * 管理员信息表
 */
export interface Employee {
    employeeId: number;
    employeeUsername: string;
    employeePassword?: string | null;
    employeeName: string;
    employeeLevel: number;
    employeeTel: string;
    employeePhoto: string;
    createTime: string;
    updateTime?: string | null;
}

/**
 * 空数据
 */
export const exampleEmployee = (): Employee => {
    return {
        "employeeId": 0,
        "employeeUsername": "",
        "employeeName": "",
        "employeeLevel": 0,
        "employeeTel": "",
        "employeePhoto": "zs.jpg",
        "createTime": ""
    }
}