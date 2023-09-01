import {getNowISO} from "@/utils/TimeUtil";

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

export const exampleAppoint = ():Appoint => {
    return {
        appointmentId: 0,
        clientId: null,
        petId: null,
        appointmentDate: getNowISO(),
        departmentId: null,
        doctorId: null,
        appointmentInfo: "",
        isSuc: null,
        isDel: null
    }
}
