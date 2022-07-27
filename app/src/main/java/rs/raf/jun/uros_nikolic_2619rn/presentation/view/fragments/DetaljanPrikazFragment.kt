package rs.raf.jun.uros_nikolic_2619rn.presentation.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jun.uros_nikolic_2619rn.R
import rs.raf.jun.uros_nikolic_2619rn.data.models.Search
import rs.raf.jun.uros_nikolic_2619rn.databinding.FragmentDetaljanPrikazBinding
import rs.raf.jun.uros_nikolic_2619rn.presentation.contract.PopularStocksContract
import rs.raf.jun.uros_nikolic_2619rn.presentation.view.states.SearchState
import rs.raf.jun.uros_nikolic_2619rn.presentation.viewmodels.PopularStocksViewModel

class DetaljanPrikazFragment(private var boja: Float): Fragment(R.layout.fragment_detaljan_prikaz) {

    private var _binding: FragmentDetaljanPrikazBinding? = null
    private val binding get() = _binding!!

    private val psViewModel: PopularStocksContract.ViewModel by sharedViewModel<PopularStocksViewModel>()

    private lateinit var openTv: TextView
    private lateinit var bidTv: TextView
    private lateinit var closeTv: TextView
    private lateinit var askTv: TextView
    private lateinit var mktcapTv: TextView
    private lateinit var epsTv: TextView
    private lateinit var ebitTv: TextView
    private lateinit var betaTv: TextView
    private lateinit var kupiBtn: Button
    private lateinit var prodajBtn: Button
    private lateinit var simbolTv: TextView
    private lateinit var lastTv: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetaljanPrikazBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initObservers()
    }

    private fun initUI(search: Search){
        openTv = binding.openTv
        bidTv = binding.bidTv
        closeTv = binding.closeTv
        askTv = binding.askTv
        mktcapTv = binding.mktcapTv
        epsTv = binding.epsTv
        betaTv = binding.betaTv
        ebitTv = binding.ebitTv
        kupiBtn = binding.kupiBtn
        prodajBtn = binding.prodajBtn
        simbolTv = binding.symbolTv
        lastTv = binding.lastTv

        simbolTv.setText("Symbol: " + search.symbol)
        lastTv.setText("Current price: " + search.last + "$")
        openTv.setText("Open: " + search.open)
        bidTv.setText("Bid: " + search.bid)
        closeTv.setText("Close: " + search.close)
        askTv.setText("Ask: " + search.ask)
        mktcapTv.setText("Mkt Cap: " + search.metrics.marketCup)
        epsTv.setText("Eps: " + search.metrics.eps)
        betaTv.setText("Beta: " + search.metrics.beta)
        ebitTv.setText("Ebit: " + search.metrics.ebit)

        val barchart = binding.chartDetaljniPrikaz
        var i: Int = 0
        val barEntries =  ArrayList<BarEntry>()
        for (i in 0..search.chart.bars.size-1) {
            barEntries.add(BarEntry(getTime(search.chart.bars[i].time),search.chart.bars[i].price))
        }

        val barDataSet = BarDataSet(barEntries,"Cena po vremenu")

            if(boja < 0){
                barDataSet.setColors(Color.RED)
            }
            else barDataSet.setColors(Color.GREEN)


        val bardata = BarData(barDataSet)
        bardata.barWidth = 0.1f
        bardata.setDrawValues(false)
        barchart.data = bardata
        barchart.invalidate()
    }

    private fun initObservers(){
        psViewModel.searchState.observe(viewLifecycleOwner) {
            renderState(it)
        }
        psViewModel.detealjanPrikaz()
    }

    private fun renderState(state: SearchState){
        when (state) {
            is SearchState.Success -> {
                initUI(state.search)
            }
            is SearchState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getTime(time: String): Float{
        val list = time.split("T")
        val vreme = list[1].split(":")
        val vrati = vreme[0].toFloat() + (vreme[1].toFloat())/100
        return vrati
    }
}