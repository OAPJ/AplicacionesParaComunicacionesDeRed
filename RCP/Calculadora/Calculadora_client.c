/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "Calculadora.h"
#include <stdlib.h>

void
calculadora_prog_1(char *host, int op, int a, int b)
{
	CLIENT *clnt;
	double  *result_1;
	intpair  calculadora_1_arg;

#ifndef	DEBUG
	clnt = clnt_create (host, Calculadora_PROG, Calculadora_VERS, "udp");
	if (clnt == NULL) {
		clnt_pcreateerror (host);
		exit (1);
	}
#endif	/* DEBUG */
	calculadora_1_arg.a = a;
	calculadora_1_arg.b = b;
	calculadora_1_arg.op = op;
	result_1 = calculadora_1(&calculadora_1_arg, clnt);
	if (result_1 == (double *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	printf("Resultado: %.2f\n", *result_1);
	printf("Ingrese un numero para continuar\n");
	int al;
	scanf("%d", &al);
#ifndef	DEBUG
	clnt_destroy (clnt);
#endif	 /* DEBUG */
}


int
main (int argc, char *argv[])
{
	char *host;
	int a=0, b=0, op=0;

	if (argc < 2) {
		printf ("usage: %s server_host\n", argv[0]);
		exit (1);
	}

		host = argv[1];

		while(1){
			system("clear");
			printf("Seleccione una opcion\n");
			printf("1) Suma\n");
			printf("2) Resta\n");
			printf("3) Multiplicacion\n");
			printf("4) Divicion\n");
			printf("5) Porcentaje\n");
			printf("6) Raiz cuadrada\n");
			printf("7) Elevar al cuadrado\n");
			printf("8) Reciproco\n");
			printf("9) Salir\n");
			scanf("%d", &op);

			if(op == 9){
				printf("Adios");
				exit(1);
			}

			printf("Num 1:\n");
			scanf("%d", &a);

			switch (op) {
				case 1:{// Suma
					printf("Num 2:\n");
					scanf("%d", &b);
					break;
				}
				case 2:{//Resta
					printf("Num 2:\n");
					scanf("%d", &b);
					break;
				}
				case 3:{//Multiplicacion
					printf("Num 2:\n");
					scanf("%d", &b);
					break;
				}
				case 4:{//Divicion
					printf("Num 2:\n");
					scanf("%d", &b);
					break;
				}
				case 5:{//Porcentaje
					printf("Num 2:\n");
					scanf("%d", &b);
					break;
				}
			}
			calculadora_prog_1 (host, op, a, b);
		}
exit (0);
}