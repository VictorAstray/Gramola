import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app'; // <--- Importamos AppComponent

bootstrapApplication(AppComponent, appConfig) // <--- Arrancamos AppComponent
  .catch((err) => console.error(err));