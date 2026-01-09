import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  // Base URL
  private apiUrl = 'http://localhost:8080/users';

  constructor(private http: HttpClient) {}

  register(bar: string, email: string, pwd1: string, pwd2: string, clientId: string, clientSecret: string) {
    let info = {
      bar: bar,
      email: email,
      pwd1: pwd1,
      pwd2: pwd2,
      clientId: clientId,
      clientSecret: clientSecret
    };
    return this.http.post<any>(this.apiUrl + '/register', info);
  }


  // ... resto del código igual ...

  login(email: string, pwd: string) {
    let info = { email: email, pwd: pwd };
    return this.http.post<any>(this.apiUrl + '/login', info);
  }

  // --- NUEVO MÉTODO DE BÚSQUEDA ---
  search(query: string, user: any) {
    let info = {
      email: user.email,
      query: query,
      clientId: user.clientId,
      clientSecret: user.clientSecret
    };
    return this.http.post<any>(this.apiUrl + '/search', info);
  }
}
