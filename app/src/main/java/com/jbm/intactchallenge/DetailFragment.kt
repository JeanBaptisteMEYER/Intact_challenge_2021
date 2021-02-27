package com.jbm.intactchallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import com.jbm.intactchallenge.databinding.FragmentDetailBinding
import com.jbm.intactchallenge.utils.Constants
import com.jbm.intactchallenge.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    val TAG: String =  "tag.jbm." + this::class.java.simpleName

    private var productId = 0

    val mainViewModel: MainViewModel by activityViewModels()

    lateinit var binding: FragmentDetailBinding


    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the ID of the selected product from argument
        arguments?.let {
            productId = it.getInt(Constants().ID_PARAM)
        }
    }

    @Override
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // create our binding and set our product
        binding = FragmentDetailBinding.inflate(LayoutInflater.from(context),
            container, false)

        // set and get the product that has been selected
        mainViewModel.setLiveProductById(productId)
        binding.product = mainViewModel.liveProduct.value

        // set actionbar title with product title
        requireActivity().title = mainViewModel.liveProduct.value?.title

        return binding.root
    }

    fun onWishListClick(view: View) {
        if ((view as Button).text.equals(getString(R.string.add_to_wishlist)))
            mainViewModel.addToWishList()
        else
            mainViewModel.removeFromWishList()

        fragmentManager?.popBackStack()
    }

    companion object {
        @JvmStatic
        fun newInstance(productId: Int) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants().ID_PARAM, productId)
                }
            }
    }
}