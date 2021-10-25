<template lang="">
  <div>
    <q-btn
      flat
      style="color: #ff0080"
      label="움직이기!"
      @click="MvMainLogout"
    />
    <q-btn flat style="color: #ff0080" label="여긴 로그인이얌" />
  </div>
</template>

<script>
import { useRouter } from "vue-router";
export default {
  name: "login-nav",
  setup(props, { emit }) {
    const router = useRouter();
    const MvMainLogout = () => {
      alert("로그아웃 될거에요");
      router.push({ name: "main-logout" });
      emit("logout");
    };
    return { MvMainLogout };
  },
};
</script>
<style lang=""></style>
