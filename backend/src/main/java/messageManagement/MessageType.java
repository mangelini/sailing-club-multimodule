package messageManagement;

import messageManagement.employee.*;
import messageManagement.member.*;

public enum MessageType {
  LOGIN_EMPLOYEE(new LoginEmployeeCommand()), ADD_EMPLOYEE(new AddEmployeeCommand()),
  GET_EMPLOYEE(new GetEmployeeCommand()), LOGIN_MEMBER(new LoginMemberCommand()),
  ADD_MEMBER(new AddMemberCommand()), CONNECTION_ESTABLISHED(new ConnectionEstablishedCommand()),
  ADD_BOAT(new AddBoatCommand()), REMOVE_BOAT(new RemoveBoatCommand()), GET_ALL_BOATS(new GetAllBoatsCommand()),
  PAY_STORAGE_FEE(new PayStorageFeesCommand()), GET_EMPLOYEE_STORAGE_FEES_TO_PAY(new GetEmployeeStorageFeesToPay()),
  NOTIFY_MEMBER_STORAGE_FEES(new NotifyMemberStorageFees()), GET_MEMBER_STORAGE_FEES_TO_PAY(new GetMemberStorageFeesToPay()),
  GET_EMPLOYEE_MEM_FEES_TO_PAY(new GetEmpMembFeesToPay()), NOTIFY_MEMBER_MEM_FEES(new NotifyMemberMembershipFees()),
  GET_MEMBER_MEM_FEES_TO_PAY(new GetMemberMembFeesToPay()), PAY_MEMBERSHIP_FEE(new PayMembershipFeeCommand()),
  ADD_RACE(new AddRaceCommand()), GET_ALL_RACES(new GetAllRacesCommand()), GET_AVAILABLE_RACES(new GetAvailableRacesCommand()),
  REGISTER_TO_RACE(new RegisterToRaceCommand()), GET_AVAILABLE_BOATS(new GetAvailableBoatsCommand()),
  VIEW_PARTICIPANTS(new ViewParticipantsCommand()), GET_ALL_MEMBERSHIP_FEES(new GetAllMembershipFeesCommand());

  private Command command;

  MessageType(Command command) {
    this.command = command;
  }

  public Reply execute(Message message) {
    return command.execute(message);
  }
}
