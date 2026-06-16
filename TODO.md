# Nxo Development Roadmap

## 🛠️ Phase 1: Foundation & Stability (Current)
- [ ] **Settings Menu**: Implement a dedicated Setup Screen for:
    - Inputting Gemini API Key (stored via `SettingsRepository`).
    - Toggle for AI Sorting (ON/OFF).
    - Slider/Selector for Top Apps count (1-10).
    - Instructions for obtaining a Gemini API key.
- [ ] **Usage Stats Permission**: Implement the flow to request `android.permission.PACKAGE_USAGE_STATS` (redirecting user to System Settings), as it is a special permission.
- [ ] **Build Stability**: Finalize `gradle.properties` and `build.gradle.kts` to eliminate all lint errors and daemon memory warnings.

## 🚀 Phase 2: Core Launcher Functionality
- [ ] **Alphabet Sidebar Logic**: Implement the `scrollToItem` logic in `LauncherScreen` to allow the alphabet sidebar to navigate the app list.
- [ ] **Home Launcher Integration**: Ensure the app correctly handles `HOME` intents and provide a "Set as Default" prompt if not already selected.
- [ ] **App Search**: Implement a fast, global search bar to filter the app list in real-time.
- [ ] **Quick Actions**: Implement long-press support to display Android App Shortcuts.
- [ ] **Wallpaper Integration**: Support for system wallpaper rendering and dynamic wallpaper changes.
- [ ] **App Launching**: Verify and optimize the intent-launching mechanism for all app types.

## 🎨 Phase 3: Visual Identity & "Non-Boring" UI
- [ ] **Advanced Glassmorphism**: Implement actual background blur/shaders as specified in `design-system.md` (avoiding simple alpha).
- [ ] **Fluid Animations**: Add transition animations when sorting changes or when scrolling through the vertical list.
- [ ] **Neon Accents**: Implement dynamic neon glows and gradients that react to the active app's theme colors.
- [ ] **Icon Pack Support**: Allow users to apply third-party icon packs for total visual customization.
- [ ] **Customizable Grid/Spacing**: Allow users to adjust the vertical gap and item width.

## 🧠 Phase 4: AI & Performance Optimization
- [ ] **Gemini 3.1 Flash Integration**: Verify the `AiEngineClient` is correctly utilizing the 3.1 model with user-provided keys.
- [ ] **Intelligent Folders**: AI-suggested app grouping into "Folders" based on usage context.
- [ ] **Baseline Profiles**: Implement Baseline Profiles to eliminate first-frame jank and optimize cold starts (as per `build-optimization.md`).
- [ ] **R8/ProGuard**: Configure full-mode shrinking and obfuscation for production release.

## 🛠️ Phase 5: Advanced System Integrations
- [ ] **Glass Widgets**: Support for Android widgets rendered with the Nxo glassmorphism style.
- [ ] **Quick Settings Shortcuts**: Direct access to system toggles from the launcher interface.
- [ ] **Custom Gesture Engine**: Implement a custom gesture system (e.g., swipe up for all apps, swipe down for notifications).
