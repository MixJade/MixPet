import {ElMessage} from "element-plus";

/**
 * 邮箱格式验证
 * @param mail 待验证邮箱
 * @return 不符号邮箱格式
 */
export const noMail = (mail: string): boolean => {
    const reg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!reg.test(mail)) {
        ElMessage.warning("邮箱格式不对")
        return true;
    } else return false;
}
