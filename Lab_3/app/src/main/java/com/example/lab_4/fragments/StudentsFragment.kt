package com.example.lab_4.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab_4.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_group_list.*
import kotlinx.android.synthetic.main.fragment_group_list.view.*
import kotlinx.android.synthetic.main.fragment_students.*
import kotlinx.android.synthetic.main.fragment_students.view.*
import kotlinx.android.synthetic.main.fragment_students.view.button
import kotlinx.android.synthetic.main.fragment_students.view.textView

private const val param = "param1"

class StudentsFragment : Fragment() {
    private var textToOutput: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            textToOutput = it.getString(param)!!
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_students, container, false)
        view.textView.text = textToOutput
        view.button.setOnClickListener { v ->
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_SUBJECT, "Student list")
            intent.putExtra(Intent.EXTRA_TEXT, textToOutput)
            intent.type = "message/rfc822"
            startActivity(Intent.createChooser(intent, "Select email"))
        }
        view.goBackButton.setOnClickListener{
            v ->
                loadFragment(GroupListFragment.newInstance())
        }
        view.doubleButton.setOnClickListener{
                v ->
            view.textView.textSize = view.textView.textSize * 1.1f;
        }
        return view


    }
    private fun loadFragment(fragment: Fragment){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container1, fragment)
        transaction?.disallowAddToBackStack()
        transaction?.commit()

    }
    companion object {
        fun newInstance(param1: String) =
            StudentsFragment().apply {
                arguments = Bundle().apply {
                    putString(param, param1)
                }
            }
    }
}