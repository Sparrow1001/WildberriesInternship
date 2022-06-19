package com.example.eightsweekone.data

import java.io.Serializable

data class HeroDTO(
    val attack_range: Int,
    val attack_type: String,
    val base_agi: Int,
    val base_armor: Double,
    val base_attack_max: Int,
    val base_attack_min: Int,
    val base_health: Int,
    val base_health_regen: Double,
    val base_int: Int,
    val base_mana: Int,
    val base_mana_regen: Double,
    val base_str: Int,
    val icon: String,
    val id: Int,
    val img: String,
    val localized_name: String,
    val move_speed: Int,
    val primary_attr: String,
    val roles: List<String>
) : Serializable