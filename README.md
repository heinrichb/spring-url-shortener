# 🌱 Spring URL Shortener

A simple, self-hosted URL shortener built with **Spring Boot** and **Java**. This project is primarily a learning opportunity to explore **Spring RESTful APIs**, **JPA**, and **SQLite** for lightweight persistence.

---

## 🚀 Features

- **Shorten URLs** via a simple API.
- **Redirect shortened URLs** to their original destinations.
- **View all shortened URLs** in a simple web interface.
- **Minimal front-end** using **Thymeleaf** and **TailwindCSS (CDN)**.
- **SQLite** for persistence (no separate database setup required).
- **RESTful API** for managing URLs.

---

## 🏗 Build & Run

### Prerequisites

- **Java 23** or later
- **Maven**
- **Git**

### Clone the Repository

```sh
git clone https://github.com/your-username/spring-url-shortener.git
cd spring-url-shortener
```

### Run the Application

```sh
make run
```

OR manually with:

```sh
mvn spring-boot:run
```

The application will start on **http://localhost:8080/**.

---

## 📖 API Endpoints

| Method | Endpoint       | Description                  |
| ------ | -------------- | ---------------------------- |
| POST   | `/api/shorten` | Shorten a new URL            |
| GET    | `/api/all`     | Retrieve all shortened URLs  |
| GET    | `/{shortUrl}`  | Redirect to the original URL |

### Example Request

```sh
curl -X POST http://localhost:8080/api/shorten \
     -H "Content-Type: application/json" \
     -d '{"originalUrl": "https://example.com"}'
```

### Example Response

```json
{
	"shortUrl": "ZzQPSW1-",
	"originalUrl": "https://example.com"
}
```

---

## 🎨 Web Interface

Visit **http://localhost:8080/** to:

- View a list of all shortened URLs.
- Submit new URLs via a simple form.

---

## 📝 License

This project is licensed under the **GNU General Public License v3 (GPL-3.0)**. See the **LICENSE** file for details.

---

## 🙌 Acknowledgments

- **Spring Boot** - For the powerful backend framework.
- **TailwindCSS** - For easy and quick styling.
- **Thymeleaf** - For simple server-side rendering.

Enjoy using and modifying this project! 🚀
