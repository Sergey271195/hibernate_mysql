id = 102

/*SEARCH ENGINE*/
WITH r AS (
    SELECT
        sum(reaches) as count,
        sourceid
    FROM search_engine_views
    WHERE counterid = 102 AND date BETWEEN '2021-01-28' AND '2021-01-29'
    GROUP BY sourceid
) SELECT
    r.count,
    s.name
FROM r LEFT JOIN
search_engine s ON r.sourceid = s.id ORDER BY r.count DESC;

/*ADV ENGINE*/

WITH r AS (
    SELECT
        sum(reaches) as count,
        sourceid
    FROM adv_engine_views
    WHERE counterid = 102 AND date BETWEEN '2021-01-28' AND '2021-01-29'
    GROUP BY sourceid
) SELECT
    r.count,
    s.name
FROM r
LEFT JOIN adv_engine s ON r.sourceid = s.id ORDER BY r.count DESC;

/*SOCIAL NETWORK*/

WITH r AS (
    SELECT
        sum(reaches) as count,
        sourceid
    FROM soc_network_views
    WHERE counterid = 102 AND date BETWEEN '2021-01-28' AND '2021-01-29'
    GROUP BY sourceid
) SELECT
    r.count,
    s.name
FROM r
LEFT JOIN soc_network s ON r.sourceid = s.id ORDER BY r.count DESC;

/*TRAFFIC_SOURCE*/

WITH r AS (
    SELECT
        sum(reaches) as count,
        sourceid
    FROM traff_source_views
    WHERE counterid = 102 AND date BETWEEN '2021-01-28' AND '2021-01-29'
    GROUP BY sourceid
) SELECT
    r.count,
    s.name
FROM r
LEFT JOIN traff_source s ON r.sourceid = s.id ORDER BY r.count DESC;

/*REFERRAL_SOURCE*/

WITH r AS (
    SELECT
        sum(reaches) as count,
        sourceid
    FROM ref_source_views
    WHERE counterid = 102 AND date BETWEEN '2021-01-28' AND '2021-01-29'
    GROUP BY sourceid
) SELECT
    r.count,
    s.name
FROM r
LEFT JOIN ref_source s ON r.sourceid = s.id ORDER BY r.count DESC;

/*SEARCH_PHRASE*/

WITH r AS (
    SELECT
        sum(reaches) as count,
        sourceid
    FROM search_phrase_views
    WHERE counterid = 102 AND date BETWEEN '2021-01-28' AND '2021-01-29'
    GROUP BY sourceid
) SELECT
    r.count,
    s.name as search_phrase
FROM r
LEFT JOIN search_phrase s ON r.sourceid = s.id ORDER BY r.count DESC;

WITH r AS (
    SELECT
        sum(reaches) as count
    FROM search_phrase_views
    WHERE counterid = 102 AND date BETWEEN '2021-01-28' AND '2021-01-29'
) SELECT
    r.count
FROM r
ORDER BY r.count DESC;