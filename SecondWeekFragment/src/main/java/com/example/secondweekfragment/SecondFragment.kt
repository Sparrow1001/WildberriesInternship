package com.example.secondweekfragment

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import java.util.*


class SecondFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button: Button = view.findViewById(R.id.inputBt)
        button.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog(){
        val builder = AlertDialog.Builder(context)
        val inflater = layoutInflater
        builder.setTitle("Очень важный диалог")
        builder.setMessage("Невероятно важное сообщение")

        val dialogLayout = inflater.inflate(R.layout.dialog_item, null)
        val nameEt = dialogLayout.findViewById<EditText>(R.id.dialogEt)
        val check = dialogLayout.findViewById<CheckBox>(R.id.checkB)

        builder.setView(dialogLayout)

        builder.setPositiveButton("Готово"){dialog, which ->
            if (check.isChecked){
                Toast.makeText(context, "${nameEt.text}, кот хочет кушать", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(context, "${nameEt.text}, тебе нужно завести кота)", Toast.LENGTH_SHORT).show()
        }


        builder.show()
    }

}