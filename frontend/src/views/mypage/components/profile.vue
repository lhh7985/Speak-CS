<template>
  <div class="profile-right-wrap">
    프로필
    <q-btn flat @click="mvModify" class="modify-btn">프로필 수정</q-btn>
  </div>
</template>
<script>
import { useRouter } from "vue-router";

export default {
  name: "profile",
  setup() {
    const router = useRouter();
    const mvModify = () => {
      router.push({ name: "mypage-modify" });
    };
    return {
      mvModify,
    };
  },
};
</script>
