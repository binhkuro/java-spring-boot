## Final Project
## 52000185 - Cao Nguyên Bình
## 52000196 - Nguyễn Tiến Đạt

## Spring Commerce
- Spring Commerce is a feature-rich e-commerce system that has been developed using the latest technologies and best software development practices. 
- The system is built on top of the popular Spring Framework and uses Java, JSP, Spring Web, Thymeleaf, Lombok, Hibernate, MYSQL, and Spring Security for user authentication and registration. 
- The combination of these technologies ensures that the system is reliable, scalable, and secure. With Spring Commerce, you can build robust e-commerce applications that can handle high traffic, complex business rules, and various payment and shipping options.
- Additionally, Spring Commerce has been thoroughly tested using JUnit and other unit testing frameworks to ensure its quality and reliability. The system has been designed with a modular and flexible code structure, making it easy to maintain and extend. Overall, Spring Commerce is a great choice for businesses looking to build a modern and robust e-commerce application.

## Software Development Principles, Patterns, Practices and Testing
This project follows common software development principles, patterns, and practices, such as:

- Separation of Concerns: Each component has its own responsibility, making the codebase easier to maintain.
- Model-View-Controller (MVC) Design Pattern: The project uses the MVC design pattern to separate the data, presentation, and control logic.
- Object-Relational Mapping (ORM): Hibernate is used to map the database tables to Java objects, making it easier to work with data.
- Spring Security: This framework provides security services, such as authentication and authorization.
- In addition to the above, JUnit is used for unit testing to ensure the correctness of the system's components and functionality.

## Code Structure:
- The code for this project is composed of multiple components, each serving a specific purpose. These components include user, product, order, contact, and user response.
- Each of these components has its corresponding repository and service, which handles the logic and interaction with the database. The repository is responsible for persisting data to the database, while the service performs the business logic for each component.
- The controllers are responsible for managing the navigation flow to their respective HTML pages. Additionally, MySQL queries are used to search for products based on their categories, making it easier for users to locate what they are looking for.
- To ensure the smooth operation of the database, the configuration details have been specified in the application.properties file. This file contains important details, such as the database URL, username, and password.
- To test the functionality of the code, JUnit test cases have been implemented. These test cases validate the correctness and accuracy of each component and ensure that the code operates as intended.

## Running the Application Locally
To run the Spring Commerce application on your local machine, follow these steps:

- Step 1: Delete the .metadata folder of the project and import the project from files in Spring Tool Suite 4 or IntelliJ.
- Step 2: Import the midterm project and wait for the system to install the references, dependencies and libraries.
- Step 3: Clean, Update Maven and setting JUnit 5 Library before running the application.
- Step 4: In MySQL, create a database named springcommerce and import the database in springcommerce.sql into the SQL of the created database.
- Step 5: Run the Spring Boot app successfully and go to localhost:8080.

Link Demo Project: https://youtu.be/swPYo4iv8w0

Some Account to login:

Admin account:
- Email: admin@gmail.com
- Password: 123456789

User account (for testing purposes):
- Email: caonguyenbinh12@gmail.com
- Password: 123456

## API Verification
To verify the APIs, you can test the project by running and testing it with JUnit tests and manual testing. As the API endpoints and documentation are not available in the provided requirements