#include "matrice.h"
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define PORT   5193 /* Default port number */
#define QLEN        6    /* Size of connection requests queue   */

int main(int argc, char * argv[]){
    struct sockaddr_in sad;     /* Struct to store the address of the server socket */
    int sd;                /* Socket descriptors */
    int port;                   /* Port number */
    char you = 'x';             /* Simbolo che indica l'utente */
    char opp = 'o';             /* Simbolo che indica l'avversario */
    int win = 0, end = 0, loose = 0, check;      /* Variabili fondamentali per il gioco */
    char M[R][C];               /* Dichiarazione della matrice */
    int row, columns;

    /* Create a socket for stream oriented communication */
    if((sd = socket(PF_INET, SOCK_STREAM, 0)) < 0){
        fprintf(stderr, "socket creation failed\n");
        exit(1);
    }

    /* Cleans the memory area that will store the transport address of the server */
    memset(&sad, 0, sizeof(sad));

    /* Set the transport address of the server */
    sad.sin_family = AF_INET;
    sad.sin_addr.s_addr = htonl(INADDR_ANY);
    port = PORT;
    sad.sin_port = htons((u_short) port);

    if(connect(sd, (struct sockaddr *) &sad, sizeof(sad))){
        fprintf(stderr,"connect failed\n");
        exit(1);
    }

    make_matrix(M);  //andiamo a generare la griglia richiamando una funzione dell'header grid
    while(1){
        while(1){
            update_matrix(M, you);
            printf("\nInsert the coordinates for your move (row columns): "); //richiediamo all'utente di inerire le coordinate nel punto in cui vuole posizionare il tuo simbolo
            scanf("%d %d", &row, &columns);
            row--; columns--; //andiamo a diminuire di uno sia le righe che le colonne in quanto per il riferimento allo slot della matrice necessitiamo di un valore in meno
            check = check_position(row, columns, M);
            switch(check){
                case 0:
                    if(check_full(M)) end = 1;
                    break;
                case 1:
                    M[row][columns] = you;
                    write(sd, &row, sizeof(int));
                    write(sd, &columns, sizeof(int));
                    if(check_win(you, row, columns, M)) win = 1;
                    update_matrix(M, you);
            }
            if(check) break;
            if(end) break; //andiamo a bloccare l'esecuzione del ciclo nel caso in cui ci sia un valore che sia vittoria sconfitta
            if(win) break;
        }
        if(end) break;
        if(win) break;

	printf("Turno del server:\n");
        read(sd, &row, sizeof(int));
        read(sd, &columns, sizeof(int));
        M[row][columns] = opp;
        if(check_win(opp, row, columns, M)) { //controlliamo se abbiamo vinto
            loose = 1;
            break;
        } if(check_full(M)){ //controlliamo se tutti gli slot della matrice sono pieni
            end = 1;
            break;
        }

    }
    
    update_matrix(M, you);
    if(win) printf("\n\nHAI VINTO\n");
    if(loose) printf("\n\nHAI PERSO\n");
    if(end) printf("\n\nPAREGGIO\n");
    close(sd);
}

