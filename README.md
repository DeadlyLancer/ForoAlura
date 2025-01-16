Api creada con el proposito de completar el challenge de Oracle One

### Gestión de Tópicos
Un tópico representa un tema o discusión en el foro. Cada tópico contiene los siguientes campos:
- **ID**: Identificador único del tópico.
- **Título**: Nombre del tema.
- **Mensaje**: Descripción o contenido principal.
- **Fecha de Creación**: Fecha en la que se creó el tópico.
- **Estado**: Indica si el tópico está abierto o cerrado.
- **Autor**: Usuario que creó el tópico.
- **Curso**: Categoría o área a la que pertenece el tópico.

### Operaciones Disponibles
1. **Crear un tópico**: Permite registrar un nuevo tópico.
2. **Obtener detalles de un tópico**: Devuelve la información completa de un tópico.
3. **Modificar un tópico**: Permite actualizar los datos de un tópico existente.
4. **Eliminar un tópico**: Elimina un tópico específico.
5. **Listar tópicos**: Devuelve un listado de todos los tópicos disponibles.

### Autenticación con JWT
La API utiliza JSON Web Tokens (JWT) para autenticar y autorizar a los usuarios. Las credenciales de acceso deben ser enviadas en el encabezado de las solicitudes protegidas.
