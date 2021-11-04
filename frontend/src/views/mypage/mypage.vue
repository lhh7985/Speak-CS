<!-- App.vue -> login.vue -> mypage.vue -->

<template>
  <div>
    <LeftPage></LeftPage>
    <RightPage></RightPage>
  </div>
</template>
<script>
import LeftPage from "./leftpage.vue";
import RightPage from "./rightpage.vue";
import "../../styles/mypage.scss";

export default {
  name: "mypage",
  components: {
    LeftPage,
    RightPage,
  },
};
</script>
<style lang=""></style>
