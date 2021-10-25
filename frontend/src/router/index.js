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
        component: () => import("../views/logout/cover.vue"),
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
        component: () => import("../views/login/cover.vue"),
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
