package com.neppplus.apipractice_20220106

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neppplus.apipractice_20220106.models.BasicResponse
import com.neppplus.apipractice_20220106.models.ReviewData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {

    val mReviewList = ArrayList<ReviewData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiList.getRequestAllReview().enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful) {

                    val br = response.body()!!

//                    서버가 주는 응답안에 보면, data 안의 reviews 목록 전체를 mReviewList에 추가해주고싶다
                    mReviewList.addAll( br.data.reviews)

                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }


        })
    }
}