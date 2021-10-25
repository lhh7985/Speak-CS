<!-- App.vue -> login.vue -> logout-nav.vue -->

<template>
  <div>
    <q-btn unelevated flat class="menu-btn">소개</q-btn>
    <q-btn unelevated flat class="menu-btn" @click="mvRegister">회원가입</q-btn>
    <q-btn unelevated flat class="menu-btn" @click="mvLogin">로그인</q-btn>
  </div>
</template>
<script>
import { useRouter } from "vue-router";
// import "../styles/cover.scss";

export default {
  name: "logout-nav",
  setup() {
    const router = useRouter();
    const mvLogin = () => {
      alert("로그인!");
      router.push({ name: "login-cover" });
    };
    const mvRegister = () => {
      router.push({ name: "register" });
    };
    return {
      mvLogin,
      mvRegister,
    };
  },
};
</script>
<style lang=""></style>
