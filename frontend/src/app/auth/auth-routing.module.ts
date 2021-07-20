import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NoRouteFoundComponent } from '../no-route/NoRouteFoundCompoent';
import AuthComponent from './auth.component';
import { LoginComponent } from './components/login/login.component';



const routes: Routes = [
    {
        path: '',
        component: AuthComponent,
        children: [
            { path: '', redirectTo: 'login', pathMatch: 'full' },
            { path: 'login', component: LoginComponent, data: { title: 'Login' } },
            { path: '**', redirectTo: 'login' }
        ]
    },
    { path: '**', component: NoRouteFoundComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AuthRoutingModule { }
