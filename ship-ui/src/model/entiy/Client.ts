/**
 * 个人信息
 */
export interface Client {
    clientId: number;
    clientUsername: string;
    clientName: string;
    clientGender: boolean;
    clientTel: string;
    clientAge: string;
    clientInfo: string;
    clientPhoto: string;
}

/**
 * 空数据
 */
export const exampleClient = ():Client => {
  return {
      "clientId": 0,
      "clientUsername": "",
      "clientName": "",
      "clientGender": false,
      "clientTel": "",
      "clientAge": "",
      "clientInfo": "",
      "clientPhoto": "",
  }
}