<!-- App.vue -> login.vue -> cover.vue -->

<template>
  <div>
    <LeftPage></LeftPage>
    <RightPage></RightPage>
  </div>
</template>
<script>
import LeftPage from "./leftpage.vue";
import RightPage from "./rightpage.vue";
// import "../../styles/cover.scss";

export default {
  name: "login-cover",
  components: {
    LeftPage,
    RightPage,
  },
};
</script>
<style lang=""></style>
