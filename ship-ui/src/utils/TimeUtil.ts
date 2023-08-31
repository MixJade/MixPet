// 根据出生日期算年龄(简化版)
const nowYear: number = new Date().getFullYear()//获取年数差
export const getAge = (birthday: string): number => {
    const birthYear = (birthday.split("-")[0] as unknown) as number
    return nowYear - birthYear
}
// 去除时间中的T
export const moveT = (str: string): string => {
    try {
        let formatTimeArray = str.split("T")
        formatTimeArray[1] = formatTimeArray[1].split(":")[0] + ":" + formatTimeArray[1].split(":")[1];
        return formatTimeArray.join("_");
    } catch (e) {
        return "未知"
    }
}

/**
 聊天页面的时间计算
 如果是在今天的日期，则只返回时分秒，
 如果是今年的而不是今天，则返回月份、日期与时分，
 如果不是今年的，则只返回年月日
 @param datetime 输入时间ISO 8601格式
 */
export function getDisplayTime(datetime: string): string {
    const date = new Date(datetime);
    const now = new Date();
    if (date.getFullYear() == now.getFullYear()) {
        if (date.getMonth() == now.getMonth() && date.getDate() == now.getDate()) {
            return `${padZero(date.getHours())}:${padZero(date.getMinutes())}:${padZero(date.getSeconds())}`;
        } else {
            return `${padZero(date.getMonth() + 1)}-${padZero(date.getDate())} ${padZero(date.getHours())}:${padZero(date.getMinutes())}`;
        }
    } else {
        return `${date.getFullYear()}-${padZero(date.getMonth() + 1)}-${padZero(date.getDate())}`;
    }
}

// 用于在数字前补零，保持时间格式的一致性
function padZero(num: number): string {
    return num < 10 ? '0' + num : String(num);
}

/**
 * 返回输入时间到今天具有多少天
 * @param dateString 输入时间，ISO 8601格式
 */
export function getDaysFromToday(dateString: string): string {
    let today = new Date();
    let date = new Date(dateString);
    let timeDiff = Math.abs(today.getTime() - date.getTime());
    return Math.ceil(timeDiff / (1000 * 3600 * 24)) + "天";
}

/**
 * 返回当前的时间（直接生成ISO格式会导致时区问题)
 */
export const getNowISO = () => {
    const date = new Date();
    return date.getFullYear() + "-" + ("0" + (date.getMonth() + 1)).slice(-2)
        + "-" + ("0" + date.getDate()).slice(-2) + "T" + ("0" + date.getHours()).slice(-2)
        + ":" + ("0" + date.getMinutes()).slice(-2) + ":00"
}