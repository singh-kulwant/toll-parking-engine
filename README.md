# Parking Ticket Service 1.0.0

Parking Ticket Service provides REST API for dealing with vehicle parking. It supports following operations park vehicle, unpark vehicle and generate parking ticket.


## API Details
```
Resource                                  Request Method	Function
/parking/vehicle/                         POST                  Park vehicle in right slot
/parking/vehicle/{vehicleRegistration}    DELETE                Unpark vehicle from slot and generate bill
/parking/status                           GET                   Fetch current status of all praking slots
```

## Installation and Use

1. Clone project
```bash
~/java-project$ git clone https://github.com/singh-kulwant/parking-ticket-service.git
```
2. Build project
```bash
~/java-project/parking-ticket-service$ sudo gradle clean build
```
3. Start application
```bash
~/java-project/parking-ticket-service$ sudo gradle bootRun
```
4. Open uri in any Rest Client
    • To park vehicle
        ◦ Url: http://localhost:9090/parking/vehicle/
        ◦ RequestMethod: POST
	
        ◦ Sample RequestBody for standard gasoline vehicle: 

```json
{
	"vehicleRegistration":"PO4534",
	"vehicleType" : "GASOLINE"
}
```
      
        ◦ Sample RequestBody for 50KW electric vehicle:
```json
{
	"vehicleRegistration":"CY345OD0",
	"vehicleType" : "HEAVY_ELECTRIC"
}
```      
        ◦ Sample RequestBody for 20KW electric vehicle: 
```json
{
	"vehicleRegistration":"YT3828UR",
	"vehicleType" : "LIGHT_ELECTRIC"
}
```
### Note: vehicleType is enum – possible values are [GASOLINE, HEAVY_ELECTRIC, LIGHT_ELECTRIC]
    • To unpark vehicle:
        ◦ Url: http://localhost:9090/parking/vehicle/{vehicleRegistration}
        ◦ RequestMethod: DELETE
        ◦ Sample request uri
            ▪ http://localhost:9090/parking/vehicle/PO4534
	    
    • To get live status of parking slots:
        ◦ Url:http://localhost:9090/parking/status
        ◦ RequestMethod: GET
### Note: EntryTime on Parking Ticket is set to 12:00 of the day for billing calculation demonstration purpose 


## Swagger Details
http://localhost:9090/v2/api-docs
http://localhost:9090/swagger-ui.html

## Java Doc Path
toll-parking-engine/build/docs/javadoc/index.html

## Api Dependencies

```
Spring Boot			2+
Gradle 				4.10+
Springfox-swagger2		2.9.2
springfox-swagger2-ui		2.9.2
```

## Sequence Diagrams

![alt text](https://github.com/singh-kulwant/parking-ticket-service/blob/master/src/main/resources/Vehicle-Park-Sequence-Diagram.png)

![alt text](https://github.com/singh-kulwant/parking-ticket-service/blob/master/src/main/resources/Vehicle-Unpark-Sequence-Diagram.png)


## Packages

```
Package                           Description
com.parking.app 	          Main class package
com.parking.config                Parking engine configurations and handlers
com.parking.rest 	          Parking engine controller
com.parking.service               Parking service and validator
com.pricing.models 	          Pricing policy interface and implementations
com.slot.models                   Parking slot models
com.vehicle.models                Vehicle classes and enums
```


## Hierarchy
### Class Hierarchy
    • java.lang.Object
        ◦ com.parking.config.ApiMessage
        ◦ com.kulsin.models.pricing.FixedHourlyBilling (implements com.kulsin.models.pricing.PricingPolicy)
        ◦ com.kulsin.models.pricing.HourlyBilling (implements com.kulsin.models.pricing.PricingPolicy)
        ◦ com.parking.config.ParkingConfigurationLoader
        ◦ com.parking.rest.ParkingController
        ◦ com.parking.service.ParkingService
        ◦ com.kulsin.models.slot.ParkingSlot
            ▪ com.kulsin.models.slot.HeavyElectricSlot
            ▪ com.kulsin.models.slot.LightElectricSlot
            ▪ com.kulsin.models.slot.StandardSlot
        ◦ com.kulsin.models.pricing.ParkingTicket
        ◦ com.parking.service.ParkingValidator
        ◦ org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
            ▪ com.parking.config.ApiExceptionHandler
        ◦ com.parking.config.SwaggerConfiguration
        ◦ com.kulsin.TollParkingApplication
        ◦ com.kulsin.models.vehicle.Vehicle

### Interface Hierarchy
    • com.kulsin.models.pricing.PricingPolicy
### Enum Hierarchy
    • java.lang.Object
        ◦ java.lang.Enum<E> (implements java.lang.Comparable<T>, java.io.Serializable)
            ▪ com.kulsin.models.vehicle.VehicleType
