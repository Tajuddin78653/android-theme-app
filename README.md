# Android Theme App (Themify)

An Android app to browse, preview and apply themes — built with Kotlin, Jetpack Compose and Material Design 3.

## Features (v1)
- Browse themes in a grid — name, preview thumbnail, category
- Preview a theme — wallpaper, colour palette, icon style, font sample
- Apply theme — sets wallpaper and accent colors
- Categories — Dark, Light, Minimal, Colorful, Nature
- Search and filter by category

## Tech Stack
| Layer | Technology |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose + Material Design 3 |
| Architecture | MVVM + StateFlow |
| Navigation | Compose Navigation |
| Data | Local JSON assets |
| Theme persistence | DataStore Preferences |
| Min SDK | API 26 (Android 8.0) |

## Project Structure
```
app/src/main/
├── java/com/themify/app/
│   ├── data/
│   │   ├── model/Theme.kt
│   │   └── repository/ThemeRepository.kt
│   ├── ui/
│   │   ├── browse/BrowseScreen.kt
│   │   ├── preview/PreviewScreen.kt
│   │   ├── components/ThemeCard.kt
│   │   └── theme/AppTheme.kt
│   ├── viewmodel/ThemeViewModel.kt
│   ├── navigation/Navigation.kt
│   └── MainActivity.kt
└── assets/
    └── themes.json
```

## Getting Started
1. Clone the repo
2. Open in Android Studio Hedgehog or later
3. Run on emulator or device (API 26+)

## Roadmap
- [ ] v1 — Browse, Preview, Apply
- [ ] v2 — Favourites, Download count
- [ ] v3 — AI theme generator

## References
- [tminet/ComposeThemeSwitch](https://github.com/tminet/ComposeThemeSwitch) — runtime theme switching pattern
- [daniyaljavaid/ThemingInCompose](https://github.com/daniyaljavaid/ThemingInCompose) — pluggable theming for Compose
- Agents used: `engineering-mobile-app-builder`, `design-ui-designer`, `design-ux-architect` from [Clone-agency-agents](https://github.com/Tajuddin78653/Clone-agency-agents)
