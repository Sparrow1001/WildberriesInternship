package com.example.fifthweektwo.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.navArgs
import android.view.ViewGroup
import com.example.fifthweektwo.MainActivity
import com.example.fifthweektwo.databinding.FragmentDetailsBinding
import com.example.fifthweektwo.domain.HomeViewModel
import com.example.fifthweektwo.model.HeroResponse
import com.squareup.picasso.Picasso


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: HomeViewModel
    val args: DetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).viewModel

        val hero = args.heroes
        setImage(hero.image.url)
        setData(hero)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun setData(hero: HeroResponse) {
        binding.nameTv.text = "Name: ${hero.name}"
        binding.genderTv.text = "Gender: ${hero.appearance.gender}"
        binding.hairColorTv.text = "Hair Color: ${hero.appearance.hairColor}"
        binding.heightTv.text = "Height: ${hero.appearance.height.toString()}"
        binding.weightTv.text = "Weight: ${hero.appearance.weight.toString()}"
        binding.powerTv.text = "Power: ${hero.powerstats.power}"
        binding.speedTv.text = "Speed: ${hero.powerstats.speed}"
        binding.strengthTv.text = "Strength: ${hero.powerstats.strength}"
        binding.intelligenceTv.text = "Intelligence: ${hero.powerstats.intelligence}"

        var aliases = ""
        for (i in hero.biography.aliases.indices){
            aliases += hero.biography.aliases[i] + ", "
        }

        binding.aliasesTv.text = "Aliases: $aliases"
        binding.alignmentTv.text = "Alignment: ${hero.biography.alignment}"
        binding.firsAppearanceTv.text = "First appearance: ${hero.biography.firstAppearance}"
        binding.fullNameTv.text = "Full Name: ${hero.biography.fullName}"
        binding.placeOfBirthTv.text = "Place Of Birth: ${hero.biography.placeOfBirth}"
    }

    private fun setImage(url: String) {
        showProgressBar()
        Picasso.with(context).load(url)
            .into(binding.heroIv)
        hideProgressBar()
    }

    private fun hideProgressBar(){
        binding.paginationPg.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        binding.paginationPg.visibility = View.VISIBLE
    }

}

