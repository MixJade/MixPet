import {AppointDto} from "@/model/DO/AppointDto";

/**
 * 医生个人中心挂号信息
 */
export interface AppointDto2 extends AppointDto {
    petPhoto: string;
    petVariety: string;
    petStatus: string;
    petSex: boolean;
    petAge: string;
}
