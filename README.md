# RestApi
LegalEntityResource Rest API application is a simple demo application, to demonstrate the basic GET,POST,PUT and DELETE operations using Spring Boot.
We have not implemented any security or database configuration.
This application is designed to run in Tomcat server.
We tested this API using a rest client.
Application is configured to run the port 8081, since most of the time 8080 default port may be occupied.

This application has the below URLs (usage of this Rest API)

1. http://localhost:8081/createlegalentities/  URL will create three entity objects.
2. http://localhost:8081/getAlllegalentities/  URL will show all the entity objects in the bean object.
3. http://localhost:8081/getlegalentity/{entityname}  URL will find a specific entity we are searching for .
   we can pass the `Entity Name` as the parameter,if the is not present, this api will return null.
4. http://localhost:8081/addlegalentity URL will add a new object in the bean object and return the bean object.
   So that we can verify if the value is added or not. This is POST request - We need some rest client to Test this.
5. http://localhost:8081/updatelegalentity/{entityname} URL will update in the bean object and return the bean object.
   So that we can verify if the value is updated or not. This is PUT request - We need some rest client to Test this.
   We should pass the `entityname` which needs to be updated and in the request body, we should pass the JSON value , which will replace the existing value for the `Entity Name`.
6. http://localhost:8081/deletelegalentity/{entityname} URL will delete a specific object from the bean object and return the bean object.
   So that we can verify if the value is deleted or not. This is a DELETE request.
   
     @Query("select pe.packageName from  PackageEntity pe where " +
            "lower(pe.packageName) like  " +
            "case when :packageName is null then lower(pe.packageName) else concat('%',lower(:packageName),'%') end " )
    List<String> getValues( @Param("packageName") String roomIDList);
   
   Error
   org.postgresql.util.PSQLException: ERROR: function lower(bytea) does not exist
  Hint: No function matches the given name and argument types. You might need to add explicit type casts.
  Position: 190
