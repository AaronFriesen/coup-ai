package model;

public enum Move {
    INCOME("Income"),
    FOREIGN_AID("Foreign Aid"),
    COUP("Coup"),
    TAX("Tax"),
    ASSASSINATE("Assassinate"),
    EXCHANGE("Exchange"),
    STEAL("Steal"),
    BLOCK_STEAL_AMBASSADOR("Block Steal (Ambassador)"),
    BLOCK_STEAL_CAPTAIN("Block Steal (Captain)"),
    BLOCK_FOREIGN_AID("Block Foreign Aid"),
    BLOCK_ASSASSINATE("Block Assassinate"),
    BLOCK_BLUFF_AMBASSADOR("Block Bluff Ambassador"),
    BLOCK_BLUFF_ASSASSIN("Block Bluff Assassin"),
    BLOCK_BLUFF_CAPTAIN("Block Bluff Captain"),
    BLOCK_BLUFF_CONTESSA("Block Bluff Contessa"),
    BLOCK_BLUFF_DUKE("Block Bluff Duke"),
    CALL_BLUFF_AMBASSADOR("Call Bluff Ambassador"),
    CALL_BLUFF_ASSASSIN("Call Bluff Assassin"),
    CALL_BLUFF_CAPTAIN("Call Bluff Captain"),
    CALL_BLUFF_CONTESSA("Call Bluff Contessa"),
    CALL_BLUFF_DUKE("Call Bluff Duke"),
    PASS("Pass");
    
    private String name;
    private Move(String name) {
    	this.name = name;
    }
    
    public String toString() {
    	return this.name;
    }
}
