struct intpair {
  int a;
  int b;
  int op;
};

program Calculadora_PROG {
  version Calculadora_VERS {
    double Calculadora(intpair) = 1;
  } = 1;
} = 0x23451111;
