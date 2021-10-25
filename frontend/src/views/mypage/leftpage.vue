<template>
  <div class="left-wrap">
    <div class="mypage-left">
      <div class="profile-wrap">
        <img class="profile-img" src="../../assets/malang.png" />
        <span class="user-name text-h4 text-bold">박디두</span>
      </div>
      <div class="mypage-menu text-h6">
        <div @click="mvProfile">프로필</div>
        <div @click="mvChart">차트</div>
        <div @click="mvBoard">나의 게시글</div>
        <div @click="mvAlarm">알림</div>
      </div>
    </div>
  </div>
</template>
<script>
import { useRouter } from "vue-router";
import "../../styles/mypage.scss";

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
