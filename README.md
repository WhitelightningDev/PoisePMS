# Poise Project Management System (PoisePMS)

The Poise Project Management System (PoisePMS) is a Java-based application designed to streamline project management tasks, particularly for construction projects. The system offers a range of features to help users create, update, finalize, and delete projects, as well as manage associated entities such as architects, contractors, and customers.This project was created as part of my time at [HyperionDev](#HyperionDev) This is project number #4

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

1. **Project Creation**: First the database checks to see if it already sxistsif not then it will create the database and its tabbles.Users can input project details such as project name, building type, physical address, ERF number, total fee, total paid to date, and deadline.
2. **Project Update**: Existing project details can be updated, including project name, building type, physical address, ERF number, total fee, total paid to date, and deadline.
3. **Project Finalization**: Upon completion, projects can be finalized, marking them as "Finalized" in the database.
4. **Project Deletion**: Users have the option to delete projects, along with associated architects, contractors, and customers.
5. **Project Finding**: Projects can be searched and selected by project number, project name, or displayed in a list of all projects stored in the database.

## How to Use

### Prerequisites

- Java Development Kit (JDK) 8 or higher installed on your system.
- MySQL Connector/J for database connectivity.

### Installation

1. Clone the repository to your local machine using the following command:git clone (https://github.com/WhitelightningDev/PoisePMS.git)

2. Set up your MySQL database and configure the connection details in the `DatabaseConnector` class.

### Running the Application

1. Open the project in your preferred Integrated Development Environment (IDE).
2. Compile the Java files and run the `Main` class to start the application.
3. Follow the on-screen prompts to navigate and interact with the system.

## Dependencies

- Java Development Kit (JDK) 8 or higher
- MySQL Connector/J (for database connectivity) can be downloaded of the internet (https://www.oracle.com/za/database/technologies/appdev/jdbc-downloads.html)

## Contributing

Contributions to the Poise Project Management System are welcome! To contribute, follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature-name`).
3. Make your changes and commit them (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature/your-feature-name`).
5. Create a new pull request.

## License

This project is licensed under the [MIT License](LICENSE). By cloning or using this project, you agree to comply with the terms and conditions of the MIT License. You are free to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the software, subject to the following conditions:

1. You must include a copy of the MIT License along with any copies or substantial portions of the software.

2. The software is provided "as is," without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, fitness for a particular purpose, and noninfringement. In no event shall the authors or copyright holders be liable for any claim, damages, or other liability, whether in an action of contract, tort, or otherwise, arising from, out of, or in connection with the software or the use or other dealings in the software.
3. Include the origional auther


## Authors

- [Daniel Mommsen](https://github.com/WhitelightningDev) - Lead Developer
- [Contact](#+27746588885)
- [Email](#whitelightningdev@gmail.com)
- [Android Dev Profile](https://developers.google.com/profile/u/116571349643980913187/dashboard)
