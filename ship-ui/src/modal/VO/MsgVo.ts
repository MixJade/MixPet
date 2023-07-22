/**
 * 聊天记录
 */
export interface MsgVo {
    msgId: number;
    isMine: boolean;
    msgContent: string;
    isImg: boolean;
    createTime: string;
    roleName: string;
    rolePhoto: string;
}
/**
 * 医生的聊天记录
 * @param youID 当前聊天的用户ID
 */
export const exampleDMsg = (youID: number): MsgVo[] => {
    console.log("聊天的用户ID是：", youID)
    return [
        {
            "msgId": 9,
            "isMine": false,
            "msgContent": "童医生，你好",
            "isImg": false,
            "createTime": "2023-03-03T22:55:24",
            "roleName": "莹",
            "rolePhoto": "lei-jun.jpg",
        },
        {
            "msgId": 13,
            "isMine": true,
            "msgContent": "你好",
            "isImg": false,
            "createTime": "2023-03-06T13:15:36",
            "roleName": "童德统",
            "rolePhoto": "doctor-ex.jpg"
        },
        {
            "msgId": 14,
            "isMine": false,
            "msgContent": "我的鱼的鳞片为什么都竖起来了",
            "isImg": false,
            "createTime": "2023-03-06T21:54:27",
            "roleName": "莹",
            "rolePhoto": "lei-jun.jpg"
        },
        {
            "msgId": 15,
            "isMine": false,
            "msgContent": "前两天还好好的",
            "isImg": false,
            "createTime": "2023-03-06T21:54:57",
            "roleName": "莹",
            "rolePhoto": "lei-jun.jpg"
        },
        {
            "msgId": 16,
            "isMine": true,
            "msgContent": "可能染上了立鳞病",
            "isImg": false,
            "createTime": "2023-03-06T21:56:26",
            "roleName": "童德统",
            "rolePhoto": "doctor-ex.jpg"
        },
        {
            "msgId": 17,
            "isMine": false,
            "msgContent": "立鳞病？那是什么",
            "isImg": false,
            "createTime": "2023-03-07T16:09:39",
            "roleName": "莹",
            "rolePhoto": "lei-jun.jpg"
        },
        {
            "msgId": 18,
            "isMine": true,
            "msgContent": "其会使病鱼体表肿胀，部分鳞片向外张开使鳞片竖起。",
            "isImg": false,
            "createTime": "2023-03-07T16:11:48",
            "roleName": "童德统",
            "rolePhoto": "doctor-ex.jpg"
        },
        {
            "msgId": 19,
            "isMine": false,
            "msgContent": "真的吗",
            "isImg": false,
            "createTime": "2023-03-07T16:12:06",
            "roleName": "莹",
            "rolePhoto": "lei-jun.jpg"
        },
        {
            "msgId": 20,
            "isMine": false,
            "msgContent": "那怎么办啊",
            "isImg": false,
            "createTime": "2023-03-07T16:14:37",
            "roleName": "莹",
            "rolePhoto": "lei-jun.jpg"
        },
        {
            "msgId": 24,
            "isMine": true,
            "msgContent": "你发张照片我看看",
            "isImg": false,
            "createTime": "2023-03-07T17:16:24",
            "roleName": "童德统",
            "rolePhoto": "doctor-ex.jpg"
        },
        {
            "msgId": 25,
            "isMine": false,
            "msgContent": "这样行吗？",
            "isImg": false,
            "createTime": "2023-03-08T13:30:33",
            "roleName": "莹",
            "rolePhoto": "lei-jun.jpg"
        },
        {
            "msgId": 26,
            "isMine": false,
            "msgContent": "ms-img-01.png",
            "isImg": true,
            "createTime": "2023-03-09T12:58:19",
            "roleName": "莹",
            "rolePhoto": "lei-jun.jpg"
        },
        {
            "msgId": 27,
            "isMine": true,
            "msgContent": "ms-img-02.png",
            "isImg": true,
            "createTime": "2023-03-09T12:58:51",
            "roleName": "童德统",
            "rolePhoto": "doctor-ex.jpg"
        },
        {
            "msgId": 28,
            "isMine": false,
            "msgContent": "ms-img-03.png",
            "isImg": true,
            "createTime": "2023-03-09T12:59:03",
            "roleName": "莹",
            "rolePhoto": "lei-jun.jpg"
        }
    ]
}
