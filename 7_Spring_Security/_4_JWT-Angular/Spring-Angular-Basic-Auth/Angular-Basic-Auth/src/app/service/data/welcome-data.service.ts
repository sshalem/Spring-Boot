import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, Optional } from '@angular/core';
import { API_URL } from 'src/app/app.constants';
import { AuthenticationBean } from 'src/app/common/AuthenticationBean';

@Injectable({
  providedIn: 'root',
})
export class WelcomeDataService {
  constructor(private http: HttpClient) {}

  executeWelcome() {
    return this.http.get<AuthenticationBean>(`${API_URL}/basicAuth`);
  }
}

// Since we are Using HttpInterceptor :
// Which adds to each HttpRequest any configured header we want ,
//  thus we dont need to add it on each request,
// it is Automaicalyy will be added by the HttpInterceptor

//This Code is before Implementing HttpInterceptor
// ----------------------------------------------------
// executeHelloWorld() {
//   let basicAuthHeaderString = this.createBasicAuthenticationHttpHeader();
//   let headers = new HttpHeaders({
//     Authorization: basicAuthHeaderString,
//   });

//   return this.http.get<HelloWorldBean>('API_URL/hello', {
//     headers,
//   });
// }

// createBasicAuthenticationHttpHeader(): string {
//   let username = 'sshb';
//   let password = '123';
//   let basicAuthHeader = 'Basic ' + window.btoa(username + ':' + password);
//   return basicAuthHeader;
// }
