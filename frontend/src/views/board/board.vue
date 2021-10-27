<!-- App.vue -> login.vue -> board.vue -->

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
  name: "board",
  components: {
    LeftPage,
    RightPage,
  },
};
</script>
