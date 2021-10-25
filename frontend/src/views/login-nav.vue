<!-- App.vue -> login.vue -> login-nav.vue -->

<template>
  <div>
    <q-btn unelevated flat class="menu-btn">소개</q-btn>
    <q-btn unelevated flat class="menu-btn">문제풀기</q-btn>
    <q-btn unelevated flat class="menu-btn">게시판</q-btn>
    <q-btn unelevated flat class="menu-btn" @click="mvMypage">마이페이지</q-btn>
    <q-btn unelevated flat class="menu-btn" @click="mvLogout">로그아웃</q-btn>
  </div>
</template>

<script>
import { useRouter } from "vue-router";
import "../styles/cover.scss";

export default {
  name: "login-nav",
  setup(props, { emit }) {
    const router = useRouter();
    const mvLogout = () => {
      alert("로그아웃 될거에요");
      router.push({ name: "logout-cover" });
      emit("logout");
    };
    const mvMypage = () => {
      router.push({ name: "mypage" });
    };
    return {
      mvLogout,
      mvMypage,
    };
  },
};
</script>
<style lang=""></style>
