/**
 * 判断一个管理员的职位
 * @param level 权限等级
 */
export const getJob = (level: number): string => {
    switch (level) {
        case 0:
            return "临时工"
        case 2:
            return "护士"
        case 4:
            return "主任"
        case 6:
            return "院长"
        default:
            return "啥也不是"
    }
}