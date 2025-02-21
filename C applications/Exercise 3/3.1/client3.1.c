/*
 * clientStream.c
 *
 *  Created on: October 24, 2023
 *      Author: zimeo
 */

/*#include <sys/types.h>*/
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>


#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define PROTOPORT  5193  	/* Default server port number */
#define LOCALHOST "127.0.0.1" 	/* Default server address */

#define YOU 0
#define PEER 1
#define EXIT 2


int main(int argc, char *argv[]) {
  struct  sockaddr_in sad; /* Struct to host the transport address of the remote socket */
  int     sd;              /* Socket descriptor              	  */
  int     port;            /* Port number                  		  */
  int     n;               /* Number of read bytes                */
  char    buf[1000];       /* Buffer for data reading and writing */
  char    *host;
  char    status = YOU;
  int     len;

  /* Create a socket for stream oriented communication */
  sd = socket(PF_INET, SOCK_STREAM, 0);
  if (sd < 0) {
      fprintf(stderr, "socket creation failed\n");
      exit(1);
  }
	
  /* Clean the memory area that will store the transport address of the remote socket (server) */
  memset((char *)&sad,0,sizeof(sad));
  
  /* Set the transport address of the remote socket (server)) */
  sad.sin_family = AF_INET;
  if (argc > 2) {
      port = atoi(argv[2]);
  } else {
      port = PROTOPORT;
  }
  if (port > 0)
      sad.sin_port = htons((u_short)port);
  else {
      fprintf(stderr,"bad port number %s\n", argv[2]);
      exit(1);
  }

  /* Check for the existence of an IP address on the command line */
  if (argc > 1) {
  	  host = argv[1]; /* if host argument is specified */
  } else {
  	  host = LOCALHOST;
  }

  sad.sin_addr.s_addr = inet_addr(host);

  /* Connect the local socket (sd) with the remote one identified by the transport address sad */
  if (connect(sd, (struct sockaddr *)&sad, sizeof(sad)) < 0) {
        fprintf(stderr,"connect failed\n");
        exit(1);
   }

while(status != EXIT){
    memset(buf,0,sizeof(buf));
    switch(status){
        case YOU:
            do{
                printf("You>");
                scanf("\n%[^\n]s",buf); //faccio si che il mio utente possa scrivere fino al \n

                len = strlen(buf);
                if(buf[len-1] == '.') status = EXIT;
                else if(buf[len-1] == '-') status = PEER;
                write(sd,&len,sizeof(int)); 
                write(sd,buf,len+1);
            }while(status==YOU);
            break;

        case PEER:
            do{
                printf("PEER>");
                read(sd,&len,sizeof(int));
                n = 0;
                while(n<len+1)
                    n+= read(sd,buf+n,sizeof(buf)-n);
                if(buf[len-1]=='.') status = EXIT;
                else if(buf[len-1]=='-') status = YOU;
                printf("%s\n",buf);
            }while(status==PEER);
            break;
    }
}
    printf("Comunicazione terminata");

   /* Close the socket. */
   close(sd);
   
   exit(0);
}
