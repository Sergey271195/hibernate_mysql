//SEARCH ENGINE
WITH r AS (
    SELECT
        sum(reaches) as count,
        goalid,
        sourceid
    FROM search_engine_goal
    WHERE counterid = 87
    GROUP BY goalid, sourceid
) SELECT
    r.count,
    s.name,
    a.name
FROM r LEFT JOIN goal a
ON r.goalId = a.id
LEFT JOIN search_engine s ON r.sourceid = s.id;

//ADV ENGINE

WITH r AS (
    SELECT
        sum(reaches) as count,
        goalid,
        sourceid
    FROM adv_engine_goal
    WHERE counterid = 87
    GROUP BY goalid, sourceid
) SELECT
    r.count,
    s.name,
    a.name
FROM r LEFT JOIN goal a
ON r.goalId = a.id
LEFT JOIN adv_engine s ON r.sourceid = s.id;

//SOCIAL NETWORK

WITH r AS (
    SELECT
        sum(reaches) as count,
        goalid,
        sourceid
    FROM soc_network_goal
    WHERE counterid = 87
    GROUP BY goalid, sourceid
) SELECT
    r.count,
    s.name,
    a.name
FROM r LEFT JOIN goal a
ON r.goalId = a.id
LEFT JOIN soc_network s ON r.sourceid = s.id;

//TRAFFIC SOURCE

WITH r AS (
    SELECT
        sum(reaches) as count,
        goalid,
        sourceid
    FROM traff_source_goal
    WHERE counterid = 87
    GROUP BY goalid, sourceid
) SELECT
    r.count,
    s.name,
    a.name
FROM r LEFT JOIN goal a
ON r.goalId = a.id
LEFT JOIN traff_source s ON r.sourceid = s.id;
