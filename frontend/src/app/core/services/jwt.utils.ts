import {Injectable} from '@angular/core';

@Injectable({providedIn: 'root'})
export class JWTUtils {

  public decodeJwt(token: string) {

    let decodedToken = this.parseJwt(token);
    return decodedToken;
  }

  public parseJwt(token: string) {
    if (token != null || token != undefined) {
      var base64Url = token.split('.')[1];
      var base64 = base64Url.replace('-', '+').replace('_', '/');
      return JSON.parse(window.atob(base64));
    }
    return null;
  }

}
