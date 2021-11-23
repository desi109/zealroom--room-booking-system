<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/desi109/zealroom--room-booking-system">
    <img style="" src="images/zealroom_logo.png" alt="Logo" width="70%" height="70%">
  </a>


  <h3 align="center">Zealroom: Room Booking System</h3>

  <p align="center">
    Zealroom is a web application for management and reservation of rooms in an educational institutions. By providing a booking platform, the software will enable organizations from different fields of activity to manage the use of common areas. In this way the organization for the use of rooms and halls will be much easier.
    <br />
    <a href="https://github.com/desi109/zealroom--room-booking-system"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/desi109/zealroom--room-booking-system">View Demo</a>
    ·
    <a href="https://github.com/desi109/zealroom--room-booking-system/issues">Report Bug</a>
    .
    <a href="https://github.com/desi109/zealroom--room-booking-system/issues">Request Feature</a>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>


<br>
<br>

<!-- ABOUT THE PROJECT -->
## About The Project
The idea of this app is to provide an easy way for making reservation of rooms and manage them in an educational institutions. 

The app has a minimalistic design and it is easy to be used by everyone.


<br>
<br>


<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites
To run this project you first need to install and set up:
* Java 8
  ```sh
  java -version
  ```
* Gradle 7.2
* MongoDB --> TODO
* Node.js v14.17.0
  ```sh
  node -v
  ```
* NPM 8.1.4
  ```sh
  npm -v
  ```
* Angular 13


<br>
<br>


### Installation

1. Clone the repo:
   ```sh
   git clone https://github.com/desi109/zealroom--room-booking-system.git
   ```

2. Start the back-end of the project:
   ```sh
   cd zealroom--room-booking-system/back-end
   ./gradlew clean build bootRun
   ```
   It is running on ```localhost:5000```.

   To restart, first kill the process:
   ```sh
   kill -9 $(lsof -t -i:5000)   
   ```
   And run the previous command to start it again.

3. Start the front-end of the project:
   ```sh
   cd zealroom--room-booking-system/front-end
   npm update
   npm i bootstrap
   npm i --save bootstrap@3
   npm i @angular-devkit/build-angular
   ng serve
   ```
   It is running on ```localhost:4200```.

   To restart, first kill the process:
   ```sh
   kill -9 $(lsof -t -i:4200)   
   ```
   And run the previous command to start it again.
 

<br>
<br>


<!-- ROADMAP -->
## Roadmap

See the [open issues](https://github.com/desi109/zealroom--room-booking-system/issues) for a list of proposed features (and known issues).





