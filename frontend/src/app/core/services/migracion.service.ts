import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { SolicitudMigracion, RespuestaMigracion } from '../models/migracion.models';

@Injectable({
    providedIn: 'root'
})
export class ServicioMigracion {
    private http = inject(HttpClient);

    procesarMigracion(solicitud: SolicitudMigracion): Observable<RespuestaMigracion> {
        return this.http.post<RespuestaMigracion>('/api/migrar', solicitud);
    }
}
