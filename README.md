#📚 Simple Library Management System
A lightweight and efficient Library Management System built using Java Servlets, JSX, MySQL, and Apache Tomcat. This project employs the MVC (Model-View-Controller) architecture to ensure clean separation of concerns and ease of maintenance.

#🌐 Features
View Available Books: Browse a list of all books currently available in the library.
Borrow Books: Users can select books to borrow, and the system updates the availability status.
Return Books: Borrowed books can be returned, making them available for others.

#🚀 Tech Stack
Architecture: MVC (Model-View-Controller)
Model: MySQL database for managing and storing book and user data.
View: JSX-based frontend for a dynamic and responsive user interface.
Controller: Java Servlets handle user interactions and connect the frontend to the backend.
Backend: Java Servlets running on Apache Tomcat.
Database: MySQL to store and manage book and user data.

#📖 How It Works
View Books: Displays a list of books available in the library by querying the MySQL database (Model), presenting data through the frontend (View).
Borrow Books: Processes user requests through Servlets (Controller), updates the database (Model), and reflects the changes in the user interface (View).
Return Books: Handles book returns similarly, marking them as available in the database and updating the view.
