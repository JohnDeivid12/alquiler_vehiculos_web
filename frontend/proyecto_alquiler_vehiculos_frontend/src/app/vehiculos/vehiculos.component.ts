import { Component, OnInit } from '@angular/core';
import { Vehiculo } from '../entities/vehiculo';
import { UsuarioService } from '../servicio/usuario.service';
import { VehiculoService } from '../servicio/vehiculo.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AlquilerService } from '../servicio/alquiler.service';
import jsPDF from 'jspdf'; 


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

      this.generarPDF({
        id: res.idAlquiler, // o usa un ID simulado si no viene en la respuesta
        correoUsuario: datos.correoUsuario,
        fechaInicio: datos.fechaInicio,
        fechaEntrega: datos.fechaEntrega,
        tipo: this.vehiculoSeleccionado?.tipo || '',
        placa: this.vehiculoSeleccionado?.placa || '',
        color: this.vehiculoSeleccionado?.color || '',
        valor: res.valor, // si tu backend devuelve el valor
      });

      this.cancelarFormulario();
      this.verDisponibles(); // recargar lista
    },
    error: (err) => {
      alert('Error al realizar el alquiler: ' + (err.error || err.message));
    }
  });
}

generarPDF(alquiler: {
  id: number,
  correoUsuario: string,
  fechaInicio: string,
  fechaEntrega: string,
  tipo: string,
  placa: string,
  color: string,
  valor: number
}) {
  const doc = new jsPDF();

  doc.setFontSize(14);
  doc.text('Comprobante de Alquiler', 10, 10);

  doc.setFontSize(12);
  doc.text(`Número de alquiler: ${alquiler.id}`, 10, 20);
  doc.text(`Correo del usuario: ${alquiler.correoUsuario}`, 10, 30);
  doc.text(`Fecha inicio: ${alquiler.fechaInicio}`, 10, 40);
  doc.text(`Fecha entrega: ${alquiler.fechaEntrega}`, 10, 50);
  doc.text(`Tipo de automóvil: ${alquiler.tipo}`, 10, 60);
  doc.text(`Placa: ${alquiler.placa}`, 10, 70);
  doc.text(`Color: ${alquiler.color}`, 10, 80);
  doc.text(`Valor del alquiler: ${alquiler.valor}`, 10, 90);
  doc.text(`Estado: pendiente de entrega`, 10, 100);

  doc.save(`comprobante-alquiler-${alquiler.id}.pdf`);
}


}
