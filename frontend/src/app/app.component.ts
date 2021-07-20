import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, NavigationEnd, Router, RouterState } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title: string = 'Ship Manager';

  constructor(private titlesevice: Title, router: Router) {
    router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        const title =
          "Ship Manager - " +
          this.getTitle(router.routerState, router.routerState.root).join("-");
        this.setTitle(title);
      }
    });
  }

  getTitle(state: any, parent: any) {
    const data: string[] = [];
    if (parent && parent.snapshot.data && parent.snapshot.data.title) {
      data.push(parent.snapshot.data.title);
    }
    if (state && parent) {
      data.push(...this.getTitle(state, state.firstChild(parent)));
    }
    return data;
  }

  setTitle(title: string) {
    this.titlesevice.setTitle(title);
  }
}
