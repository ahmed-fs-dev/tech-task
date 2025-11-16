# 🧩 Full Stack Task Management Application

A simple full-stack web application for managing tasks, built with **Java Spring Boot** (backend) and **React.js** (frontend).

---

## 🚀 Overview

This project was developed as part of the **Full Stack Developer Technical Task**.  
It demonstrates how to integrate a RESTful Spring Boot API with a modern React frontend to perform full CRUD operations on tasks.

---

## 🧱 Project Structure
```text
tech-task/
├── backend/ # Spring Boot application (REST API)
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/com/techtask/
│ │ │ │ ├── controller/
│ │ │ │ ├── configuration/
│ │ │ │ ├── model/
│ │ │ │ ├── dto/
│ │ │ │ ├── exception/
│ │ │ │ ├── repository/
│ │ │ │ └── service/
│ │ │ └── resources/
│ │ │ └── application.properties
│ ├── pom.xml
│ └── ...
│
├── frontend/ # React.js application (UI)
│ ├── public/
│ ├── src/
│ │ ├── components/
│ │ ├── sub-pages/
│ │ ├── types/
│ │ ├── utils/
│ │ ├── services/ # API calls
│ │ └── App.tsx
│ ├── package.json
│ └── ...
│
├── .gitignore
└── README.md
```
---

## ⚙️ Tech Stack & Justification

### 🖥️ Backend (Spring Boot)

| Technology | Why it’s used |
|-----------|----------------|
| **Java 21** | Latest LTS version with improved performance and modern language features. |
| **Spring Boot 3+** | Provides fast setup, auto-configuration, and production-ready defaults. |
| **Spring Web** | Used to create RESTful controllers that expose task CRUD endpoints. |
| **Spring Validation** | Adds server-side validation for request payloads to ensure clean and predictable API inputs. |
| **Spring Data JPA** | Simplifies database access with automatic repository generation—no boilerplate SQL. |
| **Spring Security** | Included for CORS handling and future-proofing. Provides a secure, production-ready backend even without authentication. |
| **H2 Database (in-memory)** | A lightweight, fast, zero-configuration database ideal for demo/testing environments. |
| **Lombok** | Reduces boilerplate by generating getters, setters, constructors, and builders. |

---

### 🎨 Frontend (React)

| Technology | Why it’s used |
|-----------|----------------|
| **React 18+** | Modern component-based framework ideal for dynamic UIs. |
| **Axios** | Cleaner HTTP client for calling the backend API, with better error handling than `fetch()`. |
| **React Hooks** | Used for state management and lifecycle logic in a clean functional style. |
| **React Hook Form** | High-performance form handling with built-in validation and minimal re-renders. |
| **Sass** | Helps write cleaner, modular, maintainable CSS with variables and nesting. |

---

## 🎯 Features

### Backend
- CRUD operations for tasks
- DTO-based validation
- Global exception handling
- In-memory persistence using H2
- Clean REST architecture with services, controllers, and repositories

### Frontend
- List all tasks (title, description, status)
- Create, edit, delete, and mark tasks as completed
- Client-side validation & error handling
- Responsive UI with real-time updates

---

## 🧰 Installation & Setup

### 1️⃣ Clone the repository
```bash
git clone https://github.com/ahmed-fs-dev/tech-task.git
cd ./tech-task
```

### 2️⃣ Run the full application using Docker Compose Ensure Docker and Docker Compose are installed.
```bash
docker-compose up --build
```