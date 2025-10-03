# Internet Library Android Project

## Overview

This Android project consists of a multi-module application that demonstrates internet connectivity monitoring capabilities. The project includes a reusable library module (`internetlibrary`) for monitoring network connectivity states and a sample application (`app`) that showcases the library's functionality.

## Project Structure

```
Internet/
├── app/                          # Main application module
│   ├── build.gradle             # App module build configuration
│   └── src/main/
│       ├── AndroidManifest.xml  # App manifest
│       ├── java/com/konbini/internet/
│       │   ├── MainActivity.kt  # Main activity with navigation
│       │   ├── FirstFragment.kt # First navigation fragment
│       │   └── SecondFragment.kt# Second navigation fragment
│       └── res/                 # App resources (layouts, strings, themes)
├── internetlibrary/             # Reusable library module
│   ├── build.gradle            # Library build configuration
│   └── src/main/
│       ├── AndroidManifest.xml # Library manifest with permissions
│       └── java/com/konbini/internetlibrary/
│           ├── Internet.kt     # Main library implementation
│           └── InternetService.kt # Service interface definition
├── build.gradle                # Root project build configuration
├── settings.gradle            # Project settings
└── gradle.properties          # Gradle properties
```

## Technical Details

### Architecture

The project follows a **modular architecture** with clear separation of concerns:

- **App Module**: Contains the UI layer with navigation-based fragments
- **Internet Library Module**: Contains the business logic for network monitoring

### Technology Stack

- **Language**: Kotlin 1.5.10
- **Build System**: Gradle 4.2.2
- **Android SDK**: 
  - Compile SDK: 30
  - Min SDK: 24
  - Target SDK: 30
- **UI Framework**: 
  - AndroidX Navigation Component
  - Material Design Components
  - View Binding
- **Asynchronous Programming**: Kotlin Coroutines with Flow

### Key Dependencies

#### App Module
```gradle
- androidx.navigation:navigation-fragment-ktx:2.3.5
- androidx.navigation:navigation-ui-ktx:2.3.5
- com.google.android.material:material:1.4.0
- androidx.constraintlayout:constraintlayout:2.1.3
```

#### Internet Library Module
```gradle
- kotlinx-coroutines-core:1.6.0
- kotlinx-coroutines-android:1.6.0
```

### Internet Library Module

The `internetlibrary` module provides a reactive way to monitor internet connectivity status using Android's `ConnectivityManager`.

#### Core Components

1. **InternetService Interface**
   ```kotlin
   interface InternetService {
       fun observe(): Flow<Status>
       
       enum class Status {
           Available, Losing, Lost, Unavailable,
           CapabilitiesChanged, LinkPropertiesChanged, BlockedStatusChanged
       }
   }
   ```

2. **Internet Implementation**
   - Uses `ConnectivityManager.NetworkCallback` for real-time network monitoring
   - Implements reactive programming with Kotlin Flow
   - Provides distinct status updates to avoid duplicate emissions
   - Handles all network state changes including availability, loss, and capability changes

#### Key Features

- **Reactive Monitoring**: Uses Kotlin Flow for continuous network status observation
- **Comprehensive Status Tracking**: Monitors 7 different network states
- **Memory Efficient**: Uses `callbackFlow` with proper cleanup
- **Thread Safe**: Leverages Kotlin Coroutines for asynchronous operations

### App Module

The main application demonstrates a simple navigation-based UI with two fragments.

#### Components

1. **MainActivity**
   - Implements `AppCompatActivity` with Material Design
   - Sets up Navigation Component with toolbar integration
   - Includes a Floating Action Button with placeholder functionality

2. **Navigation Structure**
   - Uses AndroidX Navigation Component
   - Two-fragment navigation flow (First ↔ Second)
   - Material Design navigation patterns

3. **Fragments**
   - `FirstFragment`: Entry point with navigation to second fragment
   - `SecondFragment`: Secondary screen with back navigation
   - Both fragments use View Binding for type-safe view access

### Permissions

The library module requires the following permissions:
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

### Publishing Configuration

The library is configured for Maven publishing:
- **Group ID**: `com.github.Konbitech`
- **Artifact ID**: `Internet`
- **Version**: `0.0.1`

## Usage Example

To use the Internet library in your project:

1. **Add the library dependency**:
   ```gradle
   implementation project(':internetlibrary')
   ```

2. **Initialize and observe network status**:
   ```kotlin
   val internetService = Internet(this)
   
   internetService.observe()
       .onEach { status ->
           when (status) {
               InternetService.Status.Available -> {
                   // Handle internet available
               }
               InternetService.Status.Lost -> {
                   // Handle internet lost
               }
               // Handle other statuses...
           }
       }
       .launchIn(lifecycleScope)
   ```

## Build Instructions

1. **Clone the repository**
2. **Open in Android Studio** (recommended) or use command line
3. **Build the project**:
   ```bash
   ./gradlew build
   ```
4. **Run the app**:
   ```bash
   ./gradlew installDebug
   ```

## Development Notes

- The project uses **View Binding** for type-safe view access
- **Kotlin Coroutines** are used for asynchronous operations
- The library follows **reactive programming** principles with Flow
- **Material Design** components are used throughout the UI
- The project is configured for **Android API 24+** (Android 7.0+)

## Future Enhancements

Potential improvements for the library:
- Add network speed monitoring
- Implement connection quality assessment
- Add support for specific network types (WiFi, Cellular, Ethernet)
- Include network security status monitoring
- Add unit tests for the library components

## License

This project is part of the Konbitech organization and follows their licensing terms.