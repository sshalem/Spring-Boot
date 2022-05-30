import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { API_URL, AUTHENTICAED_USER, TOKEN } from '../app.constants';
import { JwtTokenRequest } from '../common/JwtTokenRequest';
import { JwtTokenResponse } from '../common/JwtTokenResponse';

@Injectable({
  providedIn: 'root',
})
export class JwtAuthenticationService {
  constructor(private http: HttpClient) {}

  executeAutheticationService(username: string, password: string) {
    let jwtTokenRequest = new JwtTokenRequest(username, password);

    return this.http
      .post<JwtTokenResponse>(`${API_URL}/authenticate`, jwtTokenRequest)
      .pipe(
        map((data) => {
          sessionStorage.setItem(AUTHENTICAED_USER, username);
          sessionStorage.setItem(TOKEN, `Bearer ${data.token}`);
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
