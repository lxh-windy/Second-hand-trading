<template>
  <div class="contain">
    <table>
      <thead>
        <tr>
          <th>商品名</th>
          <th>卖家名</th>
          <th>价格</th>
          <th>当前状态</th>
          <th>时间</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody v-for="item in list" :key="item.gid">
        <tr>
          <td>{{ item.gname }}</td>
          <td>{{ item.uid }}</td>
          <td>{{ item.price }}</td>
          <td>{{ item.status === '0' ? '在售' : '已售出' }}</td>
          <td>{{ item.time.split('T')[0] + ' ' + item.time.split('T')[1] }}</td>
          <td>
            <button v-if="item.status === '0'" @click="delgood(item.gid)">强制下架</button>
            <p v-else>无</p>
          </td>
        </tr>
      </tbody>
    </table>
    <el-pagination
      layout="prev, pager, next,jumper"
      :total="total"
      :page-size="11"
      v-model="Page"
      @current-change="changePage"
    />
  </div>
</template>

<script lang="ts" setup>
  import { onBeforeMount, ref } from 'vue'
  import { getAllBuyMessages } from '@/hook/AllBuyMessage.ts'
  import { deletegood } from '@/hook/sold.ts'

  interface tabledata {
    gid: string
    gname: string
    price: number
    status: string
    time: string
    uid: string
  }
  const list = ref<Array<tabledata>>([])
  const Page = ref<number>(1)
  const total = ref<number>(0)

  onBeforeMount(async () => {
    try {
      const res = await getAllBuyMessages(Page.value)
      if (res !== null) {
        const data = res.data
        list.value = data.list
        total.value = data.total
        Page.value = data.pageNum
      }
    } catch {}
  })

  async function changePage(val: number) {
    Page.value = val
    try {
      const res = await getAllBuyMessages(Page.value)
      if (res !== null) {
        const data = res.data
        list.value = data.list
        total.value = data.total
        Page.value = data.pageNum
      }
    } catch {}
  }

  async function delgood(gid: string) {
    try {
      await deletegood(gid)
    } catch {
    } finally {
      const res = await getAllBuyMessages(Page.value)
      if (res !== null) {
        const data = res.data
        list.value = data.list
        total.value = data.total
        Page.value = data.pageNum
      }
    }
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
    table-layout: fixed;
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
