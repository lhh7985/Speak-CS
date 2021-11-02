<template lang="">
  <q-dialog>
    <q-card>
      <q-card-section>
        <q-form
          class="q-gutter-md form-wrap"
          ref="updatepw_form"
          @submit="onSubmit"
          @reset="onReset"
        >
          <q-input
            v-model="state.form.pass"
            :rules="state.rules.pass"
            type="password"
            label="비밀번호 *"
          />

          <q-input
            v-model="state.form.passcheck"
            :rules="state.rules.passcheck"
            type="password"
            label="비밀번호 확인 *"
          />
          <div class="submit-btns">
            <q-btn color="primary" type="submit" label="비밀번호변경" />
            <q-btn
              class="q-ml-sm"
              flat
              color="primary"
              type="reset"
              label="초기화"
            />
          </div>
        </q-form>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>
<script>
import { ref, reactive } from "vue";
export default {
  name: "login-updatepw",
  setup(props, { emit }) {
    const updatepw_form = ref(null);
    const state = reactive({
      form: {
        pass: "",
        passcheck: "",
      },
      rules: {
        pass: [
          (val) => val !== null || "필수입력 항목입니다.",
          (val) =>
            isValidPass(val) ||
            "영문, 숫자, 특수문자를 포함한 8자리 이상의 비밀번호를 생성해주세요!",
        ],
        passcheck: [
          (val) => (val !== null && val.length > 0) || "필수입력 항목입니다.",
          (val) => isValidPassCheck(val) || "입력한 비밀번호와 맞지 않습니다.",
        ],
      },
    });

    /*ㅡㅡㅡㅡㅡ 검증 ㅡㅡㅡㅡㅡ*/
    const isValidPass = (val) => {
      const passPattern =
        /^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
      return passPattern.test(val);
    };
    const isValidPassCheck = (val) => {
      if (state.form.pass != val) {
        console.log(state.form.pass, val);
        return false;
      } else {
        return true;
      }
    };
    /*ㅡㅡㅡㅡㅡ 버튼 ㅡㅡㅡㅡㅡ*/
    const onSubmit = () => {
      emit("mvlogin");
    };
    const onReset = () => {
      state.form.pass = null;
      state.form.passcheck = null;
    };

    return {
      updatepw_form,
      state,
      onSubmit,
      onReset,
    };
  },
};
</script>
<style lang=""></style>
