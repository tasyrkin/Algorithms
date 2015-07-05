package structures.heap;

import java.util.PriorityQueue;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: tim
 * Date: 12/7/12
 * Time: 8:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class CourseraMedianWithHeap {

    Logger logger = Logger.getLogger(CourseraMedianWithHeap.class.toString());

    MinAndMaxHeaps minAndMaxHeaps = new MinAndMaxHeaps();

    public void add(int element){

        if(minAndMaxHeaps.isMaxEmpty()){

            if(minAndMaxHeaps.isMinEmpty()){
                minAndMaxHeaps.offerMax(element);
            } else {
                int min = minAndMaxHeaps.pollMin();

                setElementsByComarison(element, min);
            }
            logger.info("added element [" + element + "], median [" + getMedian() + "]");
            return;
        } else if(minAndMaxHeaps.isMinEmpty()){

            if(minAndMaxHeaps.isMaxEmpty()){
                minAndMaxHeaps.offerMin(element);
            } else {

                int max = minAndMaxHeaps.pollMax();

                setElementsByComarison(max, element);
            }
            logger.info("added element [" + element + "], median [" + getMedian() + "]");
            return;
        }

        int max = minAndMaxHeaps.peekMax();
        int min = minAndMaxHeaps.peekMin();

        if(max >= element){
            if(minAndMaxHeaps.maxSize() <= minAndMaxHeaps.minSize()){
                minAndMaxHeaps.offerMax(element);
            } else {
                int polledMax = minAndMaxHeaps.pollMax();
                minAndMaxHeaps.offerMin(polledMax);
                minAndMaxHeaps.offerMax(element);
            }
        } else if(min <= element){
            if(minAndMaxHeaps.minSize() <= minAndMaxHeaps.maxSize()){
                minAndMaxHeaps.offerMin(element);
            } else {
                int polledMin = minAndMaxHeaps.pollMin();
                minAndMaxHeaps.offerMax(polledMin);
                minAndMaxHeaps.offerMin(element);
            }
        } else {
            if(minAndMaxHeaps.maxSize() < minAndMaxHeaps.minSize()){
               minAndMaxHeaps.offerMax(element);
            } else {
                minAndMaxHeaps.offerMax(element);
            }
        }
        logger.info("added element [" + element + "], median [" + getMedian() + "]");
    }

    private void setElementsByComarison(int element1, int element2) {
        if(element1 >= element2){
            minAndMaxHeaps.offerMin(element1);
            minAndMaxHeaps.offerMax(element2);
        } else {
            minAndMaxHeaps.offerMin(element2);
            minAndMaxHeaps.offerMax(element1);
        }
    }

    public double getMedian(){

        if((minAndMaxHeaps.maxSize() + minAndMaxHeaps.minSize())%2 == 0){
            int resultingMax = minAndMaxHeaps.peekMax();
            int resultingMin = minAndMaxHeaps.peekMin();

            return (double)(resultingMax + resultingMin)/2;
        } else if(minAndMaxHeaps.maxSize() > minAndMaxHeaps.minSize()) {
            return minAndMaxHeaps.peekMax();
        }
        return minAndMaxHeaps.peekMin();
    }

    public static void main(String[]args){
        CourseraMedianWithHeap cmwh = new CourseraMedianWithHeap();

        for(int i = -30; i <= 0; i++){
            cmwh.add(i);
        }

    }

}
