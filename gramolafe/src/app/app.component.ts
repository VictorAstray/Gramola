import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html', // <--- Nombre Estándar
  styles: []
})
export class AppComponent {
  title = 'gramolafe';
}