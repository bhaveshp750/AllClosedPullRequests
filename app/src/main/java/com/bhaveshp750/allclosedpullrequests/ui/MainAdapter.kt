package com.bhaveshp750.allclosedpullrequests.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhaveshp750.allclosedpullrequests.R
import com.bhaveshp750.allclosedpullrequests.modal.PullRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MainAdapter(
    private val pullRequestList: ArrayList<PullRequest>
) : RecyclerView.Adapter<MainAdapter.PullRequestItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PullRequestItemViewHolder (
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )

    override fun onBindViewHolder(holder: PullRequestItemViewHolder, position: Int) {
        holder.title.text = pullRequestList[position].title
        holder.createdDate.text = pullRequestList[position].created_date
        holder.closedDate.text = pullRequestList[position].closed_date
        holder.userName.text = pullRequestList[position].user.name
        holder.userImage.loadImage(pullRequestList[position].user.avatar_url)
    }

    override fun getItemCount(): Int {
        return pullRequestList.size
    }

    fun updateList(newList: List<PullRequest>) {
        pullRequestList.clear()
        pullRequestList.addAll(newList)
        notifyDataSetChanged()
    }

    class PullRequestItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val createdDate: TextView = view.findViewById(R.id.created_date)
        val closedDate: TextView = view.findViewById(R.id.closed_date)
        val userName: TextView = view.findViewById(R.id.user_name)
        val userImage: ImageView = view.findViewById(R.id.user_image)
    }

}

fun ImageView.loadImage(uri: String?) {
    val options = RequestOptions()
        .placeholder(R.drawable.progress_animation)
        .error(R.drawable.ic_error)

    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}