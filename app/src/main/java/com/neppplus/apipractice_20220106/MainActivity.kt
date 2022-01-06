package com.neppplus.apipractice_20220106

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.neppplus.apipractice_20220106.api.APIList
import com.neppplus.apipractice_20220106.api.ServerAPI
import com.neppplus.apipractice_20220106.models.BasicResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {

//            입력한 이메일과 비밀번호를 변수에 저장
            val inputEmail = edtEmail.text.toString()
            val inputPw = edtPassword.text.toString()

//            서버 API의 로그인 기능에 활용해보기 (ServerAPI 클래스 / APIList 인터페이스결합해서 사용해야함)
            val retrofit = ServerAPI.getRetrofit()   // API 연결 도구 생성
            val apiList = retrofit.create(APIList::class.java)  // 연결도구와 기능 목록을 결합한 객체를 생성함

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

                        Toast.makeText(this@MainActivity, "${loginUserNick}님, 환영합니다!", Toast.LENGTH_SHORT).show()
                        
                        
                    }
                    else {
//                        로그인 실패 (아이디 or 비밀번호 불일치)
                        Toast.makeText(this@MainActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                    
//                    서버 자체가 동작을 안한다는 뜻(서버 연결 자체가 실패)
                    
                }

            })

        }


    }
}