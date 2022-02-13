package messageManagement;

import messageManagement.employee.AddEmployeeCommand;
import messageManagement.employee.GetEmployeeCommand;
import messageManagement.employee.LoginEmployeeCommand;
import messageManagement.member.AddMemberCommand;
import messageManagement.member.LoginMemberCommand;

public enum MessageType {
  LOGIN_EMPLOYEE(new LoginEmployeeCommand()), ADD_EMPLOYEE(new AddEmployeeCommand()),
  GET_EMPLOYEE(new GetEmployeeCommand()), LOGIN_MEMBER(new LoginMemberCommand()),
  ADD_MEMBER(new AddMemberCommand());

  // GET_MEMBER

  private Command command;

  MessageType(Command command) {
    this.command = command;
  }

  public Reply execute(Message message) {
    return command.execute(message);
  }
}
