import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ListShipComponent} from "./list-ship/list-ship.component";
import {AddShipComponent} from "./add-ship/add-ship.component";
import {UpdateShipComponent} from "./update-ship/update-ship.component";


const routes: Routes = [
  { path: 'list-ship', component: ListShipComponent },
  { path: 'add-ship', component: AddShipComponent },
  { path: 'update-ship/:id', component: UpdateShipComponent },
  { path: '', redirectTo: '/ship-list', pathMatch: 'full' },
  { path: '**', component: ListShipComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
