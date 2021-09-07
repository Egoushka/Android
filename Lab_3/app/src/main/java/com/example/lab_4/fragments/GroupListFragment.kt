package com.example.lab_4.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.lab_4.Department
import com.example.lab_4.MainActivity
import com.example.lab_4.R
import com.example.lab_4.StaticDataSourse
import com.example.lab_4.fragments.StudentsFragment.Companion.newInstance
import kotlinx.android.synthetic.main.fragment_group_list.*
import kotlinx.android.synthetic.main.fragment_group_list.view.*

class GroupListFragment : Fragment() {
    private lateinit var outputLabel:TextView
    private var timeToOutput:Long = 0

    var interval = 1000L
    private var mHandler: Handler? = null

    companion object {
        fun newInstance() =
            GroupListFragment().apply {
                arguments = Bundle().apply {
                }
            }

        val department: Department = StaticDataSourse.getDepartment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timeToOutput = (activity as MainActivity).timeInSeconds
    }
    override fun onStop() {
        super.onStop()
        (activity as MainActivity).timeInSeconds = timeToOutput
        stopTimer()
    }
    private fun startTimer() {
        mHandler = Handler(Looper.getMainLooper())

        mStatusChecker.run()
    }
    private fun stopTimer() {
        mHandler?.removeCallbacks(mStatusChecker)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_group_list, container, false)
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
        outputLabel = view.timer
        startTimer();

        return view
    }
    private var mStatusChecker: Runnable = object : Runnable {
        override fun run() {
            try {
                timeToOutput += 1000
                updateTextUI()
            } finally {
                mHandler!!.postDelayed(this, interval)
            }
        }
    }
    private fun updateTextUI() {
        val minute = (timeToOutput / 1000) / 60
        val seconds = (timeToOutput / 1000) % 60

        outputLabel.text = "$minute:$seconds";
    }
    private fun loadFragment(fragment: Fragment){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container1, fragment)
        transaction?.disallowAddToBackStack()
        transaction?.commit()

    }

}