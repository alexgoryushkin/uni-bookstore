# uni-bookstore
Bookstore sample application

## Usage
To run the server, please execute the following from the root directory:

```
gradlew bootRun
```

Swagger UI can be found here:

```
http://localhost:8085/swagger-ui.html
```

Your Swagger definition lives here:

```
http://localhost:8085/v2/api-docs
```

## Running with Docker
To build docker image run the following:

```bash
docker build -t <name> .
```

To run docker image run the following:

```bash
docker run -d -p 8085:8085 <name>
```
