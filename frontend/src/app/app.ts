import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ServicioMigracion } from './core/services/migracion.service';
import { RespuestaMigracion } from './core/models/migracion.models';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  private migracionService = inject(ServicioMigracion);

  codigoLegado = signal('');
  lenguajeDestino = signal('Java + Spring Boot');

  codigoModerno = signal('');
  reglasAplicadas = signal<string[]>([]);
  advertencias = signal<string[]>([]);

  tabSeleccionada = signal<'codigo' | 'reporte'>('codigo');
  isGenerando = signal(false);

  ejecutarMigracion() {
    if (!this.codigoLegado()) return;

    this.isGenerando.set(true);

    this.migracionService.procesarMigracion({
      codigoLegado: this.codigoLegado(),
      lenguajeDestino: this.lenguajeDestino()
    }).subscribe({
      next: (respuesta: RespuestaMigracion) => {
        this.codigoModerno.set(respuesta.codigoModerno);
        this.reglasAplicadas.set(respuesta.reglasAplicadas || []);
        this.advertencias.set(respuesta.advertencias || []);

        this.setTab('codigo');
        this.isGenerando.set(false);
      },
      error: (err) => {
        console.error('Error en migración', err);
        this.isGenerando.set(false);
      }
    });
  }

  setTab(tab: 'codigo' | 'reporte') {
    this.tabSeleccionada.set(tab);
  }
}
