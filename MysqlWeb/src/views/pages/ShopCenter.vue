<template>
  <div class="contain">
    <div class="head"><button @click="dialogVisible = true">新建交易</button></div>
    <div style="margin-bottom: 12px">
      <div class="item" v-for="good in goodList" :key="good.gid">
        <header>
          <span>商品名：{{ good.gname }}</span>
          <br />
          <span>价格：{{ good.price }}元</span>
        </header>
        <footer>
          <el-button @click="buyGood(good.gid as string)">购买</el-button>
        </footer>
      </div>
    </div>
    <el-pagination
      layout="prev, pager, next,jumper"
      :total="total"
      :page-size="5"
      @current-change="handleCurrentChange"
      :current-page="Page"
    />
    <el-dialog v-model="dialogVisible" title="新建交易" width="500">
      <div class="dialog">
        <div class="name">商品名:<input type="text" v-model="goodname" /></div>
        <div class="price">
          价格:<input type="number" min="0" style="width: 5em" v-model="price" />
        </div>
      </div>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="addgood"> 确认 </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<style scoped>
  .head {
    display: flex;
    justify-content: right;
    width: 800px;
  }
  .head button {
    height: 40px;
    width: 70px;
    border-radius: 5px;
    background-color: rgba(250, 134, 16, 0.18);
  }
  .contain {
    display: flex;
    align-items: center;
    flex-direction: column;
  }
  .item {
    border: 1px solid red;
    border-radius: 15px;
    width: 800px;
    height: 130px;
    margin-top: 10px;
  }
  .item header {
    height: 70%;
    width: 50%;
    font-size: 20px;
    padding-left: 100px;
    padding-top: 10px;
  }
  .item footer {
    text-align: right;
    height: 20%;
    width: 100%;
    padding-right: 20px;
  }
  :deep(.number) {
    font-size: 21px;
  }
  :deep(.el-pagination__jump span) {
    font-size: 16px;
  }
  :deep(.el-pagination__jump input) {
    font-size: 16px;
  }
  :deep(.el-pager li.is-active) {
    color: white;
    background-color: rgba(239, 135, 9, 0.84);
  }
  :deep(.el-pager li:hover) {
    color: #fa8610;
  }
  :deep(.el-pager li) {
    background-color: rgba(111, 114, 115, 0.06);
    margin-right: 5px;
    margin-left: 5px;
  }
  :deep(.el-pagination button) {
    background-color: rgba(111, 114, 115, 0.06);
  }
  :deep(.el-pagination button:hover) {
    color: #fa8610;
  }
  .dialog {
    font-size: 25px;
  }
  .name input {
    font-size: 20px;
    margin-left: 15px;
  }
  .price input {
    font-size: 20px;
    margin-left: 2em;
  }
</style>

<script setup lang="ts">
  import { onBeforeMount, ref } from 'vue'
  import type { goodsMessage } from '@/api'
  import { addgoods, getAllgoods, shopping } from '@/hook/ShopCenter.ts'
  import { userStore } from '@/stores/user.ts'
  import { ElMessage } from 'element-plus'
  import { getuser } from '@/hook/Login.ts'
  interface PageResponse {
    pageNum: number
    total: number
    list: goodsMessage[]
  }

  const dialogVisible = ref<boolean>(false)
  const total = ref(1000)
  const Page = ref<number>(1)
  //商品列表
  const goodList = ref<Array<goodsMessage>>([])
  onBeforeMount(async () => {
    try {
      const response = await getAllgoods(Page.value)
      if (response !== null) {
        const data: PageResponse = response.data
        goodList.value = data.list
        total.value = data.total
        Page.value = 1
      }
    } catch {}
  })
  //新增商品
  const goodname = ref<string>('')
  const price = ref<number>(0)
  const user = userStore()
  async function addgood() {
    if (goodname.value === '') {
      ElMessage.error('商品名为空,无法添加')
      return
    }
    try {
      await addgoods({ gname: goodname.value, price: price.value, username: user.username })
      const res = await getAllgoods(Page.value)
      if (res !== null) {
        const data: PageResponse = res.data
        goodList.value = data.list
        total.value = data.total
        Page.value = data.pageNum
        goodname.value = ''
        price.value = 0
        dialogVisible.value = false
      }
    } catch {}
  }
  //分页
  async function handleCurrentChange(val: number) {
    Page.value = val
    try {
      const res = await getAllgoods(Page.value)
      if (res !== null) {
        const data: PageResponse = res.data
        goodList.value = data.list
        total.value = data.total
        Page.value = data.pageNum
        goodname.value = ''
        price.value = 0
        dialogVisible.value = false
      }
    } catch {}
  }

  //购买
  async function buyGood(gid: string) {
    try {
      await shopping(user.username, gid)
      await getuser(user.username)
    } catch {
    } finally {
      const res = await getAllgoods(Page.value)
      if (res !== null) {
        const data: PageResponse = res.data
        goodList.value = data.list
        total.value = data.total
        Page.value = data.pageNum
        goodname.value = ''
        price.value = 0
        dialogVisible.value = false
      }
    }
  }
</script>
