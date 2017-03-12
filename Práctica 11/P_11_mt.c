#include <stdio.h>
#include <stdlib.h>
#define NStates 20

/*
    ----Francisco Gil Amorós------
    ----Cell:-----
    state: 1 // q={q0,q1,q2,q3..qn}
    value: 0 // 0,1,B,R,L

    cell cA = {1,'0'} // q1,0
    cell cB = {1,'R'} // q1,R

    --Table:--
    ___________
      cA | cB

*/

struct cell{
    int state;
    char value;
}typedef cell;

char* compute(cell table[NStates][2], char* tape);
char* fInput();
void readOutput(char* tape, int state, char value);

int main(){
    // Tablas de estados (función paridad)
    cell table[NStates][2]={ { {0,'B'},{1,'R'} },
                             { {1,'1'},{1,'0'} },
                             { {1,'0'},{2,'R'} },
                             { {2,'1'},{2,'0'} },
                             { {2,'0'},{1,'R'} },
                             { {1,'B'},{3,'L'} },
                             { {3,'0'},{3,'L'} },
                             { {3,'B'},{4,'R'} },
                             { {4,'0'},{5,'1'} },
                             { {5,'1'},{5,'R'} },
                             { {5,'0'},{5,'B'} },
                           };

    char* tape = fInput();

    tape = compute(table,tape);

    return 0;
}

char* fInput(){
    char *tape;
    tape = (char*)malloc(sizeof(char));
    tape[0]='B';

    char *input;
    input = (char*)malloc(sizeof(char));

    printf("\n[*] Input (unary): ");

    int i=0;
    while (input[i-1] != '\r' && input[i-1] != '\n') {
        input[i] = getchar();
        i++;
    }
    int j;
    for(j=1;j<i;++j){
        tape[j]=input[j-1];
    }
    tape[j]='B';
    return tape;
}

void readOutput(char* tape, int state, char value){
    int i=1;
    printf("\n[*] State: %d \t",state);
    printf("Value: %c \t",value);
    printf("[*] New Tape: %c",tape[0]);
    while(tape[i]!='B'){
        printf("%c",tape[i]);
        i++;
    }
    printf("%c",tape[i]);
}

char* compute(cell table[NStates][2], char* tape){
    int state=0;
    int p=0;
    int i=0;
    int z;

    do{
        cell cA = table[i][0];
        cell cB = table[i][1];

        if(state == cA.state && tape[p] == cA.value){
            state = cB.state;

            switch(cB.value){
                case '0':
                    tape[p]='0';
                    break;
                case '1':
                    tape[p]='1';
                    break;
                case 'B':
                    tape[p]='B';
                    break;
                case 'R':
                    p++;
                    break;
                case 'L':
                    if(p > 0) p--;
                    break;
            }

            readOutput(tape,cA.state,cA.value);

            i=0;
        }
        else{
            i++;
        }


    }while(i<NStates);

    return tape;
}

