package com.musiclibrary.crud.db
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.musiclibrary.crud.R
import com.musiclibrary.crud.model.Request
import kotlinx.android.synthetic.main.activity_composition.view.*



class RequestAdapter(private val mContext: Context, private val listaProductos: List<Request>) : ArrayAdapter<Request>(mContext, 0, listaProductos) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_composition, parent, false)

        val request = listaProductos[position]

        layout.compName.text = request.Name
        layout.compTelephone.text = request.Telephone

        return layout
    }
}