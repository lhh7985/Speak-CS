<template lang="">
  <div>
    <LeftPage></LeftPage>
    <RightPage></RightPage>
  </div>
</template>
<script>
import LeftPage from "./leftpage.vue";
import RightPage from "./rightpage.vue";
export default {
  name: "login-cover",
  components: {
    LeftPage,
    RightPage,
  },
};
</script>
<style lang=""></style>
