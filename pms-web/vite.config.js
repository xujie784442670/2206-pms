import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [vue({
        reactivityTransform: true
    })],
    server: {
        port: 80,
        open: true
    },
    resolve: {
        alias: {
            '@': '/src',
            '@assets': '/src/assets',
            '@components': '/src/components',
            '@views': '/src/views',
            '@router': '/src/router',
            '@store': '/src/store',
            '@utils': '/src/utils',
            '@api': '/src/api',
            '@mock':'/src/mock'
        }
    }
})
