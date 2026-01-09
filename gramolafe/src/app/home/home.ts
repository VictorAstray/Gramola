import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.html', // Fíjate que aquí usamos el nombre corto
  styles: []
})
export class HomeComponent {
  constructor(private router: Router) {}

  logout() {
    this.router.navigate(['/login']);
  }
}