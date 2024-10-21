# PruebaTecnicaHospital
Codigo fuente de la prueba tecnica

Hola mucho gusto a quien.

Para compilar la aplicación es necesario instalar las dependencias de Maven y exportar el proyecto dentro de cualquier IDE, en mi caso use Intelij y para ello es necesario importar el proyecto como un Proyecto Maven existente.
Cabe aclarar que para poder ejecutarlo es necesario tener los plugin de Spring boot mas reciente instalado dentro del propio IDE.
Una vez hecho esto, de forma automática se habilitara la opción para ejecutar la aplicación con Spring boot.

Antes de ejecutar la aplicación en la parte de properties es necesario configurar la conexión con la base de datos, en mi caso es una base de datos en la nube, por lo cual se puede acceder desde cualquier lugar, para ello incluiré las licencias necearías para que se puedan conectar o si lo prefieren también incluiré los querys usados para la creación de tablas.

Su configuración dentro del properties queda de la siguiente forma
spring.datasource.url=jdbc:oracle:thin:@pruebatecnicahospital_high?TNS_ADMIN=---Ubicación donde se extre el wallet----/Wallet_PruebaTecnicaHospital

Una vez configurado, se puede ejecutar la aplicación sin inconvenientes.

De ante mano gracias, disfrute mucho de este reto.
Saludos
