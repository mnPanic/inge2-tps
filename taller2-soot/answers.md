# Taller 2 - Implementando An ́alisis de Dataflow

## Parte 1 - Definiendo el Zero Analysis

### Ejercicio 1 - Asignación de constante

|    n    |    OUT[n](x)    |
| :-----: | :-------------: |
| `x = 0` |   `<x, ZERO>`   |
| `x = k` | `<x, NOT_ZERO>` |

Donde `K ∈ Z − {0}` 
throw new UnsupportedOperationException();
### Ejercicio 2 - Asignación de variable


| IN[n](y) | OUT[n](x) |
| :------: | :-------: |
|   `⊥`    |    `⊥`    |
|   `Z`    |    `Z`    |
|   `NZ`   |   `NZ`    |
|   `MZ`   |   `MZ`    |

### Ejercicio 3 - Suma

| IN[n](y) | IN[n](z) | OUT[n](x) |
| :------: | :------: | :-------: |
|   `⊥`    |   `⊥`    |    `⊥`    |
|   `Z`    |   `⊥`    |    `⊥`    |
|   `NZ`   |   `⊥`    |    `⊥`    |
|   `MZ`   |   `⊥`    |    `⊥`    |
|   `⊥`    |   `Z`    |    `⊥`    |
|   `Z`    |   `Z`    |    `Z`    |
|   `NZ`   |   `Z`    |   `NZ`    |
|   `MZ`   |   `Z`    |   `MZ`    |
|   `⊥`    |   `NZ`   |    `⊥`    |
|   `Z`    |   `NZ`   |   `NZ`    |
|   `NZ`   |   `NZ`   |   `MZ`    | <!-- Puede ser una suma con el inverso aditivo --> |
|   `MZ`   |   `NZ`   |   `MZ`    |
|   `⊥`    |   `MZ`   |    `⊥`    |
|   `Z`    |   `MZ`   |   `MZ`    |
|   `NZ`   |   `MZ`   |   `MZ`    |
|   `MZ`   |   `MZ`   |   `MZ`    |

### Ejercicio 4 - Resta

| IN[n](y) | IN[n](z) | OUT[n](x) |
| :------: | :------: | :-------: |
|   `⊥`    |   `⊥`    |    `⊥`    |
|   `Z`    |   `⊥`    |    `⊥`    |
|   `NZ`   |   `⊥`    |    `⊥`    |
|   `MZ`   |   `⊥`    |    `⊥`    |
|   `⊥`    |   `Z`    |    `⊥`    |
|   `Z`    |   `Z`    |    `Z`    |
|   `NZ`   |   `Z`    |   `NZ`    |
|   `MZ`   |   `Z`    |   `MZ`    |
|   `⊥`    |   `NZ`   |    `⊥`    |
|   `Z`    |   `NZ`   |   `NZ`    |
|   `NZ`   |   `NZ`   |   `MZ`    |
|   `MZ`   |   `NZ`   |   `MZ`    |
|   `⊥`    |   `MZ`   |    `⊥`    |
|   `Z`    |   `MZ`   |   `MZ`    |
|   `NZ`   |   `MZ`   |   `MZ`    |
|   `MZ`   |   `MZ`   |   `MZ`    |

### Ejercicio 5 - Multiplicación

| IN[n](y) | IN[n](z) | OUT[n](x) |
| :------: | :------: | :-------: |
|   `⊥`    |   `⊥`    |    `⊥`    |
|   `Z`    |   `⊥`    |    `⊥`    |
|   `NZ`   |   `⊥`    |    `⊥`    |
|   `MZ`   |   `⊥`    |    `⊥`    |
|   `⊥`    |   `Z`    |    `⊥`    |
|   `Z`    |   `Z`    |    `Z`    |
|   `NZ`   |   `Z`    |    `Z`    |
|   `MZ`   |   `Z`    |    `Z`    |
|   `⊥`    |   `NZ`   |    `⊥`    |
|   `Z`    |   `NZ`   |    `Z`    |
|   `NZ`   |   `NZ`   |   `NZ`    |
|   `MZ`   |   `NZ`   |   `MZ`    |
|   `⊥`    |   `MZ`   |    `⊥`    |
|   `Z`    |   `MZ`   |    `Z`    |
|   `NZ`   |   `MZ`   |   `MZ`    |
|   `MZ`   |   `MZ`   |   `MZ`    |

### Ejercicio 6 - División

| IN[n](y) | IN[n](z) | OUT[n](x) |
| :------: | :------: | :-------: |
|   `⊥`    |   `⊥`    |    `⊥`    |
|   `Z`    |   `⊥`    |    `⊥`    |
|   `NZ`   |   `⊥`    |    `⊥`    |
|   `MZ`   |   `⊥`    |    `⊥`    |
|   `⊥`    |   `Z`    |    `⊥`    |
|   `Z`    |   `Z`    |    `⊥`    |
|   `NZ`   |   `Z`    |    `⊥`    |
|   `MZ`   |   `Z`    |    `⊥`    |
|   `⊥`    |   `NZ`   |    `⊥`    |
|   `Z`    |   `NZ`   |    `Z`    |
|   `NZ`   |   `NZ`   |   `NZ`    |
|   `MZ`   |   `NZ`   |   `MZ`    |
|   `⊥`    |   `MZ`   |    `⊥`    |
|   `Z`    |   `MZ`   |    `Z`    |
|   `NZ`   |   `MZ`   |   `NZ`    |
|   `MZ`   |   `MZ`   |   `MZ`    |

**Nota al pie:** deseamos ser optimistas y propagar el valor que se obtendría en caso de que MZ no haya sido Z.

### Ejercicio 7

```java
public int productoria(int y) {
    int x = y;
    y = 1;
    
    while (x != 1) {
        y = x ∗ y;
        x = x − 2;
    }

    return y;
}
```

|   n   | IN[n](x) | IN[n](y) | OUT[n](x) | OUT[n](y) |
| :---: | :------: | :------: | :-------: | :-------: |
|   1   |   `⊥`    |   `MZ`   |    `⊥`    |   `MZ`    |
|   2   |   `⊥`    |   `MZ`   |   `MZ`    |   `MZ`    |
|   3   |   `MZ`   |   `MZ`   |   `MZ`    |   `NZ`    |
|   4   |   `MZ`   |   `MZ`   |   `MZ`    |   `MZ`    |
|   5   |   `MZ`   |   `MZ`   |   `MZ`    |   `MZ`    |
|   6   |   `MZ`   |   `MZ`   |   `MZ`    |   `MZ`    |
|   7   |   `MZ`   |   `NZ`   |     -     |     -     |

## Parte 2 - Implementando el Zero Analysis en SOOT

Merge (supremo)
| $\sqcup$ | `⊥` | Z   | NZ  | MZ  |
| -------- | --- | --- | --- | --- |
| `⊥`      | `⊥` | Z   | NZ  | MZ  |
| Z        | Z   | Z   | MZ  | MZ  |
| NZ       | NZ  | MZ  | NZ  | MZ  |
| MZ       | MZ  | MZ  | MZ  | MZ  |

Union (infimo)
| $\sqcap$ | `⊥` | Z   | NZ  | MZ  |
| -------- | --- | --- | --- | --- |
| `⊥`      | `⊥` | `⊥` | `⊥` | `⊥` |
| Z        | `⊥` | Z   | `⊥` | Z   |
| NZ       | `⊥` | `⊥` | NZ  | NZ  |
| MZ       | `⊥` | Z   | NZ  | MZ  |

java -jar zero-analysis/target/zero-analysis-1.0-SNAPSHOT-jar-with-dependencies.jar -cp .:./examples/:$JRE -f J ZeroAnalysisTest1 -v -print-tags -p jtp.DivisionByZeroAnalysis on
java -jar zero-analysis/target/zero-analysis-1.0-SNAPSHOT-jar-with-dependencies.jar -cp .:./examples/:$JRE -f J ZeroAnalysisTest2 -v -print-tags -p jtp.DivisionByZeroAnalysis on
java -jar zero-analysis/target/zero-analysis-1.0-SNAPSHOT-jar-with-dependencies.jar -cp .:./examples/:$JRE -f J ZeroAnalysisTest3 -v -print-tags -p jtp.DivisionByZeroAnalysis on
java -jar zero-analysis/target/zero-analysis-1.0-SNAPSHOT-jar-with-dependencies.jar -cp .:./examples/:$JRE -f J ZeroAnalysisTest4 -v -print-tags -p jtp.DivisionByZeroAnalysis on
java -jar zero-analysis/target/zero-analysis-1.0-SNAPSHOT-jar-with-dependencies.jar -cp .:./examples/:$JRE -f J ZeroAnalysisTest5 -v -print-tags -p jtp.DivisionByZeroAnalysis on
java -jar zero-analysis/target/zero-analysis-1.0-SNAPSHOT-jar-with-dependencies.jar -cp .:./examples/:$JRE -f J ZeroAnalysisTest6 -v -print-tags -p jtp.DivisionByZeroAnalysis on
java -jar zero-analysis/target/zero-analysis-1.0-SNAPSHOT-jar-with-dependencies.jar -cp .:./examples/:$JRE -f J ZeroAnalysisTest7 -v -print-tags -p jtp.DivisionByZeroAnalysis on

Lo que no está andando es que por alguna razón se saltea instrucciones, por ej. en el test2 se saltea la línea 3 y en el test4 se saltea la línea 2.