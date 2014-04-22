package model;

import java.util.List;
import java.util.ArrayList;

public class CoupPlayer implements Player {

	int numIsk;
	
    public CoupPlayer(){
    	
    	numIsk = 2;
    	
    	
    }
    
    int getIsk(){
    	return numIsk;
    }
    
    void addIsk(int numIskToAdd){
    	numIsk+=numIskToAdd;
    }
    
    void removeIsk(int numIskToSub){
    	numIsk-=numIskToSub;
    	if(numIsk<0){
    		numIsk=0;
    	}
    }

}
