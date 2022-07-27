package rs.raf.jun.uros_nikolic_2619rn.presentation.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jun.uros_nikolic_2619rn.R
import rs.raf.jun.uros_nikolic_2619rn.data.models.News
import rs.raf.jun.uros_nikolic_2619rn.databinding.FragmentNewsBinding
import rs.raf.jun.uros_nikolic_2619rn.presentation.contract.NewsContract
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.recyclers.adapteres.NewsAdapter
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.states.NewsState
import rs.raf.jun.uros_nikolic_2619rn.presentation.viewmodels.NewsViewModel


class NewsFragment: Fragment(R.layout.fragment_news) {

    private val newsViewModel: NewsContract.ViewModel by viewModel<NewsViewModel>()

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initRecycler()
        initObservers()
    }

    private fun initRecycler(){
        binding.recyclerViewNews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = NewsAdapter { vest: News ->
            val url = vest.link
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        binding.recyclerViewNews.adapter = adapter
    }

    private fun initObservers(){
        newsViewModel.newsState.observe(viewLifecycleOwner) {
            renderState(it)
        }
        newsViewModel.fetchAllNews()
    }

    private fun renderState(state: NewsState){
        when (state) {
            is NewsState.Success -> {
                adapter.submitList(state.news)
            }
            is NewsState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is NewsState.DataFetched -> {
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is NewsState.Loading -> {
                Thread.sleep(1000)
            }
        }
    }
}