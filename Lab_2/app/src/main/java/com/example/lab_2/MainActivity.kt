package com.example.lab_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ArrayAdapter




class MainActivity : AppCompatActivity() {
    companion object{
        val department = StaticDataSourse.getDepartment();
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, getGroupsNumberFromEntities(department.groups))
        groups.adapter = adapter;
    }

    fun showStudentsInGroup(view: android.view.View) {
        department.groups.forEach{
            if(it.number == groups.selectedItem){
                var textToOutput:String = "";
                for (student in it.students) {
                    textToOutput +=student.name + '\n';
                }
                textView.text = textToOutput;
                return;
            }
        }
    }
    fun getGroupsNumberFromEntities(groups: List<Group>): MutableList<Int>{
        var data = mutableListOf<Int>()
            groups.forEach{
            data.add(it.number)
        }
        return data;
    }
}