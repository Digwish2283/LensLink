# LensLink

> A structured marketplace for discovering and booking photographers and editors — bringing verified portfolios, transparent pricing, and seamless booking to a space that still runs on Instagram DMs.

---

## What is LensLink?

Finding a photographer today means scrolling through Instagram, guessing at pricing, sliding into DMs, and hoping they reply. LensLink fixes that.

It's a backend-driven marketplace where clients can discover creators by location, category, and budget — and book them through a clean, structured workflow. No DMs. No guesswork.

---

## Features (MVP)

- **Authentication** — JWT-based auth with role-based access (`CLIENT` / `CREATOR`)
- **Creator profiles** — Bio, city, categories, pricing range, and a shareable slug URL
- **Service listings** — Creators publish services with title, description, price, and delivery time
- **Portfolio** — Upload and showcase images/videos, with ordering and featured media
- **Discovery** — Search and filter by category, city, and price range
- **Booking system** — Full state machine: `PENDING → ACCEPTED → COMPLETED` with cancellation support
- **Reviews** — Clients rate and review after a completed booking

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot |
| Security | Spring Security + JWT |
| ORM | Spring Data JPA (Hibernate) |
| Database | PostgreSQL |
| File Storage | Cloudinary (→ AWS S3 in Phase 2) |
| Frontend | React |
| Containerization | Docker |

---

## Architecture

LensLink is built as a **modular monolith** — clean separation of concerns without the operational overhead of microservices.

```
React Frontend
      │
Spring Security · JWT Filter
      │
┌─────────────────────────────────────────┐
│         Spring Boot Monolith            │
│                                         │
│  auth · user · profile · category       │
│  portfolio · service · booking · review │
│                                         │
│  common · config · exception handler    │
└──────────────┬──────────────────────────┘
               │
    PostgreSQL + Cloudinary
```

**Layer flow inside every module:**
```
Controller → Service → Repository → Entity
```

- Input validation lives at the **Controller** layer (`@Valid` on DTOs)
- Business rules live at the **Service** layer (state checks, role guards, ownership)
- Data integrity lives at the **Database** layer (constraints, unique keys)
- Entities never leave the service layer — DTOs only

---

## Module Structure

```
src/main/java/com/lenslink/
├── auth/
├── user/
├── profile/
├── category/
├── portfolio/
├── service/
├── booking/
├── review/
├── common/
└── config/
```

---

## Booking State Machine

```
PENDING ──► ACCEPTED ──► COMPLETED
   │              │
   ▼              ▼
REJECTED       CANCELLED (client only)
   │
CANCELLED (client or creator)
```

A review can only be submitted after a booking reaches `COMPLETED`.

---

## API Overview

| Resource | Endpoints |
|---|---|
| Auth | `POST /api/auth/register` · `POST /api/auth/login` |
| Profiles | `GET /api/profiles/{slug}` · `PUT /api/profiles/me` |
| Discovery | `GET /api/search?category=&city=&minPrice=&maxPrice=` |
| Services | `POST /api/services` · `GET /api/services/{id}` · `PATCH /api/services/{id}/toggle` |
| Portfolio | `POST /api/portfolio/upload` · `GET /api/portfolio/{creatorId}` |
| Bookings | `POST /api/bookings` · `PUT /api/bookings/{id}/accept` · `PUT /api/bookings/{id}/complete` |
| Reviews | `POST /api/reviews` · `GET /api/reviews/{creatorId}` |

Full API documentation coming soon.

---

## Development Roadmap

- [x] Blueprint & architecture design
- [ ] **Phase 1** — Auth, user, profile, reference data (cities/categories)
- [ ] **Phase 2** — Services and portfolio
- [ ] **Phase 3** — Booking system
- [ ] **Phase 4** — Reviews and discovery/search
- [ ] **Phase 5** — Redis caching, WebSocket chat, payments

---

## Getting Started

> Prerequisites: Java 17, Maven, PostgreSQL, Docker (optional)

```bash
# Clone the repo
git clone https://github.com/your-username/lenslink-backend.git
cd lenslink-backend

# Configure environment
cp src/main/resources/application.example.properties src/main/resources/application.properties
# Fill in your DB credentials and Cloudinary keys

# Run
./mvnw spring-boot:run
```

Docker support and detailed setup guide coming in Phase 1.

---

## Contributing

This project is being built incrementally and in public. If you have suggestions, open an issue or start a discussion. PRs welcome once the core is stable.

---

## License
This project is currently unlicensed. All rights reserved.
