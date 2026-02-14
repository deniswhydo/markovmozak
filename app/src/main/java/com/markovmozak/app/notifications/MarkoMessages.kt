package com.markovmozak.app.notifications

import java.time.LocalTime

object MarkoMessages {

    // --- Greetings (time-dependent) ---

    private val morningGreetings = listOf(
        "Dobro jutro, Marko! Spreman za još jedan dan kaosa?",
        "Jutro, legendo! Kava prva, zadaci drugi... ili nikad.",
        "Marko, sunce je izašlo. Tvoji zadaci čekaju. Kao i uvijek.",
        "Dobro jutro! Novi dan, nove prilike da odgađaš stvari.",
        "E Marko, ustao si! To je već pola posla. Doslovno."
    )

    private val afternoonGreetings = listOf(
        "Marko, jesi li bar nešto obavio danas?",
        "Pola dana je prošlo. Koliko zadataka si riješio? Ne odgovaraj.",
        "Marko, ručak je gotov. Zadaci nisu.",
        "Popodne je! Savršeno vrijeme za... odgađanje.",
        "Hej Marko, dan ne traje vječno. Ali tvoja lista zadataka možda da."
    )

    private val eveningGreetings = listOf(
        "Marko, sutra je novi dan... za odgađanje.",
        "Večer je. Što si danas napravio? Nemoj lagati.",
        "Marko, još malo pa spavanje. Zadaci? Sutra. Možda.",
        "Dobra večer! Tvoji zadaci ti žele laku noć. I dalje čekaju.",
        "E Marko, barem si preživio još jedan dan. Zadaci isto."
    )

    fun getGreeting(): String {
        val hour = LocalTime.now().hour
        val greetings = when {
            hour in 5..11 -> morningGreetings
            hour in 12..17 -> afternoonGreetings
            else -> eveningGreetings
        }
        return greetings.random()
    }

    // --- Task completion messages ---

    private val completionMessages = listOf(
        "BRAVO MARKO! Nisi totalno beskoristan! 🎉",
        "E, vidiš da možeš kad hoćeš!",
        "Jedan manje. Samo još %d... ne paničari.",
        "Svaka čast! Nastavi tako i možda završiš sve do 2030.",
        "Marko obavio zadatak?! Ovo ide u kalendar! 📅",
        "Čekaj, ti zapravo RADIŠ stvari? Impresivno!",
        "Bravo! Tvoja mama bi bila ponosna. Možda.",
        "WOOOOW! Marko u akciji! 🚀"
    )

    fun getCompletionMessage(remainingTasks: Int): String {
        val msg = completionMessages.random()
        return if (msg.contains("%d")) String.format(msg, remainingTasks) else msg
    }

    // --- Many open tasks ---

    private val manyTasksMessages = listOf(
        "Marko, imaš %d zadataka. Što čekaš, Božić?",
        "Ova lista je duža od tvog izgovora zašto nisi ništa napravio.",
        "%d zadataka čeka. Možda počni s jednim? Samo prijedlog.",
        "Marko, %d zadataka. To je novi rekord! Ali ne onaj dobar.",
        "Imaš %d otvorenih zadataka. Čak i ja se umaram od gledanja."
    )

    fun getManyTasksMessage(count: Int): String {
        val msg = manyTasksMessages.random()
        return if (msg.contains("%d")) String.format(msg, count) else msg
    }

    // --- Empty task list ---

    private val emptyTaskMessages = listOf(
        "Čisto kao tvoja savjest... sumnjivo. 🤔",
        "Ili si sve obavio, ili si sve zaboravio upisati.",
        "Nema zadataka? Marko, jesi li siguran?",
        "Prazna lista! Ili si produktivan ili si u poricanju.",
        "Nema ništa za napraviti? Sumnjam, ali OK."
    )

    fun getEmptyTaskMessage(): String = emptyTaskMessages.random()

    // --- Escalating reminders ---

    private val level0Reminders = listOf(
        "Hej Marko, podsjećam te: %s",
        "Marko, sjeti se: %s",
        "Prijateljski podsjetnik: %s 😊"
    )

    private val level1Reminders = listOf(
        "Marko... OVO JOŠ NISI NAPRAVIO?! %s",
        "Drugi put te pitam: %s. Hajde!",
        "Marko, ne ignoriraj me! %s"
    )

    private val level2Reminders = listOf(
        "MARKO! Treći put te pitam! %s!!",
        "OVO JE OZBILJNO: %s. NAPRAVI TO!",
        "Marko, počinjem se ljutiti. %s. SADA."
    )

    private val level3Reminders = listOf(
        "Zovem ti mamu ako ne napraviš: %s 😤",
        "ZADNJI PUT: %s!!! Poslije ovoga šaljem poruku svima!",
        "MARKO!!! %s!!! NE TESTIRAJ ME!!!"
    )

    fun getEscalatingReminder(taskTitle: String, level: Int): String {
        val reminders = when (level.coerceIn(0, 3)) {
            0 -> level0Reminders
            1 -> level1Reminders
            2 -> level2Reminders
            else -> level3Reminders
        }
        return String.format(reminders.random(), taskTitle)
    }

    // --- Empty shopping list ---

    private val emptyShoppingMessages = listOf(
        "Frižider se neće sam napuniti, Marko.",
        "Prazna lista za kupovinu? Živiš od zraka?",
        "Ništa za kupiti? Marko, barem kruh i mlijeko!",
        "Prazna lista. Nadam se da imaš barem nešto u frižideru."
    )

    fun getEmptyShoppingMessage(): String = emptyShoppingMessages.random()

    // --- Shopping completion ---

    private val shoppingDoneMessages = listOf(
        "Sve kupljeno! Marko, heroj supermarketa! 🛒",
        "Lista gotova! Frižider će biti ponosan.",
        "Svaka čast, kupovino obavljena! 🎉"
    )

    fun getShoppingDoneMessage(): String = shoppingDoneMessages.random()

    // --- Task delete messages ---

    private val deleteMessages = listOf(
        "Obrisano! Kao da nikad nije ni postojalo.",
        "Zadatak nestao! Poof! ✨",
        "Izbrisano. Tajnu čuvam. 🤫"
    )

    fun getDeleteMessage(): String = deleteMessages.random()
}
