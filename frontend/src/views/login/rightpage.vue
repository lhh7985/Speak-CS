<template lang="">
  <div class="right-wrap">
    <q-form
      class="q-gutter-md"
      ref="login_form"
      @submit="onSubmit"
      @reset="onReset"
    >
      <q-input
        ref="email"
        v-model="state.form.email"
        :rules="state.rules.email"
        lazy-rules
        type="email"
        label="이메일"
        autofocus
      >
      </q-input>
      <q-input
        dense
        v-model="state.form.pass"
        :rules="state.rules.pass"
        lazy-rules
        type="password"
        label="비밀번호"
        @keyup.enter="
          login();
          $event.target.blur();
        "
      >
      </q-input>
      <div>
        <q-btn
          color="primary"
          type="submit"
          :disable="state.login_btn_disalbe"
          label="로그인"
        />
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
import "../../styles/cover.scss";

export default {
  name: "login-right",
  components: {
    FindPwDialog,
  },
  methods: {},
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
        email: [
          (val) => (val != null && val !== "") || "필수입력 항목입니다.",
          (val) => isValidEmail(val) || "이메일형식에 맞지 않습니다.",
        ],
        pass: [(val) => (val != null && val !== "") || "필수입력 항목입니다."],
      },
      login_btn_disalbe: true, // 버튼 활성, 비활성화
      findpwdialog: false, // 모달생성, 삭제 컨트롤
    });
    /*ㅡㅡㅡㅡㅡ 검증 ㅡㅡㅡㅡㅡ*/
    const isValidEmail = (val) => {
      // eslint-disable-next-line
      const emailPattern = /^[a-zA-Z0-9]+@[a-zA-Z]+\.[a-zA-Z]{2,4}$/;
      return emailPattern.test(val) || "이메일 형식에 맞지 않습니다.";
    };

    watch(
      () => [state.form.email, state.form.pass],
      () => {
        login_form.value.validate().then((success) => {
          if (success) {
            state.login_btn_disalbe = false;
          } else {
            state.login_btn_disalbe = true;
          }
        });
      }
    );
    /*ㅡㅡㅡㅡㅡ 버튼 ㅡㅡㅡㅡㅡ*/
    const onSubmit = () => {
      /* axios */
    };
    const onReset = () => {
      state.form.email = null;
      state.form.pass = null;
      state.login_btn_disalbe = true;
    };
    /*ㅡㅡㅡㅡㅡ 비밀번호 찾기 모달 제어 ㅡㅡㅡㅡㅡ*/
    const closeFindPwDialog = () => {
      state.findpwdialog = false;
    };

    return {
      login_form,
      state,
      /* 검증 */
      isValidEmail,
      /* 제출 */
      onSubmit,
      onReset,
      /* 모달 */
      closeFindPwDialog,
    };
  },
};
</script>
<style lang=""></style>
