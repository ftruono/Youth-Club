package it.youthclub.api;

public class GeoException extends Exception {
   
	private static final long serialVersionUID = 1L;
    private String message;
    private int code;
    
    
    public int getCode() {
		return code;
	}

	
	@Override
	public String getMessage() {
		return super.getMessage();
	}

	public GeoException(int code,String s) {
		   super(s);
		   this.message=s;
		   this.code=code;
	}
	
	
   
   
   
   
   
	
	
}
