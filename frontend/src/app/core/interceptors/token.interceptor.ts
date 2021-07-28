import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';

import { StorageService } from "../services/storage.service";


@Injectable({ providedIn: 'root' })
export class TokenInterceptor implements HttpInterceptor {

  constructor(private localStorageService: StorageService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${this.localStorageService.getAccessToken()}`
      }
    });

    return next.handle(request);
  }
}

  //intercept() gets HTTPRequest object, change it and forward to HttpHandler object’s handle() method.
// It transforms HTTPRequest object into an Observable<HttpEvents>.
//next: HttpHandler object represents the next interceptor in the chain of interceptors.
// The final ‘next’ in the chain is the Angular HttpClient.
