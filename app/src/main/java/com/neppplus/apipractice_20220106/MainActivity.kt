package com.neppplus.apipractice_20220106

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neppplus.apipractice_20220106.api.APIList
import com.neppplus.apipractice_20220106.api.ServerAPI
import kotlinx.android.synthetic.main.activity_main.*

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
            apiList.postRequestLogin(inputEmail, inputPw)

        }


    }
}