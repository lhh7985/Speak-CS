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
        v-model="state.form.pass"
        :rules="state.rules.pass"
        label="성함 *"
      />
      <q-input
        filled
        v-model="state.form.pass"
        :rules="state.rules.pass"
        label="닉네임 *"
      />
      <q-btn primary label="중복확인"></q-btn>
      <q-input
        filled
        v-model="state.form.email"
        :rules="state.rules.email"
        type="email"
        label="이메일 *"
      />
      <q-btn primary label="이메일인증"></q-btn>

      <q-input
        filled
        v-model="state.form.pass"
        :rules="state.rules.pass"
        label="비밀번호 *"
      />

      <q-input
        filled
        v-model="state.form.pass"
        :rules="state.rules.pass"
        label="비밀번호 확인 *"
      />

      <div>
        <q-btn primary type="submit" label="로그인" />
        <q-btn class="q-ml-sm" flat primary type="reset" label="초기화" />
      </div>
    </q-form>
  </div>
</template>
<script>
import { ref, reactive } from "vue";
// import { useRouter } from "vue-router";
export default {
  name: "register-right",
  setup() {
    const regist_form = ref(null);
    const state = reactive({
      form: {
        name: "",
        nickname: "",
        email: "",
        pass: "",
        passcheck: "",
      },
      rules: {
        name: [(val) => (val !== null && val !== "") || "필수입력 항목입니다."],
        nickname: [
          (val) => (val !== null && val !== "") || "필수입력 항목입니다.",
        ],
        email: [
          (val) => (val !== null && val !== "") || "필수입력 항목입니다.",
        ],
        pass: [(val) => (val !== null && val !== "") || "필수입력 항목입니다."],
        passcheck: [
          (val) => (val !== null && val !== "") || "필수입력 항목입니다.",
        ],
      },
    });
    return {
      regist_form,
      state,
    };
  },
};
</script>
<style lang=""></style>
