package rs.raf.jun.uros_nikolic_2619rn.presentation.view.recyclers.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.jun.uros_nikolic_2619rn.data.models.News

class NewsDiffCallback : DiffUtil.ItemCallback<News>() {

    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.content == newItem.content
    }
}