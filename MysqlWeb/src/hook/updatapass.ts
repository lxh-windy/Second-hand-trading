import { UpdatePass, type UserMessage } from '@/api'
import { ElMessage } from 'element-plus'

async function updatapass(message: UserMessage) {
  try {
    const reponse = await UpdatePass(message)
    if (reponse.status === 200) {
      if (reponse.data.status === 200) {
        ElMessage.success('修改成功')
      } else {
        ElMessage.error('修改失败')
      }
    }
  } catch {
    ElMessage.error('系统错误')
  }
}

export { updatapass }
