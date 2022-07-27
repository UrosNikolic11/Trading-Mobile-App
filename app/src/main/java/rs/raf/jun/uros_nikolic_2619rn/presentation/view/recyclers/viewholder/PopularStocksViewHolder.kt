package rs.raf.jun.uros_nikolic_2619rn.presentation.view.recyclers.viewholder

import android.content.Context
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import rs.raf.jun.uros_nikolic_2619rn.data.models.PopularStocks
import rs.raf.jun.uros_nikolic_2619rn.databinding.StocksItemLayoutBinding
import java.util.function.Consumer

class PopularStocksViewHolder(
    private val itemBinding: StocksItemLayoutBinding,
    private val context: Context,
    private val stocksClicked: Consumer<Int>
    ): RecyclerView.ViewHolder(itemBinding.root) {

    init {
        itemBinding.chartPS.setOnClickListener{
            stocksClicked.accept(bindingAdapterPosition)
        }
    }

    fun bind(stocks: PopularStocks){
       val barchart = itemBinding.chartPS
       val textV = itemBinding.stockTv
       textV.setText(stocks.symbol + " " + stocks.name + " " + stocks.last + "$")
        var i: Int = 0
        val barEntries =  ArrayList<BarEntry>()
        for (i in 0..stocks.chart.bars.size-1) {
            barEntries.add(BarEntry(getTime(stocks.chart.bars[i].time),stocks.chart.bars[i].price))
        }

        val barDataSet = BarDataSet(barEntries,"Cena po vremenu")

        if(stocks.changeFromPreviousClose < 0){
            barDataSet.setColors(Color.RED)
        }
        else barDataSet.setColors(Color.GREEN)


        val bardata = BarData(barDataSet)
        bardata.barWidth = 0.1f
        bardata.setDrawValues(false)
        barchart.data = bardata
        barchart.invalidate()
    }

    private fun getTime(time: String): Float{
        val list = time.split("T")
        val vreme = list[1].split(":")
        val vrati = vreme[0].toFloat() + (vreme[1].toFloat())/100
        return vrati
    }
}