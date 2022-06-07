package com.musiclibrary.crud

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.musiclibrary.crud.db.AppDatabase
import com.musiclibrary.crud.model.Request
import kotlinx.android.synthetic.main.activity_composition.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RequestActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var request: Request
    private lateinit var compLiveData: LiveData<Request>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_composition)

        database = AppDatabase.getDatabase(this)

        val compId = intent.getIntExtra("id", 0)

        compLiveData = database.requests().get(compId)

        compLiveData.observe(this, Observer {
            request = it

            compName.text = request.Name
            compTelephone.text = request.Telephone
            compProblem.text = request.Problem
            compSolve1.text = request.Solve
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.composition_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit_item -> {
                val intent = Intent(this, NewRequestActivity::class.java)
                intent.putExtra("request", request)
                startActivity(intent)
            }

            R.id.delete_item -> {
                compLiveData.removeObservers(this)

                CoroutineScope(Dispatchers.IO).launch {
                    database.requests().delete(request)
                    this@RequestActivity.finish()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }
}