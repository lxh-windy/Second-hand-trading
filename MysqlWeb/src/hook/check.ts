import { Check } from '@/api'
import { ElMessage } from 'element-plus'
import router from '@/router'
import { AxiosError } from 'axios'

async function check() {
  try {
    await Check()
  } catch (error) {
    console.log(error)
    if (error instanceof AxiosError) {
      if (error.status !== 403) ElMessage.error('系统出错')
      await router.replace('/login')
    }
  }
}

export { check }
