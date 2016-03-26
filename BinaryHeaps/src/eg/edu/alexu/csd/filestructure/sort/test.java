 package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.Assert;


public class test {
	
 /*@Test
 public void test1() {
	 
  Heap<Integer> heap = new Heap<Integer> ();
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
  
  System.out.println(heap.size());
 
 
  
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
  //heap.print();
  //heap.heapify(heap.getTree().get(1));
  /*
  int a = heap.extract();
  Assert.assertEquals(16,a);
  a = heap.extract();
  Assert.assertEquals(14,a);
  a = heap.extract();
  Assert.assertEquals(10,a);
  
  a = heap.extract();
  Assert.assertEquals(9 , a);

  a = heap.extract();
  Assert.assertEquals(8,a);
 
  a = heap.extract();
  Assert.assertEquals(7,a);
  heap.heapify(heap.getRoot());
  a = heap.extract();
  Assert.assertEquals(4,a);
  heap.heapify(heap.getRoot());
  a = heap.extract();
  Assert.assertEquals(3,a);
  heap.heapify(heap.getRoot());
  a = heap.extract();
  Assert.assertEquals(2,a);
  heap.heapify(heap.getRoot());
  a = heap.extract();
  Assert.assertEquals(1,a);
  //Assert.assertEquals(heap, heap2);
 
 }*/
 @Test
 public void test2() {
	 Sort<Integer> heap2=new Sort<Integer>();
	 ArrayList<Integer> list = new ArrayList<Integer>();
	 list.add(4);
	 /*list.add(3);
	 list.add(16);
	 list.add(8);
	 list.add(1);
	 list.add(25);
	 list.add(13);
	 list.add(15);
	 list.add(45);
	 list.add(2);
	 list.add(4);*/
	 Heap h =(Heap) heap2.heapSort(list);
	//heap2.sortFast(list);
	 //heap.sortSlow(list);
	 //for(int i=0;i<list.size();i++)System.out.println(list.get(i));
	 
	 h.print();
	 
 }
 

}