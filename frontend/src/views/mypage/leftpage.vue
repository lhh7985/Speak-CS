<template>
  <div class="left-wrap">
    <div>
      <div @click="mvProfile">프로필</div>
      <div @click="mvChart">차트</div>
      <div @click="mvBoard">나의 게시글</div>
      <div @click="mvAlarm">알림</div>
    </div>
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
    const mvBoard = () => {
      router.push({ name: "mypage-board" });
    };
    const mvAlarm = () => {
      router.push({ name: "mypage-alarm" });
    };
    return {
      mvProfile,
      mvChart,
      mvBoard,
      mvAlarm,
    };
  },
};
</script>
