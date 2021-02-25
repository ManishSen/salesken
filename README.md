# Salesken
#Basic CRUD operation for salesken
# Project Description
Technologies Used : Java, SpringBoot(Java Framework), MySQL , Restful API


# Steps to Run project in docker image,

(1) To run the Application we need to pull the mysql image first :

# docker pull mysql:5.6

(2) Run mysql in docker, Please note that do not change the name (my-local) as same name has been used in application :

# docker run --name my-local -e MYSQL_ROOT_PASSWORD=admin -d mysql:5.6

(3) Connect to mysql which we just started in above command and create the DB name saleskendb

# docker exec -it my-local mysql -uroot -padmin

Once connected to DB please create schema : # saleskendb

# create database saleskendb

(4) Build application image 

# docker build -t accomodation-service .

(5) Run Docker to launch our accomodation-service application:

# docker run -d -p 8080:8080 --name accomodation-service --link my-local:mysql accomodation-service

Once the application is up, We can test the rest end points.

# 1) If you use postman to test rest APIs , please import salesken API collection that I have attached and test it.

# 2) Else I have implemented Swagger in my project that can help you test my all rest endpoints. Simply hit : http://localhost:8080/swagger-ui/

The above page shows my Item and booking controller and give all CRUD rest API with JSON to test. 

First of all hit post Item to add Item in DB :

# Please use below sample JSON inputs to test from swagger : http://localhost:8080/swagger-ui/

# POST api : http://localhost:8080/api/v1/item

 input JSON : 
               
               {
                "availability": 20,
                "category": "Hotel",
                "imageUrl": "hotelinn.jpg",
                "location": {
                  "address": "Altstadt",
                  "city": "Dusseldorf",
                  "country": "Germany",
                  "state": "Nordrhein-Westfalen",
                  "zip_code": 40216
                },
                "name": "Hyatt House Dusseldorf",
                "price": 17000,
                "rating": 5,
                "reputation": 1000
              }
# post multiple time with different input JSON to have more records in DB.
 
# 1) 
                {
                "availability": 50,
                "category": "Hotel",
                "imageUrl": "myhotel.jpg",
                "location": {
                  "address": "City Center",
                  "city": "Dusseldorf",
                  "country": "Germany",
                  "state": "Nordrhein-Westfalen",
                  "zip_code": 40218
                },
                "name": "Wieland Hotel",
                "price": 3865,
                "rating": 3,
                "reputation": 700
              }
              
# 2)         
	            {
                "availability": 10,
                "category": "Hostel",
                "imageUrl": "hostelinn.jpg",
                "location": {
                  "address": "Stockum",
                  "city": "Dusseldorf",
                  "country": "Germany",
                  "state": "Nordrhein-Westfalen",
                  "zip_code": 40220
                },
                "name": "Favour hostel",
                "price": 1200,
                "rating": 3,
                "reputation": 700
              }
              
# 3)         
				  {
                "availability": 10,
                "category": "Alternative",
                "imageUrl": "Alterantive.jpg",
                "location": {
                  "address": "City center",
                  "city": "Frankfurt",
                  "country": "Germany",
                  "state": "Hesse",
                  "zip_code": 60220
                },
                "name": "The grand hayatt hotel",
                "price": 12000,
                "rating": 4,
                "reputation": 800
              }

 
# Get Api : To get all items in DB - http://localhost:8080/api/v1/items
  
  It will give result in json with id: 
              
              { 
              	 {
					   "id" :1,   
	                "availability": 50,
	                "category": "Hotel",
	                "imageUrl": "myhotel.jpg",
	                "location": {
	                  "address": "City Center",
	                  "city": "Dusseldorf",
	                  "country": "Germany",
	                  "state": "Nordrhein-Westfalen",
	                  "zip_code": 40218
	                },
	                "name": "Wieland Hotel",
	                "price": 3865,
	                "rating": 3,
	                "reputation": 700
	              },
              	{
				      "id":2,
	                "availability": 10,
	                "category": "Hostel",
	                "imageUrl": "hostelinn.jpg",
	                "location": {
	                  "address": "Stockum",
	                  "city": "Dusseldorf",
	                  "country": "Germany",
	                  "state": "Nordrhein-Westfalen",
	                  "zip_code": 40220
	                },
	                "name": "Favour hostel",
	                "price": 1200,
	                "rating": 3,
	                "reputation": 700
	              },
             		{
					   "id":3,
	                "availability": 10,
	                "category": "Alternative",
	                "imageUrl": "Alterantive.jpg",
	                "location": {
	                  "address": "City center",
	                  "city": "Frankfurt",
	                  "country": "Germany",
	                  "state": "Hesse",
	                  "zip_code": 60220
	                },
	                "name": "The grand hayatt hotel",
	                "price": 12000,
	                "rating": 4,
	                "reputation": 800
	              }
				}
				
# To get Item by Id : http://localhost:8080/api/v1/item/{id}
 
 Output in JSON: 
				
				{
				   "id" :1,   
                "availability": 50,
                "category": "Hotel",
                "imageUrl": "myhotel.jpg",
                "location": {
	                  "address": "City Center",
	                  "city": "Dusseldorf",
	                  "country": "Germany",
	                  "state": "Nordrhein-Westfalen",
	                  "zip_code": 40218
	                },
                "name": "Wieland Hotel",
                "price": 3865,
                "rating": 3,
                "reputation": 700
              }

# Get Item by rating : http://localhost:8080/api/v1/item/?rating=4
	Output in json :    {
						"id":3,
						"availability": 10,
						"category": "Alternative",
						"imageUrl": "Alterantive.jpg",
						"location": {
						  "address": "City center",
						  "city": "Frankfurt",
						  "country": "Germany",
						  "state": "Hesse",
						  "zip_code": 60220
						},
						"name": "The grand hayatt hotel",
						"price": 12000,
						"rating": 4,
						"reputation": 800
						}
# Get item by category : http://localhost:8080/api/v1/item/?category=hotel
	Output in json :  {
						"id" :1,   
						"availability": 50,
						"category": "Hotel",
						"imageUrl": "myhotel.jpg",
						"location": {
						  "address": "City Center",
						  "city": "Dusseldorf",
						  "country": "Germany",
						  "state": "Nordrhein-Westfalen",
						  "zip_code": 40218
						},
						"name": "Wieland Hotel",
						"price": 3865,
						"rating": 3,
						"reputation": 700
					  }
# Get item by reputationBadge : http://localhost:8080/api/v1/item/?ReputationBadge=GREEN

Output in JSON :    
					
					{
						"id":3,
						"availability": 10,
						"category": "Alternative",
						"imageUrl": "Alterantive.jpg",
						"location": {
						  "address": "City center",
						  "city": "Frankfurt",
						  "country": "Germany",
						  "state": "Hesse",
						  "zip_code": 60220
						},
						"name": "The grand hayatt hotel",
						"price": 12000,
						"rating": 4,
						"reputation": 800
					}
					
# Get item by city : http://localhost:8080/api/v1/item/?city=Dusseldorf

Output in JSON :  
					
					{
						"id" :1,   
						"availability": 50,
						"category": "Hotel",
						"imageUrl": "myhotel.jpg",
						"location": {
						  "address": "City Center",
						  "city": "Dusseldorf",
						  "country": "Germany",
						  "state": "Nordrhein-Westfalen",
						  "zip_code": 40218
						},
						"name": "Wieland Hotel",
						"price": 3865,
						"rating": 3,
						"reputation": 700
					}
					
# Delete item by Id (DELETE api) : http://localhost:8080/api/v1/item/{id}
	If provided id is valid, it will delete it and return true.
	
	output JSON : {
					  deleted : true;
				    }

# Update item (PUT api) : http://localhost:8080/api/v1/item/
	eg : for ID 1 , lets update availability to 100, rating 4 and reputation 900: 
	Input JSON :     {
						"id" :1,   
						"availability": 100,
						"category": "Hotel",
						"imageUrl": "myhotel.jpg",
						"location": {
						  "address": "City Center",
						  "city": "Dusseldorf",
						  "country": "Germany",
						  "state": "Nordrhein-Westfalen",
						  "zip_code": 40218
						},
						"name": "Wieland Hotel",
						"price": 3865,
						"rating": 4, 
						"reputation": 900
					}
					

# As booking end point was not clear, I assume we just want to block number of rooms for a booking , so to reduce the item availability for a booking, I have below endpoints : 

e.g. : To book a item with Id : 1 , we need to post using below API and input JSON : Status booked is to reserve 2 rooms for given itemId. 
	
# Rest API (POST): http://localhost:8080/api/v1/booking
	Input JSON : {
				  "itemId": 1,
				  "rooms": 2,
				  "status": "BOOKED"
				}
				
# Get all bookings (GET): http://localhost:8080/api/v1/bookings
	Output JSON : 
				{
				  "id" : 1,
				  "itemId": 1,
				  "rooms": 2,
				  "status": "BOOKED"
				}
				
# To vacate booked rooms or to clear the booking , we will mark status as complete in database.
# Rest API (POST): http://localhost:8080/api/v1/booking/{id}
	Output JSON : 	
				{
				  "id" : 1,
				  "itemId": 1,
				  "rooms": 2,
				  "status": "COMPLETE"
				}

# Test coverage is 86% , See file : TrivagoCaseStudyCodeCoverage.png



 
 
