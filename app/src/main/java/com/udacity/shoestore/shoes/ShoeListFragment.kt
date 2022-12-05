package com.udacity.shoestore.shoes

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {
    private val shoeViewModel: ShoeViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        shoeViewModel.shoes.observe(viewLifecycleOwner, Observer { shoes ->
            binding.shoeList.removeAllViews()
            for (shoe in shoes) {
                addNewShoe(shoe)
            }
        })

        binding.addShoe.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun addNewShoe(shoe: Shoe) {
        val shoeItemView: TextView = layoutInflater.inflate(R.layout.shoe_list_item, null) as TextView
        shoeItemView.text = String.format(getString(R.string.shoe_listing_text), shoe.name, shoe.size, shoe.company)
        binding.shoeList.addView(shoeItemView)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.app_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionLogout -> {
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }

}