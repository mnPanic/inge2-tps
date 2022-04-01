Instrucciones para correr el Taller #1
======================================

Configuración del entorno
-------------------------

- Descargar java 1.8.0_202

    Bajar el .tar.gz

    https://www.oracle.com/ar/java/technologies/javase/javase8-archive-downloads.html#license-lightbox

    Agregar a ~/.bashrc

    export JRE=~/Descargas/jdk-8u202-linux-x64/jdk1.8.0_202/jre/lib/rt.jar
    export JAVA_HOME=~/Descargas/jdk-8u202-linux-x64/jdk1.8.0_202/
    export PATH=~/Descargas/jdk-8u202-linux-x64/jdk1.8.0_202/bin/:$PATH

- Descargar Soot

    https://repo1.maven.org/maven2/ca/mcgill/sable/soot/3.3.0/

    Bajar el archivo soot-3.3.0-jar-with-dependencies.jar

Ejercicio 1
-----------

1. Compilar el archivo fuente

    javac -d bin -g src/ReachingDefinitionsExample.java

2. Correr Soot

    java -classpath .:soot-3.3.0-jar-with-dependencies.jar:. soot.Main -cp bin:$JRE -f J ReachingDefinitionsExample -print-tags -p jap.rdtagger enabled:true -p jb use-original-names:true -p jb.cp off -keep-line-number

Ejercicio 2
-----------

1. Compilar el archivo fuente

    javac -d bin -g src/LiveVariablesExample.java

2. Correr Soot

    java -classpath .:soot-3.3.0-jar-with-dependencies.jar:. soot.Main -cp bin:$JRE -f J LiveVariablesExample -print-tags -p jap.lvtagger enabled:true -p jb use-original-names:true -p jb.cp off -keep-line-number