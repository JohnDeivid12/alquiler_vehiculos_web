import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AlquilerService {
  private baseUrl = 'http://localhost:8080/alquiler/alquilar';

  constructor(private http: HttpClient) {}

  alquilarVehiculo(datos: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, datos);
  }
}