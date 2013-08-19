package mybinaryheap;

import java.util.ArrayList;

/**
 * Minimum or maximum order Binary Heap, with maximum as deafult.
 *
 * @author Mordechai Serraf
 */
public class MyBinaryHeap<T extends Comparable>
{

    public enum TYPE
    {

        MAX, MIN
    };
    TYPE type = TYPE.MAX;
    private ArrayList<T> heap = new ArrayList();

    public MyBinaryHeap(TYPE type_)
    {
        type = type_;
    }

    public MyBinaryHeap()
    {
    }

    public int size()
    {
        return heap.size();
    }

    public T peek()
    {
        if (heap.isEmpty())
        {
            return null;
        } else
        {
            return heap.get(0);
        }
    }

    public T pop()
    {
        if (heap.isEmpty())
        {
            return null;
        } else
        {
            T rootVal = heap.get(0);

            removeRoot();

            return rootVal;
        }
    }

    /**
     * "Heapify up" checks if child obeys heap property given parents value, if
     * not we swap them and compare the element with its new parents as before.
     */
    public void add(T value)
    {
        heap.add(value);
        int currentIndex = heap.size() - 1;

        for (int i = 0; i < heap.size(); i++)
        {
            int parentIndex = getParent(currentIndex);
            if (shouldSwapParentAndChild(currentIndex, parentIndex))
            {
                // swap with parent
                T temp = heap.get(parentIndex);// using .get twice, hmm, but its o(1) soooo is that even bad?
                heap.set(parentIndex, heap.get(currentIndex));
                heap.set(currentIndex, temp);

                // we are now at the parent node
                currentIndex = parentIndex;
            } else
            {
                break;
            }
        }
    }

    private void removeRoot()
    {
        heap.set(0, heap.get(heap.size() - 1));// replace with last elemenet
        heap.remove(heap.size() - 1);
        int currentIndex = 0;
        int leftChildIndex;
        int rightChildIndex;
        for (int i = 0; i < heap.size(); i++)
        {

            leftChildIndex = getLeftChild(currentIndex);

            rightChildIndex = getRightChild(currentIndex);

            if (doesObeyHeapOrder(currentIndex, leftChildIndex) && doesObeyHeapOrder(currentIndex, rightChildIndex))
            {
                break;
            } else
            {
                if (isLeftChildBetterSuitedThanRight(leftChildIndex, rightChildIndex))
                {
                    T temp = heap.get(leftChildIndex);
                    heap.set(leftChildIndex, heap.get(currentIndex));
                    heap.set(currentIndex, temp);

                    currentIndex = leftChildIndex;
                } else
                {
                    T temp = heap.get(rightChildIndex);
                    heap.set(rightChildIndex, heap.get(currentIndex));
                    heap.set(currentIndex, temp);

                    currentIndex = rightChildIndex;
                }
            }
        }
    }

    private boolean doesObeyHeapOrder(int parentIndex, int childIndex)
    {
        if (type == TYPE.MAX)
        {
            try
            {
                return (heap.get(parentIndex).compareTo(heap.get(childIndex)) >= 0);
            } catch (IndexOutOfBoundsException e)
            {
                return true;// no child so order is okay.
            }
        } else
        {

            try
            {
                return (heap.get(parentIndex).compareTo(heap.get(childIndex)) <= 0);
            } catch (IndexOutOfBoundsException e)
            {
                return true;// no child so order is okay.
            }
        }
    }

    private boolean isLeftChildBetterSuitedThanRight(int leftChildIndex, int rightChildIndex)
    {
        if (type == TYPE.MAX)
        {
            if (rightChildIndex >= heap.size())
            {
                return true;
            } else
            {
                return (heap.get(leftChildIndex).compareTo(heap.get(rightChildIndex)) > 0);
            }
        } else
        {

            if (rightChildIndex >= heap.size())
            {
                return true;
            } else
            {
                return (heap.get(leftChildIndex).compareTo(heap.get(rightChildIndex)) < 0);
            }
        }
    }

    private boolean shouldSwapParentAndChild(int childIndex, int parentIndex)
    {
        if (type == TYPE.MAX)
        {
            return (heap.get(childIndex).compareTo(heap.get(parentIndex)) > 0);
        } else
        {
            return (heap.get(childIndex).compareTo(heap.get(parentIndex)) < 0);
        }
    }

    private int getLeftChild(int currentIndex)
    {
        return ((2 * currentIndex) + 1);
    }

    private int getRightChild(int currentIndex)
    {
        return ((2 * currentIndex) + 2);
    }

    private int getParent(int currentIndex)
    {
        return (int) (Math.floor((currentIndex - 1) / 2));
    }
}
