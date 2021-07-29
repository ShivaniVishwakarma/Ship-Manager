import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Ship} from '../../ship.model';
import {ShipService} from '../../services/ship.service';
import {ToastrService} from 'ngx-toastr';
import {Constants} from '../../../core/constants';


@Component({
  selector: 'app-ship-list',
  templateUrl: './ship-list.component.html',
  styleUrls: ['./ship-list.component.scss']
})
export class ShipListComponent implements OnInit {

  loading: boolean = true;
  ships: Ship[] = [];
  totalRecords: number = 4;

  constructor(private shipService: ShipService,
              private toastrService: ToastrService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getShipList();
  }

  getShipList() {
    this.shipService.getShipList()
      .subscribe(response => {
        this.ships = response;
        this.totalRecords = this.ships.length;
        if (!this.ships.length) {
          this.toastrService.error("Their are no records present as of now", Constants.TITLE_ERROR);
        }
        this.loading = false;
      }, error => {
        this.toastrService.error("Error while fetching ship list", Constants.TITLE_ERROR);
        this.loading = false;
      });
  }

  deleteShip(shipCode: string) {
    this.shipService.deleteShip(shipCode)
      .subscribe(response => {
        if (response.status) {
          this.toastrService.success(response.message, Constants.TITLE_SUCCESS);
          this.getShipList();
        } else {
          this.toastrService.error(response.message, Constants.TITLE_ERROR);
        }
      }, error => {
        this.toastrService.error("Error while adding ship details", Constants.TITLE_ERROR);
      });
  }


  goToEditShip(shipCode: string) {
    this.router.navigate(['ships/update', shipCode]);
  }

}
