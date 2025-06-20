import { CommonModule } from '@angular/common';
import { VehiculoService } from '../servicio/vehiculo.service';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Vehiculo } from '../entities/vehiculo';
import { UsuarioService } from '../servicio/usuario.service';

@Component({
  selector: 'app-vehiculo',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './vehiculo.component.html',
  styleUrl: './vehiculo.component.css'
})
export class VehiculoComponent implements OnInit {
  vehiculosDisponibles: Vehiculo[]=[];
  usuarioServicio: UsuarioService;
  vehiculo: Vehiculo =  new Vehiculo;
  tiposVehiculo: string[] = ['Camioneta', 'Particular', 'Microbus', 'Jeep'];
  tipoSeleccionado: string = '';
   
  ngOnInit(): void {
    this.verDisponibles()
  }
  
   constructor(private vehiculoServicio: VehiculoService){} 

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

}
