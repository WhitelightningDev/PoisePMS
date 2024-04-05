# Poise Project Management System (PoisePMS)

The Poise Project Management System (PoisePMS) is a Java-based application designed to streamline project management tasks, particularly for construction projects. The system offers a range of features to help users create, update, finalize, and delete projects, as well as manage associated entities such as architects, contractors, and customers.

## Table of Contents

- [Features](#features)
- [How to Use](#how-to-use)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Dependencies](#dependencies)
- [Contributing](#contributing)
- [License](#license)
- [Authors](#authors)
- [Acknowledgments](#acknowledgments)

## Features

1. **Project Creation**: Users can input project details such as project name, building type, physical address, ERF number, total fee, total paid to date, and deadline.
2. **Project Update**: Existing project details can be updated, including project name, building type, physical address, ERF number, total fee, total paid to date, and deadline.
3. **Project Finalization**: Upon completion, projects can be finalized, marking them as "Finalized" in the database.
4. **Project Deletion**: Users have the option to delete projects, along with associated architects, contractors, and customers.
5. **Project Finding**: Projects can be searched and selected by project number, project name, or displayed in a list of all projects stored in the database.

## How to Use

### Prerequisites

- Java Development Kit (JDK) 8 or higher installed on your system.
- MySQL Connector/J for database connectivity.

### Installation

1. Clone the repository to your local machine using the following command:git clone (https://github.com/yourusername/PoisePMS.git)

2. Set up your MySQL database and configure the connection details in the `DatabaseConnector` class.

### Running the Application

1. Open the project in your preferred Integrated Development Environment (IDE).
2. Compile the Java files and run the `Main` class to start the application.
3. Follow the on-screen prompts to navigate and interact with the system.

## Dependencies

- Java Development Kit (JDK) 8 or higher
- MySQL Connector/J (for database connectivity)

## Contributing

Contributions to the Poise Project Management System are welcome! To contribute, follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature-name`).
3. Make your changes and commit them (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature/your-feature-name`).
5. Create a new pull request.

## License

This project is licensed under the [MIT License](LICENSE).

## Authors

- [Daniel Mommsen](https://github.com/WhitelightningDev) - Lead Developer
