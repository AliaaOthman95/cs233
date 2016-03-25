 package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.Assert;


public class test {

 @Test
 public void test1() {
  
  IHeap<Integer> heap = new Heap<Integer> ();
  heap.insert(16);
  heap.insert(4);
  heap.insert(10);
  heap.insert(14);
  heap.insert(7);
  heap.insert(9);
  heap.insert(3);
  heap.insert(2);
  heap.insert(8);
  heap.insert(1);
  
  /*IHeap<Integer>  heap2 = new Heap<Integer> ();
  heap2.insert(16);
  heap2.insert(14);
  heap2.insert(10);
  heap2.insert(8);
  heap2.insert(7);
  heap2.insert(9);
  heap2.insert(3);
  heap2.insert(2);
  heap2.insert(4);
  heap2.insert(1);*/
  
 // heap.heapify(heap.getRoot());
  int a = heap.extract();
  Assert.assertEquals(16,a);
  heap.heapify(heap.getRoot());
  a = heap.extract();
  Assert.assertEquals(14,a);
  heap.heapify(heap.getRoot());
  a = heap.extract();
  Assert.assertEquals(10,a);
  System.out.println(heap.getRoot());
  heap.heapify(heap.getRoot());
  a = heap.extract();
  Assert.assertEquals(8 , a);
  heap.heapify(heap.getRoot());
  a = heap.extract();
  Assert.assertEquals(7,a);
  heap.heapify(heap.getRoot());
  a = heap.extract();
  Assert.assertEquals(9,a);
  heap.heapify(heap.getRoot());
  a = heap.extract();
  Assert.assertEquals(3,a);
  heap.heapify(heap.getRoot());
  a = heap.extract();
  Assert.assertEquals(2,a);
  heap.heapify(heap.getRoot());
  a = heap.extract();
  Assert.assertEquals(4,a);
  heap.heapify(heap.getRoot());
  a = heap.extract();
  Assert.assertEquals(1,a);
  //Assert.assertEquals(heap, heap2);
 
 }

}