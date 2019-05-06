import java.util.Scanner;
public class Main
{
    static int length,wideth;//迷宫矩阵的长度和宽度
    static int x,y;//起始位置的坐标
    static String[][] arr;//迷宫的二维数组
    static int n;//当前走的走的步数
    public static void main(String[] args){
        Scanner in = new Scanner (System.in);
        System.out.println("输入迷宫的长度、宽度(行数和列数不超过50）");
        wideth =in.nextInt();
        length =in.nextInt();  //让用户输入迷宫的宽度和长度
        arr = new String[wideth][length];
        System.out.println("请输入"+wideth+"行"+length+"列的迷宫 ('0'表示墙，'1' 表示道路 '4'表示起点 '5'表示终点 )");
//从键盘读取迷宫
        for (int i=0;i<wideth;i++) {
            for(int j=0;j<length;j++) {
                arr[i][j] = in.next();
            }
        }
//遍历一次迷宫，找到起点，记下起点的坐标
        for (int i=0;i<wideth;i++) {
            for(int j=0;j<length;j++) {
                if (arr[i][j].equals("4")) {
                    x=i;
                    y=j;
                }
            }
        }
//开始尝试找出口
        try {
            Running(x,y,n);

        }catch (Exception e) {
//接收异常，打印数组
            System.out.println();
            System.out.println("找到出口！路径如下(*代表路径)：");
            for (int i=0;i<wideth;i++) {
                for(int j=0;j<length;j++) {
                    System.out.print(arr[i][j]);
                    if(j==length-1)
                        System.out.println();
                    else System.out.print(" ");
                }
            }

            System.exit(0); //结束程序
        }
//如果程序正常退出，说明迷宫没有出口
        System.out.println("该迷宫没有出口！！！");

    }

    /*找出口的递归函数*/
    public static void Running(int x,int y,int n) throws Exception {

        System.out.println("步数："+n+"  当前位置：（"+(x+1)+","+(y+1)+")");//打印每一步方便理解


        if(arr[x][y].equals("5")){
            Exception e= new Exception();
            throw e;//当找到出口'5'时，手动抛出一个抛出异常，跳出整个递归函数
        }
        if(!arr[x][y].equals("4")) //若非起点和终点，将该点做个记号"*"表示已经走过
            arr[x][y]="*";

        /*判断上下左右有没有终点*/
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

        /*如果没有终点，则看上下左右有没有道路*/
        if(x+1<wideth&&arr[x+1][y].equals("1")){
            Running(x+1,y,n+1);  //向下
        }
        if(y+1<length&&arr[x][y+1].equals("1")){
            Running(x,y+1,n+1);  //向右
        }
        if(y-1>=0&&(arr[x][y-1].equals("1"))){
            Running(x,y-1,n+1);  //向左
        }
        if(x-1>=0&&arr[x-1][y].equals("1")){
            Running(x-1,y,n+1);  //向上
        }

        arr[x][y]="#"; //如果没有道路,说明是死胡同，将改点标记为“#”
        /* 再看上下左右有没有之前走过的路并返回之前走过的路*/
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
 /*     如果上诉条件都不满足，说明该方向的道路是死路，该层递归结束，返回上一层递归
       以此类推，直到返回第一层递归，继续找其他方向的路。
       在第一层递归中，如果上述条件都不满足，说明迷宫没有出口
       程序正常退出，不会抛出异常
 */
    }
}