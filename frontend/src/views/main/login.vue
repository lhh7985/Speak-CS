<template lang="">
  <div>
    <LoginNav class="nav-wrap"></LoginNav>
    <router-view class="book-wrap"></router-view>
  </div>
</template>

<script>
import LoginNav from "@/views/login-nav.vue";
export default {
  name: "main-login",
  components: {
    LoginNav,
  },
};
</script>
