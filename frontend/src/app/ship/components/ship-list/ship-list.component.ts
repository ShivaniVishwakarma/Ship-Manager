import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Ship } from '../../ship.model';
import { ShipService } from '../../services/ship.service';
import Page from 'src/app/models/page.model';
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-ship-list',
  templateUrl: './ship-list.component.html',
  styleUrls: ['./ship-list.component.scss']
})
export class ShipListComponent implements OnInit {

  ships: Ship[] = [];
  page: Page = {
    totalRecords: 0,
    pageNumber: 1,
    recordsPerPage: 20,
    search: ''
  };


  constructor(private shipService: ShipService, private router: Router) { }

  ngOnInit(): void {
    //this.getShipList(this.page);
    this.getShipList();
  }

  getShipList1(page: Page) {
    this.shipService.getShipList1(page)
      .subscribe(response => {
        this.ships = response;
      }, error => {
        console.log("Error Occured", error);
      });
  }

  getShipList() {
    this.shipService.getShipList()
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
          this.getShipList1(this.page);
        } else {
          alert(response.message);
        }
      }, error => {
        console.log("Error Occured", error);
      });
  }


  editShip(shipCode: string) {
    this.router.navigate(['ships/update', shipCode]);
  }

/*
  editShip(shipForm: FormGroup) {
    if (shipForm.valid) {
      let ship: Ship = shipForm.value;
      this.shipService.editShip(ship)
        .subscribe(response => {
          if (response.status) {
            alert(response.message);
            this.router.navigate(['ship/list']);
          } else {
            alert(response.message);
          }
        }, error => {
          alert("Error while adding ship");
        });
    } else {
      alert("Please fill form, something is missing or invalid");
    }
  }

 */

}
