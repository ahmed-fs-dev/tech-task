# 🧩 Full Stack Task Management Application

A simple full-stack web application for managing a list of tasks, built with **Java Spring Boot** for the backend and **React.js** for the frontend.

---

## 🚀 Overview

This project is developed as part of the **Full Stack Developer Technical Task**.  
It demonstrates the integration between a RESTful Spring Boot API and a React frontend interface for performing CRUD operations on tasks.

---

## 🧱 Project Structure
```
tech-task/
├── backend/                  # Spring Boot application (REST API)
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/techtask/
│   │   │   │   ├── controller/
│   │   │   │   ├── model/
│   │   │   │   ├── repository/
│   │   │   │   └── service/
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   ├── pom.xml
│   └── ...
│
├── frontend/                 # React.js application (UI)
│   ├── public/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/         # API calls
│   │   └── App.jsx
│   ├── package.json
│   └── ...
│
├── .gitignore
└── README.md
```

---

## ⚙️ Tech Stack

### Backend
- **Java 21**
- **Spring Boot 3+**
- **Spring Web / Validation**
- **H2 Database (in-memory)**
- **Lombok** *(for boilerplate reduction)*

### Frontend
- **React 18+**
- **Axios** *(for API calls)*
- **React Hooks**

---

## 🎯 Features

### Backend
- Create, retrieve, update, and delete tasks
- Input validation and error handling
- In-memory data persistence (H2)
- RESTful endpoints following best practices

### Frontend
- Display all tasks with title, description, and status
- Add, edit, mark as completed, or delete tasks
- Error handling and responsive UI
- Real-time updates with React state

---

## 🧰 Installation & Setup

### 1️⃣ Clone the repository
```bash
git clone https://github.com/<your-username>/fullstack-task-app.git
cd fullstack-task-app