# Nxo Launcher - Development Plan

## Overview
Nxo is a Niagara-inspired, highly aesthetic Android launcher for Android 14-17+. It focuses on "Material Expressive" design principles, glassmorphism, and intelligent AI-driven app organization.

## Tech Stack
- **Language:** Kotlin (100% Kotlin-only codebase)
- **UI Framework:** Jetpack Compose (Material Design 3+)
- **Dependency Injection:** Koin (for explicit, transparent DI)
- **Architecture:** Multi-module project (Gradle Kotlin DSL)
- **Concurrency:** Coroutines + Flow
- **AI Integration:** Google AI Client SDK for Android (Gemini)
- **Local Data:** Room + DataStore

## Project Structure
The project is built for heavy modularity:
- `:app`: Entry point and lifecycle management.
- `:core:design-system`: Shared UI primitives, theme, glassmorphism shaders, and custom design components.
- `:core:common`: Shared DI modules and utilities.
- `:features:launcher`: Main vertical list UI.
- `:features:ai-engine`: Gemini integration and app-sorting logic.

## Design Philosophy
- **Aesthetic First:** Deep integration of transparency, glass effects, borders, and expressive animations.
- **Performance:** Fluid interactions are paramount.
- **Transparency:** The architecture avoids "magic" abstractions (e.g., opting for Koin over Hilt).

## Licensing
This project is licensed under the **Open Software License 3.0 (OSL-3.0)**.
