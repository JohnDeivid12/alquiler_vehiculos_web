import { Component, OnInit } from '@angular/core';
import { Vehiculo } from '../entities/vehiculo';
import { UsuarioService } from '../servicio/usuario.service';
import { VehiculoService } from '../servicio/vehiculo.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AlquilerService } from '../servicio/alquiler.service';


@Component({
  selector: 'app-vehiculos',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './vehiculos.component.html',
  styleUrl: './vehiculos.component.css'
})
export class VehiculosComponent implements OnInit{
  vehiculoSeleccionado: Vehiculo | null = null;
  datosAlquiler = {
  correoUsuario: '',
  placaVehiculo: '',
  fechaInicio: '',
  fechaEntrega: ''
};
  vehiculosDisponibles: Vehiculo[]=[];
  usuarioServicio: UsuarioService;
  vehiculo: Vehiculo =  new Vehiculo;
  tiposVehiculo: string[] = ['Camioneta', 'Particular', 'Microbus', 'Jeep'];
  tipoSeleccionado: string = '';
  ngOnInit(): void{
      this.verDisponibles()
  }
    constructor(private vehiculoServicio: VehiculoService,
                private alquilerService: AlquilerService
    ){} 

    verDisponibles() {
  this.vehiculoServicio.obtenerDisponibles().subscribe(data => {
    this.vehiculosDisponibles = data;
    console.log(data);
  }, error => {
    console.error("Error al cargar vehículos disponibles", error);
  });
}

  filtrarPorTipo() {
  if (!this.tipoSeleccionado) {
    this.verDisponibles(); // Mostrar todos si no se selecciona ninguno
    return;
  }

  this.vehiculoServicio.buscarDisponiblesPorTipo(this.tipoSeleccionado).subscribe(data => {
    this.vehiculosDisponibles = data;
  }, error => {
    console.error("Error al filtrar vehículos por tipo", error);
  });
}
mostrarFormularioAlquiler(vehiculo: Vehiculo) {
  this.vehiculoSeleccionado = vehiculo;
  this.datosAlquiler.placaVehiculo = vehiculo.placa;
}

cancelarFormulario() {
  this.vehiculoSeleccionado = null;
  this.datosAlquiler = {
    correoUsuario: '',
    placaVehiculo: '',
    fechaInicio: '',
    fechaEntrega: ''
  };
}

alquilarVehiculo() {
  const datos = { ...this.datosAlquiler };

  this.alquilerService.alquilarVehiculo(datos).subscribe({
    next: (res) => {
      alert('Alquiler realizado exitosamente');
      this.cancelarFormulario();
      this.verDisponibles(); // recargar lista
    },
    error: (err) => {
      alert('Error al realizar el alquiler: ' + (err.error || err.message));
    }
  });
}


}
