export default {
    path:'/:any(.*)*',
    name:'Error',
    component: () => import('@/views/error/Error.vue')
}
