<template lang="">
  <div>
    <q-btn flat style="color: #ff0080" label="움직이기!" @click="MvMainLogin" />
    <q-btn flat style="color: #ff0080" label="여긴 로그아웃이얌" />
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
      router.push("/login");
      emit("login");
    };
    return {
      MvMainLogin,
    };
  },
};
</script>
<style lang=""></style>
