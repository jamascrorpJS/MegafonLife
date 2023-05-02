package com.jamascrorp.megafonlife.calendar_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jamascrorp.megafonlife.R
import com.jamascrorp.megafonlife.databinding.CalendarBinding
import com.jamascrorp.megafonlife.test.Day
import com.jamascrorp.megafonlife.test.Month
import com.jamascrorp.megafonlife.test.MonthsAdapter
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment : Fragment() {
    private var viewBinding: CalendarBinding? = null
    private val binding get() = viewBinding!!

    private val viewModel by lazy {
        ViewModelProvider(this)[CalendarFragmentViewModel::class.java]
    }
    var count = 0

    private val adapter = MonthsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = CalendarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.onDayClick = { day ->
            Toast.makeText(requireContext(), "${day.timeInMillis?.let { Date(it) }}", Toast.LENGTH_SHORT).show()
            viewModel.updateSelectionBySelectedDay(day)
            Log.d("TAG", "onViewCreated: ${viewModel.selectedStartDate?.time} ${viewModel.selectedEndDate?.time}")
adapter.notifyDataSetChanged()
        }
        viewModel.getMonths()
//        adapter.submitList(viewModel.getMonths())
        binding.rv.adapter = adapter
        binding.rv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.monthsLiveData.observe(viewLifecycleOwner) { months ->
            months?.forEach { month ->
                Log.d("check_data:", "---------------------------------------------")
                Log.d("check_data:", "${month.name}\n")
                month.days.forEach { day ->
                    Log.d("check_data:", "${day.day}-${day.isSelected}  ")
                }
                Log.d("check_data:", "---------------------------------------------\n")
            }

            adapter.items = months ?: emptyList()
            adapter.notifyDataSetChanged()
        }

//        recycler()
//        viewModel.ld.observe(viewLifecycleOwner) {
//            count++
//            when (count) {
//                1 -> {
//                        viewModel.startDate = it
//                        viewModel.startDate?.isSelected = true
//
//                }
//
//                2 -> {
//                    viewModel.endDate = it
//                    viewModel.endDate?.isSelected = true
//                    if (viewModel.startDate == viewModel.endDate) {
//                        count = 0
//                        viewModel.startDate?.isSelected = false
//                    }
//                }
//
//                3 -> {
//                    viewModel.startDate?.isSelected = false
//                    viewModel.endDate?.isSelected = false
//                    viewModel.startDate = null
//                    viewModel.endDate = null
//                    count = 0
////                    viewModel.adapter.notifyDataSetChanged()
//                    viewModel.startDate = it
//                    viewModel.startDate?.isSelected = true
//                }
//            }
//        }

//        viewModel.ld1.observe(viewLifecycleOwner) {
//            count++
//            when (count) {
//                1 -> {
//                    viewModel.startDate = it.first
//                    viewModel.startDate?.isSelected = true
//                    Log.d("TAG", "onViewCreated: ${it.second}")
//                }
//
//                2 -> {
//                    viewModel.endDate = it.first
//                    viewModel.endDate?.isSelected = true
//                    if (viewModel.startDate == viewModel.endDate) {
//                        count = 0
//                        viewModel.startDate?.isSelected = false
//                    }
//                    Log.d("TAG", "onViewCreated: ${it.second}")
//                }
//
//                3 -> {
//                    viewModel.startDate?.isSelected = false
//                    viewModel.endDate?.isSelected = false
//                    viewModel.startDate = null
//                    viewModel.endDate = null
//                    count = 0
//                    viewModel.startDate = it.first
//                    viewModel.startDate?.isSelected = true
//                    viewModel.adapter.notifyDataSetChanged()
//                    Log.d("TAG", "onViewCreated: ${it.second}")
//                }
//            }
//        }

//        viewModel.ld1.observe(viewLifecycleOwner){
//            count++
//            Log.d("TAG", "onViewCreated: $count")
//             when (count) {
//                 1 -> {
//                    count = 1
////                    if (!it.second.contains(viewModel.startDate)){
//                        viewModel.startDate = it.first
//                        viewModel.startDate?.isSelected = true
////                    }
//                    list.add(it.third)
//                    Log.d("TAG", "onViewCreated: ${it.first}")
//                    Log.d("TAG", "onViewCreated: ${it.third}")
//                }
//                2 -> {
////                    if (it.second.contains(viewModel.startDate) && !it.second.contains(viewModel.endDate)) {
//                        viewModel.endDate = it.first
//                        viewModel.endDate?.isSelected = true
////                    }
////                    if (viewModel.startDate == viewModel.endDate) {
////                        count = 0
////                        viewModel.endDate = null
////                        viewModel.startDate?.isSelected = false
////                        viewModel.startDate = null
////                    }
//                    list.add(it.third)
//                    Log.d("TAG", "onViewCreated: ${it.first}")
//                    Log.d("TAG", "onViewCreated: ${it.third}")
//                }
//                3 -> {
//                    it.second.forEach {
//                        it.isSelected = true
//                    }
////                    viewModel.adapter.notifyDataSetChanged()
////                    if (it.second.contains(viewModel.startDate) && it.second.contains(viewModel.endDate)) {
////                        it.second[list[0]].isSelected = false
////                        it.second[list[1]].isSelected = false
//////                        viewModel.startDate = null
//////                        viewModel.endDate = null
////                    }
//                    count = 0
////                    list.clear()
////                    if (!it.second.contains(viewModel.startDate)){
////                        viewModel.startDate = it.first
////                        viewModel.startDate?.isSelected = true
////                        viewModel.adapter.notifyItemChanged(it.third)
////                    }
////                    recycler()
//                }
//            }
//        }

        binding.cancel.setOnClickListener {
            findNavController().navigate(R.id.action_calendarFragment_to_aviaticketsFragment)
        }
    }

//    private fun func(it: Day?) {
//        var a = 0
//        while (a < 3) {
//                a += 1
//            if (viewModel.startDate?.isSelected == true && viewModel.endDate?.isSelected == true) {
//                viewModel.startDate?.isSelected = false
//                viewModel.endDate?.isSelected = false
//                a += 1
//            } else if (viewModel.startDate?.isSelected == false && viewModel.endDate?.isSelected == false){
//                viewModel.startDate = null
//                viewModel.endDate = null
//                a += 1
//            } else if (viewModel.startDate == null && viewModel.endDate == null) {
//                count = 0
//                viewModel.startDate = it
//                viewModel.startDate?.isSelected = true
////                viewModel.adapter.notifyDataSetChanged()
//                a += 1
//            } else {
//                a = 0
//            }
//        }
//    }
}