package com.example.shiftworkmanagement.ui.requestlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shiftworkmanagement.databinding.FragmentRequestListBinding
import com.example.shiftworkmanagement.model.RecyclerViewAdapter
import com.example.shiftworkmanagement.model.Request
import java.time.LocalDate
import java.time.LocalDateTime

class RequestListFragment : Fragment() {


    private lateinit var requestListViewModel: RequestListViewModel
    private var _binding: FragmentRequestListBinding? = null
    private val binding get() = _binding!!
    private val requestMock = arrayOfNulls<Request>(40)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requestListViewModel = ViewModelProvider(this).get(RequestListViewModel::class.java)
        _binding = FragmentRequestListBinding.inflate(inflater, container, false)

        binding.requestList.setHasFixedSize(true)

        // RecyclerViewのリストごとに区切り線を入れる
        val itemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.requestList.addItemDecoration(itemDecoration)

        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())

        binding.requestList.layoutManager = layoutManager

        for (i in 0 until 40) {
            val tempRequest = Request(i,"バイト先", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), i)
            requestMock[i] = tempRequest
        }

        val adapter = RecyclerViewAdapter(requestMock)
        binding.requestList.adapter = adapter

        return binding.root
    }

}