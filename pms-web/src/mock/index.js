
export default {
    install(app){
        if(import.meta.env.VITE_MOCK_ENABLE === 'true'){
            import('@mock/LoginApi.mock.js')
        }
    }
}
