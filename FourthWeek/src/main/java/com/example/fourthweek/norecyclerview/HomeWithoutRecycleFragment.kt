package com.example.fourthweek.norecyclerview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.fourthweek.R
import com.example.fourthweek.databinding.FragmentHomeWithoutRecycleBinding
import java.util.*


class HomeWithoutRecycleFragment : Fragment() {

    private lateinit var binding: FragmentHomeWithoutRecycleBinding
    private var list = mutableListOf<View>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeWithoutRecycleBinding.inflate(inflater, container, false)

        list = fillList(list)
        setList(list)

        binding.swipeToRefreshLayout.setOnRefreshListener {
            list = update(list)
            removeList()
            setList(list)
            binding.swipeToRefreshLayout.isRefreshing = false
        }


        return binding.root
    }

    private fun update(viewList: List<View>): MutableList<View> {
        val newList = mutableListOf<View>()
        newList.addAll(viewList)
        for (i in (0..newList.size - 2)){
            val lastMessage: TextView = newList[i].findViewById(R.id.lastMessageTv)
            val unreadCounter: TextView = newList[i].findViewById(R.id.unreadCounterTv)
            if (Random().nextBoolean()){
                lastMessage.text = getRandomString((1..10).random())
                unreadCounter.text = (1..100).random().toString()
            }
        }
        return newList
    }

    private fun setList(list: List<View>) {
        for (i in list.indices) {
            binding.viewContainerLL.addView(list[i])
        }
    }

    private fun removeList() {
        binding.viewContainerLL.removeAllViewsInLayout()
    }

    @SuppressLint("SetTextI18n")
    private fun fillList(list: List<View>): MutableList<View> {
        val newList = mutableListOf<View>()
        newList.addAll(list)
        for (i in (0..10)) {
            val childView =
                layoutInflater.inflate(R.layout.chat_list_item, binding.viewContainerLL, false)
            val chatName: TextView = childView.findViewById(R.id.chatNameTv)
            val lastMessage: TextView = childView.findViewById(R.id.lastMessageTv)
            val dateTime: TextView = childView.findViewById(R.id.dateTimeTv)
            val unreadCounter: TextView = childView.findViewById(R.id.unreadCounterTv)

            chatName.text = getRandomString((1..10).random())
            lastMessage.text = getRandomString((1..20).random())
            dateTime.text = "${Random().nextInt(2)}${Random().nextInt(3)}:" +
                    "${Random().nextInt(6)}${Random().nextInt(9)}"
            unreadCounter.text = (1..200).random().toString()

            newList.add(childView)

        }

        val footer = layoutInflater.inflate(R.layout.layout_footer, binding.viewContainerLL, false)
        val loadMore: ImageButton = footer.findViewById(R.id.loadMoreBt)
        loadMore.setOnClickListener {
            nextPage()
        }
        newList.add(footer)
        return newList
    }

    private fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    private fun nextPage(){
        list.removeAt(list.lastIndex)
        list = fillList(list)
        removeList()
        setList(list)
    }


}