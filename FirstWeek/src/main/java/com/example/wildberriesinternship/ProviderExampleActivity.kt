package com.example.wildberriesinternship

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.wildberriesinternship.databinding.ActivityProviderExampleBinding
import java.text.SimpleDateFormat
import java.util.*
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

/*
Content Provider - это оболочка для данных. Он нужен для ситуаций, когда необходимо сделать наши данные "общими"
для передачи в другие приложения. По умолчанию, доступ к базе данных приложения имеет только само приложение, а
с помощью провайдера мы можем дать или получить к ней доступ. Например, мы можем получить список контактов или
доступ к данным календаря, заметкам и тд. что бы вывести их в нашем приложении. Точно так же, мы можем записать в
них свои данные из нашего приложения.

Content Provider используется в мессенджерах вроде Telegram или WatsApp для доступа к таким приложениям телефона,
как галерея или список контактов.

 */

class ProviderExampleActivity : AppCompatActivity() {
    //Binding для упрощённой работы с XML
    private lateinit var binding: ActivityProviderExampleBinding

    @SuppressLint("Range", "SetTextI18n", "Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProviderExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var calendar: Calendar = Calendar.getInstance()
        //Слушатели для кнопок
        binding.startTimeEt.setOnClickListener {
            calendar = datePicker(binding.startTimeEt)
        }

        binding.insertButton.setOnClickListener {
            insertUser()
        }

        binding.loadButton.setOnClickListener {
            showUsers()
        }

        binding.okButt.setOnClickListener {
            addEventToCalendar(calendar)
        }

    }

    // Метод для выбора даты и времени ивента
    @SuppressLint("SimpleDateFormat")
    private fun datePicker(et: EditText): Calendar {
        val calendar: Calendar = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)

                et.setText(SimpleDateFormat("yyyy:MM:dd HH:mm").format(calendar.time))
            }
            TimePickerDialog(this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), false).show()

        }
        DatePickerDialog(this, dateSetListener, calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)).show()

        return calendar
    }
    //Метод для вставки пользователя в базу с помощью Content Provider
    private fun insertUser(){
        val values = ContentValues()

        if (binding.textName.text.toString() != "") {

            //Забираем данные из строчки
            values.put(
                MyContentProvider.name,
                binding.textName.text.toString()
            )

            //Передаём в бд с помощью ContentUri
            contentResolver.insert(MyContentProvider.CONTENT_URI, values)

            //Показываем сообщение
            Toast.makeText(baseContext, "New Record Inserted", Toast.LENGTH_LONG).show()
        } else Toast.makeText(baseContext, "Enter UserName", Toast.LENGTH_LONG).show()
    }

    @SuppressLint("Range")
    private fun showUsers(){
        // Создаём объект Cursor для ContentUri
        // Cursor - это интерфейс для реализации доступа к чтению из записи в бд
        val cursor = contentResolver.query(Uri.parse("content://com.example.wildberriesinternship/users"),
            null, null, null, null)

        //Выводим таблицу на экран
        if (cursor!!.moveToFirst()) {
            val strBuild = StringBuilder()
            while (!cursor.isAfterLast) {
                strBuild.append("""
      
    ${cursor.getString(cursor.getColumnIndex("id"))}-${cursor.getString(cursor.getColumnIndex("name"))}
    """.trimIndent())
                cursor.moveToNext()
            }
            binding.res.text = strBuild
        } else {
            binding.res.text = "No Records Found"
        }
    }

    // Метод для добавления ивента в календарь с помощью ContentResolver
    private fun addEventToCalendar(calendar: Calendar) {

        //Проверяем наличие доступа. Если он есть, производим запись, если нет, то запрашиваем.

        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.WRITE_CALENDAR)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.WRITE_CALENDAR),
                101)
        } else {

            val eventValues = ContentValues()
            val cr = contentResolver

            var startMillis = calendar.timeInMillis
            var endMillis = startMillis + binding.endTimeEt.text.toString().toInt() * 1000 * 60 * 60

            eventValues.put(CalendarContract.Events.CALENDAR_ID, "1".toLong())
            eventValues.put(CalendarContract.Events.HAS_ALARM, 1)
            eventValues.put(CalendarContract.Events.DTSTART, startMillis)
            eventValues.put(CalendarContract.Events.DTEND, endMillis)
            eventValues.put(CalendarContract.Events.TITLE, "test")
            eventValues.put(
                CalendarContract.Events.DESCRIPTION,
                binding.descriptionEt.text.toString()
            )

            eventValues.put(
                CalendarContract.Events.EVENT_TIMEZONE,
                Calendar.getInstance().timeZone.id
            )

            cr.insert(CalendarContract.Events.CONTENT_URI, eventValues)
            Toast.makeText(applicationContext, "Successfully added", Toast.LENGTH_SHORT).show()


        }
    }
}