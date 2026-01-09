import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router'; // <--- IMPORTANTE

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet], // <--- AÑADIR RouterOutlet, QUITAR RegisterComponent
  templateUrl: './app.component.html',
  styles: []
})
export class AppComponent {
  title = 'gramolafe';
}