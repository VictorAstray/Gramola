import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component'; // Este lo renombraste
import { RegisterComponent } from './register/register.component'; // Este también
import { HomeComponent } from './home/home'; // <--- ESTE ES EL NUEVO (sin .component)

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];