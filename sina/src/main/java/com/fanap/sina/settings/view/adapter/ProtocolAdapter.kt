package com.fanap.sina.settings.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.fanap.sina.R
import com.fanap.sina.databinding.SpinnerItemBinding
import java.util.ArrayList

class ProtocolAdapter(private val context: Context) : BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val data: MutableList<String>
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): String {
        return data[position]
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.spinner_item, parent, false)
            holder = ViewHolder(convertView)
            convertView!!.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        val item = data[position]

        //holder.binding.textItem.setTypeface(Typeface.createFromAsset(mContext.getAssets(),"fonts/iransans.ttf"));
        holder.binding.textItem.text = item
        return convertView
    }

    inner class ViewHolder(v: View) {
        var binding: SpinnerItemBinding = SpinnerItemBinding.bind(v)

    }

    init {
        data = ArrayList()
        data.add("http")
        data.add("https")
    }
}
