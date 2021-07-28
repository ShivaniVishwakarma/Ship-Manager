import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { Observable } from 'rxjs';

import { environment } from "../../../environments/environment";
import { User } from "../user.model";


@Injectable()
export class AuthService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  authenticate(user: User): Observable<any> {
    return this.http.post<any>(`${this.apiServerUrl}/users/authenticate`, user);
  }

  getToken() {
    return localStorage.getItem('token');
  }

}
