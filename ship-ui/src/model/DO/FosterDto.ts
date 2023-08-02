import {Foster} from "@/model/entiy/Foster";

export interface FosterDto extends Foster {
    petName: string;
    clientName: string;
}