export interface Doctor {
    doctorId: number;
    departmentId?: number | null;
    username: string;
    doctorName: string;
    doctorGender: boolean;
    doctorAge: string;
    doctorPhoto: string;
    doctorTel: string;
    authLv: number;
    doctorInfo: string;
    doctorPassword?: string | null;
    createTime?: string | null;
    updateTime?: string | null;
}

/**
 * 空数据
 */
export const exampleDoctor = (): Doctor => {
    return {
        doctorId: 0,
        departmentId: null,
        username: "",
        doctorName: "",
        doctorGender: false,
        doctorAge: "",
        doctorPhoto: "",
        doctorTel: "",
        authLv: 0,
        doctorInfo: ""
    }
}