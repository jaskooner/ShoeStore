package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import kotlinx.android.synthetic.main.fragment_items.view.*
import org.w3c.dom.Text


class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private var mContainer: ViewGroup? = null
    private val viewModel: ShoeListSharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContainer = container
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        viewModel.shoes.observe(viewLifecycleOwner, Observer { shoes ->

            Log.i("ShoeListFragment", "shoe added to scrollview")
            viewModel.shoes.value?.forEach{ shoe ->
                binding.shoeItemsLinearLayout.addView(createItemView(shoe.name, shoe.company, shoe.size, shoe.description))
            }
        })

        binding.addShoeButton.setOnClickListener { view ->
            view.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }


        return binding.root
    }

    private fun createItemView(name: String, company: String, size: Double, description: String ) : View {
        var itemView: View = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_items,
            mContainer, false)

        var nameText: TextView = itemView.shoe_name
        var companyText: TextView = itemView.shoe_company
        var sizeText: TextView = itemView.shoe_size
        var descriptionText: TextView = itemView.shoe_description

        nameText.text = name
        companyText.text = company
        sizeText.text = size.toString()
        descriptionText.text = description

        return itemView
     }

}

