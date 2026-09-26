BANNER + REYKA KALKULYATOR — Android project

This recreates the Excel calculator shown in the supplied screenshot.
Default values match the screenshot:
3.10 m × 3.80 m
Banner: 25,000 so'm/m²
3×4 reyka: 10,000 so'm/m
3×2 reyka: 8,000 so'm/m
Montaj: 15,000 so'm/m²
Internal horizontal/vertical reyka counts: 0 / 0

Formula:
Area = width × height
Reyka jami metri = 2×width + 2×height + horizontal_count×width + vertical_count×height
3×4 total = banner + 3×4 reyka + installation
3×2 total = banner + 3×2 reyka + installation

BUILD:
1. Install Android Studio.
2. Open this folder as an existing Gradle project.
3. Let Android Studio download the Android SDK/Gradle components if requested.
4. Build > Build Bundle(s) / APK(s) > Build APK(s).
5. The debug APK will be under:
   app/build/outputs/apk/debug/app-debug.apk

The project uses no external libraries.