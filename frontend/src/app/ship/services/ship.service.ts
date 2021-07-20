import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { Observable } from 'rxjs';

import { Ship } from "../ship.model";

import Page from "./../../models/page.model";
import { Response } from "./../../utils/response.model";

@Injectable()
export class ShipService {
  //baseUrl = 'http://localhost:8080/api';
  baseUrl: string = 'http://localhost:9000/api/v1';

  constructor(private http: HttpClient) {
  }

  getShipList(page: Page): Observable<Ship[]> {
    return this.http.get<Ship[]>(`${this.baseUrl}/products?pageNumber=${page.pageNumber}&recordsPerPage=${page.recordsPerPage}&search=${page.search}`);
  }

  getShip(shipCode: string): Observable<Response> {
    return this.http.get<Response>(`${this.baseUrl}/ships/${shipCode}`);
  }

  addShip(ship: Ship): Observable<Response> {
    return this.http.post<Response>(`${this.baseUrl}/ships`, ship);
  }

  editShip(ship: Ship): Observable<Response> {
    return this.http.put<Response>(`${this.baseUrl}/ships`, ship);
  }

  deleteShip(shipCode: string): Observable<Response> {
    return this.http.delete<Response>(`${this.baseUrl}/ships/${shipCode}`);
  }
}
