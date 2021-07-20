import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { Observable } from 'rxjs';

import { environment } from "../../../environments/environment";


@Injectable()
export class AuthService {

    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) {
    }

    login(username: string, password: string): Observable<any> {
        return this.http.post<any>(`${this.apiServerUrl}/login`, { username, password });
    }

}
