import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService} from "../../auth/services/auth.service";
import {Injectable, Injector} from "@angular/core";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private injector : Injector) {
  }
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let authService = this.injector.get(AuthService)
    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${authService.getToken()}`
      }
    });
    return next.handle(request);
  }
}
