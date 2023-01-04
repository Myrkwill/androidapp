package ru.myrkwill.androidapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ru.myrkwill.androidapp.R
import ru.myrkwill.androidapp.adapter.WeatherAdapter
import ru.myrkwill.androidapp.adapter.WeatherModel
import ru.myrkwill.androidapp.databinding.FragmentHoursBinding

class HoursFragment : Fragment() {

    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
    }

    private fun initRcView() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter()
        rcView.adapter = adapter
        val list = listOf(
            WeatherModel("", "12:00", "Sunny", "25 C", "", "", "", ""),
            WeatherModel("", "15:00", "Cold", "24 C", "", "", "", ""),
            WeatherModel("", "19:00", "Bold", "2 C", "", "", "", ""),
            WeatherModel("", "20:20", "Sold", "15 C", "", "", "", "")
        )
        adapter.submitList(list)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}