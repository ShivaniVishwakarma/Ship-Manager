import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { ShipService } from '../../services/ship.service';

import { Ship } from '../../ship.model';

@Component({
  selector: 'app-ship-add',
  templateUrl: './ship-add.component.html',
  styleUrls: ['./ship-add.component.scss']
})
export class ShipAddComponent implements OnInit {

  public shipForm: FormGroup;

  constructor(private fb: FormBuilder, private shipService: ShipService) {
    this.shipForm = this.fb.group({
      code: '',
      name: [''],
      length: [''],
      width: [''],
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
      alert("Please fill form, something is missing or invalid");
    }
  }

}
