package com.example.secondweekfragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class FirstFragment : Fragment() {

    //При запуске: onAttach(), onCreate(), onCreateView(), onStart(), onResume()

    // Нажитие кнопки домой: onPause(), onStop()

    // Запуск из недавно запущенных: onStart(), onResume()

    // Выход из приложение кнопкой назад: onPause(), onStop(), onDestroyView(), onDestroy(), onDetach()

    /*
    Основные отличия жизненных циклов Activity и Fragment заключаются в присутствии у второго
    дополнительных методов.

    Дополнительные методы:
    onAttach - вызывается при связывании фрагмента с активностью
    onCreateView - вызывается при создании компонентов во фрагменте. Вызывается один раз, когда фрагмент должен загрузить на экран свой интерфейс
    onViewCreated - идёт после onCreateView
    onActivityCreated - вызывается после выполнения метода onCreate в активности
    onDestroyView - вызывается при удалении компонентов во фрагменте
    onDetach - вызывается, когда фрагмент отвязывается от активности

    Одноимённые с методами активности методы фрагментов выполняют аналогичные функции.
    Метод onStart() вызывается, когда фрагмент становится видимым после запуска такого же метода в активности.

    Фрагмент всегда связан с активностью. Отдельно фрагмент от активности существовать не может.
    Если активность останавливается, то её фрагменты также останавливаются. Если активность уничтожается, то её фрагменты также уничтожаются.

    Для предствавления о взаимодействии активности и фрагментов можно провести аналогию с браузером (спасибо хабру), в
    которой окно браузера это активность, а вкладки - фрагменты

    Отличия в жизненных циклах возникли из за необходимости более чёткого управления фрагментами. На одной активности может быть
    несколько фрагментов, они могут заменять друг друга, изменяться в ходе взаимодействия с пользователем. Кроме того, это может помочь
    в управлении ресурсами устройства и увеличении быстродействия приложения, а так же это может помочь с увеличением качесва user experience.

    Жизненный цикл нужен для манипуляции поведением приложения в различных ситуациях. Например, когда система понимает,
    что приложение остановлено и вызывается метод onStop(), мы можем отключить отслеживание геолокации, что бы освободить ресурсы.
    Так делают приложения карт или любые другие сервисы, отслеживающие локацию.

    Вторым примером можно назвать прослушивание музыки в приложении ВК. Т.к без платной подписки можно слушать музыку только во
    время активного использования приложения, то можно сказать, что при вызове метода onPause() или метода onStop() музыка останавливается,
    а при onResume() снова начинает играть.

    Как ещё один пример можно взять YouTube Premium. Когда мы сворачиваем приложение при просмотре видео, видео не останавливается, а переностся в маленькое
    окошко. На сколько я понимаю, в этом случае при вызове onStop() вызывается метод запускающий это окошко, а при onResume() закрывающий его.


     */

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("onAttach", "Fragment Attached")
        Toast.makeText(context, "Fragment Attached", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate", "Fragment Created")
        Toast.makeText(context, "Fragment Created", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("onCreateView", "Create view")
        Toast.makeText(context, "Create view", Toast.LENGTH_SHORT).show()
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("onViewCreated", "View created")
        Toast.makeText(context, "View created", Toast.LENGTH_SHORT).show()

        val button: Button = view.findViewById(R.id.moveBt)
        button.setOnClickListener {
            startActivity(Intent(context, SecondActivity::class.java))
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("onStart", "Fragment started")
        Toast.makeText(context, "Fragment started", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume", "Fragment resumed")
        Toast.makeText(context, "Fragment resumed", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Log.d("onPause", "Fragment paused")
        Toast.makeText(context, "Fragment paused", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Log.d("onStop", "Fragment stopped")
        Toast.makeText(context, "Fragment stopped", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("onDestroyView", "View destroyed")
        Toast.makeText(context, "View destroyed", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "Fragment Destroyed")
        Toast.makeText(context, "Fragment Destroyed", Toast.LENGTH_SHORT).show()
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("onDetach", "Fragment Detached")
        Toast.makeText(context, "Fragment Detached", Toast.LENGTH_SHORT).show()
    }

}