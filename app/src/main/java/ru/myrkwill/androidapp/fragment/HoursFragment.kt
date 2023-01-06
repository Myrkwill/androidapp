package ru.myrkwill.androidapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import org.json.JSONArray
import org.json.JSONObject
import ru.myrkwill.androidapp.MainViewModel
import ru.myrkwill.androidapp.R
import ru.myrkwill.androidapp.adapter.WeatherAdapter
import ru.myrkwill.androidapp.adapter.WeatherModel
import ru.myrkwill.androidapp.databinding.FragmentHoursBinding

class HoursFragment : Fragment() {

    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter
    private val model: MainViewModel by activityViewModels()

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
        model.liveDataCurrent.observe(viewLifecycleOwner) { item ->
            adapter.submitList(getHoursList(item))
        }
    }

    private fun initRcView() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter()
        rcView.adapter = adapter
    }

    private fun getHoursList(item: WeatherModel): List<WeatherModel> {
        val list = ArrayList<WeatherModel>()
        val hoursArray = JSONArray(item.hours)
        for (i in 0 until hoursArray.length()) {
            val hoursItem = hoursArray[i] as JSONObject
            val weatherItem = WeatherModel(
                item.city,
                hoursItem.getString("time"),
                hoursItem.getJSONObject("condition").getString("text"),
                hoursItem.getString("temp_c"),
                "",
                "",
                hoursItem.getJSONObject("condition").getString("icon"),
                ""
            )
            list.add(weatherItem)
        }
        return list
    }

    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}