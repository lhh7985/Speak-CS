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
        path: "/info",
        name: "logout-info",
        component: () => import("../views/logout-info/info.vue"),
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
        path: "/home/info",
        name: "login-info",
        component: () => import("../views/login-info/info.vue"),
      },
      {
        path: "/home/mypage",
        name: "mypage",
        component: () => import("../views/mypage/mypage.vue"),
        children: [
          {
            path: "/home/mypage",
            name: "mypage-chart",
            component: () => import("../views/mypage/components/chart.vue"),
          },
          {
            path: "/home/mypage/board",
            name: "mypage-board",
            component: () => import("../views/mypage/components/board.vue"),
          },
          {
            path: "/home/mypage/alarm",
            name: "mypage-alarm",
            component: () => import("../views/mypage/components/alarm.vue"),
          },
          {
            path: "/home/mypage/modify",
            name: "mypage-modify",
            component: () => import("../views/mypage/components/modify.vue"),
          },
          {
            path: "/home/mypage/prob",
            name: "mypage-prob",
            component: () => import("../views/mypage/components/prob.vue"),
          },
        ],
      },
      {
        path: "/home/problem",
        name: "problem",

        component: () => import("../views/problem/problem.vue"),
        children: [
          {
            path: "/home/problem",
            name: "problem-info",
            component: () => import("../views/problem/components/info.vue"),
          },
          {
            path: "/home/problem/description",
            name: "problem-description",
            props: true,
            component: () =>
              import("../views/problem/components/description.vue"),
          },
          {
            path: "/home/problem/solve",
            name: "problem-solve",
            component: () => import("../views/problem/components/solve.vue"),
            // children: [{}],
          },
          {
            path: "/home/problem/result",
            name: "problem-result",
            component: () => import("../views/problem/components/result.vue"),
          },
        ],
      },
      {
        path: "/home/board",
        name: "board",
        component: () => import("../views/board/board.vue"),
        children: [
          {
            path: "/home/board",
            name: "board-info",
            component: () => import("../views/board/components/boardinfo.vue"),
          },
          {
            path: "/home/board/question",
            name: "board-question",
            component: () => import("../views/board/components/question.vue"),
          },
        ],
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
