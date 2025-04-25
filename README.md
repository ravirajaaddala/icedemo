# Demo application for Mortgage

## Overview
This is a simple Spring Boot web application that uses Thymeleaf as the template engine for rendering dynamic HTML pages.

## Description
This service will be used by clients, who wants to create/view/update locations,homes,loans etc 

## Features
- Spring Boot application
- Thymeleaf template engine for clean and reusable HTML code
- RESTful API integration
- JPA integration 
- H2 database is used
- Bootstrap, Jquery is used in frontend

## Prerequisites
Before running this application, ensure that you have the following installed:
- Java 17 or later
- Gradle
- IDE such as IntelliJ IDEA or Eclipse (optional)

## Functionality
- Launch the dashboard url
- Goto new location tab. fill the data and click create location
- refresh the screen
- click locations tab to view the saved locations.
- Goto Homes tab to create a new home 
- Saved homes can be see using the api /property/homes/zipcode from swagger.
   
## URLS
- Dashboard url
	http://localhost:8080/index
- Swagger URL
	http://localhost:8080/swagger-ui/index.html
- H2 Console
	http://localhost:8080/h2-console
- Actuator
	http://localhost:8081/actuator
	http://localhost:8081/actuator/metrics
	http://localhost:8081/actuator/health
	