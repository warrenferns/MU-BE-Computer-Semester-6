#include<stdio.h>
#include<conio.h>
#include<string.h>
#include<stdlib.h>
void main()
{
int n,flag,i;
char ilab[20],iopd[20],oper[20],NAMTAB[20][20];
FILE *fp1,*fp2,*DEFTAB;

fp1=fopen("macroin.dat","r");
fp2=fopen("macroout.dat","w");
n=0;
rewind(fp1);
fscanf(fp1,"%s%s%s",ilab,iopd,oper);
while(!feof(fp1))
{
if(strcmp(iopd,"MACRO")==0)
{
strcpy(NAMTAB[n],ilab);
DEFTAB=fopen(NAMTAB[n],"w");
fscanf(fp1,"%s%s%s",ilab,iopd,oper);
while(strcmp(iopd,"MEND")!=0)
{
fprintf(DEFTAB,"%s\t%s\t%s\n",ilab,iopd,oper);
fscanf(fp1,"%s%s%s",ilab,iopd,oper);
}
fclose(DEFTAB);
n++;
}
else
{
flag=0;
for(i=0;i<n;i++)
{
if(strcmp(iopd,NAMTAB[i])==0)
{
flag=1;
DEFTAB=fopen(NAMTAB[i],"r");
fscanf(DEFTAB,"%s%s%s\n",ilab,iopd,oper);
while(!feof(DEFTAB))
{
fprintf(fp2,"%s\t%s\t%s\n",ilab,iopd,oper);
fscanf(DEFTAB,"%s%s%s",ilab,iopd,oper);
}
break;
}
}
if(flag==0)
fprintf(fp2,"%s\t%s\t%s\n",ilab,iopd,oper);
}
fscanf(fp1,"%s%s%s",ilab,iopd,oper);
}
fprintf(fp2,"%s\t%s\t%s\n",ilab,iopd,oper);
getch();
}
