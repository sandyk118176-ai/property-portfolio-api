# Property Portfolio Tracker API

A REST API for managing rental property portfolios — properties, units, and tenants — built with Spring Boot, Spring Data JPA, and H2.
Frontend: [property-portfolio-frontend](https://github.com/sandyk118176-ai/property-portfolio-frontend) (React + TypeScript)

**Live demo:** https://property-portfolio-frontend.vercel.app
**API base URL:** https://property-portfolio-api.onrender.com

## Overview

This API models a simple property management workflow: a landlord owns multiple **properties**, each property has multiple **units**, and each unit can have one **tenant**. The API enforces real business rules — for example, a unit is automatically marked as occupied when a tenant moves in, and vacant again when they move out.

## Tech Stack

- **Java 21**
- **Spring Boot 3** (Spring Web, Spring Data JPA, Spring Validation)
- **H2 Database** (file-based, persists locally)
- **Maven** (with Maven Wrapper — no local Maven install required)

## Data Model
- **Property**: address, purchase price, monthly expenses
- **Unit**: unit number, monthly rent, occupied status — belongs to one Property
- **Tenant**: name, lease start/end dates — belongs to one Unit

## API Endpoints

| Method | Endpoint                          | Description                          |
|--------|------------------------------------|---------------------------------------|
| GET    | `/api/properties`                  | List all properties                   |
| GET    | `/api/properties/{id}`             | Get a single property                  |
| POST   | `/api/properties`                  | Create a new property                  |
| PUT    | `/api/properties/{id}`             | Update a property                      |
| DELETE | `/api/properties/{id}`             | Delete a property                      |
| GET    | `/api/units`                       | List all units                         |
| GET    | `/api/units/{id}`                  | Get a single unit                      |
| POST   | `/api/properties/{propertyId}/units` | Create a unit under a property        |
| PUT    | `/api/units/{id}`                  | Update a unit                          |
| DELETE | `/api/units/{id}`                  | Delete a unit                          |
| GET    | `/api/tenants`                     | List all tenants                       |
| GET    | `/api/tenants/{id}`                | Get a single tenant                    |
| POST   | `/api/units/{unitId}/tenants`      | Move a tenant into a unit               |
| PUT    | `/api/tenants/{id}`                | Update a tenant                        |
| DELETE | `/api/tenants/{id}`                | Delete a tenant (unit becomes vacant)  |

## Business Logic Highlights

- Creating a tenant automatically sets their unit's `occupied` field to `true`.
- Deleting a tenant automatically sets their unit's `occupied` field back to `false`.
- Input validation (e.g. non-blank addresses, non-negative rent) is enforced via Jakarta Bean Validation before data reaches the database.

## Architecture

Follows a standard layered architecture:
Dependencies are wired using constructor-based injection throughout, rather than field injection, for better testability.

## Running Locally

**Prerequisites:** Java 21+ installed.

```bash
# Clone the repo
git clone https://github.com/sandyk118176-ai/property-portfolio-api.git
cd property-portfolio-api

# Run the app (no Maven install needed — uses the bundled wrapper)
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

## Example Usage

Create a property:
```bash
curl -X POST http://localhost:8080/api/properties \
  -H "Content-Type: application/json" \
  -d '{"address": "123 Main St", "purchasePrice": 250000, "monthlyExpenses": 1200}'
```

## Database Console

While the app is running, view the database directly in your browser at `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:file:./data/propertydb`).

## Roadmap

- [ ] React + TypeScript frontend
- [ ] Unit tests for service layer (JUnit + Mockito)
- [ ] Pagination and filtering on GET endpoints