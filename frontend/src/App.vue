<template>
  <div class="all-wrap">
    <LoginNav v-if="state.login" @logout="Logout"></LoginNav>
    <LogoutNav v-else @login="Login"></LogoutNav>
    <router-view></router-view>
  </div>
</template>
<script>
import { ref, reactive } from "vue";
import LoginNav from "@/views/login-nav.vue";
import LogoutNav from "@/views/logout-nav.vue";
export default {
  name: "App",
  components: {
    LoginNav,
    LogoutNav,
  },
  setup() {
    const state = reactive({
      login: ref(false),
    });
    const Login = () => {
      state.login = true;
    };
    const Logout = () => {
      state.login = false;
    };
    return {
      state,
      Login,
      Logout,
    };
  },
};
</script>
<style lang=""></style>
