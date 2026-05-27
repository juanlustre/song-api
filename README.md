# Song API + SongTube Frontend

A Spring Boot REST API for songs with a YouTube-style React frontend.

## Architecture

- **Backend**: Spring Boot + JPA + PostgreSQL (port 8080)
- **Frontend**: Vite + React + TypeScript + Tailwind CSS (port 5173)

## Backend Setup

### Prerequisites
- Java 21
- PostgreSQL database

### Configuration
Set the following environment variables (or configure `application.properties`):

```
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/songdb
SPRING_DATASOURCE_USERNAME=<user>
SPRING_DATASOURCE_PASSWORD=<password>
ALLOWED_ORIGINS=http://localhost:5173
```

### Run backend

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

## Frontend Setup

### Prerequisites
- Node.js 18+

### Install dependencies

```bash
cd frontend
npm install
```

### Run frontend (dev)

```bash
cd frontend
npm run dev
```

The app will be available at `http://localhost:5173`.

The Vite dev server proxies `/api` → `http://localhost:8080`, so no CORS configuration changes are needed during development.

### Build for production

```bash
cd frontend
npm run build
```

## Running Both Concurrently

Open two terminals:

**Terminal 1** — Backend:
```bash
./mvnw spring-boot:run
```

**Terminal 2** — Frontend:
```bash
cd frontend && npm run dev
```

Then open `http://localhost:5173` in your browser.

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/velasco/songs` | List all songs |
| GET | `/velasco/songs/{id}` | Get song by ID |
| POST | `/velasco/songs` | Create a song |
| PUT | `/velasco/songs/{id}` | Update a song |
| DELETE | `/velasco/songs/{id}` | Delete a song |
| GET | `/velasco/songs/search/{keyword}` | Search songs |

### Song Schema

```json
{
  "id": 1,
  "title": "Bohemian Rhapsody",
  "artist": "Queen",
  "album": "A Night at the Opera",
  "genre": "Rock",
  "url": "https://www.youtube.com/watch?v=fJ9rUzIMcZQ"
}
```

## Frontend Features

- 🎬 YouTube-style interface with dark theme
- 🔍 Live search/filter by title, artist, album, genre
- 📺 Embedded YouTube player (iframe) for YouTube URLs
- 🎥 HTML5 video player for direct mp4/webm files
- 🔗 Fallback "Open in new tab" for other URLs
- 📱 Responsive design (mobile + desktop)
- ⚡ Loading and error states

## Environment Variables (Frontend)

| Variable | Default | Description |
|----------|---------|-------------|
| `VITE_API_BASE_URL` | `http://localhost:8080` | Backend base URL (for proxy target) |
