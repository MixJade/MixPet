export interface Appoint {
    appointmentId: number;
    clientId: number | null;
    petId: number | null;
    appointmentDate: string;
    departmentId: number | null;
    doctorId: number | null;
    appointmentInfo: string;
    isSuc: boolean | null;
    createTime: string | null;
    updateTime: string | null;
    isDel: string | null;
}