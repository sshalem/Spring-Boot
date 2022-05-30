import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JwtAuthenticationService } from '../service/jwt-authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  username = 'sshb';
  password: string;
  errorMessage: string = 'Invalid Credentials';
  invalidLogin: boolean = false;

  // we need Router Instance,
  // Angular has Built feature of 'Dependency Injection'
  // In order to use a dependency , we need to declare it as constructor argument
  // In TypeScript: whenever we pass in the constructor an argumant ,
  //                that argument will be as a member varibal of
  //                the class LoginComponent
  constructor(
    private router: Router,
    private jwtAuthenticationService: JwtAuthenticationService
  ) {
    this.password = '';
  }

  ngOnInit() {}

  handleJwtAuthLogin(): void {
    this.jwtAuthenticationService
      .executeAutheticationService(this.username, this.password)
      .subscribe(
        (data) => {
          console.log(data);
          this.router.navigate([`welcome/${this.username}`]);
          this.invalidLogin = false;
        },
        (error) => {
          console.log(error);
          this.invalidLogin = true;
        }
      );
  }

  onEnterKeyUp() {
    this.handleJwtAuthLogin();
  }
}
