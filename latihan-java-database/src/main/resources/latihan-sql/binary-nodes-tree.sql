----- INPUT -----
-- N    | P
--------|--------
-- 1    | 2
-- 3    | 2
-- 6    | 8
-- 9    | 8
-- 2    | 5
-- 8    | 5
-- 5    | null


----- OUTPUT -----
-- N    | DESCRIPTION
--------|------------
-- 1    | Leaf
-- 2    | Inner
-- 3    | Leaf
-- 5    | Root
-- 6    | Leaf
-- 8    | Inner
-- 9    | Leaf

-- WHERE :
-- 'Root', if node is root node
-- 'Leaf', if node is leaf node
-- 'Inner', if node is neither root nor leaf node


----- ANSWER -----
-- SQL SERVER
--SELECT A.N,
--CASE
--    WHEN A.P IS NULL THEN "Root"
--    WHEN B.P_COUNT IS NULL THEN "Leaf"
--    ELSE "Inner"
--END AS DESCRIPTION
--FROM BST A
--LEFT JOIN
--(
--    SELECT P, COUNT(P) AS P_COUNT
--    FROM BST
--    GROUP BY P
--) B
--ON A.N = B.P
--ORDER BY A.N ASC