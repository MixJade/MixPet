import {Pet} from "@/model/entiy/Pet";

/**
 * 后台管理页面的宠物
 */
export interface PetDto extends Pet {
    clientName: string | null
}