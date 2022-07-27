package rs.raf.jun.uros_nikolic_2619rn.presentation.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jun.uros_nikolic_2619rn.R
import rs.raf.jun.uros_nikolic_2619rn.data.models.PopularStocks
import rs.raf.jun.uros_nikolic_2619rn.databinding.FragmentPortfolioBinding
import rs.raf.jun.uros_nikolic_2619rn.presentation.contract.PopularStocksContract
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.recyclers.adapteres.PopularStocksAdapter
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.states.PopularStocksState
import rs.raf.jun.uros_nikolic_2619rn.presentation.viewmodels.PopularStocksViewModel

class PortfolioFragment: Fragment(R.layout.fragment_portfolio) {

    private var _binding: FragmentPortfolioBinding? = null
    private val binding get() = _binding!!
    private val psViewModel: PopularStocksContract.ViewModel by sharedViewModel<PopularStocksViewModel>()
    private lateinit var adapter: PopularStocksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPortfolioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val barchart = binding.chartPortfolio

        var dataVals = ArrayList<Entry>()
        dataVals.add(Entry(0F, 0F))
        dataVals.add(Entry(1F, 2F))
        dataVals.add(Entry(2F, 7F))
        dataVals.add(Entry(3F, 3F))
        val barDataSet = LineDataSet(dataVals,"Portfolio")

        val bardata = LineData(barDataSet)
        bardata.setDrawValues(false)
        barchart.data = bardata
        barchart.invalidate()

        initRecycler()
        initObservers()
    }

    private fun initRecycler(){
        binding.recyclerViewPortfolio.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = PopularStocksAdapter { deonica: PopularStocks ->
            val transaction: FragmentTransaction =
                requireActivity().supportFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
            transaction.replace(
                R.id.mainFragmentFcv,
                DetaljanPrikazFragment(deonica.changeFromPreviousClose)
            )
            transaction.commit()
        }
        binding.recyclerViewPortfolio.adapter = adapter
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