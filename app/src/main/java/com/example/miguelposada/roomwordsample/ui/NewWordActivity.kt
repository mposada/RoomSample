package com.example.miguelposada.roomwordsample.ui

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.miguelposada.roomwordsample.R
import kotlinx.android.synthetic.main.activity_new_word.*

class NewWordActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        button_save.setOnClickListener {
            val intent = Intent()
            if (TextUtils.isEmpty(edit_word.text)) {
                setResult(Activity.RESULT_CANCELED, intent)
            } else {
                val word = edit_word.text.toString()
                intent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }
}
