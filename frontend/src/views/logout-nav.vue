<!-- App.vue -> login.vue -> logout-nav.vue -->

<template>
  <div>
    <q-btn unelevated flat class="menu-btn">소개</q-btn>
    <q-btn unelevated flat class="menu-btn">회원가입</q-btn>
    <q-btn unelevated flat class="menu-btn" @click="MvMainLogin">로그인</q-btn>
  </div>
</template>
<script>
import { useRouter } from "vue-router";
import "../styles/cover.scss";

export default {
  name: "logout-nav",
  setup(props, { emit }) {
    const router = useRouter();
    const MvMainLogin = () => {
      alert("로그인 될거에요");
      router.push({ name: "login-cover" });
      emit("login");
    };
    return {
      MvMainLogin,
    };
  },
};
</script>
<style lang=""></style>
