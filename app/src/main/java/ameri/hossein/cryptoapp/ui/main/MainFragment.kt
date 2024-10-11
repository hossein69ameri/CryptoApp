package ameri.hossein.cryptoapp.ui.main

import ameri.hossein.cryptoapp.R
import ameri.hossein.cryptoapp.data.model.main.ResponseCoinsList.ResponseCoinsListItem
import ameri.hossein.cryptoapp.data.model.main.ResponseCoinsMarket
import ameri.hossein.cryptoapp.data.model.main.ResponseSupportedCurrencies
import ameri.hossein.cryptoapp.databinding.FragmentMainBinding
import ameri.hossein.cryptoapp.utils.base.BaseFragment
import ameri.hossein.cryptoapp.utils.base.BaseState
import ameri.hossein.cryptoapp.utils.isVisible
import ameri.hossein.cryptoapp.utils.setupRecyclerview
import ameri.hossein.cryptoapp.utils.showSnackBar
import ameri.hossein.cryptoapp.viewmodel.MainViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    @Inject
    lateinit var coinsAdapter: CoinsAdapter

    //Other
    private val viewModel by viewModels<MainViewModel>()
    private var coinPriceId = ""
    private var coinPriceName = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Call api
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                if (isNetworkAvailable) {
                    viewModel.intentChannel.send(MainIntent.CallCoinsList)
                    viewModel.intentChannel.send(MainIntent.CallSupportedCurrencies)
                    viewModel.intentChannel.send(MainIntent.CallCoinsMarkets)
                }
            }
        }
        //Load data
        handleStates()
    }

    private fun handleStates() {
        binding.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.CREATED) {
                    viewModel.state.collect { state ->
                        when (state) {
                            is BaseState.Idle -> {}
                            is BaseState.Loading -> coinsMarketLoading.isVisible = true
                            is BaseState.Error -> {
                                state.error?.let { root.showSnackBar(it) }
                            }

                            is BaseState.Main.LoadCoinsList -> initCoinsSpinner(state.coinsList)
                            is BaseState.Main.LoadSupportedCurrencies -> initSupportedSpinner(state.supportedList)
                            is BaseState.Main.LoadingCoinPrice -> exchangeLoading.isVisible(true, exchangePriceTxt)
                            is BaseState.Main.LoadCoinPrice -> initCoinPrice(state.price)
                            is BaseState.Main.LoadCoinsMarkets -> initCoinsMarketRecyclerView(state.coinsMarkets)
                            is BaseState.Main.NavigateToDetail -> navigateToDetail(state.id)
                            else -> {}
                        }
                    }
                }
            }
        }
    }

    private fun initCoinsSpinner(data: MutableList<ResponseCoinsListItem>) {
        //Coins name
        val coinsName = mutableListOf<String>()
        data.forEach { coinsName.add(it.name) }
        //Adapter
        val coinsAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_menu_popup_item, coinsName)
        //Update view
        binding.fromCoinAutoTxt.apply {
            setAdapter(coinsAdapter)
            setOnItemClickListener { _, _, position, _ ->
                coinPriceId = data[position].id
                //Call api
                if (coinPriceName.isNotEmpty())
                    callCoinPriceApi()
            }
        }
    }

    private fun initSupportedSpinner(data: ResponseSupportedCurrencies) {
        //Coins name
        val coinsName = mutableListOf<String>()
        data.forEach { coinsName.add(it) }
        //Adapter
        val coinsAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_menu_popup_item, coinsName)
        //Update view
        binding.toCoinAutoTxt.apply {
            setAdapter(coinsAdapter)
            setOnItemClickListener { _, _, position, _ ->
                coinPriceName = data[position]
                //Call api
                if (coinPriceId.isNotEmpty())
                    callCoinPriceApi()
            }
        }
    }

    private fun callCoinPriceApi() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                if (isNetworkAvailable) {
                    viewModel.intentChannel.send(MainIntent.CallCoinPrice(coinPriceId, coinPriceName))
                }
            }
        }
    }

    private fun initCoinPrice(price: Double) {
        binding.apply {
            exchangeLoading.isVisible(false, exchangePriceTxt)
            exchangePriceTxt.text = price.toString()
        }
    }

    private fun initCoinsMarketRecyclerView(coinsMarkets: ResponseCoinsMarket) {
        binding.apply {
            coinsMarketLoading.isVisible = false
            //Recyclerview
            coinsAdapter.setData(coinsMarkets)
            val layoutManager = GridLayoutManager(requireContext(), 2)
            coinsMarketList.setupRecyclerview(layoutManager, coinsAdapter)
            //Click
            coinsAdapter.setOnItemClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.CREATED) {
                        viewModel.intentChannel.send(MainIntent.NavigateToDetail(it.id!!))
                    }
                }
            }
        }
    }

    private fun navigateToDetail(id: String) {
        val direction = MainFragmentDirections.actionToDetail(id)
        findNavController().navigate(direction)
    }
}