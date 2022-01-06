package com.neppplus.apipractice_20220106.api

import android.util.Log

class ServerAPI {

//    서버 통신을 하게 도와주는 클래스(Retrofit)를 객체로 만들어주는 기능을 가진 클래스
//    함수 한번만 호출하면, 어디서든 편하게 가져다 쓰일 수 있게 함수로 만들어두기
//    클래스이름.기능()  => @classmethod에 대응되는 기능을 활용하려고 하는 것

    companion object {
       fun test() {
           Log.d("서버API클래스", "테스트기능")
       }

    }
}