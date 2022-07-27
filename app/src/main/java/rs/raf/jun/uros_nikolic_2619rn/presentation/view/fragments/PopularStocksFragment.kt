package rs.raf.jun.uros_nikolic_2619rn.presentation.view.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jun.uros_nikolic_2619rn.R
import rs.raf.jun.uros_nikolic_2619rn.data.models.PopularStocks
import rs.raf.jun.uros_nikolic_2619rn.databinding.FragmentPopularstocksBinding
import rs.raf.jun.uros_nikolic_2619rn.presentation.contract.PopularStocksContract
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.recyclers.adapteres.PopularStocksAdapter
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.states.PopularStocksState
import rs.raf.jun.uros_nikolic_2619rn.presentation.viewmodels.PopularStocksViewModel
import timber.log.Timber

class PopularStocksFragment: Fragment(R.layout.fragment_popularstocks) {

    private val psViewModel: PopularStocksContract.ViewModel by sharedViewModel<PopularStocksViewModel>()

    private var _binding: FragmentPopularstocksBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PopularStocksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPopularstocksBinding.inflate(inflater, container, false)
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
        binding.recyclerViewStocks.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = PopularStocksAdapter { deonica: PopularStocks ->
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
            transaction.replace(
                R.id.mainFragmentFcv,
                DetaljanPrikazFragment(deonica.changeFromPreviousClose)
            )
            transaction.commit()
        }
        binding.recyclerViewStocks.adapter = adapter
    }

    private fun initObservers(){
        psViewModel.stocksState.observe(viewLifecycleOwner) {
            renderState(it)
        }
        psViewModel.fetchAllStocks()
    }

    private fun renderState(state: PopularStocksState){
        when (state) {
            is PopularStocksState.Success -> {
                adapter.submitList(state.popularstocks)
            }
            is PopularStocksState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is PopularStocksState.DataFetched -> {
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is PopularStocksState.Loading -> {

            }
        }
    }
}