export interface Adopt {
    adoptId: number;
    petId: number|null;
    clientId: number|null;
    adoptMoney: number;
    adoptInfo: string;
    inAdopt: number;
    createTime: string;
    updateTime?: string | null;
}

/**
 * 空数据
 */
export const exampleAdopt = ():Adopt => {
    return {
        adoptId: 0,
        petId: null,
        clientId: null,
        adoptMoney: 0,
        adoptInfo: "",
        inAdopt: 2,
        createTime: ""
    }
}
