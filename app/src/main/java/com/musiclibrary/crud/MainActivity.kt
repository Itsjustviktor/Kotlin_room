package com.musiclibrary.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.musiclibrary.crud.db.AppDatabase
import com.musiclibrary.crud.db.RequestAdapter
import com.musiclibrary.crud.model.Request
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var requests = emptyList<Request>()

        val database = AppDatabase.getDatabase(this)

        database.requests().getAll().observe(this, Observer {
            requests = it

            val adapter = RequestAdapter(this, requests)

            compositionsList.adapter = adapter
        })

        compositionsList.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, RequestActivity::class.java)
            intent.putExtra("id", requests[position].RequestId)
            startActivity(intent)
        }

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, NewRequestActivity::class.java)
            startActivity(intent)
        }
    }
}