# [Install PostgreSql standalone on windows](#-)

Links :
* https://www.youtube.com/watch?v=btxwPwnU-oI
* https://roytuts.com/how-to-install-postgresql-zip-archive-in-windows/

1.	Download the zip file
2.	Uninstall it
3.	Create a new folder as “pgsql_data” in the zipped folder.

![image](https://user-images.githubusercontent.com/36256986/206190313-2b44632b-a8fb-45bf-aa7d-886246666ebc.png)

4.	Go to folder of “pgsql\bin” and open the CMD 
5.	Type the following command :

```
initdb.exe -U postgres -A password -E utf8 -W -D C:\Localdata\postgresql-15.1-1\pgsql_data
```

6.	Enter new password for superuser:
  * User : postgres
  * Pass : postgres
  
![image](https://github.com/user-attachments/assets/0be0e57c-5136-4722-b074-f3666b867a78)


# [start PostgreSql server](#-)

1.	Paste the command below in the folder of 

```
C:\Localdata\postgresql-15.1-1\pgsql\bin>pg_ctl -D ^"C^:^\Localdata^\postgresql^-15^.1^-1^\pgsql^_data^" -l logfile start

Since I modified the name of the folder use the following command:

C:\Localdata\postgresql\pgsql\bin>pg_ctl -D ^"C^:^\Localdata^\postgresql^\pgsql^_data^" -l logfile start
```

![image](https://user-images.githubusercontent.com/36256986/206190645-ad900729-ec3f-44b3-ad14-522d329c4e7b.png)

# [start pgAdmin4](#-)

1.	Go to folder of `C:\Localdata\postgresql\pgsql\pgAdmin 4\bin` , click of the `pgAdmin4.exe` file
  a.	Type a password as root
2.	Once the pgAdmin tool opens , create new server
  a.	Right click on the servers -> Register -> Server

![image](https://user-images.githubusercontent.com/36256986/206190837-7bd471b4-dd71-42b6-9f1d-ebb06ef2bbac.png)

3.	Type a name like myServer

![image](https://user-images.githubusercontent.com/36256986/206190963-bceaa588-e35b-4f2e-99b7-7b442264ac6e.png)

4.	In the tab of Connection type the following:

  Hostname   :  localhost </br>
  Port   :   5432 </br>
  Username  :   postgres  (This is the user we created in section 6 ) </br>
  Password   :   root  (This is the password we created in section 6) </br>
  
![image](https://user-images.githubusercontent.com/36256986/206191074-fced43c1-858a-44ba-9dcf-68f96fe444f7.png)











