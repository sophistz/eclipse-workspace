import java.util.Scanner;
public class Main
{
    static int length,wideth;//�Թ�����ĳ��ȺͿ��
    static int x,y;//��ʼλ�õ�����
    static String[][] arr;//�Թ��Ķ�ά����
    static int n;//��ǰ�ߵ��ߵĲ���
    public static void main(String[] args){
        Scanner in = new Scanner (System.in);
        System.out.println("�����Թ��ĳ��ȡ����(����������������50��");
        wideth =in.nextInt();
        length =in.nextInt();  //���û������Թ��Ŀ�Ⱥͳ���
        arr = new String[wideth][length];
        System.out.println("������"+wideth+"��"+length+"�е��Թ� ('0'��ʾǽ��'1' ��ʾ��· '4'��ʾ��� '5'��ʾ�յ� )");
//�Ӽ��̶�ȡ�Թ�
        for (int i=0;i<wideth;i++) {
            for(int j=0;j<length;j++) {
                arr[i][j] = in.next();
            }
        }
//����һ���Թ����ҵ���㣬������������
        for (int i=0;i<wideth;i++) {
            for(int j=0;j<length;j++) {
                if (arr[i][j].equals("4")) {
                    x=i;
                    y=j;
                }
            }
        }
//��ʼ�����ҳ���
        try {
            Running(x,y,n);

        }catch (Exception e) {
//�����쳣����ӡ����
            System.out.println();
            System.out.println("�ҵ����ڣ�·������(*����·��)��");
            for (int i=0;i<wideth;i++) {
                for(int j=0;j<length;j++) {
                    System.out.print(arr[i][j]);
                    if(j==length-1)
                        System.out.println();
                    else System.out.print(" ");
                }
            }

            System.exit(0); //��������
        }
//������������˳���˵���Թ�û�г���
        System.out.println("���Թ�û�г��ڣ�����");

    }

    /*�ҳ��ڵĵݹ麯��*/
    public static void Running(int x,int y,int n) throws Exception {

        System.out.println("������"+n+"  ��ǰλ�ã���"+(x+1)+","+(y+1)+")");//��ӡÿһ���������


        if(arr[x][y].equals("5")){
            Exception e= new Exception();
            throw e;//���ҵ�����'5'ʱ���ֶ��׳�һ���׳��쳣�����������ݹ麯��
        }
        if(!arr[x][y].equals("4")) //���������յ㣬���õ������Ǻ�"*"��ʾ�Ѿ��߹�
            arr[x][y]="*";

        /*�ж�����������û���յ�*/
        if(y-1>=0&&arr[x][y-1].equals("5")){
            Running(x,y-1,n+1);
        }
        if(x-1>=0&&arr[x-1][y].equals("5")){
            Running(x-1,y,n+1);
        }
        if(y+1<length&&arr[x][y+1].equals("5")){
            Running(x,y+1,n+1);
        }
        if(x+1<wideth&&arr[x+1][y].equals("5")){
            Running(x+1,y,n+1);
        }

        /*���û���յ㣬������������û�е�·*/
        if(x+1<wideth&&arr[x+1][y].equals("1")){
            Running(x+1,y,n+1);  //����
        }
        if(y+1<length&&arr[x][y+1].equals("1")){
            Running(x,y+1,n+1);  //����
        }
        if(y-1>=0&&(arr[x][y-1].equals("1"))){
            Running(x,y-1,n+1);  //����
        }
        if(x-1>=0&&arr[x-1][y].equals("1")){
            Running(x-1,y,n+1);  //����
        }

        arr[x][y]="#"; //���û�е�·,˵��������ͬ�����ĵ���Ϊ��#��
        /* �ٿ�����������û��֮ǰ�߹���·������֮ǰ�߹���·*/
        if(y-1>=0&&(arr[x][y-1].equals("*"))){
            Running(x,y-1,n+1);
        }
        if(x-1>=0&&arr[x-1][y].equals("*")){
            Running(x-1,y,n+1);
        }
        if(y+1<length&&arr[x][y+1].equals("*")){
            Running(x,y+1,n+1);
        }
        if(x+1<wideth&&arr[x+1][y].equals("*")){
            Running(x+1,y,n+1);
        }
 /*     ������������������㣬˵���÷���ĵ�·����·���ò�ݹ������������һ��ݹ�
       �Դ����ƣ�ֱ�����ص�һ��ݹ飬���������������·��
       �ڵ�һ��ݹ��У�������������������㣬˵���Թ�û�г���
       ���������˳��������׳��쳣
 */
    }
}