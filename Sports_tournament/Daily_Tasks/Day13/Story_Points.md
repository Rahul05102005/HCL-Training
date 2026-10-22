# Story Point Estimates

**Project:** Sports Tournament Management System (SportX)  
**Scale:** Relative Fibonacci-style estimates: 1, 2, 3, 5, 8. Points describe comparative effort, complexity, and uncertainty; they are not hours or a delivery commitment.

| Story ID | Story | Points | Estimate rationale |
| --- | --- | ---: | --- |
| US-01 | Account registration and secure access | 5 | Covers registration and login flows, validation, role-aware access, and security-related failure cases. |
| US-02 | Tournament management | 5 | Requires authorized create, view, and update flows with required-field validation and persistence. |
| US-03 | Team and player registration | 5 | Includes linked team/player data, registration rules, and duplicate or invalid-data handling. |
| US-04 | Match scheduling | 8 | More coordination is involved across tournament, team, date/time, venue, and conflict validation rules. |
| US-05 | Live match scoring | 8 | Score updates depend on match state and referee permissions, with correctness and timely display concerns. |
| US-06 | Match finalization | 5 | A bounded workflow, but result persistence, eligibility, authorization, and repeat-action safety need care. |
| US-07 | Tournament standings | 5 | Requires applying ranking rules to match results and presenting consistent ordered standings. |
| US-08 | Tournament dashboard | 5 | Combines summaries from tournament and match activity and needs clear handling of partial or empty data. |

**Total planned estimate:** 46 story points.

## Estimation notes

The estimates are an initial planning view based on the Day 9 backlog descriptions and SportX project context. They should be discussed and adjusted by the team when the detailed implementation scope and acceptance criteria are refined. The 8-point stories may need splitting if they prove too large for one iteration. No story is being described here as completed based on its point estimate.
