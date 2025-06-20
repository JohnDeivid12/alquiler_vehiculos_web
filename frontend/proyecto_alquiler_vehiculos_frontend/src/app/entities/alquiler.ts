import { Usuario } from "./usuario";
import { Vehiculo } from "./vehiculo";

export class Alquiler {
    idAlquiler: number;
    vehiculo:Vehiculo;
    usuario:Usuario;
    fechaInicio:Date;
    fechaEntrega:Date;
    valorAlquiler:number;
    estado:string;
    fechaEntregaReal:Date;
    costoExtra:number;
    valorTotalAlquiler:number;
}
