package com.example.deeplinkgenerator

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button?.setOnClickListener {
            val url = "https://cashwalk.io"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        button_add?.setOnClickListener {
            val url = "https://cashwalk.io/${et_path?.text.toString()}"
            Toast.makeText(this, "go to '$url'", Toast.LENGTH_SHORT).show()
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        tv_intent_info?.text = "https://cashwalk.io\n" +
                "intent.putExtra('source', 'Appboy')\n" +
                "intent.putExtra('uri', 'inner://{PATH}')"

        button_braze?.setOnClickListener {
            val url = "https://cashwalk.io"
            val i = Intent(Intent.ACTION_VIEW)
            i.putExtra("source", "Appboy")
            i.putExtra("uri", "inner://${et_intent_uri?.text.toString()}")
            i.data = Uri.parse(url)
            startActivity(i)
        }

        button_custom?.setOnClickListener {
            val url = "${et_scheme?.text.toString()}://${et_host?.text.toString()}"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            if (i.resolveActivity(packageManager) == null) {
                Toast.makeText(this, "no app available for '$url'", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(i)
            }
        }


        tv_intent_info2?.text = "URI: your custom deep link\n" +
                "intent.putExtra('source', 'Appboy')\n" +
                "intent.putExtra('uri', 'PATH')"

        et_intent_uri2?.run {
            setText("inner://")
            setSelection(8)
        }

        button_custom_braze?.setOnClickListener {
            val url = "${et_scheme?.text.toString()}://${et_host?.text.toString()}"
            val i = Intent(Intent.ACTION_VIEW)
            i.putExtra("source", "Appboy")
            i.putExtra("uri", "${et_intent_uri2?.text.toString()}")
            i.data = Uri.parse(url)
            if (i.resolveActivity(packageManager) == null) {
                Toast.makeText(this, "no app available for '$url'", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(i)
            }
        }
    }
}