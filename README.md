## Practica sobre Refactorizacion del Master Cloud Apps 2020 de la URJC
## Autor: Cristofer López Cabañas

##### 1. Aplicado el patron Null Object en el enumerado Color
    + Valor null añadido en el enuerado Color
    + Modificado metodo getOppositeColor en la clase Turno
    + Modificado metodo reset en la clase Game para comprobar que el color sea destinto de Color.NULL
    + Cambiado assert de la clase Pieza para comprobar que el color sea distinto de Color.NULL

##### 2. Clase Coordenada genérica para la reutilización y el principio open/close
    + Interfaz Coordenada en el paquete Utils con el patron Null object
    + Clase Coordenada en el paquete utils que implementa la interfaz con los atributos de fila y columna
    + La clase Coordenada del modelo ahora extiende de esta nueva clase

##### 3. Chain of responsibility pattern in move algorithm
    + Creada clase abstracta Middleware con los metodos de chek, checkNext, linkWith
    + Creada clase MiddlewareCorrectPairMove que comprueba si es posible realizar un movimiento en un tablero y unas coordenadas
    + Creada clase MiddlewareCorrectGlobalMove que comprueba si los saltos dados en un movimiento son proporcionales a las piezas que se han comido
    + Refactorizada la clase Game con un atributo Middleware que tiene la cadena de responsabilidad para comprobar un movimiento
    + Refactorizacion del metodo move de la clase game que llama a la cadena de responsabilidad para que compruebe si es posible realizar un movimiento, y si es posible, actualiza el board
    + NOTA: Test de las nuevas clases no realizados
    + NOTA: Cadena de responsabilidad instanciada en el constructor de la clase Game (Podría encapsularse en otra clase)
    + NOTA: No se ha creado ninguna factoria ni builder para la cadena de responsabilidad

##### 4. Fusión de las vistas de Start, Play, Resume en la clase View y modificados los test
    + Cambiado metodo interact de StartView a la clase View con los atributos de la clase
    + Cambiado metodo interact de Resume a la clase View con los atributos de la clase
    + Modificados test de la clase ResumeView para que usen una instancia de la clase View
    + Cambiado metodo interact de PlayView a la clase View con los atributos
    + Modificados test de la clase PlayView para que usen una instancia de la clase View

##### 5. Refactor MVC.pm to MVC.pv
    + Añadido el metodo abstracto control en la clase abstracta InteractorController
    + Modificada la clase principal para que use el metodo control de los controladores en lugar de llamar a las vistas (La clase principal ahora no conoce las vistas)
    + La interfaz InteractorControllerVisitorDesaparece y en la clase View desaparecen los metodos de visit ademas del metodo que llama a los controller con el metodo accept
    + En la clase abstracta InteractorController el metodo accept desaparece y en los controller tambien
    + En StartController, el metodo control pasa a comunicar las vistas con el modelo
        - Pinta el estado inicial y pasa al estado IN_GAME
    + En ResumeController ahora se pregunta al usuario si quiere seguir jugando y actualiza el estado dependiendo de la respuesta
            - Añadidos los test en la clase ResumeControllerTest y eliminado el metodo interact de la clase View
    + En PlayController ahora se lleva el control de los movimientos realizados por los usuarios
            - Primero se pregunta al usuario que introduzca el movimiento
            - A contrinuacion se intenta realizar la accion
            - Finalmente se escribe el estado actual
            - Añadidos los test en la clase PlayControllerTest mockeando la clase View y eliminado el metodo interact de la clase View
            