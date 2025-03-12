1. **Gestión Ventas**  
   "Gestión Ventas" es una aplicación de escritorio desarrollada en Java con Swing para gestionar productos, ventas y usuarios.
   Incluye funcionalidades CRUD, generación de informes en PDF/HTML y un sistema de ayuda integrado con JavaHelp. Diseñada con el patrón MVC y
   capas DAO, Service, view, controller y DTO, es ideal para aprender sobre interfaces gráficas. La aplicación está pensada para ser versátil y
   adaptable a distintos entornos de desarrollo, desde principiantes hasta desarrolladores avanzados.

3. **Funcionalidades**  
   - Administra productos, ventas y usuarios con facilidad.  
   - Genera reportes en PDF/HTML con gráficos, utilizando JasperReport para una visualización profesional.  
   - Sistema de ayuda accesible con F1 o desde el menú, ideal para usuarios nuevos.  
   - Interfaz moderna con botones redondeados y navegación intuitiva, optimizada para una experiencia de usuario fluida.  
   - Permite la gestión de datos persistentes con SQLite, incluyendo una base de datos integrada y otra externa para exportación.

4. **Tecnologías**  
   - Lenguaje: Java (Swing para GUI)  
   - Patrones: MVC, DAO, Service, DTO  
   - Informes: PDF/HTML con gráficos generados mediante JasperReport  
   - Base de datos: SQLite (incluye una base de datos interna y las carpetas externas informes y bdd son debido a una exportacion para la creacion del exe pero no son necesarias)  
   - Ayuda: JavaHelp  
   - Entornos compatibles: Funciona en IDEs como Eclipse, IntelliJ IDEA y NetBeans, con soporte para configuraciones personalizadas.

5. **Requisitos Previos**  
   - Java Development Kit (JDK) 8 o superior instalado.  
   - Un IDE compatible (Eclipse, IntelliJ IDEA, NetBeans, o similar).  
   - Dependencias: Asegúrate de tener JasperReport y el driver de SQLite incluidos en el proyecto (se incluyen en el repositorio).  
   - Git para clonar el repositorio.  
   - Sistema operativo: Compatible con Windows, macOS y Linux.

6. **Instalación**  
   - Clona el repositorio:  
     ```bash
     git clone https://github.com/jettsus/GestionVentas.git
