// 分页对象
export interface Page<T> {
    records: Array<T>;
    total: number;
}