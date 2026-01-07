import { Component } from '@angular/core';
import { RegisterComponent } from './register/register.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RegisterComponent],
  templateUrl: './app.component.html', // <--- CAMBIADO para coincidir con el nombre nuevo
  styles: []
})
export class AppComponent {
  title = 'gramolafe';
}