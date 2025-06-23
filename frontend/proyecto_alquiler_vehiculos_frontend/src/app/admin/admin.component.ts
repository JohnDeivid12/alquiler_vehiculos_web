import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminService } from '../servicio/admin.service';
import { Vehiculo } from '../entities/vehiculo';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent implements OnInit {
  
vehiculosFiltrados: Vehiculo[] = [];

tiposVehiculo: string[] = []; // lista de tipos únicos
tipoSeleccionado: string = '';

  todosVehiculos: Vehiculo[] = [];
  vehiculosDisponibles: Vehiculo[] = [];
  vehiculosPendientes: Vehiculo[] = [];
  opcionSeleccionada: string = '';
  //placaConsulta: string = '';
  idAlquilerConsulta: number;
  idAdminConsulta: number = 0;
  fechaEntregaReal: string = '';
  resultadoCosto: any = null; 
  alquiler_vehiculos: any;

  constructor(private adminService: AdminService) {}

ngOnInit(): void {
  
}


verOpciones() {
  if (this.opcionSeleccionada === 'verPendientes') {
    this.adminService.obtenerVehiculosPendientes().subscribe({
      next: (data) => {
        this.vehiculosPendientes = data ?? [];
      },
      error: (err) => {
        console.error('Error al obtener vehículos pendientes', err);
      }
    });
  } else if (this.opcionSeleccionada === 'verDisponibles') {
    this.adminService.obtenerVehiculosDisponibles().subscribe({
      next: (data) => {
        this.vehiculosDisponibles = data ?? [];
      },
      error: (err) => {
        console.error('Error al obtener vehículos disponibles', err);
      }
    });
  } else if (this.opcionSeleccionada === 'verTodos') {
  this.adminService.obtenerTodosVehiculos().subscribe({
    next: (data) => {
      this.todosVehiculos = data ?? [];
      this.vehiculosFiltrados = [...this.todosVehiculos]; // por defecto todos
      this.extraerTiposUnicos(); // genera lista de tipos
    },
    error: (err) => {
      console.error('Error al obtener todos los vehículos', err);
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


calcularCostoExtra() {
  if (!this.idAlquilerConsulta || !this.fechaEntregaReal || !this.idAdminConsulta) {
    alert('Debes completar todos los campos');
    return;
  }

  this.adminService.costoExtra(
    this.idAlquilerConsulta,
    this.fechaEntregaReal,
    this.idAdminConsulta
  ).subscribe({
    next: (res) => {
      this.resultadoCosto = {
        idAlquiler: res.idAlquiler,
        valorAlquiler: res.valorAlquiler,
        costoExtra: res.costoExtra,
        valorTotalAlquiler: res.valorTotalAlquiler,
        fechaEntregaReal: res.fechaEntregaReal
      };

      // Limpiar campos
      this.idAlquilerConsulta = 0;
      this.idAdminConsulta = 0;
      this.fechaEntregaReal = '';
    },
    error: (err) => {
      console.error('Error al calcular costo extra', err);
      alert('No se pudo calcular el costo extra');
      this.resultadoCosto = null;
    }
  });
}

filtrarPorTipo() {
  if (!this.tipoSeleccionado) {
    this.vehiculosFiltrados = [...this.todosVehiculos];
  } else {
    this.vehiculosFiltrados = this.todosVehiculos.filter(
      v => v.tipo.toLowerCase() === this.tipoSeleccionado.toLowerCase()
    );
  }
}

extraerTiposUnicos() {
  const tiposSet = new Set<string>();
  this.todosVehiculos.forEach(v => tiposSet.add(v.tipo));
  this.tiposVehiculo = Array.from(tiposSet);
}
}
