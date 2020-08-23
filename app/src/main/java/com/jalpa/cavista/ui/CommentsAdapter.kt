package com.jalpa.cavista.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jalpa.cavista.R
import com.jalpa.cavista.db.CommentsEntity
import com.jalpa.cavista.ui.CommentsAdapter.*
import kotlinx.android.synthetic.main.commets.view.*

class CommentsAdapter : RecyclerView.Adapter<CommentHolder> {

    private var allComments: MutableList<CommentsEntity>  = mutableListOf()

    constructor(allComments: MutableList<CommentsEntity>) {
       this.allComments = allComments
        Log.e("allcomments",this.allComments.size.toString())
    }

    fun addData(comment: CommentsEntity){
        Log.e(" add allcomments1",this.allComments.size.toString())
        this.allComments.add(comment)
        Log.e(" add allcomments2",this.allComments.size.toString())

        this.notifyItemInserted(allComments.size-1)

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CommentHolder {
        val from = LayoutInflater.from(p0.context)
        val inflate = from.inflate(R.layout.commets, p0, false)
        return CommentHolder(v = inflate)
    }

    override fun onBindViewHolder(commentHolder: CommentHolder, position: Int) {
        commentHolder.itemView.comment.text= allComments[position].comment
    }


    override fun getItemCount(): Int = allComments.size

    class CommentHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        private var commentView: TextView = v.findViewById(R.id.comment)

    }

}
