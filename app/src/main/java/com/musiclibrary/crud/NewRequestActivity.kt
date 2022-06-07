package com.musiclibrary.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.musiclibrary.crud.db.AppDatabase
import com.musiclibrary.crud.model.Request
import kotlinx.android.synthetic.main.activity_new_composition.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewRequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_composition)

        var compId: Int? = null

        if (intent.hasExtra("request")) {
            val producto = intent.extras?.getSerializable("request") as Request

            compName.setText(producto.Name)
            compTelephone.setText(producto.Telephone.toString())
            compProblemNew.setText(producto.Problem)
            compSolve.setText(producto.Solve)
            compId = producto.RequestId
        }

        val database = AppDatabase.getDatabase(this)

        save_btn.setOnClickListener {
            val name = compName.text.toString()
            val artist = compTelephone.text.toString()
            val source = compProblemNew.text.toString()
            val solve = compSolve.text.toString()

            val composition = Request(name, artist, source, solve)

            if (compId != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    composition.RequestId = compId

                    database.requests().update(composition)

                    this@NewRequestActivity.finish()
                }
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    database.requests().insertAll(composition)

                    this@NewRequestActivity.finish()
                }
            }
        }
    }
}