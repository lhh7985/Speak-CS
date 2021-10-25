<template lang="">
  <div>
    <q-form @submit="onSubmit" @reset="onReset" class="q-gutter-md">
      <q-input
        filled
        v-model="name"
        label="Your name *"
        hint="이메일 *"
        lazy-rules
        :rules="[(val) => (val && val.length > 0) || 'Please type something']"
      />

      <q-input
        filled
        type="number"
        v-model="age"
        label="비밀번호 *"
        lazy-rules
        :rules="[
          (val) => (val !== null && val !== '') || 'Please type your age',
          (val) => (val > 0 && val < 100) || 'Please type a real age',
        ]"
      />

      <div>
        <q-btn label="로그인" type="submit" color="primary" />
        <q-btn
          label="초기화"
          type="reset"
          color="primary"
          flat
          class="q-ml-sm"
        />
      </div>
    </q-form>
    <FindPwDialog
      v-model="state.findpwdialog"
      @closefindpwdialog="closeFindPwDialog"
    ></FindPwDialog>
  </div>
</template>
<script>
import { reactive } from "vue";
// import { useRouter } from "vue-router";
import FindPwDialog from "./components/findpw.vue";
export default {
  name: "login-right",
  components: {
    FindPwDialog,
  },
  setup() {
    const state = reactive({
      findpwdialog: false, // 모달생성, 삭제 컨트롤
    });

    /*ㅡㅡㅡㅡㅡ 비밀번호 찾기 모달 제어 ㅡㅡㅡㅡㅡ*/
    const closeFindPwDialog = () => {
      state.findpwdialog = false;
    };

    return {
      state,
      closeFindPwDialog,
    };
  },
};
</script>
<style lang=""></style>
