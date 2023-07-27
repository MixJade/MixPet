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