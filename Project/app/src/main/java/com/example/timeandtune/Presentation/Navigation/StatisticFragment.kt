package com.example.timeandtune.Presentation.Navigation

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.timeandtune.R
import com.example.timeandtune.databinding.StatisticFragmentBinding
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.MPPointF


class StatisticFragment : Fragment() {
    private var _binding: StatisticFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StatisticFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        val lineChart = binding.chart

        val entries = ArrayList<Entry>()
        entries.add(Entry(1f, 50f))
        entries.add(Entry(2f, 100f))
        entries.add(Entry(3f, 80f))
        entries.add(Entry(4f, 60f))
        entries.add(Entry(5f, 50f))
        entries.add(Entry(6f, 60f))
        entries.add(Entry(7f, 50f))

        val dataSet = LineDataSet(entries, "Label")
        dataSet.color = Color.parseColor("#34355e")
        dataSet.setDrawFilled(true)
        dataSet.fillColor = Color.parseColor("#34355e")
        dataSet.fillAlpha = 140
        dataSet.setDrawValues(false)

        val lineData = LineData(dataSet)
        lineChart.data = lineData

        val leftAxis: YAxis = lineChart.axisLeft
        leftAxis.setDrawGridLines(false)

        val xAxis: XAxis = lineChart.xAxis
        xAxis.valueFormatter = MyXAxisValueFormatter(entries)

        val rightAxis: YAxis = lineChart.axisRight
        rightAxis.isEnabled = false

        val legend: Legend = lineChart.legend
        legend.isEnabled = false

        lineChart.description.isEnabled = false


        lineChart.invalidate()
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private class MyXAxisValueFormatter(private val entries: List<Entry>) : IAxisValueFormatter {
        val daysOfWeek = listOf("", "", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        override fun getFormattedValue(value: Float, axis: AxisBase?): String {
            val index = value.toInt()
            return if (index >= 0 && index < entries.size) {
                daysOfWeek[entries[index].x.toInt()]
            } else {
                "Sun"
            }
        }
    }
}