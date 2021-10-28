<!-- App.vue -> login.vue -> info.vue -->

<template>
  <div>
    <LeftPage></LeftPage>
    <RightPage></RightPage>
  </div>
</template>
<script>
import LeftPage from "./leftpage.vue";
import RightPage from "./rightpage.vue";

export default {
  name: "info",
  components: {
    LeftPage,
    RightPage,
  },
};
</script>
