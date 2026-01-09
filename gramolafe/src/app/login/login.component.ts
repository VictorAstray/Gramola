import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  // IMPORTANTE: Si tu archivo HTML se llama login.html, cambia esto:
  templateUrl: './login.component.html', 
  styles: []
})
export class LoginComponent {
  email = '';
  pwd = '';
  mensaje = '';

  constructor(private service: UserService, private router: Router) {}

  entrar() {
    this.service.login(this.email, this.pwd).subscribe({
      next: (usuario) => {
        this.mensaje = '¡Login correcto!';
        // Redirigir a Home
        this.router.navigate(['/home']);
      },
      error: (error) => {
        this.mensaje = 'Error: Credenciales incorrectas';
      }
    });
  }
  
  // ESTA ES LA FUNCIÓN QUE FALTABA
  irARegistro() {
    this.router.navigate(['/register']);
  }
}