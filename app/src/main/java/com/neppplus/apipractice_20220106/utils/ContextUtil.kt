package com.neppplus.apipractice_20220106.utils

import android.content.Context

class ContextUtil {
    
    // 단순한 기능을 수행할 때 Context(화면단)를 필요로 하는 기능들을 모아두는 클래스
    // 단순 기능은, 어떤 객체(인스턴스)가 하던 관계 없다
    // ex) 랜덤값 뽑기는 1번이 하던 2번이 하던 어느 객체던 결과만 랜덤으로 잘 나오면 된다
    // 객체에 상관없이 클래스이름.기능() 형태로 코딩하는 게 낫다
    
    companion object {
        
//        일종의 메모장 (SharedPreferences)에 여러 항목들을 저장할 예정
//        => 메모장 파일의 이름같은 개념이 필요하다 => 변수에 담아두자
        
//        메모장 이름
        val PREF_NAME = "APIPracticePref"
        
//        항목의 키값도 변수에 담아두자(오타 방지용)
//        저장 기능 / 조회 기능 2개가 필요  ==> 둘의 키 값 통일 필요
        val TOKEN = "TOKEN"
        
        fun setToken(context : Context, token : String) {
            val pref =context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)   // 메모장 파일 열었음

//          pref.edit().putString(TOKEN, token)   // String 하나 추가 기록 메모 작성
            pref.edit().putString(TOKEN, token).apply()  // 저장(apply)
        }

        fun getToken(context: Context) : String {
            val pref =context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)   // 메모장 파일 열었음
            return pref.getString(TOKEN, "")!!   // 코틀린에서의 null관리 기법 : getString이 절대 null이 아니라고 우겨서 return해야 오류가 없어짐


        }
    }
    
}