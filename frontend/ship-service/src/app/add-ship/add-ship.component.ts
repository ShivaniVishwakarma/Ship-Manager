import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ShipService} from "../ship.service";
import {Ship} from "../ship";


@Component({
  selector: 'app-add-ship',
  templateUrl: './add-ship.component.html',
  styleUrls: ['./add-ship.component.css']
})
export class AddShipComponent implements OnInit {

  public shipForm: FormGroup;

  constructor(private fb: FormBuilder, private shipService: ShipService) {
    this.shipForm = this.fb.group({
      shipCode: '',
      shipName: [''],
      shipLengthInMeters: [''],
      shipWidthInMeters: ['']
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
          } else {
            alert(response.message);
          }
        }, error => {
          alert("Error while adding ship");
        });
    } else {
      alert("Please check the fields once, something is missing or invalid");
    }
  }

}
