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
2. In the root of the project (where the docker-compose.yml file is located), write in the terminal
   "docker-compose up --build"
3. Navigate to localhost:3000 in your browser.

This will create a Docker container containing all the micro-services, and all features are available locally.