------ INPUT -------
-- NAME         | OCCUPATION
----------------|--------------
-- Samantha     | Doctor
-- Julia        | Actor
-- Maria        | Actor
-- Meera        | Singer
-- Ashely       | Professor
-- Ketty        | Professor
-- Christeen    | Professor
-- Jane         | Actor
-- Jenny        | Doctor
-- Priya        | Singer

------ OUTPUT -------
-- DOCTOR   | PROFESSOR     | SINGLE    | ACTOR
------------|---------------|-----------|-------
-- Jenny    | Ashley        | Meera     | Jane
-- Samantha | Christeen     | Priya     | Julia
-- NULL     | Ketty         | NULL      | Maria



------ ANSWER -------
--SQL SERVER
--SELECT Doctor, Professor, Singer, Actor
--FROM (
--    SELECT
--    ROW_NUMBER() OVER(PARTITION BY OCCUPATION ORDER BY NAME ASC) AS O_ID, NAME, OCCUPATION
--    FROM OCCUPATIONS
--) A
--PIVOT (
--    MAX(NAME)
--    FOR OCCUPATION IN (
--        [Doctor],
--        [Professor],
--        [Singer],
--        [Actor]
--    )
--) AS PIVOT_TABLE