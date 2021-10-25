<template>
  <div>
    프로필
    <q-btn @click="mvModify">프로필 수정</q-btn>
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
