import {FosterDto} from "@/model/DO/FosterDto";

export interface FosterCardDto extends FosterDto {
    petVariety: string;
    petSex: boolean;
    petAge: string;
    petStatus: string;
    petInfo: string;
    petPhoto: string;
}
