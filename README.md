# AI Article Summarizer

![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green.svg)
![Angular](https://img.shields.io/badge/Angular-17-red.svg)
![OpenAI API](https://img.shields.io/badge/OpenAI-API-yellow.svg)

## Overview

This repository contains a scalable, modular AI-powered article summarizer application built with Angular 17 (frontend) and Spring Boot (backend). The backend integrates with the OpenAI API to generate concise summaries.

---

## Backend (Spring Boot)

### Technologies Used
- Java 17
- Spring Boot 3
- WebFlux (WebClient)
- OpenAI API

### Structure
```
backend/
├── controller/
├── service/
├── configuration/
└── application.properties
```

### Endpoint
**POST** `/api/summarize`

**Request:**
```json
{
  "text": "Text to summarize..."
}
```

**Response:**
```json
{
  "summary": "Generated summary"
}
```

### Running Backend
```bash
./mvnw spring-boot:run
```

---

## Frontend (Angular 17)

### Technologies Used
- Angular 17
- Standalone Components
- HttpClient API
- FormsModule (for ngModel)

### Structure
```
frontend/
├── src/
│   ├── app/
│   │   ├── summarizer/
│   │   ├── summarizer.component.ts
│   │   ├── summarizer.component.html
│   │   └── summarizer.component.scss
│   ├── app.component.ts
│   └── app.config.ts
```

### Running Frontend
```bash
npm install
ng serve
```

### Accessing the Application
```
http://localhost:4200
```

### Example Workflow
- Enter text in the textarea
- Click "Riassumi con AI"
- View generated summary below the textarea

---

## Testing with Postman

### Step 1: Open Postman
Ensure Postman is installed on your system.

### Step 2: Create a New Request
1. Open Postman and create a **new request**.
2. Select **POST** as the HTTP method.
3. Enter the request URL: `http://localhost:8080/summarize`

### Step 3: Set Headers
In the **Headers** tab, add:
- `Content-Type`: `application/json`

### Step 4: Set the Request Body
Go to the **Body** tab and select **raw**. Enter the following JSON:
```json
{
  "text": "Your article text here to summarize."
}
```

### Step 5: Send the Request
Click **Send** and check the response in the **Response** section.

### Expected Response
```json
{
  "summary": "Generated summary of the input text."
}
```

---

## Usage

1. Ensure your backend is running (`http://localhost:8080`).
2. Run the Angular frontend (`http://localhost:4200`).
3. Paste the text to summarize and click the button "Riassumi con AI".

---

## Requirements
- Set the environment variable `API_KEY` with your OpenAI API key.

---

## License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

---

Enjoy your fully functional and scalable AI summarizer application! 🚀
