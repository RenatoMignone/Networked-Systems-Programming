#include<stdio.h>
#include<stdlib.h>
#define CENTER 1

const int R = 3;
const int C = 3;

void make_matrix(char M[R][C]){
    int i, j;
    for(i = 0; i < R; i++)
        for(j = 0; j < C; j++)
            M[i][j] = ' ';
}

void update_matrix(char M[R][C], char player){
    system("clear"); //ripuliamo il terminale per inizializzare il gioco
    printf("User: %c\n\n", player); //inseriamo nella matrice i valori derivati dal gioco
    printf("   |   |   \n");
    printf(" %c | %c | %c \n", M[0][0], M[0][1], M[0][2]);
    printf("___|___|___\n");
    printf("   |   |   \n");
    printf(" %c | %c | %c \n", M[1][0], M[1][1], M[1][2]);
    printf("___|___|___\n");
    printf("   |   |   \n");
    printf(" %c | %c | %c \n", M[2][0], M[2][1], M[2][2]);
    printf("   |   |   \n");
}

int check_row(int r, char p, char M[R][C]){
    int i;
    for(i = 0; i < C; i++)
        if(p != M[r][i]) return 0;
    return 1;
}

int check_column(int c, char p, char M[R][C]){
    int i;
    for(i = 0; i < R; i++)
        if(p != M[i][c]) return 0;
    return 1;
}

int check_diag(int r, int c, char p, char M[R][C]){
    int check_r = 0, check_l = 0;
    if((r == CENTER && c == CENTER)){
    	if(p == M[0][0] && p == M[1][1] && p == M[2][2])
            check_r = 1;

        if(p == M[0][2] && p == M[1][1] && p == M[2][0])
            check_l = 1;

        if(check_l || check_r) return 1;
        return 0;
    } else{
        if(r == c){
            if(p == M[0][0] && p == M[1][1] && p == M[2][2])
                return 1;
            return 0;
        } else{
            if(p == M[0][2] && p == M[1][1] && p == M[2][0])
                return 1;
            return 0;
        }
    }
}

int check_win(char player, int r, int c, char M[R][C]){ //funzione che sia in grado di controllare se ho vinto
    if(((r==0||r==2) && c==1) || (r==1&&(c==0||c==2))){
        if(check_row(r, player, M)) return 1;
        if(check_column(c, player, M)) return 1;
        return 0;
    } else{
        if(check_row(r, player, M)) return 1;
        if(check_column(c, player, M)) return 1;
        if(check_diag(r, c, player, M)) return 1;
        return 0;
    }
}

int check_position(int r, int c, char M[R][C]){ //verifichiamo che nella posizione inserita non vi sia già un simbolo
    if(M[r][c] != ' ') return 0;
    return 1;
}

int check_full(char M[R][C]){ //verifichiamo se c'è almeno uno slot libero nella matrice, se è piena returno 0
    int i, j;
    for(i = 0; i < R; i++)
        for(j = 0; j < C; j++)
            if(M[i][j] == ' ') return 0;
    return 1;
}
