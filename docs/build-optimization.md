# Build Optimization Specification

To achieve the performance and efficiency characteristic of CachyOS and high-performance system engineering, the Nxo build process will employ aggressive optimization techniques.

## Toolchain & Compilation
- **JDK:** Adopt **Temurin JDK 21** as the required toolchain for builds to ensure consistent, high-performance JVM bytecode generation.
- **R8/D8:** Full-mode shrinking, obfuscation, and optimization enabled. Aggressive method inlining and resource shrinking.
- **Native Code (NDK/Clang):** For performance-critical components (custom shaders, heavy math for animations), we will implement JNI wrappers using C++23/26 (via Clang 19+).
- **Link-Time Optimization (LTO):** Enabled for all native libraries (`-flto=thin`).

## Performance Engineering
- **Baseline Profiles:** Mandatory for the launcher to ensure fast cold startup and jank-free first-frame rendering.
- **Memory Management:** Focus on avoiding object allocation in inner drawing loops. Utilize primitive collections where appropriate.
- **Compiler Flags:** Enable all relevant `kotlinOptions` for performance, such as `jvmTarget = "21"`, and aggressive IR compiler optimizations.

## Optimization Strategy
- **Modularization:** Strict boundary management to enable parallel compilation and incremental build optimization.
- **Resources:** Strict resource shrinking, removal of unused assets.
