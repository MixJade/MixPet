<template>
  <div>
    <PageHead>查看挂号</PageHead>
    <MyRow>
      <AppointCard v-for="a in appoint" :key="a.appointmentId" :a="a" @toSuc="toSuc"/>
    </MyRow>
  </div>
</template>

<script lang="ts" setup>
import PageHead from "@/components/PageHead.vue";
import MyRow from "@/components/show/MyRow.vue";
import {AppointDto2} from "@/model/DO/AppointDto2";
import AppointCard from "@/components/card/AppointCard.vue";
import {reqDoctorAppoint} from "@/request/DoctorApi";
import {onMounted, ref} from "vue";

const appoint = ref<AppointDto2[]>([])
onMounted(() => {
  reqDoctorAppoint().then(res => {
    appoint.value = res
  })
})
const toSuc = () => {
  alert("Good")
}
</script>

<style lang="scss" scoped>
</style>
