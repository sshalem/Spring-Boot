@echo off
echo.[33mgit pull --all    [0m
git pull --all
echo.
echo.[33mgit status        [0m
git status
echo.
echo.[33mgit add --all     [0m
git add --all
echo.
echo.[33mgit status        [0m
git status
echo.
if %ComputerName%==LAPTOP-AAAA (        
    echo.[33mgit commit -m "%DATE:~0,2%-%DATE:~3,2%-%DATE:~6,4% %TIME:~0,2%:%TIME:~3,2%"  [0m
    echo.   [36mcomputer name is = %ComputerName% [0m
    echo.   [36m%DATE% , %TIME%	 [0m
    git commit -m "%DATE:~0,2%-%DATE:~3,2%-%DATE:~6,4% %TIME:~0,2%:%TIME:~3,2%"     
) else if %ComputerName%==DESKTOP-T4E706U (
    echo.[33mgit commit -m "%DATE:~7,2%-%DATE:~4,2%-%DATE:~10,4% %TIME:~0,2%:%TIME:~3,2%" [0m
    echo.   [36mcomputer name is = %ComputerName% [0m
    echo.   [36m%DATE% , %TIME%	 [0m
    git commit -m "%DATE:~7,2%-%DATE:~4,2%-%DATE:~10,4% %TIME:~0,2%:%TIME:~3,2%"   
) else if %ComputerName%==LAPTOP-ASUS-SHA (
    echo.[33mgit commit -m "%DATE:~0,2%-%DATE:~3,2%-%DATE:~6,4% %TIME:~0,2%:%TIME:~3,2%"  [0m
    @REM echo.[33mgit commit -m "%DATE:~7,2%-%DATE:~4,2%-%DATE:~10,4% %TIME:~0,2%:%TIME:~3,2%" [0m
    @REM echo.[33mgit commit -m "%DATE:~7,2%-%DATE:~4,2%-%DATE:~12,4% %TIME:~0,2%:%TIME:~3,2%" [0m
    echo.   [36mcomputer name is = %ComputerName% [0m
    echo.   [36m%DATE% , %TIME%	 [0m
    git commit -m "%DATE:~0,2%-%DATE:~3,2%-%DATE:~6,4% %TIME:~0,2%:%TIME:~3,2%"
    @REM git commit -m "%DATE:~7,2%-%DATE:~4,2%-%DATE:~12,4% %TIME:~0,2%:%TIME:~3,2%"      
) else if %ComputerName%==LT461676 (
    echo.[33mgit commit -m "%DATE:~0,2%-%DATE:~3,2%-%DATE:~6,4% %TIME:~0,2%:%TIME:~3,2%" [0m
    echo.   [36mcomputer name is = %ComputerName% [0m
    echo.   [36m%DATE% , %TIME%	 [0m
    git commit -m "%DATE:~0,2%-%DATE:~3,2%-%DATE:~6,4% %TIME:~0,2%:%TIME:~3,2%"  
    @REM @echo off
) else (
    echo.          [31m      Warning !!!
    echo.                Warning !!!
    echo.                Warning !!!
    echo.
    echo.                *************************************************************
    echo.                * Batch CODE didn't recognize the computer name 
    echo.                * Thus "git commit" wasn't executed               
    echo.                * add it to the BATCH code                      
    echo.                * PLease add computer name  %ComputerName% to CODE      
    echo.                *************************************************************
    echo.
    echo.
    echo.                "git push" Not executed executed
    echo.
    echo.         [0m
    echo.
    echo.
    cmd /k
)

echo.
echo.[33mgit push --all   [0m
git push --all
echo.[33m .......completed git update successfuly.......[0m
cmd /k
