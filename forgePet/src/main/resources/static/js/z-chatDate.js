// 获取工龄天数
const nowDate = new Date()//工龄毫秒值
const getDate = createTime => {
    const hours = createTime.split("T")[1]
    const start = Date.parse(createTime)
    if (start >= nowDate) return hours
    //最快取整
    const dayBad = ~~((nowDate - start) / (86400000))
    if (dayBad > 0) return dayBad + "天前 " + hours
    return hours
}