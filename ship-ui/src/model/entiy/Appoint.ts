export interface Appoint {
    appointmentId: number;
    clientId: number | null;
    petId: number | null;
    appointmentDate: string;
    departmentId: number | null;
    doctorId: number | null;
    appointmentInfo: string;
    isSuc: boolean | null;
    createTime?: string;
    updateTime?: string | null;
    isDel: string | null;
}

export const exampleAppoint = () => {
    return {
        appointmentId: 0,
        clientId: null,
        petId: null,
        appointmentDate: "2023-08-31T14:30:00",
        departmentId: null,
        doctorId: null,
        appointmentInfo: "",
        isSuc: null,
        isDel: null
    }
}
