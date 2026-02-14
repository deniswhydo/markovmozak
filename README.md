# MarkovMozak

**Markov Mozak = Markovs Gehirn** - Eine lustige Erinnerungs-App auf Kroatisch fuer Marko, der staendig Sachen vergisst.

Die App ist simpel zu bedienen, aber ueberall mit Humor und frechen Spruechen gespickt.

## Features

- **Tagesuebersicht** mit zeitabhaengiger Begruessung und heutigen Aufgaben
- **Aufgabenverwaltung** mit Kategorien, Prioritaeten und Faelligkeitsdatum
- **Einkaufsliste** als separater, simpler Modus
- **Eskalierende Erinnerungen** - je laenger Marko ignoriert, desto frecher werden die Nachrichten
- **Lustiges Humor-System** mit kontextabhaengigen kroatischen Spruechen

### Kategorien

| Emoji | Kategorie | Bedeutung |
|-------|-----------|-----------|
| 🛒 | Kupovina | Einkaufen |
| 🏥 | Zdravlje | Gesundheit |
| 🔧 | Posao | Arbeit |
| 📅 | Termini | Termine |
| 🏠 | Kuća | Haus |
| 🍺 | Društvo | Soziales/Freunde |
| ❓ | Ostalo | Sonstiges |

### Prioritaeten

- 🔥 **Hitno kao požar** - Dringend wie ein Brand
- 😅 **Može sutra... ili ne** - Kann bis morgen warten... oder auch nicht
- 🦥 **Kad stigneš, legendo** - Wenn du Zeit hast, Legende

### Eskalierende Erinnerungen

| Level | Beispiel |
|-------|----------|
| 0 | "Hej Marko, podsjećam te: ..." |
| 1 | "Marko... OVO JOŠ NISI NAPRAVIO?!" |
| 2 | "MARKO! Treći put te pitam! ...!!" |
| 3 | "Zovem ti mamu ako ne napraviš: ... 😤" |

## Tech Stack

- **Kotlin** + **Jetpack Compose** (Material 3)
- **Room Database** - lokale Speicherung
- **WorkManager** - Benachrichtigungen/Erinnerungen
- **Hilt** - Dependency Injection
- **Navigation Compose** - Screen-Navigation
- Target SDK 35, Min SDK 28 (Android 9+)

## Build

```bash
./gradlew assembleDebug
```

Die APK liegt dann unter `app/build/outputs/apk/debug/`.

## Farbschema

| Farbe | Hex | Verwendung |
|-------|-----|------------|
| Orange | `#FF6B35` | Primary |
| Blau | `#004E89` | Secondary |
| Creme | `#FFF8F0` | Background |
| Rot | `#D32F2F` | Error/Urgent |
