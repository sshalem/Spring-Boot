import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, Optional } from '@angular/core';
import { API_URL } from 'src/app/app.constants';
import { HelloWorldBean } from 'src/app/common/HelloWorldBean';

@Injectable({
  providedIn: 'root',
})
export class WelcomeDataService {
  constructor(private http: HttpClient) {}

  executeWelcome() {
    return this.http.get<HelloWorldBean>(`${API_URL}/hello`);
  }
}
