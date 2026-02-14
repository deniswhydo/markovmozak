package com.markovmozak.app.notifications

import java.time.LocalTime

object MarkoMessages {

    // --- Greetings (time-dependent) ---

    private val morningGreetings = listOf(
        "Dobro jutro, Marko! Spreman za još jedan dan kaosa?",
        "Jutro, legendo! Kava prva, zadaci drugi... ili nikad.",
        "Marko, sunce je izašlo. Tvoji zadaci čekaju. Kao i uvijek.",
        "Dobro jutro! Novi dan, nove prilike da odgađaš stvari.",
        "E Marko, ustao si! To je već pola posla. Doslovno.",
        "Jutro! Jesi sanjao da si produktivan? Probaj to i na javi.",
        "Dobro jutro, Marko! Kava te čeka. Zadaci isto, ali s manje entuzijazma.",
        "Ustaj, šampione! Tvoja lista zadataka se neće sama riješiti. Vjeruj mi, probala je.",
        "Marko! Novi dan, iste navike. Ali tko zna, možda danas bude drugačije? 😂",
        "Jutarnji alarm: Marko, imaš odgovornosti. Znam, šokantno."
    )

    private val afternoonGreetings = listOf(
        "Marko, jesi li bar nešto obavio danas?",
        "Pola dana je prošlo. Koliko zadataka si riješio? Ne odgovaraj.",
        "Marko, ručak je gotov. Zadaci nisu.",
        "Popodne je! Savršeno vrijeme za... odgađanje.",
        "Hej Marko, dan ne traje vječno. Ali tvoja lista zadataka možda da.",
        "Popodnevna provjera: Još uvijek čekam da nešto odradiš.",
        "Marko, već je popodne. Nemoj mi reći da si cijelo jutro 'planirao'.",
        "Pola dana gotovo! Rezultati? Nema ih? Klasika.",
        "Hej, jesi bar ručao? Jer zadatke sigurno nisi pojeo.",
        "Popodne je, Marko. Idealan trenutak da počneš ono što si trebao ujutro."
    )

    private val eveningGreetings = listOf(
        "Marko, sutra je novi dan... za odgađanje.",
        "Večer je. Što si danas napravio? Nemoj lagati.",
        "Marko, još malo pa spavanje. Zadaci? Sutra. Možda.",
        "Dobra večer! Tvoji zadaci ti žele laku noć. I dalje čekaju.",
        "E Marko, barem si preživio još jedan dan. Zadaci isto.",
        "Večer je! Vrijeme za odmor. Čekaj, od čega se odmarat?",
        "Marko, dan je gotov. Tvoja produktivnost danas? Neću komentirati.",
        "Laku noć, Marko! Sutra te čeka sve što si danas ignorirao. 🌙",
        "Večernji izvještaj: Zadaci - 1, Marko - 0. Ali sutra je revanš!",
        "Hej Marko, jesi čuo za 'early bird gets the worm'? Sutra probaj."
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
        "WOOOOW! Marko u akciji! 🚀",
        "Tko si ti i što si napravio s pravim Markom?!",
        "Još %d zadataka. Ali hej, napredak je napredak!",
        "Zadatak riješen! Zaslužio si pauzu. Ali ne predugu. 😏",
        "Marko produktivan?! Trebam screenshot kao dokaz!",
        "LEGENDO! Jedan manje, slava tebi! 🏆",
        "Ovo zaslužuje pivu! Ali tek nakon ostalih %d zadataka.",
        "Nemoguće! Marko radi! Zovite Guinness! 📖",
        "Odlično! Sad zamisli da tako radiš SVAKI dan!"
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
        "Imaš %d otvorenih zadataka. Čak i ja se umaram od gledanja.",
        "%d zadataka?! Marko, ovo je aplikacija, ne roman!",
        "Imaš %d stvari za obaviti. Dišem duboko umjesto tebe.",
        "Marko, %d zadataka. Da sam čovjek, dobio bih napadaj panike.",
        "Još %d zadataka. Trebam li zvati pomoć? 🚨",
        "Lista raste, Marko. %d zadataka. Kad misliš početi?"
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
        "Nema ništa za napraviti? Sumnjam, ali OK.",
        "Wow, prazno! Kao tvoj frižider subotom.",
        "Nula zadataka. Ili si genije ili si zaboravio sve upisati.",
        "Prazna lista! Marko, tko si ti i gdje je pravi Marko?",
        "Ništa za danas? Sumnjivo mirno... previše mirno. 🕵️"
    )

    fun getEmptyTaskMessage(): String = emptyTaskMessages.random()

    // --- Escalating reminders ---

    private val level0Reminders = listOf(
        "Hej Marko, podsjećam te: %s",
        "Marko, sjeti se: %s",
        "Prijateljski podsjetnik: %s 😊",
        "Psst, Marko! Ne zaboravi: %s",
        "Mali podsjetnik za tebe: %s ✨"
    )

    private val level1Reminders = listOf(
        "Marko... OVO JOŠ NISI NAPRAVIO?! %s",
        "Drugi put te pitam: %s. Hajde!",
        "Marko, ne ignoriraj me! %s",
        "Halo? Marko? %s još čeka!",
        "Ponovo ja. %s. Sjećaš se? Očito ne."
    )

    private val level2Reminders = listOf(
        "MARKO! Treći put te pitam! %s!!",
        "OVO JE OZBILJNO: %s. NAPRAVI TO!",
        "Marko, počinjem se ljutiti. %s. SADA.",
        "Gubim strpljenje, Marko. %s. ODMAH!",
        "Koliko puta još?! %s!!! Hajde već jednom!"
    )

    private val level3Reminders = listOf(
        "Zovem ti mamu ako ne napraviš: %s 😤",
        "ZADNJI PUT: %s!!! Poslije ovoga šaljem poruku svima!",
        "MARKO!!! %s!!! NE TESTIRAJ ME!!!",
        "KRAJ STRPLJENJA! %s! Zovem ti ženu, mamu I šefa!!!",
        "NUKLEARNA OPCIJA: %s! Objavljujem na Facebooku da ne obavljaš zadatke! 💣",
        "Marko, %s! Idem ti obrisati WiFi lozinku ako ne napraviš! 📵"
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
        "Prazna lista. Nadam se da imaš barem nešto u frižideru.",
        "Marko, znam da voliš prazan frižider, ali obitelj ne.",
        "Nula artikala. Planiraš jesti sjećanja za večeru?",
        "Prazna lista! Da nisi na dijeti? Ako jesi, svejedno kupi WC papir."
    )

    fun getEmptyShoppingMessage(): String = emptyShoppingMessages.random()

    // --- Shopping completion ---

    private val shoppingDoneMessages = listOf(
        "Sve kupljeno! Marko, heroj supermarketa! 🛒",
        "Lista gotova! Frižider će biti ponosan.",
        "Svaka čast, kupovina obavljena! 🎉",
        "Bravo Marko! Sad si punopravni odrasli čovjek! Za danas.",
        "Kupovina done! Ekonomija ti zahvaljuje. 💰",
        "Sve kupljeno! Nadam se da nisi zaboravio nešto... opet."
    )

    fun getShoppingDoneMessage(): String = shoppingDoneMessages.random()

    // --- Task delete messages ---

    private val deleteMessages = listOf(
        "Obrisano! Kao da nikad nije ni postojalo.",
        "Zadatak nestao! Poof! ✨",
        "Izbrisano. Tajnu čuvam. 🤫",
        "Nema ga više! Problem riješen... na svoj način.",
        "Obrisano! Nitko neće znati. Osim ja. 👀",
        "Zadatak eliminiran! Efikasno. Sviđa mi se pristup."
    )

    fun getDeleteMessage(): String = deleteMessages.random()

    // --- Motivational (when user opens app after long time) ---

    val comebackMessages = listOf(
        "Marko se vratio! Mislio sam da si me izbrisao. 😢",
        "O, pa tko je tu! Dugo te nije bilo, Marko!",
        "Živ si! Već sam htio poslati potragu!",
        "Marko! Koliko dugo si mislio da će se zadaci sami obaviti?",
        "Povratak legende! Tvoji zadaci su te čekali. Strpljivo. Previše strpljivo."
    )

    fun getComebackMessage(): String = comebackMessages.random()
}
