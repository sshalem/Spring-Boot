# [Install PostgreSql satndalone on windows](#-)

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
  a. User : postgres
  b. Pass : root
  
![image](https://user-images.githubusercontent.com/36256986/206190542-9a2ac955-272c-4aaa-9cdd-277d83107cdb.png)

7.	Paste the command below in the folder of 

```
C:\Localdata\postgresql-15.1-1\pgsql\bin>pg_ctl -D ^"C^:^\Localdata^\postgresql^-15^.1^-1^\pgsql^_data^" -l logfile start
```

![image](https://user-images.githubusercontent.com/36256986/206190645-ad900729-ec3f-44b3-ad14-522d329c4e7b.png)

8.	Go to folder of `C:\Localdata\postgresql-15.1-1\pgsql\pgAdmin 4\bin` , click of the `pgAdmin4.exe` file
  a.	Type a password as root
9.	Once the pgAdmin tool opens , create new server
  a.	Right click on the servers -> Register -> Server

![image](https://user-images.githubusercontent.com/36256986/206190837-7bd471b4-dd71-42b6-9f1d-ebb06ef2bbac.png)


10.	Type a name like myServer

![image](https://user-images.githubusercontent.com/36256986/206190963-bceaa588-e35b-4f2e-99b7-7b442264ac6e.png)

11.	In the tab of Connection type the following:

  Hostname   :  localhost
  Port   :   5432
  Username  :   postgres  (This is the user we created in section 6 )
  Password   :   root  (This is the password we created in section 6)
  
![image](https://user-images.githubusercontent.com/36256986/206191074-fced43c1-858a-44ba-9dcf-68f96fe444f7.png)











