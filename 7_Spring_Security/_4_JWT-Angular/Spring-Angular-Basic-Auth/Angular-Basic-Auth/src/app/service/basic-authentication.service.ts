import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthenticationBean } from '../common/AuthenticationBean';
import { map } from 'rxjs/operators';
import { API_URL, AUTHENTICAED_USER, TOKEN } from '../app.constants';

@Injectable({
  providedIn: 'root',
})
export class BasicAuthenticationService {
  constructor(private http: HttpClient) {}

  executeAutheticationService(username: string, password: string) {
    let basicAuthHeader = 'Basic ' + window.btoa(username + ':' + password);

    let headers = new HttpHeaders({
      Authorization: basicAuthHeader,
    });

    return this.http
      .get<AuthenticationBean>(`${API_URL}/basicAuth`, {
        headers,
      })
      .pipe(
        map((data) => {
          sessionStorage.setItem(AUTHENTICAED_USER, username);
          sessionStorage.setItem(TOKEN, basicAuthHeader);
          return data;
        })
      );
  }

  getAuthenticatdUser() {
    return sessionStorage.getItem(AUTHENTICAED_USER);
  }

  getAuthenticatdToken(): any {
    if (this.getAuthenticatdUser()) {
      return sessionStorage.getItem(TOKEN);
    }
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(AUTHENTICAED_USER);
    return !(user === null);
  }

  logout() {
    sessionStorage.removeItem(AUTHENTICAED_USER);
    sessionStorage.removeItem(TOKEN);
  }
}
