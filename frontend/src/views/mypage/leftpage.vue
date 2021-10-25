<template>
  <div class="left-wrap">
    <div @click="mvProfile">프로필</div>
    <div @click="mvChart">차트</div>
  </div>
</template>
<script>
import { useRouter } from "vue-router";

export default {
  name: "mypage-left",
  setup() {
    const router = useRouter();
    const mvProfile = () => {
      router.push({ name: "mypage-profile" });
    };
    const mvChart = () => {
      router.push({ name: "mypage-chart" });
    };
    return {
      mvProfile,
      mvChart,
    };
  },
};
</script>
