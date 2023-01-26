package expt;

import java.util.*;
import java.lang.*;
import java.net.*;
import java.io.*;

public class Server{
	public static void main(String Args[]){
		int n, k=1, i, j, a=0, w=0, t, sum, msg;
		String key;
		try {
			ServerSocket ss = new ServerSocket(4444);
			Socket s = ss.accept();
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter Number of users: ");
			n = sc.nextInt();
			while(true) {
				for(i=1;i<=100;i++){
					if(n<=Math.pow(2,i)){
						k=(int)Math.pow(2,i);
						break;
					}
				}
				int codes[][];
				ArrayList<ArrayList<Integer>> enc_codes = new ArrayList<ArrayList<Integer>>(n);   // Di*Ci
				ArrayList<Integer> messages = new ArrayList<Integer>(n);     // messages
				ArrayList <Integer> enc_msg = new ArrayList<Integer> (k); //sent to client

				codes = new int [k][k];
				codes = walsh_code(k,1);
				System.out.println();
				
				for(i=0;i<n;i++) {
					System.out.print("User "+(i+1)+": ");
					for(j=0;j<k;j++) 
						System.out.print(codes[i][j]+"\t");
					System.out.println();
				}
				System.out.println();
			
				System.out.println();
				for(i=0;i<n;i++) {
					System.out.print("enter message of user "+(i+1)+": ");
					msg = sc.nextInt();
					if(msg == 0) {
						messages.add(-1);
						continue;
					}
					messages.add(1);
				}
				a=0;
				for(i=0;i<n;i++) {
					ArrayList <Integer> temp = new ArrayList<Integer> (k);
					for(j=0;j<k;j++) {
						a=messages.get(i)*codes[i][j];
						if(a <= 0) {
							temp.add(-1);          
							continue;
						}
						temp.add(1);
					}
					enc_codes.add(temp);
				}  
				for(i=0;i<k;i++) {
					a=0;
					for(j=0;j<n;j++) 
						a=a+enc_codes.get(j).get(i);  
					enc_msg.add(a);
				}

				dos.writeInt(n);
				dos.writeInt(k);

				for(i=0;i<n;i++) {
					for(j=0;j<k;j++)
						dos.writeInt(codes[i][j]);
				}

				System.out.println();
				System.out.print("superimposed msg");
				for(i=0;i<k;i++) {
					System.out.print(enc_msg.get(i) + "\t");
					dos.writeInt(enc_msg.get(i)); 
				}
				System.out.println();

				if(dis.readInt()==-1) {
					s.close();
					System.exit(0);
				}
			}
		}
		catch(SocketException e) {
			System.exit(0);
		}
		catch(Exception e) {
			System.out.println();
		}
	}

	public static int[][] walsh_code(int k, int sign) {
		int w[][] = new int[k][k];
		int copy[][] = new int[k][k];
		int m,n;

		int i,j;
		if(k==1 && sign==1) {
			w[0][0]=1;  
			return w;
		}
		copy=walsh_code(k/2,1);
		m=0;
		n=0;
		for(i=0;i<k/2;i++) {
			n=0;
			for(j=0;j<k/2;j++) {
				w[i][j]=copy[m][n];
				n++;
			}
			m++;
		}
		m=0;
		n=0;
		for(i=0;i<k/2;i++) {
			n=0;
			for(j=k/2;j<k;j++) {
				w[i][j]=copy[m][n];
				n++;
			}
			m++;
		}
		m=0;
		n=0;
		//System.out.println();
		for(i=k/2;i<k;i++) {
			n=0;
			for(j=0;j<k/2;j++) {
				w[i][j]=copy[m][n];
				n++;
			}
			m++;
		}
		m=0;
		n=0;
		for(i=k/2;i<k;i++) {
			n=0;
			for(j=k/2;j<k;j++) {
				w[i][j]=-1*copy[m][n];
				n++;
			}
			m++;
		}
		return w;
	}
}
