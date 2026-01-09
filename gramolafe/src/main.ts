import 'zone.js';  // <--- 1. Importamos la pieza que faltaba
import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component'; // <--- 2. Corregimos la ruta (añadido .component)

bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));