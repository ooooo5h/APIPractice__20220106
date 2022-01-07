package com.neppplus.apipractice_20220106.adapters

import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neppplus.apipractice_20220106.R
import com.neppplus.apipractice_20220106.models.ProductData
import com.neppplus.apipractice_20220106.models.ReviewData
import com.neppplus.apipractice_20220106.models.StoreData
import org.w3c.dom.Text
import java.text.NumberFormat


class ProductAdapter(
    val mContext: Context,
    val mList: List<ProductData>,
) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    inner class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {

        val imgProduct = row.findViewById<ImageView>(R.id.imgProduct)
        val txtProductName = row.findViewById<TextView>(R.id.txtProductName)
        val txtProductPrice = row.findViewById<TextView>(R.id.txtProductPrice)
        val imgStoreLogo = row.findViewById<ImageView>(R.id.imgStoreLogo)
        val txtStoreName = row.findViewById<TextView>(R.id.txtStoreName)


        fun bind(data: ProductData) {

            txtProductName.text = data.name
            txtStoreName.text = data.store.name
            txtProductPrice.text = "${NumberFormat.getInstance().format(data.price)}원"
            Glide.with(mContext).load(data.imageURL).into(imgProduct)
            Glide.with(mContext).load(data.store.logoURL).into(imgStoreLogo)





       }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val row = LayoutInflater.from(mContext).inflate(R.layout.product_list_item, parent, false)

        return MyViewHolder(row)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.bind(mList[position])

    }

    override fun getItemCount(): Int {

        return mList.size

    }

}