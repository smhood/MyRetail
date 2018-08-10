## Techstack
**Web Service Backend**: Spring Boot

**Datastore**: MongoDb (https://mlab.com)

**Cloud Service**: Heroku (https://heroku.com)

## Production Endpoint
https://hood-interview.herokuapp.com/api/v1/products/

## Endpoint Descriptions

1) GET https://hood-interview.herokuapp.com/api/v1/products/

Returns back a list of the prices available to choose from, from this you may see Product Ids in which these prices have been assigned too.

2) GET https://hood-interview.herokuapp.com/api/v1/products/{id}

Will return back the product id, name, and price. If the id does not have an associated product, the service will return back an empty object.

3) PUT https://hood-interview.herokuapp.com/api/v1/products/{id}

Allows you to assign a price to an existing product, or update an already existing price for a given id.
If the process fails to save a price to a product / the product doesn't exist, the service will response "false".

## Techstack decisions
#### MongoDb
Easy choice for a quick datastore, easy to integrate into the spring boot application and map directly to data models.

#### MLab
Seperating our deployments of your db and your application allows you to maintain single responsibility. Mlab is a great tool for creating
small data stores (up to 0.5GB) and acts the same as any local running mongodb service.

#### Heroku
Allows you to create a CI/CD environment directly linked to your github repository. Can be set up to automatically push when master deployments
occur, and can be turned on and off at ease. Hosting when supplied a credit card gives you a months worth of hosting time, which allows you
to keep a single application running 24/7, providing you with logs, plugins, and more to make deployments manageable.

## Running locally
Running locally requires the following as dependencies. 

Java 8+, Maven 3+

1) Clone / Download the zip from this repository. 
2) Go to file location.
3) Open up powershell.
4) run "mvn spring-boot:run"
Clone / Download the zip from this repo.
