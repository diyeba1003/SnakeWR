package dao;

public class DaoConfiguration extends RuntimeException{
	
	/*
     * Constructeurs
     */
    public DaoConfiguration( String message ) {
        super( message );
    }

    public DaoConfiguration( String message, Throwable cause ) {
        super( message, cause );
    }

    public DaoConfiguration( Throwable cause ) {
        super( cause );
    }

}
