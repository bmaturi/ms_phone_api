# Phone API
Application developed for Belong coding exercise. It exposes api's to retrieve and update customer phone records.

## Software versions
- Java 11
- Spring Boot 2.6.3
- Gradle 7.3.3
- spring-fox 3.0.0

## Useful commands

- Building the app 
  ./gradlew clean build

- Run tests
  ./gradlew test

- Starting the app
  ./gradlew bootRun

## Endpoint documentation

Once you have started the application please navigate to the below url to access the swagger documentation for the endpoints exposed via this app.

 /ms_phone_api/swagger-ui

## API's available

- /v1/phonenumbers : Fetches all the phone numbers available in the system with status

- /v1/{customerId}/phonenumbers : Fetches all the phone numbers available for the customer.
                                  Returns 200 if successful.
                                  Throws 404 if the customer is not found.

- /v1/activate : Activates the phone number for the given customer.
                 Returns 200 if successful.
                 Returns 404 if the customer or phone number is not found
                 Returns 400 for invalid request.


