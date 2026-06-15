# Design System Specification

## Material Expressive
- Follow Material Design 3 guidelines with "Expressive" extensions (dynamic color, fluid motion).

## Glassmorphism Strategy
- Use `Modifier.graphicsLayer` for performance.
- Avoid heavy `Blur` filters on scrollable elements. Implement background-aware shaders or custom drawing if necessary.
- Border rendering: Custom `DrawModifier` for elegant 1px borders with sub-pixel anti-aliasing.

## Tokens
- Colors: Define in `Color.kt` using M3 dynamic color mappings.
- Typography: Use M3 type scale.
- Spacing: 4dp grid system.
