package com.jamascrorp.megafonlife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jamascrorp.megafonlife.databinding.FragmentAviaticketsBinding
import java.util.*

class AviaticketsFragment : Fragment() {

    private var viewBinding: FragmentAviaticketsBinding? = null
    private val binding get() = viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentAviaticketsBinding.inflate(layoutInflater)
        val toolbar = binding.toolbar
        hideAction()
        toolbar.setNavigationIcon(R.mipmap.left_array)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.select.setOnClickListener {
            findNavController().navigate(R.id.action_aviaticketsFragment_to_calendarFragment)
//            findNavController().navigate(R.id.action_aviaticketsFragment_to_calendars)
        }
    }
}