package rs.raf.jun.uros_nikolic_2619rn.presentation.view.recyclers.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.jun.uros_nikolic_2619rn.data.models.PopularStocks

class PopularStocksDiffcallback: DiffUtil.ItemCallback<PopularStocks>() {

    override fun areItemsTheSame(oldItem: PopularStocks, newItem: PopularStocks): Boolean {
        return oldItem.symbol == newItem.symbol
    }

    override fun areContentsTheSame(oldItem: PopularStocks, newItem: PopularStocks): Boolean {
        return oldItem.symbol == newItem.symbol
    }

}