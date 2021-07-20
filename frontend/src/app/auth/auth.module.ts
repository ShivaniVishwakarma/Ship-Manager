import { CommonModule } from "@angular/common";
import { HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";

import { AuthService } from "./services/auth.service";
import { AuthRoutingModule } from "./auth-routing.module";
import AuthComponent from "./auth.component";
import { LoginComponent } from "./components/login/login.component";

@NgModule({
    declarations: [
        AuthComponent,
        LoginComponent
    ],
    providers: [AuthService],
    imports: [
        CommonModule,
        AuthRoutingModule,
        HttpClientModule,
        ReactiveFormsModule
    ],
    bootstrap: [AuthComponent]
})
export class AuthModule { }