package com.example.lab_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import com.example.lab_4.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.lab_4.fragments.GroupListFragment
import com.example.lab_4.fragments.StudentsFragment.Companion.newInstance


class MainActivity : AppCompatActivity() {
    public var timeInSeconds = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container1, GroupListFragment.newInstance())
                .commitNow()
        }

    }

}