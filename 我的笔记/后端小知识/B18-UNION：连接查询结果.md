# B18-UNION：连接查询结果

## 一、union和union all区别

* union：对两个结果集进行并集操作，不包括重复行，同时进行默认规则的排序；
* union All：对两个结果集进行并集操作，包括重复行，不进行排序

## 二、样例

* union为连接两个结果列之后进行去除与排序。
* union all 为连接两个结果列，包括了重复数据。

```sql
SELECT client_photo FROM client WHERE client_photo IS NOT NULL AND client_photo != ''
UNION
SELECT doctor_photo FROM doctor WHERE doctor_photo IS NOT NULL AND doctor_photo != ''
UNION
SELECT pet_photo FROM pet WHERE pet_photo IS NOT NULL AND pet_photo != ''
UNION
SELECT employee_photo FROM employee WHERE employee_photo IS NOT NULL AND employee_photo != ''
```

