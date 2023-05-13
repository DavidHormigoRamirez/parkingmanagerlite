# language: es
Característica: test de vistas


  Escenario: Mostrar index
    Dado un usuario esta en la pagina de inicio
    Entonces se muestra el titulo de la pagina de inicio

  Escenario: Mostrar usuarios
    Dado un usuario esta en la pagina de usuarios
    Entonces se muestra el titulo de la pagina de usuarios
    Y se muestra el boton de crear usuario

  Escenario: Mostrar sorteos
    Dado un usuario esta en la pagina de sorteos
    Entonces se muestra el titulo de la pagina de sorteos
    Y se muestra el boton de crear sorteo

  Escenario: Mostrar formulario de usuarios
    Dado un usuario esta en el formulario de usuarios
    Entonces se muestra el titulo de formulario de usuarios
    Y se muestra el boton de usuario submit

  Escenario: Mostrar formulario de sorteos
    Dado un usuario esta en el formulario de sorteos
    Entonces se muestra el titulo de formulario de sorteos
    Y se muestra el boton de sorteo submit