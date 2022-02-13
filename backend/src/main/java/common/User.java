package common;

public abstract class User {
    protected String username;
    protected String password;

    /**
     * Getter for username of User
     * @return username
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Getter for password of User
     * @return password
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Setter for username of User
     * @param username Username of User
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Setter for password of User
     * @param password Password of User
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
