<template lang="">
  <q-dialog>
    <q-card>
      <q-card-section>
        <div>박디두님 안녕하세요</div>
        <div>회원가입이 완료되었습니다!</div>
        <div>CS에 오신것을 환영합니다</div>
        <q-btn flat style="color: #ff0080" @click="mvLogin">로그인하기</q-btn>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>
<script>
export default {
  name: "register-complete-dialog",
  setup(props, { emit }) {
    const mvLogin = () => {
      emit("mvlogin");
    };
    return {
      mvLogin,
    };
  },
};
</script>
<style lang=""></style>
