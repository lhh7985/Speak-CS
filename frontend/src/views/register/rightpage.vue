<template>
  <div class="right-wrap">
    <div class="register-wrap">
      <q-form
        class="q-gutter-md form-wrap"
        ref="regist_form"
        @submit="onSubmit"
        @reset="onReset"
      >
        <q-input
          filled
          v-model="state.form.name"
          :rules="state.rules.name"
          lazy-rules
          label="성함 *"
        />
        <q-input
          filled
          v-model="state.form.nickName"
          :rules="state.rules.nickName"
          lazy-rules
          label="닉네임 *"
        />
        <q-btn flat style="color: #ff0080" primary label="중복확인"></q-btn>

        <q-input
          filled
          v-model="state.form.pass"
          :rules="state.rules.pass"
          type="password"
          label="비밀번호 *"
        />

        <q-input
          filled
          v-model="state.form.passcheck"
          :rules="state.rules.passcheck"
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
        <q-btn color="amber" label="이메일인증" @click="check()"></q-btn>

        <div>
          <q-btn color="primary" type="submit" label="회원가입" />
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
    <complete-dialog
      v-model="state.regist_complete"
      @mvlogin="mvLogin"
    ></complete-dialog>
  </div>
</template>
<script>
import "../../styles/register.scss";
import { ref, reactive } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { useQuasar } from "quasar";

import CompleteDialog from "./components/complete-dialog.vue";

export default {
  name: "register-right",
  components: {
    CompleteDialog,
  },
  setup() {
    const regist_form = ref(null);
    const store = useStore();
    const router = useRouter();
    const quasar = useQuasar();
    const state = reactive({
      emailduplicate: true,
      regist_complete: false,
      form: {
        name: "",
        nickName: "",
        nickName_check: false,
        pass: "",
        passcheck: "",
        email: "",
        email_check: false,
      },
      rules: {
        name: [
          (val) => val != null || "필수입력 항목입니다.",
          (val) => val.length > 0 || "필수입력 항목입니다.",
        ],
        nickName: [
          (val) => val != null || "필수입력 항목입니다.",
          (val) =>
            (val.length >= 2 && val.length <= 16) ||
            "2~16자리의 닉네임을 설정해주세요.",
        ],
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
        email: [
          (val) => (val !== null && val.length > 0) || "필수입력 항목입니다.",
          (val) => isValidEmail(val) || "이메일형식에 맞지 않습니다.",
        ],
      },
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

    /*ㅡㅡㅡㅡㅡ 버튼 ㅡㅡㅡㅡㅡ*/
    const onSubmit = () => {
      regist_form.value.validate().then((success) => {
        if (success) {
          console.log("ㅋㅋ하 ㅋㅋ");
          store
            .dispatch("root/requestUserRegist", {
              name: state.form.name,
              nickName: state.form.nickName,
              pass: state.form.pass,
              email: state.form.email,
            })
            .then(
              (response) => {
                console.log(response);
                state.regist_complete = true;
              },
              (error) => {
                console.log(error.response.data);
                emailError(error.response.data);
              }
            )
            .catch((error) => {
              console.log(error);
            });
        }
      });
    };
    const onReset = () => {
      state.form.name = null;
      state.form.nickName = null;
      state.form.email = null;
      state.form.pass = null;
      state.form.passcheck = null;
    };

    const check = () => {
      alert("hi");
    };
    /*ㅡㅡㅡㅡㅡ 다이얼로그 ㅡㅡㅡㅡㅡ*/
    const emailError = (data) => {
      quasar
        .dialog({
          title: "이메일 중복",
          message: data.message,
        })
        .onOk(() => {
          console.log("OK");
        })
        .onCancel(() => {
          console.log("Cancel");
        })
        .onDismiss(() => {
          console.log("I am triggered on both OK and Cancel");
        });
    };

    /*ㅡㅡㅡㅡㅡ MoVe ㅡㅡㅡㅡㅡ*/
    const mvLogin = () => {
      router.push({ name: "login" });
    };

    return {
      regist_form,
      state,
      /* 버튼 */
      onSubmit,
      onReset,
      check,
      /* 다이얼로그 */
      emailError,
      /* move */
      mvLogin,
    };
  },
};
</script>
