import { ElMessage } from 'element-plus'
import { GetAllBuyMessage } from '@/api'

async function getAllBuyMessages(page: number) {
  try {
    const response = await GetAllBuyMessage(page)
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

export { getAllBuyMessages }
