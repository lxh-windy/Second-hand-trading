<template>
  <div class="contain">
    <div class="moneyin">
      请输入充值金额：<input type="number" v-model="input1" min="10" />单位（人民币）<br />
      不得小于10元
    </div>
    <div class="itemgroup">
      <div class="item" v-for="price in money1" :key="price" @click="input1 = price">
        {{ price }}元
      </div>
    </div>
    <div class="itemgroup">
      <div class="item" v-for="price in money2" :key="price" @click="input1 = price">
        {{ price }}元
      </div>
    </div>
    <div class="foot"><button @click="rechargemoney">确认充值</button></div>
  </div>
</template>

<style scoped>
  .contain {
    padding-top: 200px;
    text-align: center;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .moneyin {
    font-size: 25px;
  }
  .moneyin input {
    font-size: 25px;
    width: 5em;
  }
  .itemgroup {
    display: flex;
    justify-content: space-between;
    width: 70%;
    margin-top: 15px;
  }
  .item {
    height: 150px;
    width: 250px;
    border: 2px solid black;
    margin: 5px;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 25px;
    border-radius: 10px;
  }
  .item:hover {
    border: 3px solid blue;
    color: blue;
  }
  .foot {
    margin-top: 20px;
    width: 70%;
    text-align: right;
  }
  .foot button {
    height: 45px;
    width: 100px;
    font-size: 20px;
    border-radius: 10px;
    box-shadow: 3px 3px 3px #ccc;
  }
</style>

<script setup lang="ts">
  import { reactive, ref, watch } from 'vue'
  import { userStore } from '@/stores/user.ts'
  import { recharge } from '@/hook/recharge.ts'
  import { getuser } from '@/hook/Login.ts'
  import { ElMessage } from 'element-plus'

  const money1 = reactive([10, 20, 30])
  const money2 = reactive([100, 200, 648])

  const input1 = ref<number | undefined>()
  watch(input1, (value) => {
    if (value != undefined) {
      if ((value as number) <= 10) {
        input1.value = 10
      }
    }
  })

  const user = userStore()
  async function rechargemoney() {
    try {
      if (input1.value === undefined) {
        ElMessage.error('金额为空，无法充值')
        return
      }
      await recharge({ username: user.username, money: input1.value as number })
      await getuser(user.username)
    } catch {}
  }
</script>
