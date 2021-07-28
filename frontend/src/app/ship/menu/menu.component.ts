import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JWTUtils } from 'src/app/core/services/jwt.utils';
import { StorageService } from 'src/app/core/services/storage.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  @Input() title: string = '';
  loggedInUser: string = '';

  constructor(
    private jwtUtils: JWTUtils,
    private localStorageService: StorageService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loggedInUser = this.jwtUtils.decodeJwt(this.localStorageService.getAccessToken()).sub;
  }

  logOut() {
    this.localStorageService.setAccessToken('');
    this.goToAuth();
  }

  goToAuth() {
    this.router.navigate(['auth/']);
  }

}
