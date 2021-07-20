import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NoRouteFoundComponent } from '../no-route/NoRouteFoundCompoent';

import ShipComponent from './ship.component';

import { ShipAddComponent } from './components/ship-add/ship-add.component';
import { ShipEditComponent } from './components/ship-edit/ship-edit.component';
import { ShipListComponent } from './components/ship-list/ship-list.component';

const routes: Routes = [
    {
        path: '',
        component: ShipComponent,
        children: [
            { path: '', redirectTo: 'ship/list', pathMatch: 'full' },
            { path: 'list', component: ShipListComponent, data: { title: 'Ship List' } },
            { path: 'add', component: ShipAddComponent, data: { title: 'Add New Ship' } },
            { path: 'edit/:id', component: ShipEditComponent, data: { title: 'Update Ship Details' } },
            { path: '**', redirectTo: 'list' }
        ]
    },
    { path: '**', component: NoRouteFoundComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ShipRoutingModule { }
