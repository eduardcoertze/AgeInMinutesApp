package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnDatePicker.setOnClickListener { view -> clickDatePicker(view) }


    }



    fun clickDatePicker(view: View){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
                view, selectedYear , selectedMonth, selectedDayOfMonth ->

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectDate.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy")

            val theDate = sdf.parse(selectedDate)

            val selectedDateInMinutes = theDate!!.time / 60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateToMin = currentDate!!.time / 60000

            val diff = currentDateToMin - selectedDateInMinutes

            tvSelectDateInMinutes.setText(diff.toString())
        },year,month,day)

        dpd.datePicker.setMaxDate(Date().time - 84600000)
        dpd.show()
        
    }
}