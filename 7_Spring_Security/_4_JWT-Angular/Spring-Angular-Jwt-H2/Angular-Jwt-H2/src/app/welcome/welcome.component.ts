import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WelcomeDataService } from '../service/data/welcome-data.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css'],
})
export class WelcomeComponent implements OnInit {
  username: string;
  welcomeMessageFromService: string;
  //ActivateRoute
  constructor(
    private route: ActivatedRoute,
    private welcomeDataService: WelcomeDataService
  ) {
    this.welcomeMessageFromService = '';
    this.username = '';
  }

  ngOnInit() {
    this.username = this.route.snapshot.params['name'];
  }

  getWelcomeMessage() {
    this.welcomeDataService.executeWelcome().subscribe(
      (response) => {
        this.handleSuccessulResponse(response);
      },
      (error) => {
        this.handleErrorResponse(error);
      }
    );
  }

  handleSuccessulResponse(response: any) {
    this.welcomeMessageFromService = response.message;
  }

  handleErrorResponse(error: { error: { message: string } }) {
    this.welcomeMessageFromService = error.error.message;
  }
}
