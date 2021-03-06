/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "Calculadora.h"
#include <math.h>

double *
calculadora_1_svc(intpair *argp, struct svc_req *rqstp)
{
	static double  result;
//	printf("%d\n", argp->op);
	if(argp->op == 1){
		//1 Suma
		result = argp->a + argp->b;
	}
	else if(argp->op == 2){
		//2 Resta
		result = argp->a - argp->b;
	}
	else if(argp->op == 3){
		//3 Multiplicacion
		result = argp->a * argp->b;
	}
	else if(argp->op == 4){
		//4 Divicon
		result = argp->a / argp->b;
	}
	else if(argp->op == 5){
		//5 Porcentaje
		result = (argp->b * 100) / argp->a;
	}
	else if(argp->op == 6){
		//6 Raiz cuadrada
		result = sqrt((int)argp->a);
	}
	else if(argp->op == 7){
		//7 Elevar al cuadrado
		result = argp->a * argp->a;
	}
	else{
		//8 Reciproco
		result = (double)(1.0 / argp->a);
	}

	printf("Resultado: %lf\n", result);

	return &result;
}
