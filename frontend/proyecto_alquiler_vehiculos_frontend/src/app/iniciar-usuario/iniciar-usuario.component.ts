import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Usuario } from '../entities/usuario';
import { UsuarioService } from '../servicio/usuario.service';


@Component({
  selector: 'app-iniciar-usuario',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './iniciar-usuario.component.html',
  styleUrl: './iniciar-usuario.component.css'
})
export class IniciarUsuarioComponent implements OnInit{
   constructor(private router: Router, 
    private usuarioService: UsuarioService) {}
   
   
ngOnInit(): void {
  
  }
usuarioSesion: Usuario = new Usuario;

   iniciarSesion() {
    if (this.usuarioSesion.correo && this.usuarioSesion.contrasena_usuario) {
      this.usuarioService.iniciarSesion(this.usuarioSesion.correo, this.usuarioSesion.contrasena_usuario)
        .subscribe({
          next: (data) => {
            console.log('Sesión iniciada correctamente:', data);
            alert('¡Inicio de sesión exitoso!');
            this.router.navigate(['/vehiculo']);
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
