# Architectural Blueprint

## Module Structure
- `:app`: Entry point, lifecycle management.
- `:core:design-system`: UI Primitives, glassmorphism, theme.
- `:core:common`: Shared utilities, DI modules (Koin), base interfaces.
- `:features:launcher`: Vertical list UI, app management, gesture detection.
- `:features:ai-engine`: Gemini API client, local caching (Room), sorting logic.

## Inter-Module Communication
- Modules depend strictly on `:core:common`.
- Features never depend on each other. Use a Registry or event bus pattern if required.
- DI is fully managed via Koin; modules define their own `Module` definitions.

## Data Flow
- Use Unidirectional Data Flow (UDF).
- `ViewModel` (in features) -> `StateFlow` -> `Compose UI`.
- Data layer exposes `Flow<Result<T>>` for asynchronous operations.
