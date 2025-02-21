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
    struct sockaddr_in cad;     /* Struct to store the address of the client socket */
    int sd, sd2;                /* Socket descriptors */
    int port;                   /* Port number */
    socklen_t alen;             /* Length of the client transport address */
    char you = 'x';             /* Simbolo che indica l'avversario */
    char opp = 'o';             /* Simbolo che indica l'utente */
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

    /* Bind a transport address (sad) to the socket (sd) */
    if(bind(sd, (struct sockaddr*) &sad, sizeof(sad)) < 0){
        fprintf(stderr, "bind failed\n");
        exit(1);
    }

    /* Makes the socket (sd) listen for incoming clients */
    if(listen(sd, QLEN) < 0){
        fprintf(stderr, "listen failed\n");
        exit(1);
    }

    alen = sizeof(cad);

    make_matrix(M);
    update_matrix(M, you);

    /* Gets a connection from the pending queue and creates a new socket */
    if((sd2 = accept(sd, (struct sockaddr *)&cad, &alen)) < 0){
        fprintf(stderr, "accept failed\n");
        exit(1);
    }

    while(1){
    	printf("TTurno del client:\n"); 
        read(sd2, &row, sizeof(int));
        read(sd2, &columns, sizeof(int));
        M[row][columns] = opp;
        if(check_win(opp, row, columns, M)) {
            loose = 1;
            break;
        } if(check_full(M)){
            end = 1;
            break;
        }

        while(1){
            update_matrix(M, you);
            printf("\nInserisci le coordinate della tua mossa (row columns): ");
            scanf("%d %d", &row, &columns);
            row--; columns--;
            check = check_position(row, columns, M);
            switch(check){
                case 0:
                    if(check_full(M)) end = 1;
                    break;
                case 1:
                    M[row][columns] = you;
                    write(sd2, &row, sizeof(int));
                    write(sd2, &columns, sizeof(int));
                    if(check_win(you, row, columns, M)) win = 1;
                    update_matrix(M, you);
            }
            if(check) break;
            if(end) break;
            if(win) break;
        }
        if(end) break;
        if(win) break;
    }
    
    update_matrix(M, you);
    if(win) printf("\n\nHAI VINTO\n");
    if(loose) printf("\n\nHAI PERSO\n");
    if(end) printf("\n\nPAREGGIO\n");
    close(sd2);
    close(sd);
}
