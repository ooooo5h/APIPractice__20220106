package com.neppplus.apipractice_20220106

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.neppplus.apipractice_20220106.api.APIList
import com.neppplus.apipractice_20220106.api.ServerAPI
import com.neppplus.apipractice_20220106.models.BasicResponse
import com.neppplus.apipractice_20220106.utils.ContextUtil
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {

//            입력한 이메일과 비밀번호를 변수에 저장
            val inputEmail = edtEmail.text.toString()
            val inputPw = edtPassword.text.toString()

//            실제 로그인 기능 호출(Request를 보낸다)
            apiList.postRequestLogin(inputEmail, inputPw).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    
//                    로그인 성공/실패던, 응답 자체가 돌아온 경우
//                    서버가 정상 동작한다는 뜻 
                    
//                    성공/실패 경우가 나뉨
                    if (response.isSuccessful){
//                        로그인 까지 성공 (아이디와 비밀번호 모두 일치)
//                        Toast.makeText(this@MainActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                        
//                        ~~님 환영합니다라는 토스트를 출력하려면, 로그인한 사람의 닉네임을 추출해야함
                        
                        val br = response.body()!!   // 서버의 응답 중에 본문(body)을 자동 분석된 BasicResponse형태로 저장
                        val loginUserNick = br.data.user.nick_name

                        Toast.makeText(this@LoginActivity, "${loginUserNick}님, 환영합니다!", Toast.LENGTH_SHORT).show()

//                        로그인 성공 : 서버가 토큰을 발급해서내려준다
//                        => 본인이 누군지 알려줘야하는 API들은 토큰값이 필요함 => 다른 (거의 모든)화면에'도' 전달해야한다
//                        => 사용하는 기기의 보조 기억장치에 아예 기록해두고, 필요 화면에서는 보조기억장치에서 꺼내다쓰는 걸로 처리하자
                        
//                        보조기억장치에 토큰값 저장하는 기능 실행
                        ContextUtil.setToken(mContext, br.data.token)


//                        메인 화면으로 이동
                        val myIntent = Intent(mContext, MainActivity::class.java)
                        startActivity(myIntent)

                        finish()
                        
                    }
                    else {
//                        로그인 실패 (아이디 or 비밀번호 불일치)
                        Toast.makeText(this@LoginActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                    
//                    서버 자체가 동작을 안한다는 뜻(서버 연결 자체가 실패)
                    
                }

            })

        }

        btnSignUp.setOnClickListener {
//            단순 화면 이동만 구현
            val myIntent = Intent(this, SignUpActivity::class.java)
            startActivity(myIntent)

        }

    }
}