To connect with ssh to a created linode server  
 

Open CMD on windows 

Copy the IP of the created from Linode  

![image](https://user-images.githubusercontent.com/36256986/189514391-249ba3d7-64ef-463d-a8bd-ee51d5d948fd.png)

![image](https://user-images.githubusercontent.com/36256986/189514398-1e44c219-9273-4332-b2eb-832573879067.png)

Password : aa77aa80 

---------------------------------------------------------------------------------

### [1. Setup server - Setting Up and Securing a Compute Instance](#-)

https://www.digitalocean.com/community/tutorials/initial-server-setup-with-ubuntu-20-04 

Setup server - Setting Up and Securing a Compute Instance.</br>
Basic package update and upgrade :

`sudo apt-get update && sudo apt-get upgrade`

#### [Date time Zone Modification:](#-)

`date`</br>
`timedatectl`</br>
`sudo timedatectl set-timezone Asia/Jerusalem`</br>

The root user is the administrative user in a Linux environment that has very broad privileges. </br>
Because of the heightened privileges of the root account, you are discouraged from using it on a regular basis. </br>
This is because the root account is able to make very destructive changes, even by accident. 


#### [Add a Limited User Account ](#-)

`adduser shabtay`</br>
`usermod -aG sudo shabtay`  (password 123)</br>

#### [Firewall config](#-)

`ufw app list`</br>
`ufw allow OpenSSH`</br>
`ufw enable`</br>
`ufw status`</br>

 

I didn’t perform the set up SSH keys instead of using password authentication</br>
This is to enhance your server’s security, It is strongly recommend setting up SSH keys instead of using password authentication.  

Now to connect with new user type:  `ssh shabtay@ip_address_of_server`

![image](https://user-images.githubusercontent.com/36256986/189514767-a8ae6370-30bf-4400-8506-eda8bac39b01.png)

--------------------------------------------------------------------------------------------------------

### [2. JDK Install](#-)

From Digital Ocean  Ubuntu 20.04 : </br> 
https://www.digitalocean.com/community/tutorials/how-to-install-apache-tomcat-10-on-ubuntu-20-04 

JDK Install  : 

`sudo apt update` </br>
`sudo apt install default-jdk` </br>
`Java -version` </br>

--------------------------------------------------------------------------------------------------------

### [3. Tomcat Install](#-)

From Digital Ocean  Ubuntu 20.04 : </br> 
https://www.digitalocean.com/community/tutorials/how-to-install-apache-tomcat-10-on-ubuntu-20-04 

##### [Tomcat Install](#-): 

`sudo useradd -m -d /opt/tomcat -U -s /bin/false tomcat` </br>
`cd /tmp` </br>
`wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.65/bin/apache-tomcat-9.0.65.tar.gz` </br> 
`sudo tar xzvf apache-tomcat-9*tar.gz -C /opt/tomcat --strip-components=1` </br>
`sudo chown -R tomcat:tomcat /opt/tomcat/` </br>
`sudo chmod -R u+x /opt/tomcat/bin` </br>

##### [Configuring Admin Users](#-)

`sudo nano /opt/tomcat/conf/tomcat-users.xml` </br>

Copy and paste the lines below

```
<role rolename="manager-gui"/>
<role rolename="admin-gui"/>
<user username="username" password="password" roles="manager-gui,admin-gui"/>
```

To remove the restriction for the Manager page, open its config file for editing: 

`sudo nano /opt/tomcat/webapps/manager/META-INF/context.xml`

Comment out the Valve definition, as shown: 

![image](https://user-images.githubusercontent.com/36256986/189515077-76e1b1aa-4791-4ea6-b3e3-d0f421eb3e01.png)


Then repeat for Host Manager: </br>
`sudo nano /opt/tomcat/webapps/host-manager/META-INF/context.xml`

##### [Creating a systemd service](#-)

`sudo update-java-alternatives -l`

This command result the path : `/usr/lib/jvm/java-1.11.0-openjdk-amd64`

`sudo nano /etc/systemd/system/tomcat.service`

Add the following lines:

```
[Unit]
Description=Tomcat
After=network.target 

[Service]
Type=forking
User=tomcat
Group=tomcat
Environment="JAVA_HOME=/usr/lib/jvm/java-1.11.0-openjdk-amd64"
Environment="JAVA_OPTS=-Djava.security.egd=file:///dev/urandom"
Environment="CATALINA_BASE=/opt/tomcat"
Environment="CATALINA_HOME=/opt/tomcat"
Environment="CATALINA_PID=/opt/tomcat/temp/tomcat.pid"
Environment="CATALINA_OPTS=-Xms512M -Xmx1024M -server -XX:+UseParallelGC"

ExecStart=/opt/tomcat/bin/startup.sh
ExecStop=/opt/tomcat/bin/shutdown.sh
RestartSec=10
Restart=always

[Install]
WantedBy=multi-user.target
```

type following commands:

```
sudo systemctl daemon-reload 
sudo systemctl start tomcat 
sudo systemctl status tomcat 
```

Press q to exit the command. 

To enable Tomcat starting up with the system: 

`sudo systemctl enable tomcat`

Open firewall port:

`sudo ufw allow 8080`

--------------------------------------------------------------------------------------------------------

### [4. MySql on Ubuntu](#-)

https://hevodata.com/learn/installing-mysql-on-ubuntu-20-04/ 

```java
sudo apt update
sudo apt upgrade
sudo apt install mysql-server
mysql --version
```

In order to use a password to connect to MySQL as root, you will need to switch its authentication method from </br>
`auth_socket` to `mysql_native_password`. </br>
To do this, open up the MySQL prompt from your terminal: </br>
From <https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-ubuntu-18-04>  

![image](https://user-images.githubusercontent.com/36256986/189515240-7a9a77b6-52d7-4d47-9bd9-106bb720ca8a.png)

```sql
SELECT USER, HOST from mysql.user; 
SELECT User, host, Grant_priv ,  plugin , authentication_string, password_last_changed FROM mysql.user; 
```

`SHOW VARIABLES LIKE 'validate_password%';`

![image](https://user-images.githubusercontent.com/36256986/189515548-2c6860e4-9a70-4cd2-836e-9d988f96b705.png)

`SET GLOBAL validate_password.policy = 0;`

![image](https://user-images.githubusercontent.com/36256986/189515556-e71e1eae-cc7a-40c3-8f85-0b9f1803b5b1.png)

```sql
sudo mysql
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password by 'mynewpassword';
```

See work around for error below in link, Great Link : </br>
https://ostechnix.com/fix-mysql-error-1819-hy000-your-password-does-not-satisfy-the-current-policy-requirements/ 

`ERROR 1819 (HY000): Your password does not satisfy the current policy requirements`

```sql
FLUSH PRIVILEGES;
exit
sudo mysql
mysql -u root -p
CREATE USER 'shabtay'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'shabtay'@'localhost' WITH GRANT OPTION;
exit
```

I must [```install mysql_secure_installation```](#-) because this way I can enable to Remote connect with MySql Workbench </br>

`sudo mysql_secure_installation` </br>

Click n , since we don't want to change the password again</br>  
(Somehow If I don't want to change I get the same error) 

Check MySql version: 

```sql
sudo mysql --version
```

Connect to MySQL server: 

```sql
sudo mysql -u root -p
```


Show all databases: 

```sql
mysql> show databases; 
```
 

Check if MySql is live 

```sql
sudo systemctl status mysql
```
 

Restart MySql server: 

```sql
sudo systemctl restart mysql
```


#### [How to Connect to a Database Remotely Using the MySQL Workbench Tool](#-)

https://www.linode.com/docs/guides/deploy-mysql-workbench-for-database-administration/ 

Open MySql workbench, and fill it as follows: 

Connection name: whatever name I want </br>
SSH Hostname : 194.195.241.160 IP of the server from Linode </br>
SSH Username: sshb (When I setup the server , I add new user, this is the username I gave the user when I created it ) </br>
SSH Password : aa77aa80 (When I setup the server , I add new user, this is the password I gave the user when I created it ) </br>
MySql Hostname : 127.0.0.1 </br>
MySql Server Port : 3306 </br>
Username : root </br>
Password : mynewpassword </br>

![image](https://user-images.githubusercontent.com/36256986/189515704-886303ca-6c7e-42e0-9b1e-fb7640ce33fa.png)

