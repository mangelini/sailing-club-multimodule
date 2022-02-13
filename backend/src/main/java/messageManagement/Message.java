package messageManagement;

import java.io.Serializable;

// TODO Better genrics by restricting T only to Employee and Member

public class Message<T> implements Serializable {
  private static final long serialVersionUID = 1L;
  private T user;
  private MessageType requestType;
  private String query;
  private Serializable newObject;

  /**
   * Empty constructor
   */
  public Message() {
  }

  public Message(T user, MessageType requestType, String query, Serializable newObject) {
    this.user = user;
    this.requestType = requestType;
    this.query = query;
    this.newObject = newObject;
  }

  public Message(T user, MessageType requestType, String query) {
    this.user = user;
    this.requestType = requestType;
    this.query = query;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public T getUser() {
    return user;
  }

  public void setUser(T user) {
    this.user = user;
  }

  public MessageType getRequestType() {
    return requestType;
  }

  public void setRequestType(MessageType requestType) {
    this.requestType = requestType;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public Serializable getNewObject() {
    return newObject;
  }

  public void setNewObject(Serializable newObject) {
    this.newObject = newObject;
  }

}
