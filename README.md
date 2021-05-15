## My Contacts
My Contacts is an application for listing your contacts. 
The app loads the contacts from external **"people.csv"** file and import them in to the database.
### Installation
 Server side reads its properties from an external file that named **mycontacts.properties**. Property file should contain the commands following below.
 ```bash
//for the automatically creating tables
 spring.jpa.generate-ddl=true
 spring.jpa.hibernate.ddl-auto=create

//for the database connection. you can use different databases.
 spring.datasource.url=jdbc:postgresql://localhost:5432/contactlist
 spring.datasource.username=contactlist
 spring.datasource.password=contactlist

//if you want to run your client locally this property should be defined. 
//Otherwise client app will get CORS error during REST calls to the server app
 client.baseurl=http://localhost:3000
```

In the client app you need to define **REACT_APP_BASE_URL** in the **.env** file. Otherwise the client can not reach the server app.
```bash
REACT_APP_BASE_URL=http://localhost:8080
```

### Building Client App
Run the yarn build command when the parent working directory(pwd) is the **mycontacts/client/my-contacts**.
This script automatically builds the client app and copies the build files into the mycontacts/src/resources/static

### Building Server App
Run the mvn package command when the parent working directory(pwd) is the mycontacts
You can find the **mycontacts-0.0.1-SNAPSHOT.jar** file that located into target folder

### Running The Build Jar
Run the **java -jar mycontacts-0.0.1-SNAPSHOT.jar** command when pwd is the directory that have the files<br/>
**mycontacts-0.0.1-SNAPSHOT.jar<br/>**
**mycontacts.properties<br/>**
**people.csv<br/>**
**my-contacts-app.log**

### Running Client App on local
Run the **npm start** command when the pwd is the **mycontacts/client/my-contacts**. Client app uses the localhost:3000 by default.

### Running Server App on local
Run the **mvn spring-boot:run** command when the pwd is the **mycontacts**. Server app uses the localhost:8080 by default.

### Logging
You can find the logs in the my-contacts-app.log file.

### REST Endpoint
Request<br/>
GET /contact/search?name=${name}&page=${page}&size=${size}<br/>

```curl
curl -i -H 'Accept: application/json' 'http://localhost:8080/contact/search?name=Simpson&page=0&size=10'
```

Response
```http response
{
    "errorCode": null,
    "errorMessage": null,
    "data": {
        "content": [
            {
                "id": 1,
                "name": "Homer Simpson",
                "avatarUrl": " https://vignette.wikia.nocookie.net/simpsons/images/b/bd/Homer_Simpson.png/revision/latest/scale-to-width-down/72?cb=20140126234206"
            },
            {
                "id": 2,
                "name": "Marge Simpson",
                "avatarUrl": " https://vignette.wikia.nocookie.net/simpsons/images/4/4d/MargeSimpson.png/revision/latest/scale-to-width-down/78?cb=20180314071936"
            },
            {
                "id": 3,
                "name": "Bart Simpson",
                "avatarUrl": " https://vignette.wikia.nocookie.net/simpsons/images/6/65/Bart_Simpson.png/revision/latest/scale-to-width-down/87?cb=20180319061933"
            },
            {
                "id": 4,
                "name": "Lisa Simpson",
                "avatarUrl": " https://vignette.wikia.nocookie.net/simpsons/images/5/57/Lisa_Simpson2.png/revision/latest/scale-to-width-down/74?cb=20180319000458"
            },
            {
                "id": 5,
                "name": "Maggie Simpson",
                "avatarUrl": " https://vignette.wikia.nocookie.net/simpsons/images/8/89/Maggie.png/revision/latest/scale-to-width-down/87?cb=20090115172358"
            },
            {
                "id": 6,
                "name": "Abraham Simpson II",
                "avatarUrl": " https://vignette.wikia.nocookie.net/simpsons/images/a/a9/Abraham_Simpson.png/revision/latest/scale-to-width-down/75?cb=20151011181838"
            },
            {
                "id": 265,
                "name": "Homer Simpson",
                "avatarUrl": " https://vignette.wikia.nocookie.net/simpsons/images/b/bd/Homer_Simpson.png/revision/latest/scale-to-width-down/58?cb=20140126234206"
            },
            {
                "id": 266,
                "name": "Marge Simpson",
                "avatarUrl": " https://vignette.wikia.nocookie.net/simpsons/images/0/0b/Marge_Simpson.png/revision/latest/scale-to-width-down/56?cb=20180626055729"
            },
            {
                "id": 267,
                "name": "Bart Simpson",
                "avatarUrl": " https://vignette.wikia.nocookie.net/simpsons/images/6/65/Bart_Simpson.png/revision/latest/scale-to-width-down/70?cb=20180319061933"
            },
            {
                "id": 268,
                "name": "Lisa Simpson",
                "avatarUrl": " https://vignette.wikia.nocookie.net/simpsons/images/e/ec/Lisa_Simpson.png/revision/latest/scale-to-width-down/58?cb=20130818181431"
            }
        ],
        "pageable": {
            "sort": {
                "sorted": false,
                "unsorted": true,
                "empty": true
            },
            "pageNumber": 0,
            "pageSize": 10,
            "offset": 0,
            "paged": true,
            "unpaged": false
        },
        "last": false,
        "totalPages": 6,
        "totalElements": 60,
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "numberOfElements": 10,
        "size": 10,
        "number": 0,
        "first": true,
        "empty": false
    }
}
```