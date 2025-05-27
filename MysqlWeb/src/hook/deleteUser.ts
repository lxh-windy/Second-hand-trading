import { DeleteUser } from '@/api'
import { ElMessage } from 'element-plus'

async function deleteUser(username: string): Promise<void> {
  try {
    const response = await DeleteUser(username)
    if (response.data.status === 200) {
      ElMessage.success('删除成功')
    } else {
      ElMessage.error('删除失败')
    }
  } catch {
    ElMessage.error('系统错误')
  }
}

export { deleteUser }
