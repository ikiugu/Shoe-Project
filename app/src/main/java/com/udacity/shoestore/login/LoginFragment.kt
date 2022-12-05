package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.loginButton.setOnClickListener {
            navigate()
        }

        binding.createAccountButton.setOnClickListener {
            navigate()
        }

        return binding.root
    }

    private fun navigate() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
    }
}