import {Department} from "@/model/entiy/Department";

export interface DepartDto extends Department {
    doctorNum: number;
    headName: null | string;
}
