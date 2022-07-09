package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseMethod
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_items.view.*
import kotlinx.android.synthetic.main.fragment_shoe_detail.*
import kotlinx.android.synthetic.main.fragment_shoe_detail.view.*
/* My Handler Methods */

interface MyHandlersListener {
    fun saveShoe(view: View, shoe: Shoe)
}


class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private lateinit var nameText: TextView
    private  val viewModel: ShoeListSharedViewModel by activityViewModels()
    private lateinit var myHandlersListener: MyHandlersListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        myHandlersListener = object : MyHandlersListener {
            override fun saveShoe(view: View, shoe: Shoe) {
                viewModel.addShoe(shoe)
                view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())

            }
        }

        nameText = binding.shoeNameText
        setHasOptionsMenu(true)
        binding.shoe = Shoe("",12.0, "", "")
        binding.shoeVM = viewModel
        binding.myHandlers = myHandlersListener





        binding.cancelButton.setOnClickListener { view ->
            view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }

//        binding.saveButton.setOnClickListener{ view ->
//            view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
//        }


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        logout()
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController())


    }

    private fun logout() {
        view?.findNavController()!!.navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToLoginFragment2())
    }


}