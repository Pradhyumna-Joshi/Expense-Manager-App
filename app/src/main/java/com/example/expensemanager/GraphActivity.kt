package com.example.expensemanager

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.expensemanager.databinding.ActivityGraphBinding
import com.example.expensemanager.databinding.ActivityMainBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.reflect.Array

class GraphActivity : AppCompatActivity() {


    private var binding_: ActivityGraphBinding? = null
    val binding get() = binding_!!

    var income = 0
    var expense = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_ = ActivityGraphBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        income = intent.getIntExtra("income", 0)
        expense = intent.getIntExtra("expense", 0)
        setUpPieChart()
        loadPieChart()
    }

    private fun setUpPieChart() {


        binding.pieChart.apply {
            setHoleColor(Color.parseColor("#1f2732"))
            setUsePercentValues(true)
            setEntryLabelTextSize(16f)
            setEntryLabelColor(Color.WHITE)
            centerText="Spendings"
            setCenterTextSize(20f)
            setCenterTextColor(Color.WHITE)
            description.isEnabled

            val l=legend
            l.verticalAlignment=Legend.LegendVerticalAlignment.TOP
            l.horizontalAlignment=Legend.LegendHorizontalAlignment.RIGHT
            l.orientation=Legend.LegendOrientation.VERTICAL
            l.textColor=Color.WHITE
            l.textSize=12f
            l.setDrawInside(true)
            l.isEnabled
        }
    }

    private fun loadPieChart() {


        val pieEntryList = ArrayList<PieEntry>()

        val colorList = ArrayList<Int>()



        if (income!= 0) {
            pieEntryList.add(PieEntry(income.toFloat(), "Income"))
            colorList.add(Color.parseColor("#86DC3D"))
        }

        if (expense != 0) {
            pieEntryList.add(PieEntry(expense.toFloat(), "Expense"))
            colorList.add(Color.parseColor("#FF0000"))

        }

        val pieDataSet = PieDataSet(pieEntryList,(income-expense).toString())
        pieDataSet.setColors(colorList)
        pieDataSet.valueFormatter = PercentFormatter(binding.pieChart)
        pieDataSet.valueTextSize = 16F
        pieDataSet.setValueTextColor(Color.parseColor("#ffffff"))

        val pieData = PieData(pieDataSet)

        binding.pieChart.data = pieData
        binding.pieChart.invalidate()

        binding.pieChart.animateY(1400,Easing.EaseInOutQuart)


    }

}