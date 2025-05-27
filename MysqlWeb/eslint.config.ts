import { globalIgnores } from 'eslint/config'
import { defineConfigWithVueTs, vueTsConfigs } from '@vue/eslint-config-typescript'
import pluginVue from 'eslint-plugin-vue'
import skipFormatting from '@vue/eslint-config-prettier/skip-formatting'

// To allow more languages other than `ts` in `.vue` files, uncomment the following lines:
// import { configureVueProject } from '@vue/eslint-config-typescript'
// configureVueProject({ scriptLangs: ['ts', 'tsx'] })
// More info at https://github.com/vuejs/eslint-config-typescript/#advanced-setup

export default defineConfigWithVueTs(
  {
    name: 'app/files-to-lint',
    files: ['**/*.{ts,mts,tsx,vue}'],
  },

  globalIgnores(['**/dist/**', '**/dist-ssr/**', '**/coverage/**']),

  pluginVue.configs['flat/essential'],
  vueTsConfigs.recommended,
  skipFormatting,
  {
    files: ['**/*.vue'],
    rules: {
      'max-len': [
        'error',
        {
          code: 100, // 每行代码最多 80 字符
          ignoreUrls: true, // 忽略 URL（通常较长）
          ignoreStrings: true, // 忽略字符串（如长文本）
          ignoreTemplateLiterals: true, // 忽略模板字符串
          ignoreRegExpLiterals: true, // 忽略正则表达式
        },
      ],
      'vue/html-indent': ['error', 2], // 模板2空格缩进
      'vue/script-indent': ['error', 2, { baseIndent: 1 }],
      'vue/multi-word-component-names': 'warn',
      'vue/html-closing-bracket-newline': [
        'error',
        {
          singleline: 'never', // 对于单行元素，闭合括号前不允许有换行
          multiline: 'always', // 对于多行元素，闭合括号前必须要有换行
        },
      ],
    },
  },
  {
    ignores: [
      // 忽略所有工程配置文件
      '**/*.config.js', // 如 webpack.config.js, vite.config.js
      '**/module/**', // 忽略 module 目录
      '**/config/**', // 忽略 config 目录
      '**/build/**', // 忽略 build 目录
      '**/dist/**', // 忽略 dist 目录
      '**/node_modules/**', // 默认忽略 node_modules
      '**/src/assets/**',
    ],
  },
  {
    files: ['**/*.js', '**/*.ts'],
    rules: { indent: ['error', 2] },
  },
)
