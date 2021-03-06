package com.neppplus.apipractice_20220106.fcm

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFCMService : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

//        (앱이 켜진 상태에서만) 실제 푸시알림 신호가 왔을 때 어떤 행동을 하고 싶은지 적어주는 함수

//        p0 : 어떤 알림인지 그 정보를 담고 있다

        val text = p0.notification!!.title!!   // 알림의 제목 추출
        Log.d("푸시알림수신", text)

//        UI담당 쓰레드에게 토스트 요청(할 일 할당) => Handler 이용
        val myHandler = Handler(Looper.getMainLooper())

        myHandler.post {
            Toast.makeText(applicationContext, "text", Toast.LENGTH_SHORT).show()
        }

    }
}