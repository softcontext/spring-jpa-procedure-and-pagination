DELIMITER //

CREATE PROCEDURE proc_multiply (INOUT inParam INT, INOUT outParam INT)
BEGIN
	SET outParam = inParam * 2;
END //

-----------------------------------------------------------------------

SET @inParam = 10;
SET @outParam = 0;
CALL proc_multiply(@inParam, @outParam);
SELECT @inParam, @outParam;

-----------------------------------------------------------------------

