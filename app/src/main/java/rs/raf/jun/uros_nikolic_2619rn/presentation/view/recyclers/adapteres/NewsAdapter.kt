package rs.raf.jun.uros_nikolic_2619rn.presentation.view.recyclers.adapteres

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.jun.uros_nikolic_2619rn.data.models.News
import rs.raf.jun.uros_nikolic_2619rn.databinding.NewsItemLayoutBinding
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.recyclers.diff.NewsDiffCallback
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.recyclers.viewholder.NewsViewHolder
import java.util.function.Consumer

class NewsAdapter(
    private val newsClick: Consumer<News>
) : ListAdapter<News, NewsViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemBinding = NewsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(itemBinding, parent.context) { position: Int ->
            val vest = getItem(position)
            newsClick.accept(vest)
        }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}