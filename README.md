# Automatic Irrigation System
# Introduction
As a irrigation system which helps the automatic irrigation of agricultural plots without human intervention, system has to
be designed to fulfil the requirement of maintaining and configuring the plots of plot by the irrigation time slots and the
amount of water required for each irrigation period.
The irrigation system should have integration interface with a sensor device to direct letting the sensor to irrigate based o
n
the configured time slots/amount of water.

## Requirement 
### Function Requirement
- Plot of land - Group of APIs to add/update plots and irrigation details
  - Add new plot of land
  - Configure a plot of land
  - Edit a plot of land
  - List all plots and it's details
  - Delete a plot of land
  - Add a new plot of land with irrigation configuration
  - Get all plots with irrigation details
  - Create a new irrigation
  - Update existing irrigation
- Automatic irrigation system - Scheduled job to communication with the sensor
- Alerting - Implemented by sending email notification incase the sensor not available and after exceeding the 
  retry times

### NonFunction Requirement
- authentication or authorization not in scope

## Database
H2 (In-Memory) database used for this system. Use the following URL to access the database.
Datasource properties configured in application.yaml

http://localhost:8080/automatic-irrigation-system/api/v1/h2-console/login.jsp

## Build & Run Instructions ###

The prerequisite of this application are Java 17 and MVN.

Step 1: Clone the Repository

Step 2: Build Command : mvn clean install

Step 3: Run Command : mvn spring-boot:run

**Note:** Once the project is build successfully then execute the Step 3. Trace and monitor the
logs whether the application started or not.

## Application Health Check URL
http://localhost:8080/automatic-irrigation-system/api/v1/actuator/health

## API Documentation
Used Open API - Swagger to generate the documenation for this API (Reference: https://swagger.io/specification/)

http://localhost:8080/automatic-irrigation-system/api/v1/swagger-ui/index.html

###
The following curl commands can be used for testing or the Postman exported collection under this path
resources/postman-collection/

### Get Plot By Id
curl --location --request GET 'http://localhost:8080/automatic-irrigation-system/api/v1/plots/1001' \
--header 'Content-Type: application/json'

## Add plot of land with irrigation configuration
curl --location --request POST 'http://localhost:8080/automatic-irrigation-system/api/v1/plots' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Corn Land2",
"description": "Corn Farm",
"latitude": 10.1234567,
"longitude": 20.1234567,
"cultivatedArea": 2000,
"areaUnit": "SQUARE_METER",
"agriculturalCropType": "CORN",
"irrigation": {
"irrigationType": "SPRINKLER",
"amountOfWater": 1000,
"liquidUnit": "GALLON",
"duration": 3600,
"interval": 21600,
"irrigationStatus": "ACTIVE",
"nextIrrigationAt": "2022-12-18T14:30:15.620801"
}
}'

### Edit plot of Land with Irrigation configuration
curl --location --request PUT 'http://localhost:8080/automatic-irrigation-system/api/v1/plots' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": 1,
"name": "Corn Land",
"description": "Corn Land in Kashmir",
"latitude": 10.1234567,
"longitude": 20.1234567,
"cultivatedArea": 2000,
"areaUnit": "SQUARE_METER",
"agriculturalCropType": "CORN",
"irrigation": {
"id": 1,
"irrigationType": "SPRINKLER",
"amountOfWater": 1000,
"liquidUnit": "GALLON",
"duration": 3600,
"interval": 21600,
"irrigationStatus": "ACTIVE",
"nextIrrigationAt": "2022-12-18T14:30:15.620801"
}
}'

### List All plots with its irrigation details
curl --location --request GET 'http://localhost:8080/automatic-irrigation-system/api/v1/plots' \
--header 'Content-Type: application/json'

### Get Irrigation by Id
curl --location --request GET 'http://localhost:8080/automatic-irrigation-system/api/v1/irrigations/1001' \
--header 'Content-Type: application/json'

### Delete Plot of Land
curl --location --request DELETE 'http://localhost:8080/automatic-irrigation-system/api/v1/plots/1005' \
--header 'Content-Type: application/json'
