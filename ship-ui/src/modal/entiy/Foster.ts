export interface Foster {
    fosterId: number;
    fosterCode: string;
    petId: number;
    clientId: number;
    fosterTerm: string;
    fosterMoney: number;
    fosterInfo: string;
    createTime?: string | null;
    updateTime?: string | null;
    isDel?: string | null;
}