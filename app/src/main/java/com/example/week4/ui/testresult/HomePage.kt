package com.example.week4.ui.testresult
import android.os.Bundle
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.example.week3.BaseFragment
import com.example.week4.R
import com.example.week4.service.BaseCallBack
import com.example.week4.service.ServiceConnector
import com.example.week4.ui.adapter.TaskAdapter
import com.example.week4.ui.db.TaskResponse
import com.example.week4.utils.gone
import com.example.week4.utils.toast
import com.example.week4.utils.visible
import kotlinx.android.synthetic.main.home_page.*

class HomePage : BaseFragment() {
    override fun getLayoutID(): Int = R.layout.home_page

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareView()
        getTasks()
        updateTask()
    }

    private fun getTasks() {
        ServiceConnector.restInterface.getTaskByPagination()
            .enqueue(object : BaseCallBack<List<TaskResponse>>()
        {
            override fun onSuccess(data: List<TaskResponse>) {
                //super.onSuccess(data)
                toast("Success.")
                populateData(data)
            }

            override fun onFailure() {
                super.onFailure()
                noText.visible()
                toast("Server failure.")
            }
        })
    }

    private fun populateData(tasks: List<TaskResponse>) {
        val taskAdapter = TaskAdapter(tasks){
            toast ("${it.id}")
        }

        recyclerview_task.adapter = taskAdapter
        visibleData(tasks)
    }

    private fun visibleData(list : List<TaskResponse>){
        if(list.isNullOrEmpty()){
            recyclerview_task.gone()
            noText.visible()
        }
    }

    private fun showDialog(callback: taskDialogCallback){
        val md  = MaterialDialog(requireContext())
        md.title(R.string.Task)
        md.input()
        md.message(R.string.enterTask)
        md.positiveButton(R.string.Ok){ callback.proceed() }
        md.negativeButton(R.string.Cancel){ callback.cancel() }
        md.show()
    }


    interface taskDialogCallback{
        fun proceed()
        fun cancel()
    }

    private fun updateTask(){
        floatingActionButton.setOnClickListener {
            val callBack = object : taskDialogCallback{
                override fun proceed() {

                    /// must be POST updateTask
                    /// I got server error...
                    toast("Task is updated.")
                }

                override fun cancel() {
                    toast("Cancelled from dialog.")
                }
            }
            showDialog(callBack)
        }
    }
}
