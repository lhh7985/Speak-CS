<template lang="">
  <div class="book-wrap">
    <rightPage></rightPage>
  </div>
</template>

<script>
import rightPage from "@/views/rightpage.vue";

export default {
  name: "main",
  components: {
    rightPage,
  },
};
</script>
