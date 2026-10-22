# Project Board Setup — Day 13

**Project:** Sports Tournament Management System (SportX)  
**Prepared board tool:** GitHub Projects or Jira (choose one for the submission)  
**Board status:** Setup instructions and backlog details are prepared here. No external board has been created or updated by this document.

## Board columns / workflow

Create these statuses in this order:

1. **Backlog** — captured work not yet selected for active preparation.
2. **Ready** — acceptance criteria and dependencies are clear; work can be started.
3. **In Progress** — implementation or task work is underway.
4. **In Review** — work is awaiting peer/reviewer validation.
5. **Done** — all applicable conditions in [`Definition_of_Done.md`](Definition_of_Done.md) are satisfied.

For the initial board, place all eight stories in **Backlog**. This represents unstarted planning work and avoids claiming work has begun or passed review. Move stories only when the real work reaches the next status.

## Backlog cards

Create one issue/card for each row. Use the story title as the card title, the story statement as the description, and paste the acceptance criteria into the card body. Set the estimate using the story points below. Apply the matching ID label or prefix.

| ID | Title | Description | Points | Initial status |
| --- | --- | --- | ---: | --- |
| US-01 | Account registration and secure access | As a user, I want to register and sign in securely, so that I can use the system features permitted for my role. | 5 | Backlog |
| US-02 | Tournament management | As an organizer, I want to create, view, and update tournament details, so that I can set up and maintain a competition. | 5 | Backlog |
| US-03 | Team and player registration | As an organizer, I want to register teams and associate players with them, so that participants can be represented in a tournament. | 5 | Backlog |
| US-04 | Match scheduling | As an organizer, I want to schedule matches with participating teams, dates, and venues, so that teams know when and where to compete. | 8 | Backlog |
| US-05 | Live match scoring | As a referee, I want to record score updates during a match, so that the current match progress is available to viewers. | 8 | Backlog |
| US-06 | Match finalization | As a referee, I want to finalize a completed match, so that its result becomes the official recorded result. | 5 | Backlog |
| US-07 | Tournament standings | As a participant, I want to view standings for a tournament, so that I can understand team rankings and progress. | 5 | Backlog |
| US-08 | Tournament dashboard | As an organizer, I want to see a dashboard summary of tournaments and match activity, so that I can monitor competition progress from one place. | 5 | Backlog |

The full acceptance criteria for each card are in [`User_Stories.md`](User_Stories.md). Link or attach [`Definition_of_Done.md`](Definition_of_Done.md) as a project-level reference; for individual cards, include a short note such as: “Done when the applicable project Definition of Done is met.” Keep the full checklist on the project so it does not need to be duplicated eight times.

## Suggested fields

- **Title:** `[US-##] Story title`
- **Description:** user story plus acceptance criteria
- **Status:** one of the five statuses above
- **Estimate:** story points (use the native estimate field if available)
- **Labels:** `user-story`, `US-##` (optional), and feature area if useful
- **Definition of Done:** link to the project-level DoD document

## GitHub Projects checklist

- [ ] Open the repository on GitHub and select **Projects** (or create a project from the Projects page).
- [ ] Create a board project named **SportX — Day 13 User Story Backlog**.
- [ ] Configure the five statuses: Backlog, Ready, In Progress, In Review, Done.
- [ ] Add the eight user story issues/cards using the IDs, descriptions, and estimates in this document; paste each card's acceptance criteria from `User_Stories.md`.
- [ ] Set all eight new cards to **Backlog** and add the project DoD link to the project description or card templates.
- [ ] Confirm the board shows the status and estimate fields and all eight IDs.
- [ ] Capture a screenshot showing the project name, all five workflow statuses, and the eight story cards (or capture additional screenshots if the columns require scrolling).
- [ ] Save the screenshot in the required Day 13 submission location and include the board URL with the submission.

## Jira alternative

- [ ] Create a Jira Scrum or Kanban project named **SportX — Day 13 User Story Backlog**.
- [ ] Configure the workflow to use Backlog, Ready, In Progress, In Review, and Done.
- [ ] Create eight issues of type **Story**. Use the `US-##` IDs in summaries or labels, add the acceptance criteria, and set story points.
- [ ] Keep all eight stories in Backlog initially; link the Definition of Done from the project documentation or each issue.
- [ ] Verify the board columns and story point estimates, capture a screenshot, and record the board URL for submission.

## Evidence checklist

- [ ] Board URL recorded.
- [ ] Screenshot clearly shows the project name and configured statuses.
- [ ] All eight stories appear with their IDs and estimates.
- [ ] Acceptance criteria and DoD reference are present in issue/card details.
- [ ] Screenshot and board URL are included with the Day 13 submission.

**Submission state:** Documentation and board entry checklist are ready. Creating the actual GitHub Projects/Jira board, adding cards, and capturing a screenshot remain manual steps.
