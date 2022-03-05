package messageManagement;

import entities.Employee;
import entities.Member;

import java.io.Serial;
import java.io.Serializable;

public class Message<T> implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;
  private T user;
  private MessageType requestType;
  private Serializable newObject;

  private Message(T user, MessageType requestType, Serializable newObject) {
    this.user = user;
    this.requestType = requestType;
    this.newObject = newObject;
  }

  private Message(T user, MessageType requestType) {
    this.user = user;
    this.requestType = requestType;
  }

  /**
   * Public Constructor for creating a new Message from Member client
   * @param member Member creating the message
   * @param requestType Type of request
   * @param newObject Data passed to the backend from client
   * @return Returns actual object
   */
  public static Message<Member> newInstance(Member member, MessageType requestType, Serializable newObject) {
    return new Message<Member>(member, requestType, newObject);
  }

  /**
   * Public Constructor for creating a new Message from Member client
   * @param member Member creating the message
   * @param requestType Type of request
   * @return Returns actual object
   */
  public static Message<Member> newInstance(Member member, MessageType requestType) {
    return new Message<Member>(member, requestType);
  }

  /**
   * Public Constructor for creating a new Employee from Employee client
   * @param employee Employee creating the message
   * @param requestType Type of request
   * @param newObject Data passed to the backend from client
   * @return Returns actual object
   */
  public static Message<Employee> newInstance(Employee employee, MessageType requestType, Serializable newObject) {
    return new Message<Employee>(employee, requestType, newObject);
  }

  /**
   * Public Constructor for creating a new Employee from Employee client
   * @param employee Employee creating the message
   * @param requestType Type of request
   * @return Returns actual object
   */
  public static Message<Employee> newInstance(Employee employee, MessageType requestType) {
    return new Message<Employee>(employee, requestType);
  }

  public Message(MessageType requestType){
    this.requestType = requestType;
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

  public Serializable getNewObject() {
    return newObject;
  }

  public void setNewObject(Serializable newObject) {
    this.newObject = newObject;
  }

}
