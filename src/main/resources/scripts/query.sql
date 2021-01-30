id = 92

/*SEARCH ENGINE*/
WITH r AS (
    SELECT
        sum(reaches) as count,
        goalid,
        sourceid
    FROM search_engine_goal
    WHERE counterid = 92
    GROUP BY goalid, sourceid
) SELECT
    r.count,
    s.name,
    a.name as goal_name
FROM r LEFT JOIN goal a
ON r.goalId = a.id
LEFT JOIN search_engine s ON r.sourceid = s.id ORDER BY goal_name, r.count DESC;

/*ADV ENGINE*/

WITH r AS (
    SELECT
        sum(reaches) as count,
        goalid,
        sourceid
    FROM adv_engine_goal
    WHERE counterid = 92
    GROUP BY goalid, sourceid
) SELECT
    r.count,
    s.name,
    a.name as goal_name
FROM r LEFT JOIN goal a
ON r.goalId = a.id
LEFT JOIN adv_engine s ON r.sourceid = s.id ORDER BY goal_name, r.count DESC;

/*SOCIAL NETWORK*/

WITH r AS (
    SELECT
        sum(reaches) as count,
        goalid,
        sourceid
    FROM soc_network_goal
    WHERE counterid = 92
    GROUP BY goalid, sourceid
) SELECT
    r.count,
    s.name,
    a.name as goal_name
FROM r LEFT JOIN goal a
ON r.goalId = a.id
LEFT JOIN soc_network s ON r.sourceid = s.id ORDER BY goal_name, r.count DESC;

/*TRAFFIC_SOURCE*/

WITH r AS (
    SELECT
        sum(reaches) as count,
        goalid,
        sourceid
    FROM traff_source_goal
    WHERE counterid = 92
    GROUP BY goalid, sourceid
) SELECT
    r.count,
    s.name,
    a.name as goal_name
FROM r LEFT JOIN goal a
ON r.goalId = a.id
LEFT JOIN traff_source s ON r.sourceid = s.id ORDER BY goal_name, r.count DESC;

/*REFERRAL_SOURCE*/

WITH r AS (
    SELECT
        sum(reaches) as count,
        goalid,
        sourceid
    FROM ref_source_goal
    WHERE counterid = 92
    GROUP BY goalid, sourceid
) SELECT
    r.count,
    s.name,
    a.name as goal_name
FROM r LEFT JOIN goal a
ON r.goalId = a.id
LEFT JOIN ref_source s ON r.sourceid = s.id ORDER BY goal_name, r.count DESC;

/*SEARCH_PHRASE*/

WITH r AS (
    SELECT
        sum(reaches) as count,
        goalid,
        sourceid
    FROM search_phrase_goal
    WHERE counterid = 92
    GROUP BY goalid, sourceid
) SELECT
    r.count,
    s.name as search_phrase,
    a.name as goal_name,
    goalid
FROM r LEFT JOIN goal a
ON r.goalId = a.id
LEFT JOIN search_phrase s ON r.sourceid = s.id ORDER BY goal_name, r.count DESC;

WITH r AS (
    SELECT
        sum(reaches) as count,
        goalid
    FROM search_phrase_goal
    WHERE counterid = 92
    GROUP BY goalid
) SELECT
    r.count,
    a.name as goal_name,
    goalid
FROM r LEFT JOIN goal a
ON r.goalId = a.id
ORDER BY goal_name, r.count DESC;
