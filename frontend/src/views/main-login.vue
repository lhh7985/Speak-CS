<template lang="">
  <div class="book-wrap">
    <!-- <q-btn @click="changeLogin">로그인아웃</q-btn> -->
    <leftPage v-if="state.islogin == true"></leftPage>
    <rightPage></rightPage>
  </div>
</template>

<script>
import { reactive, onBeforeMount } from "vue";
import leftPage from "@/views/leftpage.vue";
import rightPage from "@/views/rightpage.vue";

export default {
  name: "main",
  components: {
    leftPage,
    rightPage,
  },
  setup() {
    const state = reactive({
      islogin: null,
    });

    onBeforeMount(() => {
      state.islogin = true;
      console.log(state.islogin);
    });

    const changeLogin = () => {
      state.islogin ? (state.islogin = false) : (state.islogin = true);
      console.log(state.islogin);
    };
    return {
      state,
      onBeforeMount,
      changeLogin,
    };
  },
};
</script>
