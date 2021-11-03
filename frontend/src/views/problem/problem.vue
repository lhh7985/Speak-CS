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
  name: "problem",
  components: {
    LeftPage,
    RightPage,
  },
  setup() {
    return {};
  },
};
</script>
<style lang=""></style>
