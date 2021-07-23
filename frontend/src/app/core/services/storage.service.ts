import { Injectable } from "@angular/core";

@Injectable({ providedIn: 'root' })
export class StorageService {
    readonly ACCESS_TOKEN_KEY = 'ACCESS_TOKEN';

    setAccessToken(token: string) {
        localStorage.setItem(this.ACCESS_TOKEN_KEY, token);
    }

    getAccessToken() {
        return localStorage.getItem(this.ACCESS_TOKEN_KEY);
    }

}
