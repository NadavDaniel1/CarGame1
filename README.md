# Car Game App
Welcome to the Car Game! This game challenges your reflexes and coordination as you navigate a car to avoid falling stones.

## Overview
The Car Game App is an interactive mobile application developed for Android that combines elements of fun and learning. The game features a dynamic car racing experience with obstacles, collectibles, and various challenges.

## Technologies Used
- **Java**: Core programming language used for Android development.
- **Android Studio**: Integrated Development Environment (IDE) used for building and testing the application.
- **Google Maps API**: Used for integrating maps to show the location
- **RecyclerView**: Used for displaying the list of top 10 scores.
- **SharedPreferences**: Used for saving and loading high scores.
- **Gson**: Used for converting Java objects to JSON and vice versa.

## Screenshots

<img src="https://github.com/user-attachments/assets/ad2cd115-ef18-4ed8-82b9-9b29fbb0297c" alt="gameCar1_menu" width="300" height="600">   
<img src="https://github.com/user-attachments/assets/1ec4c021-6e10-40c9-b2dd-795b1459bae7" alt="gameCar1__gameScreenButtons" width="300" height="600">   

<img src="https://github.com/user-attachments/assets/d3e0f874-d3a8-44a1-b464-3902fd23176f" alt="gameCar1_gameOver" width="300" height="600"> 
<img src="https://github.com/user-attachments/assets/7fdf8757-355e-4abf-907d-c82a19331bb2" alt="gameCar1__recordsTop10" width="300" height="600"> 





# Features
**Dynamic Gameplay:** Stones appear at random intervals and move down the screen, increasing the challenge.

**Responsive Controls:** Move your car left or right using on-screen buttons or sensors to dodge the stones.

**Collision Detection:** Accurate collision detection ensures that any contact between the car and a stone results in a crash.

**Lives System:** You start with three lives. Each collision costs you a life, and the game ends when you run out of lives.

**RTL Support:** The game supports both LTR (left-to-right) and RTL (right-to-left) layouts, adapting to the device's language settings.

**Dynamic Racing Gameplay**: Navigate through various tracks, avoiding obstacles and collecting items.

**Top Scores**: View and manage the top 10 high scores.

**Location-based Features:** display the location using Google Maps.

**Sound Effects:** Enjoy immersive sound effects for crashes and collectibles.

**User-Friendly Interface:** Intuitive controls and clean UI design.

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/NadavDaniel/CarGame1.git
   
2. Open the project in Android Studio.
   
3. Add your Google Maps API key to the local.properties file:
    ```sh
   MAPS_API_KEY=your_api_key
   
5. Build and run the application on your Android device or emulator.


## Difficulties Faced
**Google Maps Integration:** Integrating Google Maps into the app and ensuring it worked seamlessly with the game logic was challenging. I faced issues with API keys and map rendering, which required thorough debugging.

**Real-time Location Updates:** Implementing real-time location updates to track and display the user's location accurately on the map posed several technical challenges.

**RecyclerView Performance:** Ensuring smooth scrolling and efficient data handling in RecyclerView for the top scores list required careful optimization.

**Handling Permissions:** Managing runtime permissions for location access required careful consideration to ensure a smooth user experience without compromising security.

## Credits
Developed by [Nadav Daniel]
Course: Mobile Application Development





