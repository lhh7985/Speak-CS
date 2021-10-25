<template lang="">
  <div>
    <q-btn>소개</q-btn>
    <q-btn>회원가입</q-btn>
    <q-btn @click="MvMainLogin">로그인</q-btn>
  </div>
</template>
<script>
import { useRouter } from "vue-router";
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
