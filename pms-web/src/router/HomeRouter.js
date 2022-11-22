export default {
    path: '/',
    name:'Home',
    component: () => import('@/views/home/Home.vue'),
    children: [
        {
            path: 'account',
            name: 'Account',
            component: () => import('@/views/account/Account.vue')
        },
        {
            path: 'role',
            name: 'Role',
            component: () => import('@/views/role/Role.vue')
        },
        {
            path: 'permission',
            name: 'Permission',
            component: () => import('@/views/permission/Permission.vue')
        }
    ]
}
