To connect with ssh to a created linode server  
 

Open CMD on windows 

Copy the IP of the created from Linode  

![image](https://user-images.githubusercontent.com/36256986/189514391-249ba3d7-64ef-463d-a8bd-ee51d5d948fd.png)

![image](https://user-images.githubusercontent.com/36256986/189514398-1e44c219-9273-4332-b2eb-832573879067.png)

Password : aa77aa80 

---------------------------------------------------------------------------------

### Setup server - Setting Up and Securing a Compute Instance 

https://www.digitalocean.com/community/tutorials/initial-server-setup-with-ubuntu-20-04 

Basic package update and upgrade 
`sudo apt-get update && sudo apt-get upgrade`

Date time Zone Modification: 
`date`
`timedatectl`
`sudo timedatectl set-timezone Asia/Jerusalem`

The root user is the administrative user in a Linux environment that has very broad privileges. </br>
Because of the heightened privileges of the root account, you are discouraged from using it on a regular basis. </br>
This is because the root account is able to make very destructive changes, even by accident. 


Add a Limited User Account 

`adduser shabtay`</br>
`usermod -aG sudo shabtay`  (password 1234)</br>

Firewall config 

`ufw app list`</br>
`ufw allow OpenSSH`</br>
`ufw enable`</br>
`ufw status`</br>

 

I didn’t perform the set up SSH keys instead of using password authentication</br>
This is to enhance your server’s security, It is strongly recommend setting up SSH keys instead of using password authentication.  

Now to connect with new user type:  ssh shabtay@ip_address_of_server 
