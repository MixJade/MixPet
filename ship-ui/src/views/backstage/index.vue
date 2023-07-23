<template>
  <el-container>
    <el-aside width="200px">
      <el-avatar :src="'/api/common/download?name='+employeeNow.photo"/>
      <el-menu
          active-text-color="#ffd04b"
          background-color="#545c64"
          :default-active="this.$route.path"
          text-color="#fff"
          router
      >
        <el-sub-menu index="">
          <template #title>
            <el-icon>
              <icon-menu/>
            </el-icon>
            <span>个人中心</span>
          </template>
          <el-menu-item-group :title="employeeNow.name">
            <el-menu-item index="/backstage">个人资料</el-menu-item>
            <el-menu-item index="/backstage/x-departmentList">部门列表</el-menu-item>
            <el-menu-item index="/backstage/x-employeeList">员工列表</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>
        <el-menu-item index="/backstage/y-clientList">
          <el-icon>
            <User/>
          </el-icon>
          <span>用户列表</span>
        </el-menu-item>
        <el-menu-item index="/backstage/y-petList">
          <el-icon>
            <Football/>
          </el-icon>
          <span>宠物列表</span>
        </el-menu-item>
        <el-menu-item index="/backstage/y-doctorList">
          <el-icon>
            <Apple/>
          </el-icon>
          <span>医生列表</span>
        </el-menu-item>
        <el-menu-item index="/backstage/y-appointmentList">
          <el-icon>
            <Tickets/>
          </el-icon>
          <span>挂号列表</span>
        </el-menu-item>
        <el-menu-item index="/backstage/y-adoptList">
          <el-icon>
            <School/>
          </el-icon>
          <span>领养列表</span>
        </el-menu-item>
        <el-menu-item index="/backstage/y-fosterList">
          <el-icon>
            <OfficeBuilding/>
          </el-icon>
          <span>寄养列表</span>
        </el-menu-item>
        <el-menu-item index="/backstage/y-noticeList">
          <el-icon>
            <DataBoard/>
          </el-icon>
          <span>公告列表</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import {
  User, Football, Tickets, DataBoard, OfficeBuilding, School, Apple,
  Menu as IconMenu,
} from '@element-plus/icons-vue'
import {reqGetLoginE} from "@/request/EmployeeApi";
import {reactive} from "vue";

const employeeNow = reactive({
  name: "",
  photo: "zs.jpg"
})
reqGetLoginE().then(res => {
  employeeNow.photo = res.employeePhoto
  employeeNow.name = res.employeeName
})
</script>

<style scoped lang="scss">
.el-container {
  height: 96vh;
  /*侧边栏*/
  .el-aside {
    background-color: #545c64;
    user-select: none;
    /*头像*/
    .el-avatar {
      position: relative;
      height: 72px;
      width: 72px;
      top: 20px;
      left: 50%;
      margin-left: -36px;
    }

    /*导航菜单*/
    .el-menu {
      position: relative;
      top: 16px;
    }
  }

  .el-main {
    background-color: #F0EAD6;
  }
}
</style>