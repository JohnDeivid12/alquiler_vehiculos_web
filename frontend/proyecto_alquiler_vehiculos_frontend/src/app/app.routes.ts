import { Routes } from '@angular/router';
import { RegistroUsuarioComponent } from './registro-usuario/registro-usuario.component';
import { IniciarUsuarioComponent } from './iniciar-usuario/iniciar-usuario.component';
import { VehiculoComponent } from './vehiculo/vehiculo.component';

export const routes: Routes = [
    {path: 'registro-usuario', component: RegistroUsuarioComponent},
    { path: 'iniciar-sesion', component: IniciarUsuarioComponent },
    { path: 'vehiculo', component: VehiculoComponent },
];
