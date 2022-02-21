package messageManagement;

import messageManagement.employee.AddEmployeeCommand;
import messageManagement.employee.GetEmployeeCommand;
import messageManagement.employee.GetStorageFeesToPay;
import messageManagement.employee.LoginEmployeeCommand;
import messageManagement.member.*;

public enum MessageType {
  LOGIN_EMPLOYEE(new LoginEmployeeCommand()), ADD_EMPLOYEE(new AddEmployeeCommand()),
  GET_EMPLOYEE(new GetEmployeeCommand()), LOGIN_MEMBER(new LoginMemberCommand()),
  ADD_MEMBER(new AddMemberCommand()), CONNECTION_ESTABLISHED(new ConnectionEstablishedCommand()),
  ADD_BOAT(new AddBoatCommand()), REMOVE_BOAT(new RemoveBoatCommand()), GET_ALL_BOATS(new GetAllBoatsCommand()),
  PAY_STORAGE_FEE(new PayStorageFeesCommand()), GET_STORAGE_FEES_TO_PAY(new GetStorageFeesToPay());

  private Command command;

  MessageType(Command command) {
    this.command = command;
  }

  public Reply execute(Message message) {
    return command.execute(message);
  }
}
