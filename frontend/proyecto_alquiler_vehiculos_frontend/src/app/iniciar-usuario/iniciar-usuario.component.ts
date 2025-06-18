import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-iniciar-usuario',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './iniciar-usuario.component.html',
  styleUrls: ['./iniciar-usuario.component.css']
})
export class IniciarUsuarioComponent implements OnInit{
  constructor(private router: Router) {}
ngOnInit(): void {
  
  }
  usuario = {
    correo: 'usuario',
    contrasena_usuario: 'usuario'
  };

  iniciarSesion() {
  if (this.usuario.correo && this.usuario.contrasena_usuario) {
    console.log('Iniciando sesión con:', this.usuario);
    // Aquí puedes llamar a un servicio para autenticar al usuario
    alert('¡Inicio de sesión exitoso!');
    this.router.navigate(['/vehiculos']);
  } else {
    console.log('Por favor, complete todos los campos');
    alert('Por favor, complete todos los campos');
  }
}
}