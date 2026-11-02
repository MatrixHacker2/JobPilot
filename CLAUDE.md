# CLAUDE.md — JobPilot

Instructions for Claude Code in this repository. The full roadmap is in `README.md`.

## What is JobPilot
An application to manage job and freelance applications: save offers, track the status of each application, and let an LLM analyze how well an offer fits the user's profile (match score, strengths, gaps, cover letter).

## Current status
**Phase 0 — Setup (complete).** Spring Boot 4.0.9 skeleton in `backend/` (Maven, package `com.jobpilot`), `docker-compose.yml` with PostgreSQL, no domain code yet. Update this section when each phase is completed.

## Stack
| Layer | Technology |
|---|---|
| Backend | Java 21, Spring Boot 4, Spring Data JPA, Spring Security + JWT |
| DB | PostgreSQL + Flyway |
| AI | Spring AI (provider abstraction: OpenAI / Gemini / local Ollama) |
| Tests | JUnit 5, Mockito, Testcontainers, JaCoCo |
| Infra | Docker Compose (local), GitHub Actions (CI), GCP Cloud Run (prod) |
| Frontend | React + Vite |

## Planned repo structure
```
JobPilot/
├── backend/          # Spring Boot (Maven)
├── frontend/         # React + Vite
├── docker-compose.yml
├── .github/workflows/
├── README.md
└── CLAUDE.md
```

## Backend conventions
- Packages by feature (`company`, `joboffer`, `application`, `auth`, `ai`…), each with `controller`, `service`, `repository`, `domain`, `dto`.
- Never expose JPA entities in the API: use DTOs (Java records) and mappers.
- Validation with Jakarta Validation (`@Valid`) on input DTOs.
- Errors handled globally with `@RestControllerAdvice` and `ProblemDetail` responses (RFC 7807).
- DB schema **only** via Flyway migrations (`db/migration/V{n}__description.sql`). Never edit an already applied migration; create a new one. `spring.jpa.hibernate.ddl-auto=validate`.
- Constructor injection; no field `@Autowired`.
- Application statuses: `SAVED → SENT → INTERVIEW → OFFER | REJECTED`. Transitions are validated in the service layer.
- Every application query must filter by the authenticated user (from Phase 3).
- AI is always used behind our own interface (e.g. `LlmClient`); the domain does not depend on a specific provider. LLM responses are requested as structured JSON and validated.
- Spring profiles: `dev` and `prod`. Secrets only via environment variables.

## Frontend conventions
- React + Vite, functional components and hooks. Prefer TypeScript.
- API calls centralized in an `api/` layer; no hardcoded URLs.

## Tests
- Service unit tests with JUnit 5 + Mockito.
- Integration tests with Testcontainers (real PostgreSQL, never H2).
- All new business logic must come with tests.

## Commands (to be filled in once code exists)
```bash
docker compose up -d                 # local PostgreSQL
cd backend && ./mvnw spring-boot:run # API
cd backend && ./mvnw verify          # tests + coverage
cd frontend && npm run dev           # frontend
```

## Working rules for Claude
- Work phase by phase following the README roadmap; do not jump ahead unless asked.
- Before adding a new dependency, check that it is free/open source and justify it.
- Prefer services with a free tier. If something requires payment or a trial (e.g. Cloud SQL), flag it and propose an alternative.
- Never commit secrets, keys or `.env`. Keep `.env.example` up to date.
- Make small, verifiable changes; run tests before considering something done.
- Commits follow Conventional Commits (`feat:`, `fix:`, `docs:`, `test:`, `chore:`).
- Language: all documentation, code, identifiers and commit messages in English.
