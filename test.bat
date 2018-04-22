@ECHO OFF

SET success = 0
SET total = 0

for %%a in (.\testdata\*.testcase) do (

	echo.
	echo -----------------------
	echo Running: %%a
	SET /A total += 1

	java -jar KillerSokoban.jar < %%a > test_output.log
	IF NOT ERRORLEVEL 0 (
		echo PROGRAM CRASHED!
		echo TEST FAILED!
	) ELSE (
		
		FC %%a.expected test_output.log
		IF ERRORLEVEL 1 ( 
			echo TEST FAILED! 
		) ELSE (
		
			echo TEST SUCCESS!
			SET /A success += 1
		)
		
	)

)

del test_output.log

SET /A fail = total - success

echo.
echo -----------------------
echo Done!
echo Total tests run: %total%
echo Successful tests: %success%
echo Failed tests: %fail%
echo -----------------------
pause