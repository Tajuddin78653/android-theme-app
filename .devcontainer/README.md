# Codespace Setup — Themify Android App

This devcontainer configures a full Android build environment in GitHub Codespaces.
No local install needed.

## How to use

### 1. Open Codespace
- Go to https://github.com/Tajuddin78653/android-theme-app
- Click green **Code** button → **Codespaces** tab → **New codespace**
- Wait ~3 minutes for setup

### 2. Build the APK
```bash
./gradlew assembleDebug
```
APK output: `app/build/outputs/apk/debug/app-debug.apk`

### 3. Download the APK
- In VS Code file explorer → navigate to `app/build/outputs/apk/debug/`
- Right-click `app-debug.apk` → **Download**

### 4. Test in browser (no phone needed)
- Go to https://appetize.io
- Click **Upload** → upload `app-debug.apk`
- Click **Run** → your app runs in a browser emulator

### 5. Install on your Android phone
- Transfer APK to phone via WhatsApp/email/Google Drive
- Open it → tap Install (enable "Install from unknown sources" if needed)

## Useful commands
```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Clean build
./gradlew clean assembleDebug

# Check for lint errors
./gradlew lint
```
