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