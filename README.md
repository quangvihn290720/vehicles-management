
# Vehicle Management System

Develop a basic vehicle management system using the Java Spring Boot framework. The system should allow users to register, authenticate (JWT), and manage vehicles with role-based access.


## Main Features

- User Registration: Endpoint for user sign-up.
- Login: Endpoint for user authentication.
- Add Vehicle: (Admin only) Endpoint to add a new vehicle.
- View Vehicles: Endpoint to retrieve all vehicles, including associated maintenance records (Admin) or user-specific vehicles and their records.
- Update Vehicle: Endpoint to update vehicle details, restricted to the vehicle owner or Admin.
- Add Maintenance Record: Endpoint to add maintenance records for vehicles, restricted to the record owner or Admin.
- View Maintenance Records: Endpoint to view maintenance records, linked with vehicle information, restricted to the record owner or Admin.
- Update Maintenance Record: Endpoint to update maintenance records, restricted to the record owner or Admin.

## Other Features

- Create Role/Permission
- Get All Role/Permission (Only Admin) 

## Tech stack
* Build tool: maven >= 3.9.5
* Java: 21
* Framework: Spring boot
* DBMS: MySQL

## Prerequisites
* Java SDK 21
* A MySQL server
* Create a database schema named 'vehicles-management'

## Start application
`mvn spring-boot:run`

## Build application
`mvn clean package`

## API Reference

* Link document in POSTMAN: https://documenter.getpostman.com/view/15417658/2sA3kbfd6H

* The backend localhost service will start at http://localhost:8080.

* Please note: All operations with the API must be logged and must access the token with OAuth 2.0 authorization.
* For the ADMIN role, please use the account below:
* `username: admin`
* `password: admin`

#### Create new user

```http
  POST /api/v1/users/registration
```

| Parameter  | Type     | Description           |
| :--------  | :------- |:----------------------|
| `username` | `string` | At least 6 characters |
| `password` | `string` | At least 6 characters |
| `firstName`| `string` |                       |
| `lastName` | `string` |                       |

#### Login

```http
  POST /api/v1/auth/log-in
```

| Parameter | Type     | Description |
| :-------- | :------- |:------------|
| `username`| `string` |             |
| `password`| `string` |             |

####  Add Vehicle: (Admin role only)

```http
  POST /api/v1/vehicles
```

| Parameter | Type        | Description |
|:----------|:------------|:------------|
| `make`    | `string`    |             |
| `model`   | `string`    |             |
| `year`    | `LocalDate` |             |
| `type`    | `string`    |             |
| `users`   | `Object`    |             |

####  View All Vehicle: (Admin role only)

```http
  GET /api/v1/vehicles
```

| Parameter  | Type     | Description                |
| :--------  | :------- | :------------------------- |

####  Get Vehicle By Id

```http
  GET /api/v1/vehicles/{id}
```

| Parameter | Type     | Description |
|:----------|:---------|:------------|
| `id`      | `string` |             |

####  UPDATE Vehicle

```http
  PUT /api/v1/vehicles
```

| Parameter | Type         | Description |
|:----------|:-------------|:------------|
| `make`    | `string`     |             |
| `model`   | `string`     |             |
| `year`    | `LocalDate`  |             |
| `type`    | `string`     |             |
| `users`   | `Object`     |             |

####  Add Maintenance Record

```http
  POST /api/v1/maintenance
```

| Parameter     | Type        | Description                |
|:--------------| :-------    | :------------------------- |
| `description` | `string`    |                            |
| `serviceDate` | `LocalDate` |                            |
| `cost`        | `string`    |                            |
| `vehicles`    | `Object`    |                            |

####  View All Maintenance Record: (Admin role only)

```http
  GET /api/v1/maintenance
```

| Parameter  | Type     | Description                |
| :--------  | :------- | :------------------------- |

####  Get Maintenance Record Id

```http
  GET /api/v1/maintenance/{id}
```

| Parameter  | Type     | Description                |
| :--------  | :------- | :------------------------- |
| `id`       | `string` |                            |

####  Get Maintenance By Vehicle Id

```http
  GET /api/v1/maintenance/vehicles/{id}
```

| Parameter  | Type     | Description                |
| :--------  | :------- | :------------------------- |
| `id`       | `string` |                            |

####  UPDATE Vehicle

```http
  PUT /api/v1/maintenance/
```

| Parameter     | Type        | Description                |
|:--------------| :-------    | :------------------------- |
| `description` | `string`    |                            |
| `serviceDate` | `LocalDate` |                            |
| `cost`        | `string`    |                            |
| `vehicles`    | `Object`    |                            |