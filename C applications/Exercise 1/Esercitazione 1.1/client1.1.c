/*#include <sys/types.h>*/
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

#define PROTOPORT  5193  	/* Default server port number */
#define LOCALHOST  "127.0.0.1" 	/* Default server address */

int main(int argc, char *argv[]) {
  char buf[256];
  int clientSocket;
  struct sockaddr_in serverAddr;
  socklen_t serverAddrLen;
  
  char *host;

  /* Check for the existence of an IP address on the command line */
  if (argc > 1) {
          host = argv[1]; /* if the parameter has been passed */
  } else {
          host = LOCALHOST;
  }
  
  /* Create a socket for datagram oriented communication */
  //PF_INET, specifica l'utilizzo di un IPv4
  //SOCK_DGRAM, specifica l'utilizzo di datagrams, quindi del protocollo UDP di trasporto
  //0 indica il protocollo default, ovvero UDP
  clientSocket = socket(PF_INET, SOCK_DGRAM, 0);

  /* Clean the memory area that will store the transport address of the remote socket (server) */
  memset(&serverAddr, 0, sizeof(serverAddr));
	
  /* Set the transport address of the remote socket (server) */
  serverAddr.sin_family = AF_INET;
  serverAddr.sin_port = htons(PROTOPORT);
  serverAddr.sin_addr.s_addr= inet_addr(host); /*inet addr trasforma l'IP del localhost in un intero da assegnare ad s_addr*/

  serverAddrLen = sizeof(serverAddr);

  /* Clean the memory area that will store the data to send */
  memset(buf, 0, sizeof(buf));
  
  /* Send the data (no useful data is present in the message) */
  sendto(clientSocket, buf, sizeof(buf), 0, (struct sockaddr *)&serverAddr, serverAddrLen);

 //pulisce la memoria del buffer
  memset(buf, 0, sizeof(buf));
  
  /* Receive the data from the server; we assume that the data are encapsulated in a single message */
  recvfrom(clientSocket, buf, sizeof(buf), 0, (struct sockaddr *)&serverAddr, &serverAddrLen);

  //stampiamo il numero di volte in cui il server è stato contattato mantenendo uno stato al suo interno per il client
  printf("This server has been contacted %d time%s\n", *(int *)buf, *(int *)buf==1 ? "." : "s.");

  close(clientSocket);
  return 0;
}
