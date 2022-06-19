package com.example.sevensweekone.view.details

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.sevensweekone.data.HeroDTO
import com.example.sevensweekone.databinding.FragmentHeroDetailsBinding
import com.example.sevensweekone.utils.Constants.Companion.BASE_URL

class HeroDetailsFragment : Fragment() {

    private lateinit var binding: FragmentHeroDetailsBinding
    private val args: HeroDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hero = args.heroes
        setImage(hero.img)
        setData(hero)
    }

    @SuppressLint("SetTextI18n")
    private fun setData(hero: HeroDTO) {
        with(binding) {
            nameTv.text = "Name: ${hero.localized_name}"
            when (hero.primary_attr) {
                "int" -> primaryAttrTv.text = "Primary attribute: Intelligence"
                "agi" -> primaryAttrTv.text = "Primary attribute: Agility"
                "str" -> primaryAttrTv.text = "Primary attribute: Strength"
            }

            var roles = ""
            for (i in hero.roles) roles = "$roles$i, "
            rolesTv.text = "Roles: ${roles.removeSuffix(", ")}"

            attackRangeTv.text = "Attack Range: ${hero.attack_range}"
            attackTypeTv.text = "Attack Type: ${hero.attack_type}"
            baseAttackMaxTv.text = "Base Attack Max: ${hero.base_attack_max}"
            baseAttackMinTv.text = "Base Attack Min: ${hero.base_attack_min}"
            baseAgiTv.text = "Base Agility: ${hero.base_agi}"
            baseArmorTv.text = "Base Armor: ${hero.base_armor}"
            baseHealthTv.text = "Base Health: ${hero.base_health}"
            baseIntTv.text = "Base Intelligence: ${hero.base_int}"
            baseManaTv.text = "Base Mana: ${hero.base_mana}"
            baseStrTv.text = "Base Strength: ${hero.base_str}"
            moveSpeedTv.text = "Move Speed: ${hero.move_speed}"
            baseHealthRegenTv.text = "Base Health Regen: ${hero.base_health_regen}"
            baseManaRegenTv.text = "Base Mana Regen: ${hero.base_mana_regen}"
        }


    }

    private fun setImage(url: String) {

        binding.heroIv.load("$BASE_URL${url}")

    }


}