/**
 * 单个医生
 */
import {Doctor} from "@/modal/entiy/Doctor";
import {Page} from "@/modal/DO/Page";

export interface DoctorDto extends Doctor {
    departmentName: string;
}
/**
 * 后台管理的医生列表
 */
export const exampleDoctorBack = (): Page<DoctorDto> => {
    return {
        "records": [
            {
                "doctorId": 1,
                "departmentId": 1,
                "doctorCode": "32312221",
                "doctorName": "童德统",
                "doctorGender": true,
                "doctorAge": "1990-10-10",
                "doctorPhoto": "doctor-1.jpg",
                "doctorTel": "1114185977@qq.com",
                "doctorJob": "副院长",
                "doctorInfo": "国内著名医学专家，擅长绝育",
                "doctorPassword": null,
                "createTime": null,
                "updateTime": null,
                "isDel": null,
                "departmentName": "犬猫科"
            },
            {
                "doctorId": 2,
                "departmentId": 4,
                "doctorCode": "32301062",
                "doctorName": "爱丽丝",
                "doctorGender": false,
                "doctorAge": "2003-02-01",
                "doctorPhoto": "doctor-2.jpg",
                "doctorTel": "mix_pet_doctor@sina.com",
                "doctorJob": "麻醉科主任",
                "doctorInfo": "擅长麻醉",
                "doctorPassword": null,
                "createTime": null,
                "updateTime": null,
                "isDel": null,
                "departmentName": "美容室"
            },
            {
                "doctorId": 4,
                "departmentId": 8,
                "doctorCode": "32301063",
                "doctorName": "汤姆",
                "doctorGender": true,
                "doctorAge": "2000-01-11",
                "doctorPhoto": "doctor-3.jpg",
                "doctorTel": "1114185977@qq.com",
                "doctorJob": "医生",
                "doctorInfo": "擅长x光拍片",
                "doctorPassword": null,
                "createTime": null,
                "updateTime": null,
                "isDel": null,
                "departmentName": "中兽科"
            },
            {
                "doctorId": 5,
                "departmentId": 4,
                "doctorCode": "32301064",
                "doctorName": "张大嘴",
                "doctorGender": false,
                "doctorAge": "2002-01-05",
                "doctorPhoto": "doctor-4.jpg",
                "doctorTel": "mix_pet_doctor@sina.com",
                "doctorJob": "医生",
                "doctorInfo": "著名宠物医美专家",
                "doctorPassword": null,
                "createTime": null,
                "updateTime": null,
                "isDel": null,
                "departmentName": "美容室"
            },
            {
                "doctorId": 6,
                "departmentId": 3,
                "doctorCode": "32301065",
                "doctorName": "李妙手",
                "doctorGender": true,
                "doctorAge": "2001-01-06",
                "doctorPhoto": "3b161abe-7142-449c-ab36-07a689da65de.jpg",
                "doctorTel": "1114185977@qq.com",
                "doctorJob": "医生",
                "doctorInfo": "国外著名医学专家",
                "doctorPassword": null,
                "createTime": null,
                "updateTime": null,
                "isDel": null,
                "departmentName": "化验室"
            },
            {
                "doctorId": 8,
                "departmentId": 2,
                "doctorCode": "32301076",
                "doctorName": "李正骨",
                "doctorGender": false,
                "doctorAge": "2000-01-28",
                "doctorPhoto": "3cd8e749-e177-4e5b-846c-930efab7bd38.jpg",
                "doctorTel": "1114185977@qq.com",
                "doctorJob": "医生",
                "doctorInfo": "国内外著名骨科专家",
                "doctorPassword": null,
                "createTime": null,
                "updateTime": null,
                "isDel": null,
                "departmentName": "骨科"
            },
            {
                "doctorId": 9,
                "departmentId": 8,
                "doctorCode": "32302189",
                "doctorName": "钟回春",
                "doctorGender": true,
                "doctorAge": "2000-02-10",
                "doctorPhoto": "92833192-79d4-42d6-a657-d000d8ca6295.jpg",
                "doctorTel": "mix_pet_doctor@sina.com",
                "doctorJob": "医生",
                "doctorInfo": "多年老中医",
                "doctorPassword": null,
                "createTime": null,
                "updateTime": null,
                "isDel": null,
                "departmentName": "中兽科"
            }
        ],
        "total": 7
    }
}

/**
 * 医生个人中心
 */
export const exampleDoctorOne = (): DoctorDto => {
    return {
        "doctorId": 1,
        "departmentId": null,
        "doctorCode": "32312221",
        "doctorName": "童德统",
        "doctorGender": true,
        "doctorAge": "1990-10-10",
        "doctorPhoto": "doctor-1.jpg",
        "doctorTel": "1114185977@qq.com",
        "doctorJob": "副院长",
        "doctorInfo": "国内著名医学专家，擅长绝育",
        "doctorPassword": null,
        "createTime": null,
        "updateTime": null,
        "isDel": null,
        "departmentName": "犬猫科"
    }
}