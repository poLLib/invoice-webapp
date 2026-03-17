# Invoice Manager Web Application

Welcome to the Invoice Manager Web Application! This project is designed to help users manage companies and their invoices within a database. It provides a user-friendly interface to perform various operations such as adding, viewing, editing, and deleting companies and invoices.

## Table of Contents

1. [Features](#features)
2. [Technologies Used](#technologies-used)
3. [Installation](#installation)
4. [Usage](#usage)
5. [API Documentation](#api-documentation)
6. [Contributing](#contributing)
7. [License](#license)

## Features

- **Company Management**: Add, edit, view, and delete companies.
- **Invoice Management**: Create, update, view, and delete invoices.
- **Statistics**: View statistics related to companies and invoices.
- **Search and Filter**: Search and filter invoices by various criteria.
- **Responsive Design**: User-friendly interface that works on both desktop and mobile devices.

## Technologies Used

- **Frontend**:
  - React 18
  - HTML5
  - CSS3 (Bootstrap 5)
- **Backend**:
  - Java 21
  - Spring Boot 3
  - Maven
- **Database**:
  - MariaDB
- **Infrastructure**:
  - Docker
  - Apache Tomcat (embedded)
- **VCS**:
  - Git
  - GitHub

## Installation

### Quick Start (Docker - Recommended)

The easiest way to run the application. You only need [Docker](https://www.docker.com/products/docker-desktop/) installed.

```bash
git clone https://github.com/poLLib/invoice-webapp.git
cd invoice-webapp
docker compose up
```

That's it! Open http://localhost:3000 in your browser.

| Service  | URL |
|----------|-----|
| Frontend | http://localhost:3000 |
| Backend API | http://localhost:8080/api |
| Swagger UI | http://localhost:8080/swagger-ui/index.html |

To stop the application:
```bash
docker compose down
```

To rebuild after code changes:
```bash
docker compose up --build
```

---

### Manual Installation (for Development)

Use this method if you want to develop with hot-reload support.

#### Prerequisites

- [JDK 21 Temurin](https://adoptium.net/temurin/releases/?arch=any&version=21&os=any)
- [Node.js 18+](https://nodejs.org/)
- [Docker](https://www.docker.com/products/docker-desktop/)
- [Git](https://git-scm.com/downloads)
- [Maven](https://maven.apache.org/download.cgi)

#### Steps

1. **Clone the repository:**
   ```bash
   git clone https://github.com/poLLib/invoice-webapp.git
   cd invoice-webapp
   ```

2. **Start the database in Docker container:**
   ```bash
   cd invoice-server-starter
   docker compose up -d
   ```

3. **Start the backend server:**
   ```bash
   mvn spring-boot:run
   ```

4. **Install and start the frontend** (in a new terminal):
   ```bash
   cd invoice-client-starter
   npm install
   npm start
   ```

5. **Open your browser and navigate to:**
   ```
   http://localhost:3000
   ```

## Usage

### Adding a Company
1. Navigate to the "Companies" section.
2. Click "Add Company".
3. Fill in the company details and click "Submit".

### Managing Invoices
1. Navigate to the "Invoices" section.
2. Click "Add Invoice" to create a new invoice.
3. Use the search and filter options to find specific invoices.
4. Click on an invoice to view or edit details.

### Viewing Statistics
1. Navigate to the "Statistics" section.
2. View various statistics related to companies and invoices.

## API Documentation

The API provides endpoints for managing companies and invoices using JSON format. You can also use the interactive API documentation via [Swagger UI](http://localhost:8080/swagger-ui/index.html#/).

Below is a summary of the available endpoints:

### Companies (Persons)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/persons` | Get all companies |
| POST | `/api/person` | Create a new company |
| GET | `/api/person/{id}` | Get a single company by ID |
| PUT | `/api/person/{id}` | Update a company by ID |
| DELETE | `/api/person/{id}` | Delete a company by ID |
| GET | `/api/persons/statistics` | Get statistics of each company |

### Invoices

| Method | Endpoint                | Description |
|--------|-------------------------|-------------|
| GET | `/api/invoices`         | Get all invoices |
| POST | `/api/invoice`          | Create a new invoice |
| GET | `/api/invoice/{id}`     | Get a single invoice by ID |
| PUT | `/api/invoice/{id}`     | Update an invoice by ID |
| DELETE | `/api/invoice/{id}`     | Delete an invoice by ID |
| GET | `/api/invoices/statistics` | Get invoice statistics |

### Invoices of a Company

| Method | Endpoint                | Description |
|--------|-------------------------|-------------|
| GET | `/api/identification/{identificationNumber}/sales` | Get all sold invoices by company identification number |
| GET | `/api/identification/{identificationNumber}/purchases` | Get all received invoices by company identification number |

## Contributing

Contributions are welcome! If you'd like to contribute:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
