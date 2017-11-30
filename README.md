Build and Deploy
----------------------

Make sure you have all necessary tools installed

    Java 8
    
Build the application:

    mvn clean install
     
Run locally
----------------------

    * Java
        java -jar target/hr-recruiting-service.jar 
    

EndPoints:
----------
* /api/offer POST
  * For Creating new Offer
  * ex. request:
  Body
    ```
    {
    	"name": "developer",
    	"start_date":"12-12-2017"
    }
    ```
  * response: 201 for created, 409 if the offer exists before 
  
* /api/offer GET
  * List all offers
  * example response:
    ```
    {
        "offers": [
            {
                "name": "developer",
                "start_date": "2017-12-12",
                "applications_nums": 1
            }
        ]
    }
    ```
* /api/offer/{offer_name} GET
  * get offer by name
  * response example:
  ```
  {
              "name": "developer",
              "start_date": "2017-12-12",
              "applications_nums": 1
          }

  ```
* /api/offer/apply POST
  * apply for a candidate for spesific offer
  * request:
    ```
    {
    	"offer_name":"developer",
    	"email":"mo@gmail.com",
    	"resume":"i'm super"
    }
    ```
  * response 201, 409: user applied  already, 404:  offf is not exists 
  
* /api/offer/{offer_name}/candidate GET
  * Get all candidates applied for this offer name
  * response 200:
  ```
  {
      "applications": [
          {
              "offer_name": "developer",
              "email": "mo@gmail.com",
              "resume": "i'm super",
              "status": "APPLIED"
          }
      ]
  }

  ```
  * 404: no offer matches name
* /api/offer/change_candidate_status PATCH
  * Change candidate status
  * request:
  ```
  {
  	"email":"mo@gmail.com",
  	"offer_name":"developer",
  	"status": "REJECTED"
  }
  ```
  * response 204: sccessful, 404: offer or user are not there, 

* /api/offer/application_number GET
  * get number of applications
  * response: 
   ```
   {
       "number_of_applications": 0
   }
   ```
