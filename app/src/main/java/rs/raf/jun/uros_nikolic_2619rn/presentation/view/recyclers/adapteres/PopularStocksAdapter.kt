package rs.raf.jun.uros_nikolic_2619rn.presentation.view.recyclers.adapteres

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.jun.uros_nikolic_2619rn.data.models.PopularStocks
import rs.raf.jun.uros_nikolic_2619rn.databinding.StocksItemLayoutBinding
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.recyclers.diff.PopularStocksDiffcallback
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.recyclers.viewholder.PopularStocksViewHolder
import java.util.function.Consumer

class PopularStocksAdapter(
    private val stockClicked: Consumer<PopularStocks>
): ListAdapter<PopularStocks, PopularStocksViewHolder>(PopularStocksDiffcallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularStocksViewHolder {
        val itemBinding = StocksItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularStocksViewHolder(itemBinding, parent.context) { position: Int ->
            val deonica = getItem(position)
            stockClicked.accept(deonica)
        }
    }

    override fun onBindViewHolder(holder: PopularStocksViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}