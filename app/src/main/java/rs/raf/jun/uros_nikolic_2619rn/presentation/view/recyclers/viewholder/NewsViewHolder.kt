package rs.raf.jun.uros_nikolic_2619rn.presentation.view.recyclers.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.jun.uros_nikolic_2619rn.data.models.News
import rs.raf.jun.uros_nikolic_2619rn.databinding.NewsItemLayoutBinding
import java.util.function.Consumer

class NewsViewHolder(
    private val itemBinding: NewsItemLayoutBinding,
    private val context: Context,
    private val newsClick: Consumer<Int>,
) : RecyclerView.ViewHolder(itemBinding.root) {

    init {
        itemBinding.imageView.setOnClickListener{
            newsClick.accept(bindingAdapterPosition)
        }
    }

    fun bind(news: News){
        itemBinding.titleTv.text = news.title
        itemBinding.dateTv.text = news.date
        Glide
            .with(context)
            .load(news.image)
            .into(itemBinding.imageView)
    }
}