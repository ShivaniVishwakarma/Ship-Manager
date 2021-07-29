import { Component } from "@angular/core";

@Component({
    selector: 'app-ship',
    template: `<app-menu [title]="title"></app-menu>
            <router-outlet> </router-outlet>`
})
export class ShipComponent {
    title: string = 'SHIP MANAGER';
}
