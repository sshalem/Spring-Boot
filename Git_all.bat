@echo off
echo.
echo.
echo.
echo.
echo.
echo.
echo.           *****************************
echo.           * step 1 : git pull --all   *
echo.           ***************************** 
@echo on
git pull --all
@echo off
::pause
::cls
@echo off
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.           *************************
echo.           * step 2 : git status   *
echo.           ************************* 
@echo on
git status
@echo off
::cls

@echo off
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.           ****************************
echo.           * step 3 : git add --all   *
echo.           **************************** 
@echo on
git add --all
@echo off
::cls
@echo off
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.           *************************
echo.           * step 4 : git status   *
echo.           ************************* 
@echo on
git status
@echo off
::pause
::cls
@echo off
echo.
echo.
echo.
echo.
echo.
echo.
echo.
::pause
::echo.
::echo.
::echo.
::echo.
::echo.
::echo.
::echo.
::echo *********************************************
::echo *	%DATE% , %TIME%	    *
::echo *********************************************
::echo * step 5 : git commit -m "%DATE:~0,2%-%DATE:~3,2%-%DATE:~6,4% %TIME:~0,2%:%TIME:~3,2%" *
::echo *********************************************
::echo.
::echo.
::git commit -m "%DATE:~0,2%-%DATE:~3,2%-%DATE:~6,4% %TIME:~0,2%:%TIME:~3,2%"
echo.
echo.
echo.
echo.
if %ComputerName%==LAPTOP-AAAA (        
    echo.           *********************************************
    echo.           * computer name is = %ComputerName%            *    
    echo.           *********************************************
    echo.           *	%DATE% , %TIME%	    *
    echo.           *********************************************
    echo.           * step 5 : git commit -m "%DATE:~0,2%-%DATE:~3,2%-%DATE:~6,4% %TIME:~0,2%:%TIME:~3,2%" *
    echo.           *********************************************
    echo.
    echo.
    @echo on
    git commit -m "%DATE:~0,2%-%DATE:~3,2%-%DATE:~6,4% %TIME:~0,2%:%TIME:~3,2%"     
) else if %ComputerName%==DESKTOP-T4E706U (
    echo.           *********************************************
    echo.           * computer name is = %ComputerName%            *    
    echo.           *********************************************
    echo.           *	%DATE% , %TIME%	    *
    echo.           *********************************************
    echo.           * step 5 : git commit -m "%DATE:~7,2%-%DATE:~4,2%-%DATE:~10,4% %TIME:~0,2%:%TIME:~3,2%" *
    echo.           *********************************************
    echo.
    echo.
    @echo on
    git commit -m "%DATE:~7,2%-%DATE:~4,2%-%DATE:~10,4% %TIME:~0,2%:%TIME:~3,2%"   
) else if %ComputerName%==ASUS (
    echo.           *********************************************
    echo.           * computer name is = %ComputerName%            *    
    echo.           *********************************************
    echo.           *	%DATE% , %TIME%	    *
    echo.           *********************************************
    echo.           * step 5 : git commit -m "%DATE:~7,2%-%DATE:~4,2%-%DATE:~12,4% %TIME:~0,2%:%TIME:~3,2%" *
    echo.           *********************************************
    echo.
    echo.
    @echo on
    git commit -m "%DATE:~7,2%-%DATE:~4,2%-%DATE:~12,4% %TIME:~0,2%:%TIME:~3,2%"      
) else if %ComputerName%==LT461676 (
    echo.           *********************************************
    echo.           * computer name is = %ComputerName%            *    
    echo.           *********************************************
    echo.           *	%DATE% , %TIME%	    *
    echo.           *********************************************
    echo.           * step 5 : git commit -m "%DATE:~0,2%-%DATE:~3,2%-%DATE:~6,4% %TIME:~0,2%:%TIME:~3,2%" *
    echo.           *********************************************
    echo.
    echo.
    @echo on
    git commit -m "%DATE:~0,2%-%DATE:~3,2%-%DATE:~6,4% %TIME:~0,2%:%TIME:~3,2%"  
    @REM @echo off
) else (
    echo.                Warning !!!
    echo.                Warning !!!
    echo.                Warning !!!
    echo.
    echo.                ************************************************
    echo.                * Batch CODE didn't recognize the computer name *
    echo.                * Thus git commit wasn't executed               *
    echo.                * add it to the BATCH code                      *
    echo.                * PLease add computer name  %ComputerName% to CODE  *    
    echo.                *************************************************
    echo.
    echo.
    echo            ...  git push --all  --> Not executed executed
    echo.
    echo.
    echo.
    echo.
    cmd /k
)


@echo off
::pause
::@echo off
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.               *******************************
echo.               * step 6 :  git push --all    *						
echo.               *******************************
@echo on
git push --all
@echo off
echo.
echo.
echo.
echo.
echo.
echo.
echo.            .......completed git update successfuly.......
echo.
echo.
echo.
echo.
cmd /k

