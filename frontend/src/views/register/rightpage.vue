<template>
  <div class="right-wrap">
    <div class="register-wrap">
      <q-form
        class="q-gutter-md form-wrap"
        ref="login_form"
        @submit="onSubmit"
        @reset="onReset"
      >
        <q-input
          filled
          v-model="state.form.name"
          :rules="state.rules.name"
          lazy-rules
          label="성함 *"
          autofocus
        />
        <q-input
          filled
          v-model="state.form.nickname"
          :rules="state.rules.nickname"
          lazy-rules
          label="닉네임 *"
        />
        <q-btn primary label="중복확인"></q-btn>

        <q-input
          filled
          v-model="state.form.pass"
          :rules="state.rules.pass"
          lazy-rules
          type="password"
          label="비밀번호 *"
        />

        <q-input
          filled
          v-model="state.form.passcheck"
          :rules="state.rules.passcheck"
          lazy-rules
          type="password"
          label="비밀번호 확인 *"
        />
        <q-input
          filled
          v-model="state.form.email"
          :rules="state.rules.email"
          lazy-rules
          type="email"
          label="이메일 *"
        />
        <q-btn primary label="이메일인증"></q-btn>

        <div>
          <q-btn
            color="primary"
            type="submit"
            :disable="state.regist_btn_disable"
            label="회원가입"
          />
          <q-btn
            class="q-ml-sm"
            flat
            color="primary"
            type="reset"
            label="초기화"
          />
        </div>
      </q-form>
    </div>
  </div>
</template>
<script>
import { ref, reactive, watch } from "vue";
// import { useRouter } from "vue-router";
import "../../styles/register.scss";

export default {
  name: "register-right",
  setup() {
    const regist_form = ref(null);
    const state = reactive({
      form: {
        name: "",
        nickname: "",
        pass: "",
        passcheck: "",
        email: "",
      },
      rules: {
        name: [(val) => (val !== null && val !== "") || "필수입력 항목입니다."],
        nickname: [
          (val) => (val !== null && val !== "") || "필수입력 항목입니다.",
        ],
        pass: [
          (val) => (val !== null && val !== "") || "필수입력 항목입니다.",
          (val) =>
            isValidPass(val) ||
            "영문, 숫자, 특수문자를 포함한 8자리 이상의 비밀번호를 생성해주세요!",
        ],
        passcheck: [
          (val) => (val !== null && val !== "") || "필수입력 항목입니다.",
          (val) => isValidPassCheck(val) || "입력한 비밀번호와 맞지 않습니다.",
        ],
        email: [
          (val) => (val !== null && val !== "") || "필수입력 항목입니다.",
          (val) => isValidEmail(val) || "이메일형식에 맞지 않습니다.",
        ],
      },
      regist_btn_disable: true,
    });
    /*ㅡㅡㅡㅡㅡ 검증 ㅡㅡㅡㅡㅡ*/
    const isValidEmail = (val) => {
      // eslint-disable-next-line
      const emailPattern = /^[a-zA-Z0-9]+@[a-zA-Z]+\.[a-zA-Z]{2,4}$/;
      return emailPattern.test(val) || "이메일 형식에 맞지 않습니다.";
    };
    const isValidPass = (val) => {
      const passPattern =
        /^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
      return (
        passPattern.test(val) ||
        "영문, 숫자, 특수문자를 포함한 8자리 이상의 비밀번호를 생성해주세요!"
      );
    };
    const isValidPassCheck = (val) => {
      if (state.form.pass != val) {
        console.log(state.form.pass, val);
        return false;
      } else {
        return true;
      }
    };
    watch(
      () => [state.form.pass, state.form.passcheck],
      () => {
        console.log(state.form.pass, state.form.passcheck);
      }
    );

    /*ㅡㅡㅡㅡㅡ 버튼 ㅡㅡㅡㅡㅡ*/
    const onSubmit = () => {
      /* axios */
    };
    const onReset = () => {
      state.form.name = null;
      state.form.nickname = null;
      state.form.email = null;
      state.form.pass = null;
      state.form.passcheck = null;
      state.regist_btn_disable = true;
    };
    return {
      regist_form,
      state,
      /* 검증 */
      /* 제출 */
      onSubmit,
      onReset,
    };
  },
};
</script>
