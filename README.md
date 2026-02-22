```mermaid
graph TD
    user[👤 Usuario] -->|Accede vía HTTPS| browser[🌐 Navegador Web]

    subgraph "Railway Platform (Single Deployment)"
        subgraph "Spring Boot Artifact (.jar)"
            angular[Front-End: Angular <br/> (Zoneless + Signals)]
            springboot[Back-End: Spring Boot <br/> (Java 17)]
            
            browser -->|Carga aplicación| angular
            angular -->|Envía código COBOL (REST)| springboot

            subgraph "Motor de Migración (Core)"
                controller[API Controller]
                service[Servicio de Migración]
                rules[Reglas (Patrón Strategy)]

                springboot --> controller
                controller -->|Delega proceso| service
                service -->|Itera y aplica| rules
                rules -->|Transforma a Java| service
            end

            service -->|Devuelve resultado| controller
            controller -->|Respuesta JSON| angular
        end
    end

    angular -->|Muestra código Java| browser

    style user fill:#f9f,stroke:#333,stroke-width:2px
    style browser fill:#e1f5fe,stroke:#0288d1,stroke-width:2px
    style angular fill:#dd0031,stroke:#333,stroke-width:2px,color:white,stroke-dasharray: 5 5
    style springboot fill:#6db33f,stroke:#333,stroke-width:2px,color:white
    style "Railway Platform (Single Deployment)" fill:#f0f0f0,stroke:#666,stroke-width:2px,stroke-dasharray: 5 5
    style "Motor de Migración (Core)" fill:#e8eaf6,stroke:#3f51b5,stroke-width:1px
