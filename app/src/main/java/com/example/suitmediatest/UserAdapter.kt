package com.example.suitmediatest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmediatest.retrofit.User

class UserAdapter(private val users: MutableList<User>, private val onUserClickListener: (User) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarImageView: ImageView = itemView.findViewById(R.id.avatarImageView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val emailTextView: TextView = itemView.findViewById(R.id.emailTextView) // Ensure this ID exists in your layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .into(holder.avatarImageView)
        holder.nameTextView.text = "${user.first_name} ${user.last_name}"
        holder.emailTextView.text = user.email  // Set the email text

        holder.itemView.setOnClickListener {
            onUserClickListener(user)
        }
    }

    override fun getItemCount(): Int = users.size

    fun addUsers(newUsers: List<User>) {
        val currentSize = users.size
        users.addAll(newUsers)
        notifyItemRangeInserted(currentSize, newUsers.size)
    }

    fun clearUsers() {
        users.clear()
        notifyDataSetChanged()
    }
}
