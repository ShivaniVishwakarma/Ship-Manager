import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { ShipService } from '../../services/ship.service';
import { Ship } from '../../ship.model';

@Component({
  selector: 'app-ship-edit',
  templateUrl: './ship-edit.component.html',
  styleUrls: ['./ship-edit.component.scss']
})
export class ShipEditComponent implements OnInit {

  public shipForm: FormGroup;

  constructor(private fb: FormBuilder,
    private shipService: ShipService,
    private activateRoute: ActivatedRoute,
    private router: Router) {
    this.shipForm = this.fb.group({
      code: '',
      name: [''],
      length: [''],
      width: [''],
    });
  }

  ngOnInit(): void {
    this.activateRoute.params.subscribe(response => this.getShip(response.id));
  }

  getShip(shipCode: string) {
    this.shipService.getShip(shipCode)
      .subscribe(response => {
        if (response.status) {
          this.shipForm.patchValue(response.data);
        } else {
          alert(response.message);
        }
      }, error => {
        alert("Error while fetching ship details");
      });
  }


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

}
