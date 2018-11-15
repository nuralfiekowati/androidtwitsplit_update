package com.tweeter.alfi.twitsplit

import android.R.layout.simple_list_item_1
import android.app.SearchManager
import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityTwit : MainActivityTwitI, AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar.setLogo(R.drawable.alientwiticon)
        actionBar.setDisplayUseLogoEnabled(true)
    }

    override fun onStart() {
        super.onStart()
        Log.e("TAG", "onStart is working")
    }

    val toSplit = SplitMessageTwit()

    /**
     * This function will return a list of string obtained from each substring (resulting from the 50 characters of splitting).
     */

    override fun readyToSumbit(postString: String): List<String> {
        val toSplitPost = toSplit.doSplit(postString, 50)
        val messages= toSplitPost.map{
            it
        }

        return messages
    }

    /**
     * When the list of string contains error string, the toast below will appear.
     */

    fun errorToast() {
        val myToast = Toast.makeText(this, "Error! Your sentence contains a span \n" +
                "of non-whitespace characters longer than 50 characters!", Toast.LENGTH_SHORT)
        myToast.show()
    }

    /**
     * This function is used to show (posting) the splitted strings to a list view.
     */

    override fun tweetSubmit(view: View) {
        val postString = plain_text_input.text.toString()
        val goTweet = readyToSumbit(postString)
        val errorString = "ERROR!!Non-whitespace!"
        if (goTweet.contains(errorString)) {
            errorToast()
        } else {
            lv_messages.adapter = ArrayAdapter(this, simple_list_item_1, goTweet)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        Log.d("TAG", "Lalalala4 ")
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
            val search = menu.findItem(R.id.search).actionView as SearchView
            search.setSearchableInfo(manager.getSearchableInfo(componentName))

            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(s: String): Boolean {
                    Log.d("TAG", "onQueryTextSubmit ")
                    return false
                }

                override fun onQueryTextChange(s: String): Boolean {

                    if (s == "") {
                        Log.d("TAG", "onQueryTextChange is Empty ")
                    } else {
                        Log.d("TAG", "onQueryTextChange is not Empty")
                    }

                    return false

                }

            })

        }

        return true
    }

    override fun onResume() {
        super.onResume()
        Log.e("TAG", "onResume is working")
    }

    override fun onPause() {
        super.onPause()
        Log.e("TAG", "onPause is working")
    }

    override fun onStop() {
        super.onStop()
        Log.e("TAG", "onStop is working")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("TAG", "onRestart is working")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("TAG", "onDestroy is working")
    }

}

interface MainActivityTwitI{
    fun readyToSumbit(postString: String): List<String>
    fun tweetSubmit(view: View)
}
