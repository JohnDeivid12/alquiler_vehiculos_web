import { Component, OnInit } from '@angular/core';
import { AdminService } from '../servicio/admin.service';
import { Vehiculo } from '../entities/vehiculo';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, FormsModule], 
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  vehiculosPendientes: Vehiculo[] = [];
  opcionSeleccionada: string = '';

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    
  }

  verOpciones() {
    if (this.opcionSeleccionada === 'verPendientes') {
      this.adminService.obtenerVehiculosPendientes().subscribe({
        next: (data) => {
          this.vehiculosPendientes = data ?? []; // Si data es null, se asigna un arreglo vacío
          console.log('Vehículos pendientes cargados:', this.vehiculosPendientes);
          if (this.vehiculosPendientes.length === 0) {
            alert('Todos los vehículos pendientes han sido entregados.');
          }
        },
        error: (err) => {
          console.error('Error al obtener vehículos pendientes', err);
        }
      });
    }
  }

  marcarComoEntregado(placa: string) {
    if (!placa) return;

    this.adminService.entregarVehiculo(placa).subscribe({
      next: (mensaje) => {
        alert(mensaje);
        this.verOpciones(); // recargar la tabla
        this.opcionSeleccionada = '';
        setTimeout(() => this.opcionSeleccionada = 'verPendientes', 0);
      },
      error: (error) => {
        console.error('Error al entregar vehículo', error);
        alert("No se pudo marcar como entregado: " + error.error);
      }
    });
  }
}

