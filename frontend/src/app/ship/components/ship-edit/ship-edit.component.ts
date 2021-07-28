import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Constants } from '../../../core/constants';

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
    private toastrService: ToastrService,
    private router: Router) {
    this.shipForm = this.fb.group({
      shipCode: ['', Validators.required],
      shipName: ['', Validators.required],
      shipLengthInMeters: ['', Validators.required],
      shipWidthInMeters: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.activateRoute.params.subscribe(response => this.getShip(response.id));
    this.shipForm.get('shipCode')?.disable();
  }

  getShip(shipCode: string) {
    this.shipService.getShip(shipCode)
      .subscribe(response => {
        if (response.status) {
          this.shipForm.patchValue(response.data);
        } else {
          this.toastrService.error(response.message && 'Error while fetching ship details', Constants.TITLE_ERROR);
        }
      }, error => {
        this.toastrService.error("Error while updating ship", Constants.TITLE_ERROR);
      });
  }

  editShip(shipForm: FormGroup) {
    if (shipForm.valid) {
      let ship: Ship = shipForm.getRawValue();
      this.shipService.editShip(ship)
        .subscribe(response => {
          if (response.status) {
            this.toastrService.success(response.message, Constants.TITLE_SUCCESS);
            this.router.navigate(['ships/list']);
          } else {
            this.toastrService.error(response.message, Constants.TITLE_ERROR);
          }
        }, error => {
          this.toastrService.error("Error while updating ship", Constants.TITLE_ERROR);
        });
    } else {
      this.toastrService.error("Please fill form, something is missing or invalid", Constants.TITLE_ERROR);
    }
  }

}
