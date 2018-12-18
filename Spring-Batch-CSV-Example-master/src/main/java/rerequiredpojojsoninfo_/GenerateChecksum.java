package com.pearson.cd.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenerateChecksum {

	public GenerateChecksum() {
		// TODO Auto-generated constructor stub
	}
	
	public BigInteger checkSum(Object obj) {
		if ( obj == null )
			return BigInteger.ZERO;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    
	    try {
	    	ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			oos.close();

		    MessageDigest m = MessageDigest.getInstance("SHA1");
		    m.update(baos.toByteArray());
		    
		    return new BigInteger(1, m.digest());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return BigInteger.ZERO;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			return BigInteger.ZERO;
		}
	}

}
