# Kata Motor codigo legado 
<div align="center">
<img src="https://img.shields.io/badge/Angular-DD0031.svg?style=flat&logo=angular&logoColor=white">
<img src="https://img.shields.io/badge/Java-ED8B00.svg?style=flat&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/Spring_Boot-6DB33F.svg?style=flat&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/Railway-131415.svg?style=flat&logo=railway&logoColor=white">
</div>

---

## Enlaces importantes 📌

| Recurso | Link |
|--------|------|
| **Repositorio GitHub** | https://github.com/mesa963/kata_motor |
| **Documentación Api** | https://motorcodigolegado.up.railway.app/swagger-ui/index.html |
| **Demo funcional** | https://motorcodigolegado.up.railway.app/ |

---


## Arquitectura

```mermaid
---

---
flowchart TD
    A([Usuario]) --> B([Navegador Web])
    
    subgraph Railway [Plataforma Railway]

        B --> C[Frontend: Angular]
        C -- "Petición REST" --> D[Backend: Spring Boot]
        
        subgraph Motor [Motor Reglas]
            D --> E[API Controller]
            E --> F[Servicio Migración]
            F --> G[Reglas]
        end
        
        G -. "Transforma" .-> F
        F -. "Respuesta JSON" .-> C
    end
```
## Flujo Motor legado
---

```mermaid

flowchart TD
    Inicio[MigracionController] --> Service[ServicioMigracion]
    Service --> Split[Dividir codigo legado por líneas]
    
    Split --> LoopLineas[Iterar línea por línea]
    LoopLineas --> LoopReglas[Iterar lista de Reglas Inyectadas]
    
    LoopReglas --> Evaluar{regla.coincide?}
    
    Evaluar -- "Sí" --> Transformar[regla.transformar]
    Transformar --> Guardar[Agregar línea a resultado Java]
    
    Evaluar -- "No" --> SiguienteRegla[Probar siguiente Regla]
    SiguienteRegla --> LoopReglas
    
    Guardar --> LoopLineas
    LoopLineas -- "Fin de lineas del código" --> Fin[Retornar código final]
```
