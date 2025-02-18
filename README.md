# KMTest
Authored by: Azmi Hasna Zahrani

## Overview

KMTest is an Android application that consists of three screens. The primary functionality of the app is to check if a given sentence is a palindrome.

## Screens

### 1. First Screen

- **Input Fields**: 
  - Two input fields:
    - **Name Input**: For entering the user's name.
    - **Palindrome Input**: For entering the sentence to check for palindrome status.

- **Button**:
  - A button titled **"Check"** below the input fields to check palindrome status of Palindrome Input.
  - When clicked, it shows a dialog with the message **"isPalindrome"** if the sentence is a palindrome.
  - **"Next"** button will save Name Input and navigate to Second Screen

### 2. Second Screen

The second screen of the KMTest application features a static "Welcome" text label, providing a friendly greeting to users.
Additionally, it includes two dynamic labels: one displays the name entered on the first screen, while the other shows the "Selected User Name."
To enhance user interaction, there is a button labeled "Choose a User." When this button is clicked, it navigates the user to the third screen, allowing for further actions within the app.

### 3. Third Screen

The third screen of the KMTest application presents a List/Table view of users, dynamically populated with data retrieved from available API.
Each user entry includes their email, first name, last name, and avatar.
To enhance user experience, the screen features a pull-to-refresh functionality, allowing users to refresh the list easily.
Additionally, as users scroll to the bottom of the list, the app automatically loads the next page of data, utilizing parameters for page and per_page to fetch additional entries.
When a user clicks on an item in the list, the "Selected User Name" label on the second screen is updated to reflect the name of the selected user.

## Getting Started

### Prerequisites

- Android Studio
- Android SDK

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/goodgirlwannabe/KMTest.git
2. Open the project in Android Studio.
3. Build the project: `./gradlew build` (or the equivalent command for your build system).
