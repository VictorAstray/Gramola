import { Component } from '@angular/core';
import { RegisterComponent } from './register/register.component'; // Importamos el registro

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RegisterComponent], // Añadimos el registro aquí
  templateUrl: './app.html',
  styles: []
})
export class AppComponent { // <--- Fíjate que ahora se llama AppComponent
  title = 'gramolafe';
}