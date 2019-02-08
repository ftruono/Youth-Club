package it.youthclub.api;

public class ItemApi {
    private String key[]; //categorie api.
    private String value; //categoria mia
    
    public ItemApi(String key[],String value) {
    	setKey(key);
    	setValue(value);
    }
    
    

	public String[] getKey() {
		return key.clone();
	}



	public void setKey(String[] key) {
		this.key = key.clone();;
	}



	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}



	@Override
	public int hashCode() {
		int result = 17;
        result = 31 * result + value.hashCode();
        for(String t : key)
        	result = 31 * result + t.hashCode();
        
        return result;
	}
    
    
    
    
	
}
