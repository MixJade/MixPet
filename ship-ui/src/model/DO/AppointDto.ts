/**
 * 挂号单信息
 */
import {Appoint} from "@/model/entiy/Appoint";

export interface AppointDto extends Appoint {
    clientName: string | null;
    petName: string;
    departmentName: string | null;
    doctorName: string | null;
}