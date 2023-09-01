export interface Foster {
    fosterId: number;
    fosterCode: string;
    petId: number | null;
    clientId: number | null;
    fosterTerm: string;
    fosterMoney: number;
    fosterInfo: string;
    createTime?: string | null;
    updateTime?: string | null;
}

/**
 * 空数据
 */
export const exampleFoster = (): Foster => {
    return {
        fosterId: 0,
        fosterCode: "",
        petId: null,
        clientId: null,
        fosterTerm: "",
        fosterMoney: 0,
        fosterInfo: ""
    }
}
