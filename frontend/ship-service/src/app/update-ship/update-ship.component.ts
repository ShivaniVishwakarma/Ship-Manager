import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ShipService} from "../ship.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Ship} from "../ship";

@Component({
  selector: 'app-update-ship',
  templateUrl: './update-ship.component.html',
  styleUrls: ['./update-ship.component.css']
})
export class UpdateShipComponent implements OnInit {

  public shipForm: FormGroup;

  constructor(private fb: FormBuilder,
              private shipService: ShipService,
              private activateRoute: ActivatedRoute,
              private router: Router) {
    this.shipForm = this.fb.group({
        shipCode: '',
        shipName: [''],
        shipLengthInMeters: [''],
        shipWidthInMeters: ['']
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
      let ship: Ship= shipForm.value;
      this.shipService.updateShip(ship)
        .subscribe(response => {
          if (response.status) {
            alert(response.message);
            this.router.navigate(['list-ship']);
          } else {
            alert(response.message);
          }
        }, error => {
          alert("Error while updating ship");
        });
    } else {
      alert("Please fill form, something is missing or invalid");
    }
  }


}
