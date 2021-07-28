import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from "@angular/router";
import { ToastrService } from 'ngx-toastr';

import { ShipService } from '../../services/ship.service';
import { Ship } from '../../ship.model';
import { Constants } from '../../../core/constants';

@Component({
  selector: 'app-ship-add',
  templateUrl: './ship-add.component.html',
  styleUrls: ['./ship-add.component.scss']
})
export class ShipAddComponent implements OnInit {

  public shipForm: FormGroup;

  constructor(private fb: FormBuilder,
    private shipService: ShipService,
    private toastrService: ToastrService,
    private router: Router) {
    this.shipForm = this.fb.group({
      shipName: ['', Validators.required],
      shipLengthInMeters: ['', Validators.required],
      shipWidthInMeters: ['', Validators.required]
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
            this.shipForm.reset();
            this.goToShipList();
            this.toastrService.success(response.message, Constants.TITLE_SUCCESS);
          } else {
            this.toastrService.error(response.message, Constants.TITLE_ERROR);
          }
        }, error => {
          this.toastrService.error("Error while adding ship", Constants.TITLE_ERROR);
        });
    } else {
      this.toastrService.error("Please fill form, something is missing or invalid", Constants.TITLE_ERROR);
    }
  }

  goToShipList() {
    this.router.navigate(['ships']);
  }


}
