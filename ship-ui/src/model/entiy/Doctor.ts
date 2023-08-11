export interface Doctor {
    doctorId: number;
    departmentId?: number | null;
    doctorCode: string;
    doctorName: string;
    doctorGender: boolean;
    doctorAge: string;
    doctorPhoto: string;
    doctorTel: string;
    doctorJob: string;
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
        doctorCode: "",
        doctorName: "",
        doctorGender: false,
        doctorAge: "",
        doctorPhoto: "",
        doctorTel: "",
        doctorJob: "",
        doctorInfo: ""
    }
}