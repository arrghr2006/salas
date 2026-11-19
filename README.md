**SISTEMA PARA GESTION DE RESERVAS DE SALAS DE REUNION**

**Tecnologias utilizadas**

- Spring Boot 4.1.2

- PostgreSQL 17

La aplicación está configurada para levantar con el perfil **qa** y utiliza el puerto 8088.

La api esta documentada usando swagger, **apiDoc url**: http://localhost:8088/swagger-ui/index.html.

Las dos rutas fundamentales son:
- http://localhost:8088/salas
- http://localhost:8088/reservas

La base de datos se debe llamar **meetroombooking**, se puede crear vacia y al ejecutar el proyecto se importan los datos, en el perfil qa hacia una base de datos h2 y en el perfil prod hacia postgreSql.

En el controlador MeetRoomBooking necesite utilizar dos métodos Post, el típico de la creación y otro para el listado. El problema estaba en que el del listado recibe como filtros el nombre de la sala y la fecha, en ambos casos los valores pueden contener espacios en blanco y se me hacia mas pesado trabajarlo con GET.

Todas las rutas que utilicen Post o Delete estan protegidas con autenticación, excepto el listado de reservas, todo esta contemplado en el paquete de security la clase SecurityConfig.

La aplicacion posee 15 test de integracion para el controlador de las reservas, los test no se implementaron con los datos importados por los problemas de las secuencias.

En el dia de hoy (19 nov) me tome el tiempo de configurar la IA de Jetbrain con el agente Junie y eso me cambio la vida:
- Encontro la razon del porque el ambiente de test no se estaba levantando, basicamente era por ponerle en el pom que el driver H2 se usara solo en test, se actualizao a runtime y comenzó a funcionar OK.
- Encontro los problemas para el uso de Jwt, los corrigio y me configuro todo para que funcionara OK. Basicamente era por estar retornando en el metodo UserDetailsServiceImpl.loadUserByUsername() el tipo de usuario por defecto de la seguridad de Spring y no elque estaba utilizando en la aplicacion, eso generaba una exceptio de TypeCast.
- Me fue de tremenda ayuda para los test de integracion aunque se confundió un poco con el getAll de reservas y el List de las reservas, ahí intervine para implementar un caso en que no funcionara por usar un formato de fecha incorrecto.

Disculpeme el readme, quizas poco profesional, pero de verdad no quiero dilatar mas esto innecesariamente y se que estoy atrasado.

Con un poco mas de tiempo usaría base de datos con migraciones como lo pidio, pero realmente no lo había hecho antes y me llevaría aun un poco más de tiempo. El negocio se puede mejorar poniendo un estado a los reservas para no perder las solicitudes, ademas de gestionar los clientes y asociarlos a las mismas. El sistema se presta para montarlo en una arquitectura de microservicios, separando la gestion de las salas y las de las reservas como servicios separados.