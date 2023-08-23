export enum RoleEnum {
    DOCTOR = "DOCTOR",
    CLIENT = "CLIENT"
}

export interface LoginVo {
    username: string,
    password: string,
    remember: boolean
    role: RoleEnum
}