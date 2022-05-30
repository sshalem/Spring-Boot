import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtAuthenticationService } from '../jwt-authentication.service';

@Injectable({
  providedIn: 'root',
})
export class HttpInterceptorJwtAuthService implements HttpInterceptor {
  constructor(private jwtAuthenticationService: JwtAuthenticationService) {}
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let jwtAuthToken = this.jwtAuthenticationService.getAuthenticatdToken();
    let username = this.jwtAuthenticationService.getAuthenticatdUser();

    if (jwtAuthToken && username) {
      req = req.clone({
        setHeaders: { Authorization: jwtAuthToken },
      });
    }
    return next.handle(req);
  }
}
