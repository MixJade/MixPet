/**
 * 挂号单信息
 */
import {Appoint} from "@/modal/entiy/Appoint";

export interface AppointDto extends Appoint {
    clientName: string | null;
    petName: string;
    departmentName: string | null;
    doctorName: string | null;
}