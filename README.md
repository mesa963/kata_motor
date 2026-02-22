graph TD
    user[Usuario] -->|Accede vía HTTPS| browser[Navegador Web]

    subgraph Railway[Railway Platform Single Deployment]
        angular[Front-End: Angular Zoneless + Signals]
        springboot[Back-End: Spring Boot Java 17]
        
        browser -->|Carga aplicación| angular
        angular -->|Envía código COBOL| springboot

        subgraph Motor[Motor de Migración Core]
            controller[API Controller]
            service[Servicio de Migración]
            rules[Reglas Patrón Strategy]

            springboot --> controller
            controller -->|Delega| service
            service -->|Aplica| rules
            rules -->|Transforma| service
        end

        service -->|Resultado| controller
        controller -->|Respuesta JSON| angular
    end

    style user fill:#f9f,stroke:#333,stroke-width:2px
    style browser fill:#e1f5fe,stroke:#0288d1,stroke-width:2px
    style angular fill:#dd0031,stroke:#333,stroke-width:2px,color:#fff
    style springboot fill:#6db33f,stroke:#333,stroke-width:2px,color:#fff
    style Railway fill:#f0f0f0,stroke:#666,stroke-width:2px,stroke-dasharray: 5 5
    style Motor fill:#e8eaf6,stroke:#3f51b5,stroke-width:1px
