package com.udacity.shoestore.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding

class ShoeDetailFragment : Fragment() {
    private val shoeViewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.saveButton.setOnClickListener {
            with(binding) {
                if (shoeNameEditText.text.isEmpty() || companyNameEditText.text.isEmpty() || shoeSizeEditText.text.isEmpty() || descriptionEditText.text.isEmpty()) {
                    Toast.makeText(context, getString(R.string.field_validation_error_message), Toast.LENGTH_SHORT).show()
                } else {
                    shoeViewModel.addShoe(
                        name = shoeNameEditText.text.toString().trim(),
                        company = companyNameEditText.text.toString().trim(),
                        size = shoeSizeEditText.text.toString().toDouble(),
                        description = descriptionEditText.text.toString().trim()
                    )
                    findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
                }
            }
        }

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }

        return binding.root
    }
}