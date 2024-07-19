# Invoice Management Web Application

Welcome to the Store and Invoice Management Web Application! This project is designed to help users manage companies and their invoices within a database. It provides a user-friendly interface to perform various operations such as adding, viewing, editing, and deleting companies and invoices.

## Table of Contents

1. [Features](#features)
2. [Technologies Used](#technologies-used)
3. [Installation](#installation)
4. [Usage](#usage)
5. [API Documentation](#api-documentation)
6. [Contributing](#contributing)
7. [License](#license)
8. [Contact](#contact)

## Features

- **Company Management**: Add, edit, view, and delete companies.
- **Invoice Management**: Create, update, view, and delete invoices.
- **Statistics**: View statistics related to companies and invoices.
- **Search and Filter**: Search and filter invoices by various criteria.
- **Responsive Design**: User-friendly interface that works on both desktop and mobile devices.

## Technologies Used

- **Frontend**:
  - React.js
  - HTML5
  - CSS3 (Bootstrap)
- **Backend**:
  - Node.js
- **Database**:
  - MariaDB
- **Application Server**
  - Apache
- **Other Tools**:
  - Git
  - GitHub

## Installation

### Prerequisites

- JDK 18 [Download JDK 18 Temurin](https://adoptium.net/temurin/releases/?os=any&version=18)
- Node.js (v20.14.0 or later) [Download Node.js](https://nodejs.org/)
- XAMPP (which includes MariaDB and Apache) [Download XAMPP](https://www.apachefriends.org/index.html)

### Steps

1. **Clone the repository:**
   ```bash
   git clone https://github.com/poLLib/invoice-webapp.git
   cd invoice-webapp
   ```

2. **Install frontend (Node.js) dependencies:**
   ```bash
   cd invoice-client-starter
   npm install
   npm install bootstrap@5.2.3 react-router-dom@6.10.0
   ```

3. **Start the database and the application server on XAMPP**
   ```bash
   # For Linux
   cd /opt/lampp
   sudo ./xampp start

   # For macOS
   cd /Applications/XAMPP
   sudo ./xampp start

   # For Windows Command Prompt or PowerShell
   "C:\xampp\xampp-control.exe"
   ```
   Alternatively, you can open XAMPP Control Panel and start Apache server and MariaDB/MySQL.
   
4. **Start the backend server:**
   ```bash
   # Compile the Java source files
   javac -d out  /invoice-webapp/invoice-server-starter/src/main/java/cz/pollib/ApplicationMain.java

   cd out
   
   # Run the main class
   java com.example.YourMainClass
   ```
   Alternatively, you can use your preferred IDE to run the server.
   
5. **Start the frontend server:**
   ```bash
   cd ../invoice-client-starter
   npm start
   ```

6. **Open your browser and navigate to:**
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

The API provides endpoints for managing companies and invoices. Below is a summary of the available endpoints:

- **Companies**
  - `GET /api/companies`: Get all companies.
  - `POST /api/companies`: Create a new company.
  - `GET /api/companies/:id`: Get a single company by ID.
  - `PUT /api/companies/:id`: Update a company by ID.
  - `DELETE /api/companies/:id`: Delete a company by ID.

- **Invoices**
  - `GET /api/invoices`: Get all invoices.
  - `POST /api/invoices`: Create a new invoice.
  - `GET /api/invoices/:id`: Get a single invoice by ID.
  - `PUT /api/invoices/:id`: Update an invoice by ID.
  - `DELETE /api/invoices/:id`: Delete an invoice by ID.

For detailed API documentation, refer to the [API Documentation](API_DOCUMENTATION.md) file.

## Contributing

We welcome contributions from the community! To contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit them (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

Please ensure your code follows the project's coding standards and passes all tests.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For questions or support, please contact:

- **Your Name** - [your.email@example.com](mailto:your.email@example.com)
- **GitHub Issues** - [https://github.com/your-username/store-invoice-management/issues](https://github.com/your-username/store-invoice-management/issues)

Thank you for using our Store and Invoice Management Web Application!

---

Feel free to customize this template to better fit your project and provide more specific details where necessary.
