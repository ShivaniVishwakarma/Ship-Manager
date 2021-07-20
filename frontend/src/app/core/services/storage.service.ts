import { Injectable } from "@angular/core";

@Injectable({ providedIn: 'root' })
export class StorageService {
    readonly ACCESS_TOKEN_KEY = 'ACCESS_TOKEN';
    readonly REFRESH_TOKEN_KEY = 'REFRESH_TOKEN';


    setAccessToken(token: string) {
        localStorage.setItem(this.ACCESS_TOKEN_KEY, token);
    }

    getAccessToken() {
        return localStorage.getItem(this.ACCESS_TOKEN_KEY);
    }

    setRefreshToken(token: string) {
        localStorage.setItem(this.REFRESH_TOKEN_KEY, token);
    }

    getRefreshToken() {
        return localStorage.getItem(this.REFRESH_TOKEN_KEY);
    }

}
