import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router'; 
import { UsuarioService } from '../servicio/usuario.service';



@Component({
  selector: 'app-iniciar-usuario',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './iniciar-usuario.component.html',
  styleUrls: ['./iniciar-usuario.component.css']
})
export class IniciarUsuarioComponent implements OnInit{
  constructor(private router: Router, 
    private usuarioService: UsuarioService) {}
   
ngOnInit(): void {
  
  }
  usuario = {
    correo: '',
    contrasena_usuario: ''
  };

  iniciarSesion() {
    if (this.usuario.correo && this.usuario.contrasena_usuario) {
      this.usuarioService.iniciarSesion(this.usuario.correo, this.usuario.contrasena_usuario)
        .subscribe({
          next: (data) => {
            console.log('Sesión iniciada correctamente:', data);
            alert('¡Inicio de sesión exitoso!');
            this.router.navigate(['/vehiculos']);
          },
          error: (err) => {
            console.error('Error en inicio de sesión:', err);
            alert('Credenciales inválidas');
          }
        });
    } else {
      alert('Por favor, complete todos los campos');
    }
  }
}