import { type rechargeMessage, Recharge } from '@/api'
import { ElMessage } from 'element-plus'

async function recharge(message: rechargeMessage) {
  try {
    const reponse = await Recharge(message)
    const data = reponse.data
    if (data.status === 200) {
      ElMessage.success('充值成功')
    } else {
      ElMessage.error('充值失败')
    }
  } catch {
    ElMessage.error('系统出错')
  }
}

export { recharge }
