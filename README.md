# bltx
ANDROID E3-SR

## 1. Propón los pasos, clases, layouts y recursos que utilizarías para hacer un Stepper reutilizable y que cumpla con los parámetros definidos en la guía de material design:
https://material.io/guidelines/components/steppers.html . 
![alt text](https://material.io/archive/guidelines/assets/0B7WCemMG6e0VTVpuUmNUSUhRZTA/components-acux-stepper-altlblplcmntoptstp.png)


## 2. Crea un shake action en android y pon el código.


## 3. Explica cómo organizas en base a tu experiencia un proyecto en Android utilizando MVP e implementando Clean Architecture, menciona los paquetes que utilizarías y la distribución de módulos.
**APP**

![alt text](https://github.com/glarab/bltx/blob/master/Captura%20de%20Pantalla%202020-01-04%20a%20la(s)%2018.18.59.png?raw=true)

Es la capa que interactúa con la interfaz de usuario e interactua con el DOMAI.:
*Paquetes*:	

 - **broadcast**: Broadcast receivers.
 -  **di**: Clases de la inyección  de dependencias del proyecto globales.
 - **mvp**: Paquete donde se implementara el patron **MVP**, yo recomiendo agrupar por modulos y funcionalidades.
 - **service**: Services, IntentServices,Jobs y variaciones.
 -  **util**: Paquete de clases utilidades de la aplicación, se recomienda implementar el patron **serviceLocator** para acceder a las utilidades.

**DOMAIN**

![alt text](https://github.com/glarab/bltx/blob/master/Captura%20de%20Pantalla%202020-01-04%20a%20la(s)%2018.21.40.png?raw=true)

Modulo de negocio,  en esta clase se declara y se implementara los caso de uso del negocio.
*Paquetes*

 - **UseCase**: Casos de uso 
 - **UseCaseImp**: Implementación de los casos de uso.

**DATA**

![alt text](https://github.com/glarab/bltx/blob/master/Captura%20de%20Pantalla%202020-01-04%20a%20la(s)%2018.19.32.png?raw=true)

En esta capa se encuentra una definición de las diferentes fuentes de datos, y la forma en que se debe utilizar.
Recomiendo implementar  el patron **repositorio** que, para una determinada solicitud, es capaz de decidir dónde encontrar la información.
*Paquetes*

 - **repository**: Implementa el patron repository.
 - **store**: Clases, configuraciones e implementaciones de los repositorios de datos. Ejm: Preferencias, serviciosWeb, base de datos.



## 4. Diseña un custom view de una brújula utilizando canvas y pon el código que utilizarías en esta sección.
