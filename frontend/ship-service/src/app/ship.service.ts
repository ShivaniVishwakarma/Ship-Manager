import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Ship} from "./ship";
import {environment} from "../environments/environment";
import { Response } from "../app/response.model"

@Injectable({
  providedIn: 'root'
})
export class ShipService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getAllShips() : Observable<Ship[]>{
    return this.http.get<Ship[]>(`${this.apiServerUrl}/getall`)
  }

  public getShip(shipCode : string) : Observable<Response>{
    return this.http.get<Response>(`${this.apiServerUrl}/get/${shipCode}`)
  }

  public addShip(ship : Ship) : Observable<Response>{
    return this.http.post<Response>(`${this.apiServerUrl}/add`,ship)
  }

  public updateShip(ship : Ship) : Observable<Response>{
    return this.http.put<Response>(`${this.apiServerUrl}/update`,ship)
  }

  public deleteShip(shipCode : String) : Observable<Response>{
    return this.http.delete<Response>(`${this.apiServerUrl}/delete/${shipCode}`)
  }
}
