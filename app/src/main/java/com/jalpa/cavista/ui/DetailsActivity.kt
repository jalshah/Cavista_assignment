package com.jalpa.cavista.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.jalpa.cavista.R
import com.jalpa.cavista.db.CommentsEntity
import com.jalpa.cavista.db.DataBaseClient
import com.jalpa.cavista.model.ImagesResponse
import kotlinx.android.synthetic.main.details_activity.*
import kotlinx.android.synthetic.main.details_activity.empty_msg


class DetailsActivity : AppCompatActivity() {

    private var allComments = mutableListOf<CommentsEntity>()
    private lateinit var adapter :CommentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.details_activity)

        val imageObject : ImagesResponse? = intent?.extras?.getParcelable<ImagesResponse>("imageObject");
        collapse_toolbar.title= imageObject?.title
        toolbar.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.e("OnClick","1111called");
                onBackPressed()
            }
        })
        Glide.with(this).load(imageObject?.images?.get(0)?.link).centerCrop().into(image)

        allComments = DataBaseClient.getInstance(this).getAppDatabase().CommentsDao().getAllComments(imageObject?.id)

        submit_btn.setOnClickListener {
            var entity = CommentsEntity()
            entity.id = imageObject?.id.toString()
            entity.comment = n_comment.text.toString()

            DataBaseClient.getInstance(this).appDatabase.CommentsDao().saveComment(entity)

            adapter = p_comments.adapter as CommentsAdapter
            adapter.addData(entity)
            p_comments.scrollToPosition(allComments.size-1)
            empty_msg.visibility=View.GONE
        }

        p_comments.apply {

            adapter = CommentsAdapter(allComments)
            p_comments.adapter = adapter
        }

        if (allComments.size>0){
            empty_msg.visibility=View.GONE
            p_comments.scrollToPosition(allComments.size-1)
        } else{
            empty_msg.visibility=View.VISIBLE
        }

    }

}



