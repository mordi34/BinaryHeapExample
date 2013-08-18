package mybinaryheap;

import mybinaryheap.MyBinaryHeap.TYPE;

/**
 *
 * @author Mordechai
 */
public class Driver
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        MyBinaryHeap<String> mbh = new MyBinaryHeap(TYPE.MAX);
        /*
         mbh.add(10);
         mbh.add(12);
         mbh.add(15);
         mbh.add(5);
         mbh.add(3);
         mbh.add(18);
         mbh.add(1);
         */

        mbh.add("a");
        mbh.add("d");
        mbh.add("j");
        mbh.add("w");
        mbh.add("b");
        mbh.add("c");

        System.out.println(mbh.peek());

        System.out.println("---------------------------------");

        int size = mbh.size();
        for (int i = 0; i < size; i++)
        {
            System.out.println(mbh.pop());
        }
        System.out.println("---------------------------------");
        
        System.out.println(mbh.peek());

    }
}
