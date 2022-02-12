package com.infojunks.eatoestestapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
    )

    private fun checkEmail(email: String): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

    fun login(view: View) {
        val queue = Volley.newRequestQueue(this)
        val url = "https://reqres.in/api/login"
        var name = ""
        var token = ""
        val text = findViewById<TextView>(R.id.textView4)
        var email = text.toString()
        val text2 = findViewById<TextView>(R.id.textView5)
        var password = text2.toString()
        if (checkEmail(email)) {
            Toast.makeText(applicationContext, "Email is not valid", Toast.LENGTH_SHORT).show()
        } else {
            val jsonObjectRequest : JsonObjectRequest = object : JsonObjectRequest(Request.Method.POST, url,null, { response ->

                try{
                    name = response.getString("name")
                    token = response.getString("token")
                }
                catch(e:JSONException){
                    e.printStackTrace()
                }
            }, { error ->
                Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show()
            }) {

                override fun getParams(): Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["email"] = email
                    params["password"] = password
                    return params
                }
            }

            queue.add(jsonObjectRequest)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            if (!name.isEmpty()) {
                intent.putExtra("name", name)
                intent.putExtra("token", token)
            }
        }
    }
}