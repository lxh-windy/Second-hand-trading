import { GetAllBuy } from '@/api'
import { ElMessage } from 'element-plus'

async function getAllBuy(username: string, pageNum: number) {
  try {
    const response = await GetAllBuy(username, pageNum)
    if (response.data.status === 200) {
      return response.data
    } else {
      ElMessage.error('查询失败')
      return Promise.reject()
    }
  } catch {
    ElMessage.error('系统错误')
    return Promise.reject()
  }
}

export { getAllBuy }
