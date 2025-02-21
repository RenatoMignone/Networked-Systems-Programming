# Networked-Systems-Programming

This repository provides example implementations of client-server applications in both **C** and **Java**. The C code demonstrates a low-level approach using standard POSIX socket programming libraries, while the Java application is built with Spring Boot and integrates with MongoDB along with several additional features.

> **Note:** Each folder contains its respective track file detailing the exercise instructions and progress.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Repository Structure](#repository-structure)
- [Prerequisites](#prerequisites)
- [Installation and Setup](#installation-and-setup)
  - [C Client-Server](#c-client-server)
  - [Java (Spring Boot) Application](#java-spring-boot-application)
- [Usage](#usage)
  - [Running the C Programs](#running-the-c-programs)
  - [Running the Java Application](#running-the-java-application)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

---

## Overview

This project is divided into two main parts:

1. **C Implementation:**  
   The C programs implement a basic client-server model using low-level socket programming. The following libraries are used:
   - `<sys/socket.h>`
   - `<netinet/in.h>`
   - `<arpa/inet.h>`
   - `<stdio.h>`
   - `<string.h>`
   - `<unistd.h>`

2. **Java Implementation (Spring Boot):**  
   The Java application leverages the Spring Boot framework to create a robust server. It integrates with MongoDB for data persistence and includes features such as RESTful API endpoints and security configurations.

---

## Features

- **C Client-Server:**
  - Basic socket programming for network communication.
  - Example of message exchange between client and server.
  - Contains a track file with exercise instructions.

- **Java (Spring Boot) Application:**
  - REST API endpoints for handling client requests.
  - MongoDB integration for data storage.
  - Security and configuration best practices.
  - Contains a track file with exercise instructions.

---