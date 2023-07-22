export enum RoleEnum {
    ADMIN = "EMPLOYEE",
    DOCTOR = "DOCTOR",
    CLIENT = "CLIENT"
}

export interface LoginVo {
    username: string,
    password: string,
    remember: boolean
    role: RoleEnum
}