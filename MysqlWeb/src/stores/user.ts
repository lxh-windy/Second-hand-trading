import { defineStore } from 'pinia'
import { ref } from 'vue'

export const userStore = defineStore('user', () => {
  const username = ref<string>('')
  const pass = ref<string>('')
  const money = ref<number>()
  return { username, pass, money }
})
