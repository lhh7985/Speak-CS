<!-- App.vue -> logout.vue -->

<template>
  <div>
    <LogoutNav class="nav-wrap"></LogoutNav>
    <router-view class="book-wrap"></router-view>
  </div>
</template>

<script>
import LogoutNav from "@/views/logout-nav.vue";
export default {
  name: "main-logout",
  components: {
    LogoutNav,
  },
};
</script>
