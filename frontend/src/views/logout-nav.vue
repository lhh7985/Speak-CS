<!-- App.vue -> login.vue -> logout-nav.vue -->

<template>
  <div>
    <div>
      <q-btn
        unelevated
        flat
        class="menu-btn menu-btn-cover"
        @click="mvLogoutCover"
        >커버</q-btn
      >
    </div>
    <div>
      <q-btn unelevated flat class="menu-btn" @click="mvLogoutInfo">소개</q-btn>
      <q-btn unelevated flat class="menu-btn" @click="mvRegister"
        >회원가입</q-btn
      >
      <q-btn unelevated flat class="menu-btn" @click="mvLogin">로그인</q-btn>
    </div>
  </div>
</template>
<script>
import { useRouter } from "vue-router";
// import "../styles/cover.scss";

export default {
  name: "logout-nav",
  setup() {
    const router = useRouter();
    const mvLogoutCover = () => {
      router.push({ name: "logout-cover" });
    };
    const mvLogoutInfo = () => {
      router.push({ name: "logout-info" });
    };
    const mvLogin = () => {
      // alert("로그인!");
      // router.push({ name: "login-cover" });
      router.push({ name: "login" });
    };
    const mvRegister = () => {
      router.push({ name: "register" });
    };
    return {
      mvLogoutCover,
      mvLogoutInfo,
      mvLogin,
      mvRegister,
    };
  },
};
</script>
