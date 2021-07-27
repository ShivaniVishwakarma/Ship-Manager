import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

import { ShipService } from '../../services/ship.service';

import { Ship } from '../../ship.model';
import {Router} from "@angular/router";

@Component({
  selector: 'app-ship-add',
  templateUrl: './ship-add.component.html',
  styleUrls: ['./ship-add.component.scss']
})
export class ShipAddComponent implements OnInit {

  public shipForm: FormGroup;

  constructor(private fb: FormBuilder, private shipService: ShipService, private router: Router) {
    this.shipForm = this.fb.group({
      shipName: ['',Validators.required],
      shipLengthInMeters: ['',Validators.required],
      shipWidthInMeters: ['',Validators.required]
    });
  }

  ngOnInit(): void {
  }

  saveShip(shipForm: FormGroup) {
    if (shipForm.valid) {
      let ship: Ship = shipForm.value;
      this.shipService.addShip(ship)
        .subscribe(response => {
          if (response.status) {
            alert(response.message);
            this.shipForm.reset();
            this.goToShipList();
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

  goToShipList() {
    this.router.navigate(['ships']);
  }


}
