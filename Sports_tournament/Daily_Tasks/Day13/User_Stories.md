# Day 13 — User Stories

**Project:** Sports Tournament Management System (SportX)  
**Source reviewed:** [`docs/day9/user-stories.md`](../../docs/day9/user-stories.md) and [`docs/day9/backlog-board.md`](../../docs/day9/backlog-board.md)

## Source note

I could not find a Project Catalog or a numbered FR1–FR8 list in the current `Sports_tournament` project files. To avoid making up requirements, these eight stories carry forward the eight distinct feature areas already recorded in the Day 9 project backlog. The FR labels below are working references for board tracking, not catalog IDs verified against a catalog. If the original catalog is supplied later, confirm the mapping before submission.

## Stories and acceptance criteria

### US-01 — Account registration and secure access
**Working requirement reference:** FR-01 — user registration, login, and role-based access (from the Day 9 backlog).

**As a** user, **I want** to register and sign in securely, **so that** I can use the system features permitted for my role.

**Acceptance criteria**
- A user can register by providing valid required details.
- A user with valid credentials can sign in; invalid credentials produce a clear error.
- Access to protected actions follows the user's assigned role.
- Invalid or incomplete registration details are explained and are not accepted.

### US-02 — Tournament management
**Working requirement reference:** FR-02 — create and manage tournaments.

**As an** organizer, **I want** to create, view, and update tournament details, **so that** I can set up and maintain a competition.

**Acceptance criteria**
- An authorized organizer can create a tournament with its required details.
- Required fields and invalid values are validated with useful feedback.
- Saved tournament details can be viewed and updated by an authorized user.
- Users without permission cannot perform organizer-only changes.

### US-03 — Team and player registration
**Working requirement reference:** FR-03 — register teams and players.

**As an** organizer, **I want** to register teams and associate players with them, **so that** participants can be represented in a tournament.

**Acceptance criteria**
- A team can be registered with its required details.
- Players can be associated with the appropriate team.
- Missing, invalid, or duplicate registration data is rejected with a clear explanation.
- Team and player information can be reviewed after saving.

### US-04 — Match scheduling
**Working requirement reference:** FR-04 — schedule tournament matches.

**As an** organizer, **I want** to schedule matches with participating teams, dates, and venues, **so that** teams know when and where to compete.

**Acceptance criteria**
- An authorized organizer can schedule a match for a tournament.
- The selected teams, date/time, and venue are recorded and displayed.
- Invalid scheduling details and conflicts are detected and explained.
- A match cannot be scheduled with invalid or ineligible teams.

### US-05 — Live match scoring
**Working requirement reference:** FR-05 — record live match scores.

**As a** referee, **I want** to record score updates during a match, **so that** the current match progress is available to viewers.

**Acceptance criteria**
- An authorized referee can record a score update for the correct active match.
- The displayed match score reflects accepted updates.
- Invalid updates and updates from unauthorized users are rejected with a clear message.
- Updates remain associated with the correct match.

### US-06 — Match finalization
**Working requirement reference:** FR-06 — finalize completed matches.

**As a** referee, **I want** to finalize a completed match, **so that** its result becomes the official recorded result.

**Acceptance criteria**
- An authorized referee can finalize a match that is eligible for completion.
- The final result is saved and can be retrieved afterward.
- Unauthorized users and invalid finalization attempts are rejected with a clear message.
- Repeating finalization does not silently create a conflicting result.

### US-07 — Tournament standings
**Working requirement reference:** FR-07 — view tournament standings.

**As a** participant, **I want** to view standings for a tournament, **so that** I can understand team rankings and progress.

**Acceptance criteria**
- A user can view standings for a selected tournament.
- Standings reflect recorded match results and the configured ranking rules.
- Teams are presented in rank order with relevant points or totals.
- An unavailable or unknown tournament is handled with a clear message.

### US-08 — Tournament dashboard
**Working requirement reference:** FR-08 — review tournament and match activity on a dashboard.

**As an** organizer, **I want** to see a dashboard summary of tournaments and match activity, **so that** I can monitor competition progress from one place.

**Acceptance criteria**
- The dashboard presents relevant tournament and match summaries.
- Summary information is readable and corresponds to the available project data.
- Empty or unavailable data is handled clearly.
- Dashboard information is refreshed or updated according to the application's supported behavior.

## Planning status

These are planning artifacts for the Day 13 submission. Their presence here does not mean the application features have been newly implemented, accepted, or placed on an external project board.
