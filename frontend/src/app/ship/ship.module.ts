import { CommonModule } from "@angular/common";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import {NgForm, ReactiveFormsModule} from "@angular/forms";
import { ShipAddComponent } from "./components/ship-add/ship-add.component";
import { ShipEditComponent } from "./components/ship-edit/ship-edit.component";
import { ShipListComponent } from "./components/ship-list/ship-list.component";
import { ShipService } from "./services/ship.service";
import { ShipRoutingModule } from "./ship-routing.module";
import {ShipComponent} from "./ship.component";
import { MenuComponent } from './menu/menu.component';
import { ActionComponent } from "../core/shared/components/action.component";
import { TokenInterceptor } from "../core/interceptors/token.interceptor";
import {TableModule} from "primeng/table";
import { FormsModule } from '@angular/forms';
import {PaginatorModule} from 'primeng/paginator';

@NgModule({
  declarations: [
    MenuComponent,
    ShipComponent,
    ShipListComponent,
    ShipAddComponent,
    ShipEditComponent,
    ActionComponent
  ],
  providers: [ShipService,
    [{
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }]],
  imports: [
    CommonModule,
    ShipRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    TableModule,
    FormsModule,
    PaginatorModule
  ],
  bootstrap: [ShipComponent]
})
export class ShipModule { }
