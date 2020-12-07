package Thread;

public class TestMyThread3 {
    public static void main(String[] args) {
        MyThread3 t1 = new MyThread3("T1");
        MyThread3 t2 = new MyThread3("T2");
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();
    }
}
class MyThread3 extends Thread{
    MyThread3(String name){
        super(name);
    }
    public void run(){
        for (int i=0; i<5; i++) {
            System.out.println(i+" "+this.getName());
            int r = (int)(Math.random()*1000);
            try{
                sleep(r);
            }
            catch(InterruptedException ex){}
        }
        System.out.println("Done");
    }
}
