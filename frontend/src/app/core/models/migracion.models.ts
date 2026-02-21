export interface SolicitudMigracion {
    codigoLegado: string;
    lenguajeDestino: string;
}

export interface RespuestaMigracion {
    codigoModerno: string;
    reglasAplicadas: string[];
    advertencias: string[];
}
