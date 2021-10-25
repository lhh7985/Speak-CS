import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/",
    name: "main-logout",
    component: () => import("../views/main/logout.vue"),
    children: [
      {
        path: "/",
        name: "logout-cover",
        component: () => import("../views/logout-cover/cover.vue"),
      },
      {
        path: "/login",
        name: "login",
        component: () => import("../views/login/login.vue"),
      },
      {
        path: "/register",
        name: "register",
        component: () => import("../views/register/register.vue"),
      },
    ],
  },
  {
    path: "/home",
    name: "main-login",
    component: () => import("../views/main/login.vue"),
    children: [
      {
        //children의 맨 처음 상위 페이지는, 부모의 path를 따라가야함
        path: "/home",
        name: "login-cover",
        component: () => import("../views/login-cover/cover.vue"),
      },
      {
        path: "/home/mypage",
        name: "mypage",
        component: () => import("../views/mypage/mypage.vue"),
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
