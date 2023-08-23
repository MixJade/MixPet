/**
 * 单个医生
 */
import {Doctor} from "@/model/entiy/Doctor";

export interface DoctorDto extends Doctor {
    departmentName: string;
}

/**
 * 医生个人中心
 */
export const exampleDoctorOne = (): DoctorDto => {
    return {
        "doctorId": 0,
        "departmentId": null,
        "username": "",
        "doctorName": "",
        "doctorGender": true,
        "doctorAge": "",
        "doctorPhoto": "",
        "doctorTel": "",
        "authLv": 0,
        "doctorInfo": "",
        "doctorPassword": null,
        "createTime": null,
        "updateTime": null,
        "departmentName": ""
    }
}