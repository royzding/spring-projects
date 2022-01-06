package com.sample.microservices.common.chapter.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Bird {
    protected transient String name;
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public Bird() {
       this.name = "Matt";
    }
 }
 public class Eagle_19 extends Bird implements Serializable {
	 
	 //Object obj = new Object();
	 
    { this.name = "Olivia"; }
    public Eagle_19() {
       this.name = "Bridget";
    }
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
       var e = new Eagle_19();
       e.name = "Adeline";
       
       File dataFile = new File("C:/tmp/eagle.data");
       
  	   try (var out = new ObjectOutputStream(
  	           new BufferedOutputStream(
  	              new FileOutputStream(dataFile)))) {
  		   out.writeObject(e);	   
  	   }    	

		 try (var in = new ObjectInputStream(
		         new BufferedInputStream(
		            new FileInputStream(dataFile)))) {

		       var object = (Eagle_19)in.readObject();
		       
		       System.out.println(object.name);
		       
		 } catch (EOFException ex) {
		    // File end reached
		 }

    }
 }

