import { Component, OnInit } from '@angular/core';
import {Ship} from "../ship";
import {ShipService} from "../ship.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-list-ship',
  templateUrl: './list-ship.component.html',
  styleUrls: ['./list-ship.component.css']
})
export class ListShipComponent implements OnInit {

  ships: Ship[] = [];

  constructor(private shipService: ShipService, private router: Router) { }

  ngOnInit(): void {
    this.getShips();
  }

  getShips() {
    this.shipService.getAllShips()
      .subscribe(response => {
        this.ships = response;
      }, error => {
        console.log("Error Occured", error);
      });
  }

  deleteShip(shipCode: string) {
    this.shipService.deleteShip(shipCode)
      .subscribe(response => {
        if (response.status) {
          alert(response.message);
          this.getShips();
        } else {
          alert(response.message);
        }
      }, error => {
        console.log("Error Occured", error);
      });
  }

  editShip(shipCode: string) {
    this.router.navigate(['update-ship', shipCode]);
  }


}
