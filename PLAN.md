# Nxo Launcher - Technical Specification

## 1. Architectural Blueprint
Nxo is a multi-module project. Communication between modules is handled via explicit interfaces defined in `:core:common` to maintain strict decoupling.

- `:app`: Thin entry point. Handles `LauncherActivity` registration and Koin initialization.
- `:core:design-system`: Contains:
  - Custom `Compose` theme (Material Design 3).
  - Reusable `Modifier` extensions for glassmorphism (via `graphicsLayer` shaders/blur).
  - Shared typography, color tokens, and animation specifications.
- `:core:common`: Holds shared Koin modules, domain models, and interfaces.
- `:features:launcher`: Responsible for:
  - `LauncherActivity` UI (Vertical List).
  - App list fetching (`PackageManager`).
  - Gesture handling (swipe detection for interaction).
- `:features:ai-engine`: Responsible for:
  - Gemini API client wrapper.
  - App classification/sorting algorithms.
  - Local caching (Room) of AI-sorted lists to minimize network usage.

## 2. UI/UX Implementation Strategy
- **Glassmorphism:** We will leverage `Modifier.graphicsLayer` and `RenderEffect` (where supported) to create performant, blurred glass backgrounds. Avoid standard `blur` modifiers if they affect scroll performance.
- **Animations:** Custom `Transition` and `AnimatedContent` for expressive, smooth interactions.
- **State Management:** `StateFlow` and `ViewModel` across all features to ensure UI responsiveness.

## 3. AI Engine Strategy
- **Gemini Integration:** Use the Google AI Client SDK for Kotlin.
- **Security:** API keys must never be hardcoded. Use `BuildConfig` or encrypted `DataStore` (using `EncryptedSharedPreferences` or similar) for key storage.
- **Optimization:** AI sorting runs asynchronously. Results are cached locally (Room). The UI initially displays the system-default list while the AI logic runs in the background.

## 4. Performance Goals
- **App Launch Time:** < 500ms.
- **Frame Rate:** Locked 60/90/120Hz (jank-free scrolling).
- **Background Usage:** Aggressive job scheduling (WorkManager) for AI syncs when device is idle/charging.
