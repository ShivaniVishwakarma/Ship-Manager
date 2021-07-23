import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';
import { Constants } from '../../../core/constants';

import { StorageService } from '../../../core/services/storage.service';
import { AuthService } from '../../services/auth.service';
import {Ship} from "../../../ship/ship.model";
import {User} from "../../user.model";
import {JWTUtils} from "../../../core/services/jwt.utils";


@Component({
  selector: 'app-auth-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public authForm: FormGroup;
  isLoggedIn = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private storageService: StorageService,
    private route: ActivatedRoute,
    private router: Router,
    private toasterService: ToastrService,
  ) {
    this.authForm = this.fb.group({
      username: [''],
      password: ['']
    });
  }

  ngOnInit(): void {
  }

  login(authForm: FormGroup) {
    if (authForm.valid) {
      let user: User = authForm.value;
      this.authService.login(user)
        .subscribe(response => {
          //this.storageService.setAccessToken(response.data);
          if (response.status) {
            alert(response.message);
            this.authForm.reset();
            this.updateToken(response.data);
          } else {
            alert(response.message);
          }
        }, error => {
          this.toasterService.error('Error while login', Constants.TITLE_ERROR);
          //this.updateToken({});
        });
    } else {
      alert("Please fill form, something is missing or invalid");
    }
  }


  updateToken(data: any) {
    this.storageService.setAccessToken(data);
    //this.storageService.setRefreshToken('$2a$10$sHCWxNowmG6kOOkfaDQPp.KC6mErRsSVHoz1L1Vr0DDAXV41AVRRa');
    this.route.queryParams
      .subscribe(params => {
        let url = params.returnUrl;
        if (!url) { url = '/ships'; }
        this.router.navigate([url]);
      });
  }

}
