import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { AddShipComponent } from './add-ship/add-ship.component';
import { UpdateShipComponent } from './update-ship/update-ship.component';
import { ListShipComponent } from './list-ship/list-ship.component';
import { MenuComponent } from './menu/menu.component';
import {AppRoutingModule} from "./app-routing.module";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    AddShipComponent,
    UpdateShipComponent,
    ListShipComponent,
    MenuComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
