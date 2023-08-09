/**
 * 查询时的分页参数
 */
export interface PageQuery {
    numPage: number
    pageSize: number
}

//宠物列表的查询参数
export interface YPetList extends PageQuery {
    petName: string
    clientName: string
}

// 用户列表的查询参数
export interface YClientList extends PageQuery {
    clientName: string
}

// 医生列表的查询参数
export interface YDoctorList extends PageQuery {
    doctorName: string
    departmentName: string
}

// 医生列表的查询参数
export interface XEmployeeList extends PageQuery {
    employeeName: string
}

// 科室列表的查询参数
export interface XDepartmentList extends PageQuery {
    departmentName: string
}

// 领养表查询参数
export interface YAdoptList extends PageQuery {
    petName: string
    clientName: string
}

// 寄养表
export interface YFosterList extends PageQuery {
    petName: string
    clientName: string
}

// 寄养表
export interface YAppointList extends PageQuery {
    seaName: string
    seaType: number
}