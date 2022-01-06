package com.neppplus.apipractice_20220106

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.neppplus.apipractice_20220106.api.APIList
import com.neppplus.apipractice_20220106.api.ServerAPI
import com.neppplus.apipractice_20220106.models.BasicResponse
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btnSignUp.setOnClickListener {

//            입력한 아이디 ~ 연락처 추출
            val inputEmail = edtEmail.text.toString()
            val inputPw = edtPassword.text.toString()
            val inputNickname = edtNickname.text.toString()
            val inputPhoneNum = edtPhone.text.toString()

//            추가된 회원가입 기능 활용
            apiList.putRequestSignUp(inputEmail, inputPw, inputNickname, inputPhoneNum).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

//                    회원가입 요청을 날리고, DB에 가입까지 진행하고 난 후 서버가 내려준 응답이 돌아왔을 때 실행하는 부분(나중에 실행되는 부분이란 이야기)
//                    code : 200만 성공 => response.isSuccessful => 코드값이 200인가와 똑같은 질문
                    if (response.isSuccessful) {

//                        BasicResponse형태로 자동 분석된 응답은 200으로 돌아왔을대만 정상작동함
                        val br = response.body()!!   // BasicResponse를 추출

//                        회원가입 성공 처리만 br변수이용해서 진행하면 됨
//                        이미 같은 구조에 대한 분석을 클래스와 변수들로 만들어두었음
//                        분석이 끝났으니 변수들을 가져다 사용만 하면됨
                        val signUpNickname = br.data.user.nick_name
                        Toast.makeText(this@SignUpActivity, "${signUpNickname}님 회원가입을 축하드립니다.", Toast.LENGTH_SHORT).show()

                        finish()   // 로그인 화면으로 복귀시키기

                    }
                    else {
//                        400, 403, 404, 500 등 모든 에러가 여기 해당

//                        문제가 발생했기 때문에, BasicResponse 정상 응답 제공 x
                        val testStr = response.errorBody()!!.string()    // 에러의 경우는, 별도로 에러 본문 확인해야함
                        Log.d("문제응답", testStr)
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }


            })
        }

        btnEmailCheck.setOnClickListener {

            val inputEmail = edtEmail.text.toString()

            apiList.getRequestDuplCheck("EMAIL", inputEmail).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                    if (response.isSuccessful) {
//                        code : 200으로 성공(사용가능)
                        txtEmailCheckResult.text = "사용해도 좋은 이메일입니다."
                    }
                    else {
//                        그 외 : 사용하면 안되는 이메일
                        txtEmailCheckResult.text = "중복된 이메일입니다. 다시 입력해주세요."
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }


            })
        }
    }
}