import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // Necesario para el input
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './home.component.html',
  styles: []
})
export class HomeComponent {
  usuario: any = {};
  busqueda: string = '';
  canciones: any[] = [];

  constructor(private router: Router, private service: UserService) {
    // 1. Recuperar usuario al cargar la página
    const userJson = localStorage.getItem('usuario');
    if (userJson) {
      this.usuario = JSON.parse(userJson);
    } else {
      this.router.navigate(['/login']); // Si no hay usuario, echar fuera
    }
  }

  buscar() {
    if (!this.busqueda) return;

    this.service.search(this.busqueda, this.usuario).subscribe({
      next: (resultados) => {
        console.log("Canciones encontradas:", resultados);
        this.canciones = resultados;
      },
      error: (err) => {
        console.error("Error buscando:", err);
        alert("Error al buscar. Revisa que tu Client ID y Secret sean válidos en Spotify.");
      }
    });
  }

  logout() {
    localStorage.removeItem('usuario'); // Limpiar memoria
    this.router.navigate(['/login']);
  }
}