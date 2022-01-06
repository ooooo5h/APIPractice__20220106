package com.neppplus.apipractice_20220106.api

import retrofit2.Retrofit

class ServerAPI {

//    서버 통신을 하게 도와주는 클래스(Retrofit)를 객체로 만들어주는 기능을 가진 클래스
//    함수 한번만 호출하면, 어디서든 편하게 가져다 쓰일 수 있게 함수로 만들어두기
//    클래스이름.기능()  => @classmethod에 대응되는 기능을 활용하려고 하는 것

    companion object {
//        이 companion object {}안에 적는 변수와 함수들은, ServerAPI.변수/함수명 으로 바로 활용(노출)이 가능하다

//        val BASE_URL = "https://api.gudoc.in"

        private val BASE_URL = "https://api.gudoc.in"  //이 변수는 노출안되고, 이 클래스 내부에서만 사용할 예정 -> private 처리
        private var retrofit : Retrofit? = null  // 서버 연결을 전담하는 변수. 기본적으로는 아직 안만든 상태기때문에 null대입

//        retrofit 변수에 환경설정 + 객체화 => 가져다 쓸 수 있게 하는 기능을 함수로 제공
        fun getRetrofit() : Retrofit {





        }

    }

}