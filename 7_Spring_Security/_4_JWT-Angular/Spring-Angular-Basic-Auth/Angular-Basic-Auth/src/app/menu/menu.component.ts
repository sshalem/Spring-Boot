import { Component, OnInit } from '@angular/core';
import { BasicAuthenticationService } from '../service/basic-authentication.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
})
export class MenuComponent implements OnInit {
  // Use this 'basicAuthenticationService' dependency for checking if user is logged in
  // by ths: show/hide some of the navigaion bars.
  // I donot want to show any nav bars except Login , when I'm on the login page
  // I dont want to see the login nav , while I'm on logged in
  constructor(public basicAuthenticationService: BasicAuthenticationService) {}

  ngOnInit() {}
}
