package com.example.week4.utils
import android.R.attr
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.time.Duration
import android.R.attr.name

import android.content.SharedPreferences

import android.content.Context.MODE_PRIVATE




fun Fragment.toNextFragment(@IdRes id : Int){
    findNavController().navigate(id)
}

fun View.visible(){
    this.visibility = View.VISIBLE
}
fun View.gone(){
    this.visibility = View.GONE
}

fun Fragment.toast(messageToShow : String, duration: Int = Toast.LENGTH_LONG){
    Toast.makeText(requireContext(), messageToShow, duration).show()
}

fun EditText.getString() : String{
    return this.text.toString()
}

fun Fragment.saveDataAsString(key : String, value : String){
    // Storing data into SharedPreferences
    val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("My shared preferences", MODE_PRIVATE)
// Creating an Editor object to edit(write to the file)
    val myEdit = sharedPreferences.edit()
    myEdit.putString(key,value)
// Once the changes have been made,
// we need to commit to apply those changes made,
// otherwise, it will throw an error
    myEdit.commit()


}