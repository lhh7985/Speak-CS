<!-- App.vue -> login.vue -> login-nav.vue -->

<template>
  <div>
    <q-btn unelevated flat class="menu-btn" @click="mvInfo">소개</q-btn>
    <q-btn unelevated flat class="menu-btn">문제풀기</q-btn>
    <q-btn unelevated flat class="menu-btn" @click="mvBoard">게시판</q-btn>
    <q-btn unelevated flat class="menu-btn" @click="mvMypage">마이페이지</q-btn>
    <q-btn unelevated flat class="menu-btn" @click="mvLogout">로그아웃</q-btn>
  </div>
</template>

<script>
import { useRouter } from "vue-router";
// import "../styles/cover.scss";

export default {
  name: "login-nav",
  setup(props, { emit }) {
    const router = useRouter();
    const mvInfo = () => {
      router.push({ name: "info" });
    };
    const mvLogout = () => {
      alert("로그아웃 될거에요");
      router.push({ name: "logout-cover" });
      emit("logout");
    };
    const mvMypage = () => {
      router.push({ name: "mypage-profile" });
    };
    const mvBoard = () => {
      router.push({ name: "board-info" });
    };
    return {
      mvInfo,
      mvLogout,
      mvMypage,
      mvBoard,
    };
  },
};
</script>
