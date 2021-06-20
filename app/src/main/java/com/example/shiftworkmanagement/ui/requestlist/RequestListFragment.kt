package com.example.shiftworkmanagement.ui.requestlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shiftworkmanagement.R
import com.example.shiftworkmanagement.databinding.FragmentRequestListBinding
import com.example.shiftworkmanagement.databinding.FragmentRequestShiftBinding
import com.example.shiftworkmanagement.model.RecyclerViewAdapter

class RequestListFragment : Fragment() {


    private lateinit var requestListViewModel: RequestListViewModel
    private var _binding: FragmentRequestListBinding? = null
    private val binding get() = _binding!!
    private val dataset = arrayOfNulls<String>(20)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requestListViewModel = ViewModelProvider(this).get(RequestListViewModel::class.java)
        _binding = FragmentRequestListBinding.inflate(inflater, container, false)

        binding.requstList.setHasFixedSize(true)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())

        binding.requstList.layoutManager = layoutManager

        var i = 0
        for (i in 0 until 20) {
            dataset[i] = "data$i"
        }

        val adapter = RecyclerViewAdapter(dataset)
        binding.requstList.adapter = adapter

        return binding.root
    }

}