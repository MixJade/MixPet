import {Adopt} from "@/modal/entiy/Adopt";

export interface AdoptDto extends Adopt {
    petName: string;
    clientName: string;
}