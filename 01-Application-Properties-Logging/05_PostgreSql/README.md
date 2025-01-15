# [Install PostgreSql standalone on windows](#-)

Links :
* https://www.youtube.com/watch?v=btxwPwnU-oI
* https://roytuts.com/how-to-install-postgresql-zip-archive-in-windows/

1.	Download the zip file
2.	Uninstall it , it will uninstall it to folder [`pgsql`](#-)
3.	Create a new folder as [`pgsql_data`](#-) . 

![image](https://user-images.githubusercontent.com/36256986/206190313-2b44632b-a8fb-45bf-aa7d-886246666ebc.png)

4.	Open CMD , go to directory [`C:\Localdata\DB\postgresql\pgsql\bin`](#-)
5.	Type the following command below, It will install inside the folder of `pgsql_data` all th relevant data:

```
initdb.exe -U postgres -A password -E utf8 -W -D C:\Localdata\DB\postgresql\pgsql\pgsql_data
```

6.	Enter new password for superuser:
  * User : postgres
  * Pass : postgres
  
![image](https://github.com/user-attachments/assets/29dc11d3-3555-4b54-9d33-b04801dbb734)



# [start PostgreSql server](#-)

1.	Paste the command we got (See the image above) 

```
C:\Windows\System32>cd \Localdata\DB\postgresql\pgsql\bin

C:\Localdata\DB\postgresql\pgsql\bin>pg_ctl -D ^"C^:^\Localdata^\DB^\postgresql^\pgsql^_data^" -l logfile start
```

![image](https://github.com/user-attachments/assets/8d0bedf1-340c-4dad-a211-713154902c5e)


# [start pgAdmin4](#-)

1.	Go to folder of [`C:\Localdata\DB\postgresql\pgsql\pgAdmin 4\bin`](#-) , click of the [`pgAdmin4.exe`](#-) file
  * Type a password as root
2.	Once the pgAdmin tool opens , create new server
  *	Right click on the servers -> Register -> Server

![image](https://user-images.githubusercontent.com/36256986/206190837-7bd471b4-dd71-42b6-9f1d-ebb06ef2bbac.png)

3.	Type a name like myServer

![image](https://user-images.githubusercontent.com/36256986/206190963-bceaa588-e35b-4f2e-99b7-7b442264ac6e.png)

4.	In the tab of Connection type the following:

  Hostname   :  localhost </br>
  Port   :   5432 </br>
  Username  :   postgres  (This is the user we created in section 6 ) </br>
  Password   :   root  (This is the password we created in section 6) </br>
  
![image](https://user-images.githubusercontent.com/36256986/206191074-fced43c1-858a-44ba-9dcf-68f96fe444f7.png)











