import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  // La URL de tu backend Java (puerto 8080)
  private apiUrl = 'http://localhost:8080/users/register';

  constructor(private http: HttpClient) {}

  register(bar: string, email: string, pwd1: string, pwd2: string, clientId: string, clientSecret: string) {
    // Empaquetamos los datos en un objeto JSON
    let info = {
      bar: bar,
      email: email,
      pwd1: pwd1,
      pwd2: pwd2,
      clientId: clientId,
      clientSecret: clientSecret
    };
    
    // Enviamos el paquete a Java con una petición POST
    return this.http.post<any>(this.apiUrl, info);
  }
}