package com.neppplus.apipractice_20220106

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.neppplus.apipractice_20220106.adapters.ReviewAdapter
import com.neppplus.apipractice_20220106.models.BasicResponse
import com.neppplus.apipractice_20220106.models.ReviewData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {

    val mReviewList = ArrayList<ReviewData>()

    lateinit var mReviewAdapter : ReviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiList.getRequestAllReview().enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful) {

                    val br = response.body()!!

//                    서버가 주는 응답안에 보면, data 안의 reviews 목록 전체를 mReviewList에 추가해주고싶다
                    mReviewList.addAll( br.data.reviews)

//                    리싸이클러뷰 세팅이 완료된 이후에 데이터를 추가한다면, 자동반영 안됨.
//                    어댑터의 내용이 변경되었으니까 반영해달라는 코드를 추가로 작성해야함
                    mReviewAdapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }


        })

        mReviewAdapter = ReviewAdapter(mContext, mReviewList)
        reviewRecyclerView.adapter = mReviewAdapter
        reviewRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }
}