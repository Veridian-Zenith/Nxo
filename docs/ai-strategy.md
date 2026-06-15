# AI Engine Strategy

## Gemini Integration
- **SDK:** Google AI Client SDK for Android.
- **API Keys:** MUST NOT be hardcoded. Use `local.properties` (ignored by git) and inject via build config.
- **Workflow:** 
  1. Trigger app list update via `WorkManager`.
  2. Send app meta to Gemini.
  3. Receive sorted classification.
  4. Save to `Room` database.
  5. UI observes `Room` database (`Flow`).

## Caching & Offline
- AI results are cached locally in `Room`.
- If offline, fallback to system-default sorting (A-Z).
