import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../user.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  // He quitado la línea de styleUrl por si acaso no tienes el archivo css, para evitar errores.
  styles: []
})
export class RegisterComponent {
  bar = '';
  email = '';
  pwd1 = '';
  pwd2 = '';
  clientId = '';
  clientSecret = '';
  
  mensaje = ''; 

  constructor(private service: UserService) {}

  registrar() {
    if (this.pwd1 !== this.pwd2) {
      this.mensaje = '¡Las contraseñas no coinciden!';
      return;
    }

    this.service.register(this.bar, this.email, this.pwd1, this.pwd2, this.clientId, this.clientSecret)
      .subscribe({
        next: (ok) => {
          this.mensaje = '¡Registro enviado! Revisa la terminal de Java.';
          console.log('Exito:', ok);
        },
        error: (err) => {
          this.mensaje = 'Error al registrar: ' + err.message;
          console.error('Error:', err);
        }
      });
  }
}