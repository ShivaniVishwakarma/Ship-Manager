import {Component, OnInit, ViewChild} from '@angular/core';
import { Router } from '@angular/router';

import { Ship } from '../../ship.model';
import { ShipService } from '../../services/ship.service';
import Page from '../../../models/page.model';
import { ToastrService } from 'ngx-toastr';
import { Constants } from '../../../core/constants';
import {LazyLoadEvent} from "primeng/api";
import {Table} from "primeng/table";



@Component({
  selector: 'app-ship-list',
  templateUrl: './ship-list.component.html',
  styleUrls: ['./ship-list.component.scss']
})
export class ShipListComponent implements OnInit {

  loading: boolean = true;
  ships: Ship[] = [];
  searchStr: string = '';

  // @ViewChild('dt') table: Table | undefined;



  page: Page = {
    page: 0,
    size: 4,
    sort: [],
    shipName: ''
  };

  first: number = 0;
  last: number = 0;
  totalRecords: number = 4;

  constructor(private shipService: ShipService,
    private toastrService: ToastrService,
    private router: Router) { }

  ngOnInit(): void {
    this.getShipList();
  }

  getShipList() {
    this.shipService.getShipList1(this.page)
      .subscribe(response => {
        this.ships = response.data;
        //this.totalRecords = ; //this.ships.length;

        console.log("Ships: ", this.ships);
        if (!this.ships.length) {
          this.toastrService.error("Their are no records as of now", Constants.TITLE_ERROR);
        }
        this.loading = false;
      }, error => {
        this.toastrService.error("Error while fetching ship list", Constants.TITLE_ERROR);
        this.loading = false;
      });
  }
/*
  getAllShips(event: { lazyEvent: string }) {
    this.shipService.getShipList1(this.page)
      .subscribe(response => {
        this.ships = response.data;
        console.log("Ships: ", this.ships);
        if (!this.ships.length) {
          this.toastrService.error("Their are no records as of now", Constants.TITLE_ERROR);
        }
        this.loading = false;
      }, error => {
        this.toastrService.error("Error while fetching ship list", Constants.TITLE_ERROR);
        this.loading = false;
      });
  }

 */

  // test(p: { lazyEvent: string }){
  //   this.shipService.getShips()
  //     .then(res => {
  //     console.log('res=====>', res);
  //       this.ships = this.ships.slice(event.first, (event.first + event.rows));
  //     //this.ships = res;
  //     this.totalRecords =  this.ships.length;
  //     this.loading = false;
  //   })
  //
  // }
  myFilter(){
   if(this.searchStr.length){
     setTimeout(() => {
       this.page.shipName = this.searchStr;
       this.shipService.getShipList1(this.page)
         .subscribe(response => {
           this.ships = response.data;
           console.log("Ships: ", this.ships);
           this.page.size = this.ships.length < 10 ? this.page.size : this.ships.length / 10;
           if (!this.ships.length) {
             this.toastrService.error("Their are no records as of now", Constants.TITLE_ERROR);
           }
           this.loading = false;
         }, error => {
           this.toastrService.error("Error while fetching ship list", Constants.TITLE_ERROR);
           this.loading = false;
         });

     }, 1000);
   }else {
     this.getShipList();
     this.page.shipName ='';
   }
  }

  loadShips(event: any) {
    // let first = event.first || 0; //this.page.page;
    // let rows = event.rows || 2; //this.page.size;
    // this.loading = true;
    // let shoulfFechNext =  this.totalRecords / first
    // if(!first){
    //   return;
    // }
    //
    // this.page.page = first;
    // this.page.size = rows;
    // this.ships = this.ships.slice(page, (page + pageCount));
    // this.loading = false;

    // this.shipService.getShipList1(this.page)
    //   .subscribe(response => {
    //     this.ships = response.data;
    //     console.log("Ships: ", this.ships);
    //     if (!this.ships.length) {
    //       this.toastrService.error("Their are no records as of now", Constants.TITLE_ERROR);
    //     }
    //     this.loading = false;
    //   }, error => {
    //     this.toastrService.error("Error while fetching ship list", Constants.TITLE_ERROR);
    //     this.loading = false;
    //   });


    // setTimeout(() => {
    //   this.test({lazyEvent: JSON.stringify(event)})
    //
    // }, 1000);
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
