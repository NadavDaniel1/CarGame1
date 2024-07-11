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

## Difficulties Faced
**Google Maps Integration:** Integrating Google Maps into the app and ensuring it worked seamlessly with the game logic was challenging. I faced issues with API keys and map rendering, which required thorough debugging.
**Real-time Location Updates:** Implementing real-time location updates to track and display the user's location accurately on the map posed several technical challenges.
**RecyclerView Performance:** Ensuring smooth scrolling and efficient data handling in RecyclerView for the top scores list required careful optimization.
**Handling Permissions:** Managing runtime permissions for location access required careful consideration 
to ensure a smooth user experience without compromising security.
**Location on Score Click:** Attempting to show the user's location when a score is clicked.

## Credits
Developed by [Nadav Daniel]
Course: Mobile Application Development





