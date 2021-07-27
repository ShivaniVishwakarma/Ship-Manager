import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { Observable } from 'rxjs';

import { environment } from "../../../environments/environment";
import {Response} from "../../utils/response.model";
import {User} from "../user.model";


@Injectable()
export class AuthService {

    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) {
    }

    authenticate(user: User): Observable<Response> {
      let resp = this.http.post<Response>(`${this.apiServerUrl}/authenticate`, user);
      console.log(resp);
      return resp;
    }

    getToken(){
      return localStorage.getItem('token');
    }

}
