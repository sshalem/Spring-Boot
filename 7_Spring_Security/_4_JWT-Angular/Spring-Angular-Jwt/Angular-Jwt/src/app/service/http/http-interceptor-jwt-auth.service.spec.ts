import { TestBed } from '@angular/core/testing';

import { HttpInterceptorJwtAuthService } from './http-interceptor-jwt-auth.service';

describe('HttpInterceptorJwtAuthService', () => {
  let service: HttpInterceptorJwtAuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpInterceptorJwtAuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
