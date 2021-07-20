import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { Observable } from 'rxjs';

import { Ship } from "../ship.model";

import Page from "./../../models/page.model";
import { Response } from "./../../utils/response.model";
import {environment} from "../../../environments/environment";

@Injectable()
export class ShipService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  getShipList(page: Page): Observable<Ship[]> {
    return this.http.get<Ship[]>(`${this.apiServerUrl}/getall?pageNumber=${page.pageNumber}&recordsPerPage=${page.recordsPerPage}&search=${page.search}`);
  }

  getShip(shipCode: string): Observable<Response> {
    return this.http.get<Response>(`${this.apiServerUrl}/get/${shipCode}`);
  }

  addShip(ship: Ship): Observable<Response> {
    return this.http.post<Response>(`${this.apiServerUrl}/add`, ship);
  }

  editShip(ship: Ship): Observable<Response> {
    return this.http.put<Response>(`${this.apiServerUrl}/update`, ship);
  }

  deleteShip(shipCode: string): Observable<Response> {
    return this.http.delete<Response>(`${this.apiServerUrl}/delete/${shipCode}`);
  }
}
