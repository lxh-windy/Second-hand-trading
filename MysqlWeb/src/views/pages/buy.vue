<template>
  <div class="contain">
    <table>
      <thead>
        <tr>
          <th>购买物品</th>
          <th>价格</th>
          <th>时间</th>
        </tr>
      </thead>
      <tbody v-for="item in list" :key="item.gid">
        <tr>
          <td>{{ item.gname }}</td>
          <td>{{ item.price }}</td>
          <td>{{ item.time.split('T')[0] + ' ' + item.time.split('T')[1] }}</td>
        </tr>
      </tbody>
    </table>
    <el-pagination
      layout="prev, pager, next,jumper"
      :total="total"
      :page-size="15"
      v-model="Page"
      @current-change="changePage"
    />
  </div>
</template>

<script lang="ts" setup>
  import { onBeforeMount, ref } from 'vue'
  import { userStore } from '@/stores/user.ts'
  import { getAllBuy } from '@/hook/buy.ts'

  interface tabledata {
    gid: string
    gname: string
    price: number
    time: string
  }
  const user = userStore()
  const list = ref<Array<tabledata>>([])
  const Page = ref<number>(1)
  const total = ref<number>(1)

  onBeforeMount(async () => {
    try {
      const response = await getAllBuy(localStorage.getItem('username') as string, Page.value)
      const data = response.data
      list.value = data.list
      total.value = data.total
      Page.value = data.pageNum
    } catch {}
  })

  async function changePage(val: number) {
    try {
      Page.value = val
      const response = await getAllBuy(user.username, Page.value)
      const data = response.data
      list.value = data.list
      total.value = data.total
      Page.value = data.pageNum
    } catch {}
  }
</script>

<style scoped>
  .contain {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-top: 20px;
  }
  table {
    border-collapse: collapse;
    width: 70%;
    margin-bottom: 15px;
    border: 2px solid black;
  }
  table th {
    width: 30%;
    border-right: 2px solid black;
    border-bottom: 2px solid black;
    font-size: 23px;
  }
  table td {
    text-align: center;
    width: 30%;
    border: 1px solid black;
    font-size: 20px;
  }
  :deep(.number) {
    font-size: 20px;
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
</style>
