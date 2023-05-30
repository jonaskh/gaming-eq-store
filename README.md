# Welcome to Cyberpunk Gaming Gadgets!

![img.png](img.png)

Github repo: https://github.com/jonaskh/gaming-eq-store

This application was designed as a project in NTNU Ålesunds classes WebTechonology and Application Development.

The description of the project is design a website for an online gaming store called Cyberpunk Gaming Gadgets.

The application uses a micro-service approach where the
different services are decoupled from one another.

The services used are:

1. Frontend (ReactJS, JavaScript)
2. Backend (Java Spring Boot)
3. Database (PostgreSQL)
4. Webserver (Nginx)


The website is hosted on https://www.group09.web-tek.ninja for the remainder of the
examination period, after which it will only be available locally. The URL is also only accessible while on NTNUs internal network.

## Installation
In order to run the project locally, Docker must be installed on your computer. Then perform the following steps:

1. Clone the main branch of the repository listed above.
2. Edit the name of the .envTEMPLATE file in the root directory to ".env", and input some random variable in each value in the file
2. Navigate to the api directory and run the backend, either through a IDEA or through terminal.
3. Navigate to the webapp directory and run "npm install" and "npm start" in the terminal.
4. The website will now be available at localhost:3000

All services are now available, as the database Docker image is launched with the back end.

On the server this is done automatically using docker-compose.