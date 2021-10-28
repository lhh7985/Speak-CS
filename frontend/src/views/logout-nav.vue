<!-- App.vue -> login.vue -> logout-nav.vue -->

<template>
  <div>
    <div>
      <q-btn
        unelevated
        flat
        id="menuBtn1"
        class="menu-btn menu-btn-cover menus click-menu"
        @click="mvLogoutCover"
        >커버</q-btn
      >
    </div>
    <div>
      <q-btn
        unelevated
        flat
        id="menuBtn2"
        class="menu-btn menus"
        @click="mvLogoutInfo"
        >소개</q-btn
      >
      <q-btn
        unelevated
        flat
        id="menuBtn3"
        class="menu-btn menus"
        @click="mvRegister"
        >회원가입</q-btn
      >
      <q-btn
        unelevated
        flat
        id="menuBtn4"
        class="menu-btn menus"
        @click="mvLogin"
        >로그인</q-btn
      >
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
      removeColor();
      addColor("menuBtn1");
      router.push({ name: "logout-cover" });
    };
    const mvLogoutInfo = () => {
      removeColor();
      addColor("menuBtn2");
      router.push({ name: "logout-info" });
    };
    const mvRegister = () => {
      removeColor();
      addColor("menuBtn3");
      router.push({ name: "register" });
    };
    const mvLogin = () => {
      removeColor();
      addColor("menuBtn4");
      router.push({ name: "login" });
    };
    const removeColor = () => {
      const menus = document.getElementsByClassName("menus");
      for (var i = 0; i < menus.length; i++) {
        menus[i].classList.remove("click-menu");
      }
    };
    const addColor = (id) => {
      const btn = document.getElementById(id);
      btn.classList.add("click-menu");
    };

    return {
      mvLogoutCover,
      mvLogoutInfo,
      mvRegister,
      mvLogin,
    };
  },
};
</script>
