package com.example.lostphonealert.ui.theme

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.Toast
import android.view.View
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.lostphonealert.R

class MainActivity : ComponentActivity() {
    //UI Variables
    private lateinit var editText: EditText
    private lateinit var addButton: Button
    private lateinit var listView: ListView
    private lateinit var adapter: CustomAdapter
    private val itemList: MutableList<String> = mutableListOf()

    val contactList: MutableList<Contact> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        addButton = findViewById(R.id.addButton)
        listView = findViewById(R.id.listView)

        // Initialize the custom adapter for the ListView
        adapter = CustomAdapter(this, itemList)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            itemList.removeAt(position)
            adapter.notifyDataSetChanged()
        }

        addButton.setOnClickListener {
            val userInput = editText.text.toString()
            if (isValidPhoneNumber(userInput)) {
                itemList.add(userInput)
                adapter.notifyDataSetChanged()
                editText.text.clear()
            } else {
                showToast(this,"Invalid phone number")
                }
            }
        }
    }

private fun isValidPhoneNumber(phoneNumber: String): Boolean {
    // Regular expression to match common phone number formats
    val regex = "^[+]?[0-9]{10,15}\$"
    return phoneNumber.matches(Regex(regex))
}

private fun showToast(activity: Activity, message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}

// Create a custom adapter by extending ArrayAdapter
class CustomAdapter(context: Context, items: List<String>) :
    ArrayAdapter<String>(context, R.layout.list_item_layout, R.id.itemText, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.list_item_layout, parent, false)

        val itemText = rowView.findViewById<TextView>(R.id.itemText)
        val deleteButton = rowView.findViewById<Button>(R.id.deleteButton)

        itemText.text = getItem(position)

        deleteButton.setOnClickListener {
            remove(getItem(position))
            notifyDataSetChanged()
        }

        return rowView
    }
}