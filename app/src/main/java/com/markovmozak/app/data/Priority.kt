package com.markovmozak.app.data

enum class Priority(val emoji: String, val displayName: String, val level: Int) {
    HITNO("🔥", "Hitno kao požar", 3),
    SREDNJE("😅", "Može sutra... ili ne", 2),
    NISKO("🦥", "Kad stigneš, legendo", 1);

    fun label(): String = "$emoji $displayName"
}
