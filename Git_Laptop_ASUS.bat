@echo off
echo _
echo _
echo _
echo _
echo _
echo _
echo _
echo 		*****************************
echo 		* step 1 : git pull --all   *
echo 		***************************** 
@echo on
git pull --all
@echo off
::pause
::cls
@echo off
echo _
echo _
echo _
echo _
echo _
echo _
echo _
echo 		*************************
echo 		* step 2 : git status   *
echo 		************************* 
@echo on
git status
@echo off
::cls

@echo off
echo _
echo _
echo _
echo _
echo _
echo _
echo _
echo 		****************************
echo 		* step 3 : git add --all   *
echo 		**************************** 
@echo on
git add --all
@echo off

::cls
@echo off
echo _
echo _
echo _
echo _
echo _
echo _
echo _
echo 		*************************
echo 		* step 4 :  git status  *
echo 		************************* 
@echo on
git status
@echo off
pause
::cls
@echo off
echo _
echo _
echo _
echo _
echo _
echo 		*********************************************
echo 		*	%DATE% , %TIME%							*
echo 		*********************************************
echo 		* step 5 : git commit -m "%DATE:~7,2%-%DATE:~4,2%-%DATE:~12,4% %TIME:~0,2%:%TIME:~3,2%" *
echo 		********************************************* 
@echo on
git commit -m "%DATE:~7,2%-%DATE:~4,2%-%DATE:~12,4% %TIME:~0,2%:%TIME:~3,2%"
@echo off
::pause

@echo off
echo _
echo _
echo _
echo _
echo _
echo _
echo _
echo 		*******************************
echo 		* step 6 :  git push --all    *						
echo 		*******************************
@echo on
git push --all
@echo off
echo _
echo _
echo _
echo _
echo _
echo _
echo _
cmd /k echo .......completed git update successfuly.......


