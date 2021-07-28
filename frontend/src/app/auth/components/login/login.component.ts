import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';
import { Constants } from '../../../core/constants';

import { StorageService } from '../../../core/services/storage.service';
import { AuthService } from '../../services/auth.service';
import { User } from "../../user.model";



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
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
  }

  get username() {
    return this.authForm.get('username');
  }
  get password() {
    return this.authForm.get('password');
  }

  login(authForm: FormGroup) {
    if (authForm.valid) {
      let user: User = authForm.value;
      this.authService.authenticate(user)
        .subscribe(response => {
          if (response.token) {
            this.authForm.reset();
            this.updateToken(response.token);
            this.toasterService.success("You logged in successfully.", Constants.TITLE_SUCCESS);
          } else {
            this.toasterService.error("Invalid username or password", Constants.TITLE_ERROR);
          }
        }, error => {
          this.toasterService.error('Error while login', Constants.TITLE_ERROR);
        });
    } else {
      this.toasterService.warning("Please fill form, something is missing or invalid", Constants.TITLE_ERROR);
    }
  }

  updateToken(data: any) {
    this.storageService.setAccessToken(data);
    this.route.queryParams
      .subscribe(params => {
        let url = params.returnUrl;
        if (!url) { url = '/ships'; }
        this.router.navigate([url]);
      });
  }

}
