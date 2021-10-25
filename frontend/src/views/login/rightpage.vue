<template lang="">
  <div>
    <q-form
      class="q-gutter-md"
      ref="login_form"
      @submit="onSubmit"
      @reset="onReset"
    >
      <q-input
        filled
        v-model="state.form.email"
        :rules="state.rules.email"
        type="email"
        label="이메일 *"
      />

      <q-input
        filled
        v-model="state.form.pass"
        :rules="state.rules.pass"
        label="비밀번호 *"
      />

      <div>
        <q-btn label="로그인" type="submit" color="primary" />
        <q-btn
          class="q-ml-sm"
          label="초기화"
          type="reset"
          color="primary"
          flat
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
import { ref, reactive, watch } from "vue";
// import { useRouter } from "vue-router";
import FindPwDialog from "./components/findpw.vue";
export default {
  name: "login-right",
  components: {
    FindPwDialog,
  },
  setup() {
    const login_form = ref(null); // 로그인폼저장

    const state = reactive({
      form: {
        email: "",
        pass: "",
      },
      rules: {
        /*
        즉변할때
        error-message="Please use maximum 3 characters"
        :error="!isValid"
        return -> isValid: computed(() => model.value.length <= 3)
        */
        email: [(val) => !val || "필수입력 항목입니다.", isValidEmail()],
        pass: [(val) => (val !== null && val !== "") || "필수입력 항목입니다."],
      },
      login_btn_disalbe: true,
      findpwdialog: false, // 모달생성, 삭제 컨트롤
    });
    /*ㅡㅡㅡㅡㅡ 검증 ㅡㅡㅡㅡㅡ*/
    const isValidEmail = (val) => {
      const emailPattern = /^$/;
      return emailPattern.test(val);
    };

    watch(
      () => [state.form.email, state.form.pass],
      () => {
        login_form.value.validate((valid) => {
          if (valid) {
            state.login_btn_disalbe = false;
          }
        });
      }
    );
    /*ㅡㅡㅡㅡㅡ 버튼 ㅡㅡㅡㅡㅡ*/
    const onSubmit = () => {
      login_form.value.validate((valid) => {
        if (valid) {
          console.log(state.form);
        } else {
          console.log("error");
        }
      });
    };
    const onReset = () => {
      state.form.email = null;
      state.form.pass = null;
      state.login_btn_disalbe = false;
    };
    /*ㅡㅡㅡㅡㅡ 비밀번호 찾기 모달 제어 ㅡㅡㅡㅡㅡ*/
    const closeFindPwDialog = () => {
      state.findpwdialog = false;
    };

    return {
      login_form,
      state,
      onSubmit,
      onReset,
      closeFindPwDialog,
    };
  },
};
</script>
<style lang=""></style>
