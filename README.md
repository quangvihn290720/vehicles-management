
# Vehicle Management System

Develop a basic vehicle management system using the Java Spring Boot framework. The system should allow users to register, authenticate, and manage vehicles with role-based access.


## Features

- User Registration: Endpoint for user sign-up.
- Login: Endpoint for user authentication.
- Add Vehicle: (Admin only) Endpoint to add a new vehicle.
- View Vehicles: Endpoint to retrieve all vehicles, including associated maintenance records (Admin) or user-specific vehicles and their records.
- Update Vehicle: Endpoint to update vehicle details.
- Add Maintenance Record: Endpoint to add maintenance records for vehicles.
- View Maintenance Records: Endpoint to view maintenance records, linked with vehicle information.
- Update Maintenance Record: Endpoint to update maintenance records.

## Tech stack
* Build tool: maven >= 3.9.5
* Java: 21
* Framework: Spring boot
* DBMS: MySQL

## Prerequisites
* Java SDK 21
* A MySQL server

## Start application
`mvn spring-boot:run`

## Build application
`mvn clean package`

## API Reference

#### Create new user

```http
  POST /api/v1/users/registration
```

| Parameter  | Type     | Description                |
| :--------  | :------- | :------------------------- |
| `username` | `string` |               |
| `password` | `string` |               |
| `firstName`| `string` |               |
| `lastName` | `string` |               |

#### Login

```http
  POST /api/v1/auth/log-in
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `username`| `string` |                      |
| `password`| `string` |                      |

####  Add Vehicle: (Admin role only)

```http
  POST /api/v1/vehicles
```

| Parameter  | Type     | Description                |
| :--------  | :------- | :------------------------- |
| `make`     | `string` |               |
| `model`    | `string` |               |
| `year`     | `string` |               |
| `type`     | `string` |               |
| `users.id` | `string` |               |

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

| Parameter  | Type     | Description                |
| :--------  | :------- | :------------------------- |
| `id`     | `string` |               |

####  UPDATE Vehicle

```http
  PUT /api/v1/vehicles
```

| Parameter  | Type     | Description                |
| :--------  | :------- | :------------------------- |
| `make`     | `string` |               |
| `model`    | `string` |               |
| `year`     | `string` |               |
| `type`     | `string` |               |
| `users.id` | `string` |               |

####  Add Maintenance Record

```http
  POST /api/v1/maintenance
```

| Parameter     | Type        | Description                |
| :------------ | :-------    | :------------------------- |
| `description` | `string`    |                            |
| `serviceDate` | `LocalDate` |                            |
| `cost`        | `string`    |                            |
| `vehicles.id` | `string`    |                            |

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

####  UPDATE Vehicle

```http
  PUT /api/v1/maintenance/
```

| Parameter     | Type        | Description                |
| :------------ | :-------    | :------------------------- |
| `description` | `string`    |                            |
| `serviceDate` | `LocalDate` |                            |
| `cost`        | `string`    |                            |
| `vehicles.id` | `string`    |                            |