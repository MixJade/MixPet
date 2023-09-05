<template>
  <div>
    <div class="base-page">
      <!-- 导航栏-->
      <PageHead>宠物详情</PageHead>
      <MyRow2>
        <!-- 左边div：宠物头像-->
        <div class="left-div">
          <img :src="'/api/common/download?name='+pet.petPhoto" alt="头像" class="my-avatar">
          <h3>{{ pet.petName }}</h3>
          <span>{{ getAge(pet.petAge) }}岁</span>
        </div>
        <template #right>
          <!-- 右边div，宠物信息-->
          <table class="myTable">
            <tr>
              <td class="bold">姓名</td>
              <td>{{ pet.petName }}</td>
            </tr>
            <tr>
              <td class="bold">品种</td>
              <td>{{ pet.petVariety }}</td>
            </tr>
            <tr>
              <td class="bold">性别</td>
              <td>{{ pet.petSex ? "公" : "母" }}</td>
            </tr>
            <tr>
              <td class="bold">出生日期</td>
              <td>{{ pet.petAge }}</td>
            </tr>
            <tr>
              <td class="bold">最近状况</td>
              <td>{{ pet.petInfo }}</td>
            </tr>
            <tr>
              <td class="bold">健康状况</td>
              <td>{{ pet.petStatus }}</td>
            </tr>
          </table>
          <el-button v-if="inAdopt===0 && pet.clientId==null" style="width: 50%" type="primary"
                     @click="dialogVisible = true">
            申请领养
          </el-button>
          <el-button v-else-if="pet.clientId==null" disabled style="width: 50%" type="info">
            审核中
          </el-button>
        </template>
      </MyRow2>
    </div>
    <!--弹出框-->
    <el-dialog v-model="dialogVisible" title="申请领养">
      <el-form :model="dialogAsk">
        <el-form-item label="申请理由" label-width="140px">
          <el-input v-model="dialogAsk.adoptInfo" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="预付押金" label-width="140px">
          <el-input-number
              v-model="dialogAsk.adoptMoney"
              :max="5000"
              :min="100"
              :step="100"
              controls-position="right"
          />
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="adoptPet">
          确认
        </el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import PageHead from "@/components/row/PageHead.vue";
import {examplePet, Pet} from "@/model/entiy/Pet";
import {getAge} from "@/utils/TimeUtil";
import {onMounted, reactive, ref} from "vue";
import {PetAskOne} from "@/model/VO/PetAskOne";
import {reqPetOne} from "@/request/PetApi";
import {reqAdoptPet, reqInAdopt} from "@/request/AdoptApi";
import MyRow2 from "@/components/row/MyRow2.vue";

// 如此获取传参
const props = defineProps<{
  petId: string
}>()
// 样例数据
const pet = ref<Pet>(examplePet())
// 是否领养
const inAdopt = ref<number>(0)

// 弹出框设置
const dialogVisible = ref(false)
const dialogAsk: PetAskOne = reactive({
  petId: parseInt(props.petId),
  adoptInfo: "",
  adoptMoney: 300
})
const adoptPet = () => {
  dialogVisible.value = false
  inAdopt.value = 1
  reqAdoptPet(dialogAsk)
}

onMounted(() => {
  reqPetOne(parseInt(props.petId)).then(res => {
    pet.value = res
  })
  reqInAdopt(parseInt(props.petId)).then(res => {
    inAdopt.value = res
  })
})
</script>

<style lang="scss" scoped>
/*设置大致布局*/
.base-page {
  position: absolute;
  top: 15vh;
  background-color: whitesmoke;
  box-shadow: 5px 5px 10px 0 rgba(0, 0, 0, 0.5);
  width: 50vw;
  left: 25vw;
  padding: 16px;
  border-radius: 12px;
  @media (min-width: 576px) and (max-width: 768px) {
    width: 60vw;
    left: 20vw;
  }
  @media (max-width: 575px) {
    width: 80vw;
    left: 10vw;
  }

  img.my-avatar {
    border-radius: 50%;
    margin-top: 10px;
    width: 128px;
    height: 128px;
  }
}

/*设置信息展示表格*/
.myTable {
  border-collapse: collapse;
  /*设置定位*/
  width: 100%;
  margin-bottom: 20px;

  td {
    border: 1px solid black;
    border-left: none;
    border-right: none;
    padding: 2px;
  }

  tr:first-child td {
    border-top: none;
  }

  tr:last-child td {
    border-bottom: none;
  }

  .bold {
    font-weight: bold;
  }
}
</style>