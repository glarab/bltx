# bltx
ANDROID E3-SR

## 1. Propón los pasos, clases, layouts y recursos que utilizarías para hacer un Stepper reutilizable y que cumpla con los parámetros definidos en la guía de material design:
~~https://material.io/guidelines/components/steppers.html~~ 
[https://material.io/archive/guidelines/components/steppers.html#](https://material.io/archive/guidelines/components/steppers.html#)

![alt text](https://material.io/archive/guidelines/assets/0B7WCemMG6e0VaXlOV1dLTk11dnc/components-acux-stepper-nonlinear.png)
**ALCANCE**:
Se desarrollara un stepper horizontal igual al de la imagen, el cual tendra interacción con un **Viewpager**.

Pasos para implementar:

 1. Se implementar  un customview el cual heredara de la clase View, en el customview dibujaremos   un recyclerView horizontal y un ViewPager(Opcional).
 2. Se implementara el adapter AdapterStepper.kl, y se maqueta el item_step.xml como se muestra en la imagen:
![alt text](https://github.com/glarab/bltx/blob/master/Captura%20de%20Pantalla%202020-01-05%20a%20la(s)%2000.50.38.png?raw=true)
En el adapter contemplaremos los estados, cambios en la vista para cumplir con los otros estados. Para el item de la primera posición se ocultara la linea inicial y para el item de la ultima posición se ocultara la linea final.

3. Se implementa el bean Step, sus atributos seran:
	nameStep
	state
	fragment
	isSelected

4. Cada ves que se intente avanzar o retroceder se enviara se invocara un listener cuyo atributo será un collback que invocara a un método do para ejecutar o cancelar la acción.
5. Se  invocaran interfaces listener cada ves que se cambie de pagina, se cancele el paso, se invoque la ultima pagina, etc.

***Clases:***
MyStepperView.kl
IMyStepperView.kl
AdapterStepper.kl
Step.kl
StepperListener.kl
AdapterPages.kl
ValidateStep.kl


***layouts:***
item_step.xml

***drawable:***
ic_check.xml
circle.xml

***values:***
attrs_my_stepper_view.xml


## 2. Crea un shake action en android y pon el código.

**Ver proyecto ShakeAction**

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
**Ver proyecto MyCompass**
