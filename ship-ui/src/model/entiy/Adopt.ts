export interface Adopt {
    adoptId: number;
    petId: number;
    clientId: number;
    adoptMoney: number;
    adoptInfo: string;
    inAdopt: number;
    createTime: string;
    updateTime?: string | null;
}