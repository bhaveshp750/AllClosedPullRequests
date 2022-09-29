package com.bhaveshp750.allclosedpullrequests.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhaveshp750.allclosedpullrequests.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressView: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        recyclerView = findViewById(R.id.recycler_view)
        progressView = findViewById(R.id.progress_view)
        val statusTextView: TextView = findViewById(R.id.status_text_view)

        val mainAdapter = MainAdapter(arrayListOf())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mainAdapter

        viewModel.pullRequestList.observe(this) { pullRequestList ->
            pullRequestList?.let {
                if(it.isNotEmpty()) {
                    statusTextView.visibility = View.GONE
                    mainAdapter.updateList(it)
                }else{
                    statusTextView.visibility = View.VISIBLE
                    statusTextView.text = "No Pull Requests yet."
                }
            }
        }

        viewModel.pullRequestLoadError.observe(this) { isError ->
            isError?.let {
                if (it) {
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
                    statusTextView.text = "Something went wrong! Please Try Again."
                    statusTextView.visibility = View.VISIBLE
                }
            }
        }

        viewModel.loading.observe(this) { isLoading ->
            isLoading?.let {
                progressView.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }
}