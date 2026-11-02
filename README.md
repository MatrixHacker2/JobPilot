# JobPilot

> Your copilot for job hunting and freelance work.

JobPilot is an application to **manage your job applications**: you save job offers, track the status of each one, and an **LLM analyzes how well each offer fits your profile**, highlights your strengths and gaps, and drafts a cover letter for you.

> 🚧 **Status:** Phase 0 — Setup. The project has just started.

## Vision

Job hunting is repetitive and disorganized: offers spread across dozens of tabs, outdated spreadsheets, forgotten follow-ups and generic cover letters. JobPilot centralizes everything in one place and uses AI where it adds real value:

- **Organization:** a kanban board with the status of each application.
- **Analysis:** automatic extraction of offer data (stack, seniority, salary, work mode).
- **Decision:** a *match score* between your profile and the offer, with an explanation.
- **Action:** tailored cover letters and follow-up reminders.

It is also a portfolio project that showcases a professional backend: a well-designed REST API, security, serious testing, CI/CD, AI integration behind a provider abstraction, and cloud deployment.

## Main features

- Management of companies, job offers and applications with a status flow: `SAVED → SENT → INTERVIEW → OFFER / REJECTED`.
- Filtering and pagination by status, company and date.
- User profile built from your CV.
- LLM-powered offer analysis (pasted text or URL) and match score with strengths and gaps.
- Cover letter draft generation.
- Statistics dashboard (applications per week, response rate).
- Follow-up reminders.

## Tech stack

| Layer | Technology |
|---|---|
| Backend | Java 21 · Spring Boot 4 · Spring Data JPA · Spring Security + JWT |
| Database | PostgreSQL · Flyway |
| AI | Spring AI (OpenAI / Gemini / local model with Ollama) |
| Testing | JUnit 5 · Mockito · Testcontainers · JaCoCo |
| DevOps | Docker Compose · GitHub Actions · GCP Cloud Run |
| Frontend | React (Vite) |
| API docs | OpenAPI / Swagger |

### Stack and cost decisions

The goal is for the whole project to be runnable for **free**:

- **Spring AI vs LangChain4j:** **Spring AI** is chosen for its native Spring Boot integration and provider abstraction.
- **LLM:** development with **Ollama** (local, free) or the **Gemini free tier**; OpenAI remains a paid option, swappable via configuration.
- **Cloud Run:** has a generous monthly free tier (scales to zero).
- **Production database:** ⚠️ **Cloud SQL is not free** (only covered by GCP trial credits). Free PostgreSQL alternatives: **Neon** or **Supabase**. To be decided in Phase 6.
- **GitHub Actions:** free for public repositories.

## Roadmap

### Phase 0 — Setup
- [x] README with vision and roadmap, `.gitignore` and `CLAUDE.md`
- [x] MIT license
- [x] Spring Boot skeleton (Spring Initializr)
- [x] `docker-compose` with PostgreSQL

### Phase 1 — CRUD core
- [ ] `Company`, `JobOffer` and `Application` entities with statuses
- [ ] Flyway migrations
- [ ] REST API with validation (`@Valid`), DTOs and global error handling (`@ControllerAdvice`)
- [ ] Pagination and filters (status, company, date)
- [ ] OpenAPI/Swagger documentation

### Phase 2 — Quality
- [ ] Unit tests for services and integration tests with Testcontainers
- [ ] GitHub Actions: build, tests and coverage (JaCoCo)
- [ ] Structured logging and dev/prod profiles

### Phase 3 — Security
- [ ] Registration and login with JWT
- [ ] Each user only sees their own applications
- [ ] Basic rate limiting

### Phase 4 — AI
- [ ] Upload a CV and store it as a profile
- [ ] Structured data extraction from an offer (stack, seniority, salary, work mode) from text or URL
- [ ] Match score between profile and offer, with strengths and gaps
- [ ] Cover letter draft tailored to each offer
- [ ] LLM provider abstraction (OpenAI, Gemini or local model)

### Phase 5 — Frontend
- [ ] React kanban board of applications
- [ ] Offer detail view with its AI analysis
- [ ] Statistics dashboard

### Phase 6 — Production
- [ ] Follow-up reminders with `@Scheduled`
- [ ] Deployment on Cloud Run with a managed PostgreSQL database
- [ ] Public demo with fictional data

## Getting started

*Coming soon.* This will be documented here once the project skeleton exists (requirements: JDK 21, Docker, Node.js).

## License

Distributed under the [MIT](LICENSE) license.
