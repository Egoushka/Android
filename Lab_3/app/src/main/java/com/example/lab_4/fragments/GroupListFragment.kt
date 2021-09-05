package com.example.lab_4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab_4.Department
import com.example.lab_4.MainActivity
import com.example.lab_4.R
import com.example.lab_4.StaticDataSourse
import com.example.lab_4.fragments.StudentsFragment.Companion.newInstance
import kotlinx.android.synthetic.main.fragment_group_list.*
import kotlinx.android.synthetic.main.fragment_group_list.view.*
private const val param = "param1"

class GroupListFragment : Fragment() {
    private var textToOutput = ""
    companion object {
        fun newInstance(param1: String) =
            GroupListFragment().apply {
                arguments = Bundle().apply {
                    putString(param, param1)
                }
            }

        val department: Department = StaticDataSourse.getDepartment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            textToOutput = it.getString(param)!!
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_group_list, container, false)
        view.textView.text = textToOutput
        view.button.setOnClickListener { v ->
            for (it in department.groups) {
                if(it.number.toString().equals(groups.selectedItem)){
                    var textToOutput:String = "";
                    for (student in it.students) {
                        textToOutput +=student.name + '\n';
                    }

                    loadFragment(StudentsFragment.newInstance(textToOutput))
                }
            }
        }

        return view
    }
    private fun loadFragment(fragment: Fragment){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container1, fragment)
        transaction?.disallowAddToBackStack()
        transaction?.commit()

    }

}