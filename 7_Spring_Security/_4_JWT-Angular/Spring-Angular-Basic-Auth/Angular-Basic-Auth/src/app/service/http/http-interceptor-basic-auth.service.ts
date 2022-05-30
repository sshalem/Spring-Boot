import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BasicAuthenticationService } from '../basic-authentication.service';

@Injectable({
  providedIn: 'root',
})
export class HttpInterceptorBasicAuthService implements HttpInterceptor {
  constructor(private basicAuthenticationService: BasicAuthenticationService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    // let basicAuthHeaderString = 'Basic ' + window.btoa(username + ':' + password);
    let basicAuthHeaderString =
      this.basicAuthenticationService.getAuthenticatdToken();
    let username = this.basicAuthenticationService.getAuthenticatdUser();

    if (basicAuthHeaderString && username) {
      req = req.clone({
        setHeaders: { Authorization: basicAuthHeaderString },
      });
    }
    return next.handle(req);
  }
}
