package com.example.week4.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week4.R
import com.example.week4.ui.db.Task
import com.example.week4.ui.db.TaskResponse

class TaskAdapter(val tasks : List<TaskResponse>, val callBack : (TaskResponse) -> Unit ) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_task,parent,false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task= tasks[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int = tasks.size

    inner class TaskViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val description = itemView.findViewById<AppCompatTextView>(R.id.textViewDescription)
        val check = itemView.findViewById<ImageView>(R.id.imageViewTask)

        fun bind(task : TaskResponse){
            description.text = task.task.description

            itemView.setOnClickListener {
                callBack.invoke(task)
            }

            check.setOnClickListener {
                check.setImageResource(R.drawable.ic_success_outline)
            }
        }
    }
}