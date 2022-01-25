# Telemetry Service

In this service you can see basic features and patterns in Kotlin:
* DDD, TDD
* Hexagonal Architecture
* Domain Model
* Unit Tests
* Integration Tests
* REST controller
* In memory repo

### Model
```
  ┌───────────────────┐           ┌───────────────────┐
  │      Vehicle      │           │ TelemetricVehicle │ 
  └───────────────────┘           └───────────────────┘ 
```

### Run the tests
`./gradlew test`


### Run
`./gradlew bootRun`

```
curl --location --request POST 'http://localhost:8080/vehicles' \
--header 'Content-Type: application/json' \
--data-raw '{
    "chassis_number": "ChassisNumber1234",
    "license_plate": "1234ABC",
    "brand": "Toyota",
    "category": "CCME",
    "infleet_date": "2000-10-31T01:30:00"
}'
```

### TODO
- [-] sealed telemetry
- [-] Message job processor
- [-] Persistence
- [-] Upgrade Kotlin
- [-] E2E tests
- [-] Performance tests

