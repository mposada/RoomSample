package com.example.miguelposada.roomwordsample.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.miguelposada.roomwordsample.R
import com.example.miguelposada.roomwordsample.data.Word
import kotlinx.android.synthetic.main.recyclerview_item.view.*


class WordAdapter: RecyclerView.Adapter<WordAdapter.ViewHolder>() {

    companion object {
        var words: List<Word>? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(itemView = view)
    }

    override fun getItemCount(): Int {
        words?.let {
            return it.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        words?.let {
            holder.setData(it[position])
        }
    }

    fun setWords(data: List<Word>) {
        words = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(word: Word) {
            itemView.textView.text = word.mWord
        }
    }

}