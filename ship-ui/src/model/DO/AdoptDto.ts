import {Adopt} from "@/model/entiy/Adopt";

export interface AdoptDto extends Adopt {
    petName: string;
    clientName: string;
}