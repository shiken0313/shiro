package com.itcast.salt;


import java.io.Serializable;

import org.apache.shiro.util.SimpleByteSource;

public class MyByteSource extends SimpleByteSource implements Serializable{

	public MyByteSource(String string) {
		super(string);
		
	}


}
