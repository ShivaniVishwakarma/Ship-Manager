import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { StorageService } from "../services/storage.service";


@Injectable({ providedIn: 'root' })
export class TokenInterceptor implements HttpInterceptor {

  constructor(private localStorageService: StorageService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    console.log("request : ", request);

    if(!request.url.includes("/authenticate")){
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.localStorageService.getAccessToken()}`
        }
      });
    }
    return next.handle(request);
  }
}
