package com.example.thirdweek

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.thirdweek.R
import com.example.thirdweek.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.listNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.authConstBt.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_authorizationConstraintFragment)
        }

        binding.calcConstBt.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_calculatorConstraintFragment)
        }

        binding.musicConstBt.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_musicConstraintFragment)
        }

        binding.profileConstBt.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_profileConstraintFragment)
        }

        binding.authBt.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_authorizationFragment)
        }

        binding.calcBt.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_calculatorFragment)
        }

        binding.musicBt.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_musicFragment)
        }

        binding.profileBt.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_profileFragment)
        }

        binding.authComposeBt.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_authorizationComposeActivity)
        }

        binding.musicComposeBt.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_musicComposeActivity)
        }
    }

}