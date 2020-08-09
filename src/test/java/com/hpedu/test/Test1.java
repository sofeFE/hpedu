package com.hpedu.test;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

public class Test1 {

    @org.junit.Test
    public void main() {
        System.out.println(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        System.out.println("now= " + LocalDateTime.ofEpochSecond(1558011443, 0, ZoneOffset.UTC));
    }

    /*将两个有序的 原数组, 合并为一个有序的数组.*/
    @org.junit.Test
    public void testMerge(){
        int[] arr1 = new int[100]; 
        int[] arr2 = new int[200];
        
        fillData(arr1);
        fillData(arr2);
        
        Assert.assertNotNull(arr1);
        Assert.assertNotNull(arr2);
        Assert.assertTrue(arr1.length > 0 && arr2.length > 0);
        
        display(arr1) ;
        display(arr2) ;
        
//        display(merge(arr1,arr2)) ;
        display(merge2(arr1,arr2)) ;
        
        
    }

    private void fillData(int[] arr1) {
        for (int i = 0,size = arr1.length; i < size; i++) {
            arr1[i] = (int) (Math.random() * 99 + 1) ;
        }
        sort(arr1,arr1.length) ;
    }

    private void sort(int[] arr1, int position) {
        for (int i = 0, len = arr1.length; i < len ; i++) {
            for (int j = i+1; j < position ; j++) {

                if(arr1[i] >  arr1[j] ) {
                    
                    arr1[i] = arr1[i] ^ arr1[j] ;
                    arr1[j] = arr1[i] ^ arr1[j] ;
                    arr1[i] = arr1[i] ^ arr1[j] ;
                }
                
            }
        }
        
    }

    private int[] merge(int[] arr1, int[] arr2) {
        /*合并两个有序的源数组*/
        /*假设是 两个 升序的 数组*/
        
        int [] targetArr = new int[arr1.length + arr2.length ] ;
        
        Integer sourceData1 = null ,sourceData2 = null ;
        
        for (int i = 0, j = 0 , length =targetArr.length ; j < length; i++) {
            
            if(i < arr1.length )sourceData1 = arr1[i];
            
            if(i < arr2.length)sourceData2 = arr2[i];
            
            if( sourceData1 != null && sourceData2 != null ) {
                /*如果sourceData1大,则往后排*/
                if(sourceData1.intValue() > sourceData2.intValue()){
                    
                    targetArr[j++] = sourceData2.intValue() ;
                    targetArr[j++] = sourceData1.intValue() ;
                    
                }else{
                    
                    targetArr[j++] = sourceData1.intValue() ;
                    targetArr[j++] = sourceData2.intValue() ;
                }
            }else {
                
                if(sourceData1 != null )targetArr[j] = sourceData1.intValue() ; 
                if(sourceData2 != null )targetArr[j] = sourceData2.intValue() ;
                j++ ;
            }

            sort(targetArr,j);
            
            sourceData1 = null  ;
            sourceData2 = null ;
        }
        
        return targetArr ;
    }
    private int[] merge2(int[] arr1,int[] arr2) {
        int[] targetArr = new int[arr1.length + arr2.length];
        for (int i = 0,j = 0 ,len = targetArr.length; i < len; i++) {
            if(i < arr1.length) {
                targetArr[i] = arr1[i] ;
            }
            if(i >= arr1.length) {
                targetArr[i] = arr2[j++] ;
            }
            
        }
        sort(targetArr,targetArr.length);
        
        return targetArr ;
    }
    private <T> void displayObj(T[] arr1) {
        System.out.println(Arrays.toString(arr1));
    }
    private void  display(int[] arr1) {
        System.out.println(Arrays.toString(arr1));
    }

    @org.junit.Test
    public void RemoveDuplicates(){
        Integer[] arr = {10,10,10,20,21,11,12,13} ;
        displayObj(arr);
        
        displayObj(removeDuplicate(arr));
    }

    private Integer[] removeDuplicate(Integer[] arr) {
        Assert.assertNotNull(arr);
        setDuplicateToNull(arr);
//     return   cleanUpNull(arr);
        return handleNull(arr) ;
    }

    private Integer[] cleanUpNull(Integer[] arr) {
        /*将null值的位置 补全.*/
        for (int i = 0 ,len = arr.length; i < len; i++) {
            if(arr[i] == null ){
                /*如果是最后的位置,停止*/
                if(i == len -1) break;
                
                /*将所有后面的位置补到前一个*/
                /*出现bug: 如果两个位置都是null ,那么最后 数组中间将出现null*/
                for (int j = i + 1; j <= len - 1 ; j++) {
                    if(arr[j] == null ) continue; 
                    arr[i] = arr[j] ;
                    arr[j] = null ;
                    break ;
                }
            }
        }
        return arr ;
    }

    private void setDuplicateToNull(Integer[] arr) {
        for (int i = 0,len = arr.length; i < len; i++) {
            if(arr[i] == null ){
                continue ;
            }
            for (int j = i + 1 ; j < len; j++) {
                if( arr[j] ==null ){
                    continue ;
                }
                if(arr[i].intValue() == arr[j].intValue()) {
                    arr[j] = null ;
                }
            }
        }
    }
    private Integer[] handleNull(Integer[] arr) {
        int len = arr.length ;
        Integer[] newArr = new Integer[len];
        
        
        for (int j = 0; j < len; j++) {
            
            for (int i = 0; i < len; i++) {

                if( i == len - 1 ) {
                    newArr[j] = arr[i];
                    arr[i] = null ;
                }
                if(arr[i] == null) continue;
                
                newArr[j] = arr[i] ;
                arr[i] = null ;
               
                break ;
            }
        }
        return newArr ;
    }
    
    
    @org.junit.Test
    public void equals(){
        Integer a = 100;
        Integer b = new Integer(100) ;
        Integer c  = 128 ;
        System.out.println( a == c);
        System.out.println( a == b);
    }
    
    
    @org.junit.Test
    public void fuck(){
        LocalDateTime time = LocalDateTime.of(1990, 10, 7, 0, 0);
        System.out.println(
                time.getDayOfYear() 
        );
    }
    
    

}
