package com.markovmozak.app.data

enum class Category(val emoji: String, val displayName: String) {
    KUPOVINA("🛒", "Kupovina"),
    ZDRAVLJE("🏥", "Zdravlje"),
    POSAO("🔧", "Posao"),
    TERMINI("📅", "Termini"),
    KUCA("🏠", "Kuća"),
    DRUSTVO("🍺", "Društvo"),
    OSTALO("❓", "Ostalo");

    fun label(): String = "$emoji $displayName"
}
