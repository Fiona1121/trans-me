# TransMe 🎙✨  
**A powerful online transcription tool**

[![Java](https://img.shields.io/badge/Java-8%2B-blue)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5-green)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-17-blue)](https://reactjs.org/)
[![MongoDB](https://img.shields.io/badge/MongoDB-Database-brightgreen)](https://www.mongodb.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

> **TransMe** is an advanced transcription management system that provides real-time speech-to-text conversion, terminology extraction, translation, and summarization. It integrates AI-based speech recognition with a user-friendly text editor to enhance productivity.

## 🚀 Features  

- 🎤 **Speech-to-Text** – Converts audio recordings into editable text.  
- 🔍 **Terminology Extraction** – Automatically identifies and highlights key terms.  
- 🔄 **Translation & Summarization** – Provides multilingual translations and concise summaries.  
- 📂 **Audio Management** – Upload, organize, and process audio files.  
- 🔐 **Secure Authentication** – User authentication with JWT and role-based access control.  
- 🎛 **Intuitive UI** – Block-based text editing for easy content structuring.  


## 🛠️ Tech Stack  

- **Frontend**: React, Redux, Material-UI  
- **Backend**: Spring Boot, MongoDB, Google Speech-to-Text API  
- **Authentication**: JWT-based security  
- **Cloud Storage**: Google Drive API for audio files  
- **Deployment**: Docker (planned), GitLab CI/CD  


## 📥 Installation  

### 1️⃣ Clone the Repository  

```sh
git clone https://github.com/Fiona1121/trans-me.git
cd trans-me
```

### 2️⃣ Backend Setup  

- **Navigate to the backend directory**  

  ```sh
  cd backend
  ```

- **Set up environment variables** in a `.env` file:  

  ```ini
  SERVER_PORT=8080
  MONGO_URI=<your_mongodb_connection_string>
  JWT_SECRET=<your_jwt_secret>
  GOOGLE_API_KEY=<your_google_api_key>
  ```

- **Install dependencies and start the backend**  

  ```sh
  mvn clean install
  mvn spring-boot:run
  ```

### 3️⃣ Frontend Setup  

- **Navigate to the frontend directory**  

  ```sh
  cd ../frontend
  ```

- **Install dependencies**  

  ```sh
  npm install
  ```

- **Start the frontend server**  

  ```sh
  npm start
  ```

- Open [http://localhost:3000](http://localhost:3000) in your browser.


## 📂 Project Structure  

```plaintext
trans-me/
├── backend/  
│   ├── src/main/java/com/transme/  
│   │   ├── controllers/  # API Controllers  
│   │   ├── services/     # Business Logic  
│   │   ├── models/       # MongoDB Schema  
│   │   ├── config/       # Security & Configurations  
│   │   ├── repositories/ # Database Access Layer  
│   ├── application.yml   # Spring Boot Configurations  
│   ├── pom.xml           # Maven Dependencies  
│   ├── Dockerfile        # Planned Deployment  
├── frontend/  
│   ├── src/components/   # UI Components  
│   ├── src/pages/        # Application Pages  
│   ├── src/redux/        # Global State Management  
│   ├── src/api/          # API Calls  
│   ├── package.json      # Node Dependencies  
│   ├── Dockerfile        # Planned Deployment  
└── README.md  
```


## ⚙️ Usage  

1. **Upload an Audio File**  
   - Supports `.wav` format  
   - Uses Google Speech-to-Text for transcription  

2. **Text Editing & Block Management**  
   - Add, remove, and restructure blocks of text  
   - Extract key terms for further use  

3. **Translation & Summarization**  
   - Get instant translations in multiple languages  
   - Summarize lengthy transcripts automatically  

4. **User Authentication**  
   - Secure login & registration using JWT  


## 🚧 Roadmap  

- [x] Speech-to-Text Integration  
- [x] Terminology Extraction  
- [ ] UI Enhancements for Block-based Editing  
- [ ] Docker Deployment  
- [ ] CI/CD Integration with GitLab  
