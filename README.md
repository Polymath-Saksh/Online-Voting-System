# Online Voting System

## Project Description
The Online Voting System is a web application that allows users to register, log in, and vote for candidates in an election. The system includes both a frontend and a backend, providing a complete solution for managing elections online.

## Features
- User registration and login
- Admin dashboard for managing voters and candidates
- Voter dashboard for viewing profile and voting
- Real-time vote counting and results display
- Secure authentication using JWT tokens

## Technologies Used
- Frontend: React, Vite, React Router
- Backend: Spring Boot, JPA, MySQL, JWT

## Setup Instructions

### Backend Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/Polymath-Saksh/Online-Voting-System.git
   cd Online-Voting-System/voting-spring
   ```

2. Configure the database:
   - Create a MySQL database named `voting_system`.
   - Update the `application.properties` file with your database credentials.

3. Build and run the backend:
   ```bash
   ./mvnw spring-boot:run
   ```

### Frontend Setup
1. Navigate to the frontend directory:
   ```bash
   cd ../frontend_voting/voting-app-frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Run the frontend:
   ```bash
   npm run dev
   ```

## Backend API Endpoints

### Voter Endpoints
- `POST /voters/register`: Register a new voter
- `POST /voters/login`: Login a voter and issue a JWT token
- `GET /voters/me`: Get logged-in voter details
- `PUT /voters/{id}`: Update voter details

### Admin Endpoints
- `GET /admin/voters`: Get all voters
- `PUT /admin/voters/{id}`: Update voter details
- `DELETE /admin/voters/{id}`: Delete a voter
- `POST /admin/candidates`: Add a new candidate
- `PUT /admin/candidates/{id}`: Update candidate details
- `DELETE /admin/candidates/{id}`: Delete a candidate
- `GET /admin/results`: Get election results

### Candidate Endpoints
- `GET /candidates`: Get all candidates
- `POST /candidates/{id}/vote`: Vote for a candidate

## Frontend Structure
- `src/components`: Contains reusable components like `Header`, `HomePage`, `Table`, etc.
- `src/pages`: Contains page components like `LoginPage`, `RegisterPage`, `AdminDashboard`, `VoterDashboard`, etc.
- `src/styles`: Contains CSS files for styling the components and pages.

## Usage Instructions
1. Register a new voter using the registration page.
2. Log in with the registered email and password.
3. Admin users can access the admin dashboard to manage voters and candidates.
4. Voter users can access the voter dashboard to view their profile and vote for candidates.
5. View the election results on the results page.

## Contributing
We welcome contributions to the Online Voting System project. To contribute, please follow these guidelines:
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes and push them to your forked repository.
4. Create a pull request with a detailed description of your changes.
