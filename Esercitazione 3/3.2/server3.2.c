/*
 * serverStream.c
 *
 *  Modified on: October 24, 2023
 *      Author: zimeo
 */
/*#include <sys/types.h>*/
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define PROTOPORT   5193 /* Default port number */
#define QLEN        6    /* Size of connection requests queue   */

#define YOU 0
#define PEER 1
#define EXIT 2

#define MAX(x,y) (((x) > (y))? (x) : (y))


int main(int argc, char *argv[]) {
  struct  sockaddr_in sad; /* Struct to store the transport address of the server socket */
  struct  sockaddr_in cad; /* Struct to store the transport address of the client socket */
  int     sd, sd2;         /* Socket descriptors */
  int     port; 	   /* Port number */
  socklen_t     alen;      /* Length of the client transport address  */
  char    buf[1000];       /* Buffer for sending and receiving data */
  int     visits = 0;      /* Status variable to count the number of connections from clients */
  char    status;
  int len;
  fd_set readfds;
  int maxfds;
  int n;

  /* Create a socket for stream oriented communication */
  sd = socket(PF_INET, SOCK_STREAM, 0);
  if (sd < 0) {
      fprintf(stderr, "socket creation failed\n");
      exit(1);
  }
   printf("Socket id: %d\n", sd );
   /* Clean the memory area that will store the transport address of the local socket (server) */
   memset((char *)&sad, 0, sizeof(sad));	
   
   /* Set the transport address of the local socket (server) */
   sad.sin_family = AF_INET; 
   sad.sin_addr.s_addr = htonl(INADDR_ANY); 
	if (argc > 1) { 		
        port = atoi(argv[1]);   	
   } else {
        port = PROTOPORT;       	
   }
   sad.sin_port = htons((u_short)port);



  /* Bind a transport address (sad) to the local socket (sd) */
  if (bind(sd, (struct sockaddr *)&sad, sizeof(sad)) < 0) {
      fprintf(stderr,"bind failed\n");
      exit(1);
   }

  /* Characterize the socket as a welcome socket (server) */
  if (listen(sd, QLEN) < 0) {
      fprintf(stderr,"listen failed\n");
      exit(1);
  }
    while(1){
            if ( (sd2=accept(sd, (struct sockaddr *)&cad, &alen)) < 0) {
      	        fprintf(stderr, "accept failed\n");
                exit(1);
            }
        while(status != EXIT){
            //con questo codice ottengo lo stato dinamicamente al variare degli eventi;
            FD_ZERO(&readfds); //readfds è l'insieme di tutti i descrittori di file
            FD_SET(sd2,&readfds); //setta dentro readfds il valore della socket di rete 
            FD_SET(STDIN_FILENO, &readfds); //setta dentro readfds il valore di STDIN_FILENO, nonchè l'input da tastiera
            // printf("readfds %ld\n",readfds);
            //dentro readfds quindi gestiamo una serie di valori che il nostro programma può gestire e usare
            maxfds = MAX(STDIN_FILENO, sd2)+1; //calcolo il maggiore tra i due e lo inserisco dentro maxfds, dopodichè gli sommo 1

            select(maxfds, &readfds, NULL,NULL,NULL);
            //printf("readfds %ld\n",readfds);

            if(FD_ISSET(STDIN_FILENO,&readfds)) status = YOU;
            else status = PEER;

            memset(buf,0,sizeof(buf));

                switch(status){
        case YOU:
                printf("You>");
                scanf("\n%[^\n]s",buf); //faccio si che il mio utente possa scrivere fino al \n

                len = strlen(buf);
                if(buf[len-1] == '.') status = EXIT;
                write(sd2,&len,sizeof(int)); 
                write(sd2,buf,len+1);
                break;

        case PEER:
                printf("PEER>");
                read(sd2,&len,sizeof(int));
                n = 0;
                while(n<len+1)
                    n+= read(sd2,buf+n,sizeof(buf)-n);
                if(buf[len-1]=='.') status = EXIT;
                printf("%s\n",buf);
                break;
            }
    }
            close(sd2);

  }
  close (sd);

}
