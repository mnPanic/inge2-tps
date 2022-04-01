# Respuestas Taller #1 - Análisis de Dataflow

Grupo #6

| Nombre            | Mail                    | LU     |
| ----------------- | ----------------------- | ------ |
| Manuel Panichelli | panicmanu@gmail.com     | 72/18  |
| Elias Cerdeira    | eliascerdeira@gmail.com | 692/12 |

## Ejercicio 1 - Reaching Definitions

a. La línea `a = c - a;` usa las definiciones

  - `c = 1` de la línea 10.
  - `c = c + 2` de la línea 12.
  - `a = 8` de la línea 5.
  - `a = 5` de la línea 8.

b. La línea `return a;` usa la definición `a = c - a` de la línea 14.

Output de Soot:

```jimple
/*14*/
/*c#3 has reaching def: c#3 = 1*/
/*c#3 has reaching def: c#3 = c#3 + 2*/
/*a has reaching def: a = 8*/
/*a has reaching def: a = 5*/

return a#8;
/*15*/
/*a#8 has reaching def: a#8 = c#3 - a*/
```

## Ejercicio 2 - Live Variables

| Sentencia    |  `a`  |  `b`  |  `c`  |  `d`  |  `r`  |
| ------------ | :---: | :---: | :---: | :---: | :---: |
| `d = a - b;` |  SI   |  SI   |  SI   |  SI   |  NO   |
| `r = c;`     |  NO   |  NO   |  NO   |  NO   |  SI   |
| `r = d;`     |  NO   |  NO   |  NO   |  NO   |  SI   |
| `return r;`  |  NO   |  NO   |  NO   |  NO   |  NO   |

Output de Soot:

```jimple
d = a - b;
/*6*/
/*Live Variable: c*/
/*Live Variable: d*/
/*Live Variable: a*/
/*Live Variable: b*/

...

r = c;
/*9*/
/*Live Variable: r*/

...

r = d;
/*11*/
/*Live Variable: r*/

...

return r;
/*13*/
```