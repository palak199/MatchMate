
# MatchMate 💖

An android app that simulates a Matrimonial App by displaying 
match cards similar to Shaadi.com's format. 
Built with Kotlin, Jetpack Compose, Room, and Retrofit, users can scroll through profiles, accept or reject matches, and view previously accepted  matches.

## Demo
Screenshots and demo clips: https://drive.google.com/file/d/1X23BA5nY9RlgFN0ZT_YYEz8_k5BF7cAX/view?usp=sharing
<img src="https://github.com/palak199/MatchMate/blob/main/screenshots/Accepted-dark.jpg" height="400"/>
<img src="https://github.com/palak199/MatchMate/blob/main/screenshots/Accepted-light.jpg" height="400"/>
<img src="https://github.com/palak199/MatchMate/blob/main/screenshots/Matches-light.jpg" height="400"/>
<img src="https://github.com/palak199/MatchMate/blob/main/screenshots/My%20Profile-dark.jpg" height="400"/>
<img src="https://github.com/palak199/MatchMate/blob/main/screenshots/My%20Profile-light.jpg" height="400"/>

## Features
- Home Tab to display profiles in cards with details. In background it fetches user data like name, age, city(From API) and then displays them with additional details like education, religion, community and profession (As observed to be the some of the prime factors considered during match making)
- Matches Tab to view accepted/rejected profiles
- Profile Tab to display user profile (Dummy as of now)
- Local match score based on age/religion matching
- Offline support with Room database (holds 50 undecided profiles)
- Network check with graceful error handling and retry logic
- Light/dark mode toggle



## API Reference

#### Get all items

```http
  GET https://randomuser.me/api/?results=10
```

**Note**: After data is fetched from the above API, a match score is  calculated, random religion, caste and profession is added (from a list of local list of religions/caste/profession)


## Installation

1. Clone the repository and open in android studio
2. Let the gradle sync and build
3. Run the application on an emulator or physical device




    
## Architecture and folder structure

This app uses *MVVM* architecture with a clean separation of concerns.

Reasons:
- **separation of concerns**
models: data layer
views: ui / presentation
viewmodels: business logic
- states are managed with jetpack compose allowing a **unidirectional flow of data**
- **survives configuration changes** due to use of viewmodel
- **easy testing** as all the layers are separated out


Link to folder structure: https://limewire.com/d/AAUaF#8bRXtDF9Yw



## Tech Stack

Jetpack Compose\
Coil for image rendering (supports caching by default and light weight)\
Retrofit for Network Calls\
Material3 UI\
Room DB (for offline local storage)

### Libraries and versions

agp = "8.7.3" \
kotlin = "2.1.10" \
compose = "1.8.2" \
material3 = "1.3.2" \
activity = "1.10.1" \
navigationCompose = "2.9.0" \
coilCompose = "2.2.2" \
coreKtx = "1.16.0" \
appcompat = "1.7.0" \
material = "1.12.0" \
junit = "4.13.2" \
junitVersion = "1.2.1" \
espressoCore = "3.6.1" \
room = "2.7.1" \
retrofit = "2.9.0" \
gsonConverter = "2.9.0"
## To Do

- Add more fields for profiles like hometown and eating preferences
- Stimulate flaky network
- Add authentication module
- Cloud database syncing
- Swipe gesture for a more modern look
- Sort/filter profiles based on match score
- Profile editing

## Match Score logic

Currently a simplified logic has been used. More advanced logic can be integrated easily.

Each user profile receives a match score based on:

Age similarity (60 score) + Religion match (40 score)

