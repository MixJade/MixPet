# B7-mysql查询null

* 应该使用isNull进行查询(作用为`==null`)
* `SELECT pet_id as roleId, pet_name as roleName FROM pet WHERE is_del = '0' AND isNull(client_id)`
* 直接使用`=null`是什么也查不出来的